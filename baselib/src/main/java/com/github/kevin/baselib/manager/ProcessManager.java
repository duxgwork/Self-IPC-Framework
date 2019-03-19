package com.github.kevin.baselib.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import com.github.kevin.baselib.utils.CacheCenter;
import com.github.kevin.baselib.ProcessInterface;
import com.github.kevin.baselib.service.ProcessService;
import com.github.kevin.baselib.annotion.ClassId;
import com.github.kevin.baselib.bean.RequestBean;
import com.github.kevin.baselib.bean.RequestParameter;
import com.github.kevin.baselib.utils.ProcessInvocationHandler;
import com.google.gson.Gson;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProcessManager {
    //------主进程---1 单例 ------
    Gson gson = new Gson();
    private CacheCenter mCacheCenter = CacheCenter.getInstance();

    private ProcessInterface mProcessInterface;

    public ProcessManager() {

    }

    private static final ProcessManager instance = new ProcessManager();

    public static ProcessManager getInstance() {
        return instance;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void register(Class<?> clazz) {
        mCacheCenter.register(clazz);
    }

    //------另外一个对象---1 单例 ------
    //----- 其他进程发送过来的
    public <T> T getInstance(Class<?> clazz, Object... paramaters) {
        //发请求 获取单例
        sendRequest(ProcessService.GET_INSTANCE, clazz, null, paramaters);
        T proxy = (T)Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new ProcessInvocationHandler(clazz));
        return proxy;
    }


    public String sendRequest(int type, Class<?> clazz, Method method, Object[] paramaters) {
        RequestParameter[] requestParameters = null;
        if (paramaters != null && paramaters.length > 0) {
            requestParameters = new RequestParameter[paramaters.length];
            for (int i = 0; i < paramaters.length; i++) {
                Object paramater = paramaters[i];
                String paramaterClasssName = paramaters.getClass().getName();
                String paramaterValue = gson.toJson(paramater);
                RequestParameter requestParameter = new RequestParameter(paramaterClasssName, paramaterValue);
                requestParameters[i] = requestParameter;
            }
        }
        String clssName = clazz.getAnnotation(ClassId.class).value();
        String menthodName = method == null ? "" : method.getName();
        RequestBean requestBean = new RequestBean(type, clssName, menthodName, requestParameters);
        String request = gson.toJson(requestBean);
        try {
            return mProcessInterface.send(request);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void connect(Context context) {
        bind(context, null, ProcessService.class);
    }

    public void connect(Context context, String packageName) {
        bind(context, packageName, ProcessService.class);
    }

    private void bind(Context context, String packageName, Class<? extends ProcessService> service) {
        Intent intent;
        if (TextUtils.isEmpty(packageName)) {
            //单App通信
            intent = new Intent(context, service);
        } else {
            //多App通信
            intent = new Intent();
            intent.setPackage(packageName);
            intent.setAction(service.getName());
        }
        context.bindService(intent, new ProcessConnection(), Context.BIND_AUTO_CREATE);
    }

    class ProcessConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mProcessInterface = ProcessInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }


}
