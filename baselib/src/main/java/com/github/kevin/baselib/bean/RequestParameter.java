package com.github.kevin.baselib.bean;

public class RequestParameter {
    private String parameterClassName;
    private String getParameterValue;

    public RequestParameter(){

    }

    public RequestParameter(String parameterClassName, String getParameterValue) {
        this.parameterClassName = parameterClassName;
        this.getParameterValue = getParameterValue;
    }

    public String getParameterClassName() {
        return parameterClassName;
    }

    public void setParameterClassName(String parameterClassName) {
        this.parameterClassName = parameterClassName;
    }

    public String getGetParameterValue() {
        return getParameterValue;
    }

    public void setGetParameterValue(String getParameterValue) {
        this.getParameterValue = getParameterValue;
    }
}
