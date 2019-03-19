package com.github.kevin.baselib.service;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import com.github.kevin.baselib.ProcessInterface;
import com.github.kevin.baselib.bean.RequestBean;
import com.github.kevin.baselib.bean.RequestParameter;
import com.github.kevin.baselib.utils.CacheCenter;
import com.google.gson.Gson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * 处理中心
 */
public class ProcessService extends Service {
    //获取对象
    public static final int GET_INSTANCE = 1;
    //执行方法
    public static final int GET_METHOD = 2;
    Gson gson = new Gson();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new ProcessInterface.Stub() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public String send(String request) throws RemoteException {
                //主进程：1    其他进程：2   其他进程===>主进程
                //运行在主进程，因为ProcessService注册在主进程的
                RequestBean requestBean = gson.fromJson(request, RequestBean.class);
                switch (requestBean.getType()) {
                    case GET_INSTANCE:
                        Method method = CacheCenter.getInstance().getMethod(requestBean.getClssName(), "getInstance");
                        //反射的前提
                        Object[] object = makeparamaterObjects(requestBean);
                        try {
                            Object userManager = method.invoke(null, object);
                            CacheCenter.getInstance().putObject(requestBean.getClssName(), userManager);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        break;
                    case GET_METHOD:
                        Object userManager = CacheCenter.getInstance().getObject(requestBean.getClssName());
                        Method getPerson = CacheCenter.getInstance().getMethod(requestBean.getClssName(), requestBean.getMenthodName());
                        Object[] mParamaters1 = makeparamaterObjects(requestBean);
                        try {
                            Object person = getPerson.invoke(userManager, mParamaters1);
                            String data = gson.toJson(person);
                            return data;

                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                        break;
                }


                return null;
            }
        };
    }

    private Object[] makeparamaterObjects(RequestBean requestBean) {
        //参数还原
        Object[] mParapaters = null;
        RequestParameter[] requestParameters = requestBean.getRequestParameters();
        if (requestParameters != null && requestParameters.length > 0) {
            mParapaters = new Object[requestParameters.length];
            for (int i = 0; i < requestParameters.length; i++) {
                RequestParameter requestParameter = requestParameters[i];
                Class<?> clazz = CacheCenter.getInstance().getClassType(requestParameter.getParameterClassName());
                mParapaters[i] = gson.fromJson(requestParameter.getGetParameterValue(), clazz);

            }
        } else {
            mParapaters = new Object[0];
        }
        return mParapaters;

    }

}
