package com.github.kevin.baselib.utils;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;

import java.lang.reflect.Method;
import java.util.HashMap;

public class CacheCenter {
    private HashMap<String, Class> mClassses = new HashMap<>();
    private HashMap<Class<?>, HashMap<String, Method>> mMethods = new HashMap<>();
    private HashMap<String, Object> mObjects = new HashMap<>();

    private CacheCenter(){

    }

    private static CacheCenter instance = new CacheCenter();

    public static CacheCenter getInstance(){
        return instance;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void register(Class<?> clazz){
        mClassses.put(clazz.getName(),clazz);
        registerMethod(clazz);
    }

    public Object getObject(String name){
        return mObjects.get(name);
    }

    public void putObject(String name, Object object){
        mObjects.put(name, object);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void registerMethod(Class<?> clazz) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods){
            mMethods.putIfAbsent(clazz, new HashMap<String, Method>());
            //map都不会为空
            HashMap<String,Method> map = mMethods.get(clazz);
            map.put(method.getName(), method);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public Method getMethod(String clssName, String name) {
        Class clazz = getClassType(clssName);
        if (name != null){
            mMethods.putIfAbsent(clazz, new HashMap<String, Method>());
            HashMap<String,Method> methods = mMethods.get(clazz);
            Method method = methods.get(name);
            if (method != null){
                return method;
            }
        }
        return null;
    }

    public Class getClassType(String name) {
        if (TextUtils.isEmpty(name)){
            return null;
        }
        Class<?> clazz = mClassses.get(name);
        if (clazz == null){
            try {
                clazz = Class.forName(name);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return clazz;


    }

}
