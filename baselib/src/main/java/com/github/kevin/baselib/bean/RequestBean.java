package com.github.kevin.baselib.bean;

public class RequestBean {
    private int type;
    private String clssName;
    private String menthodName;
    private RequestParameter[] requestParameters;

    public RequestBean(int type, String clssName, String menthodName, RequestParameter[] requestParameters) {
        this.type = type;
        this.clssName = clssName;
        this.menthodName = menthodName;
        this.requestParameters = requestParameters;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getClssName() {
        return clssName;
    }

    public void setClssName(String clssName) {
        this.clssName = clssName;
    }

    public String getMenthodName() {
        return menthodName;
    }

    public void setMenthodName(String menthodName) {
        this.menthodName = menthodName;
    }

    public RequestParameter[] getRequestParameters() {
        return requestParameters;
    }

    public void setRequestParameters(RequestParameter[] requestParameters) {
        this.requestParameters = requestParameters;
    }
}
