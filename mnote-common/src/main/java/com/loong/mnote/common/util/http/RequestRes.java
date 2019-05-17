package com.loong.mnote.common.util.http;

import java.util.List;
import java.util.Map;

/**
 * Created by shan on 2016/7/22.
 */
public class RequestRes {

    private String method;

    private String url;

    private Map<String, String> headers;

    private Map<String, ? extends Object> params;

    private List<HttpParam> paramList;

    private String paramText;
    private byte [] paramByte;

    private boolean proxy;
    private String proxyHost;

    private int proxyPort;

    private Integer connectTimeout;

    private Integer socketTimeout;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, ? extends Object> getParams() {
        return params;
    }

    public void setParams(Map<String, ? extends Object> params) {
        this.params = params;
    }

    public List<HttpParam> getParamList() {
        return paramList;
    }

    public void setParamList(List<HttpParam> paramList) {
        this.paramList = paramList;
    }

    public String getParamText() {
        return paramText;
    }

    public void setParamText(String paramText) {
        this.paramText = paramText;
    }

    public byte[] getParamByte() {
        return paramByte;
    }

    public void setParamByte(byte[] paramByte) {
        this.paramByte = paramByte;
    }

    public boolean isProxy() {
        return proxy;
    }

    public void setProxy(boolean proxy) {
        this.proxy = proxy;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setConnectTimeout(Integer connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public void setSocketTimeout(Integer socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public Integer getConnectTimeout() {
        return connectTimeout;
    }

    public Integer getSocketTimeout() {
        return socketTimeout;
    }
}
