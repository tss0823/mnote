package com.loong.mnote.service.component.redis;

import java.io.Serializable;

/**
 * @author: sam
 * @date: 2019-02-01 17:25
 */
public class SubPubMessage implements Serializable {

    private String appName;

    private String key;

    private Object data;

    public SubPubMessage() {
    }

    public SubPubMessage(String appName, String key, Object data) {
        this.appName = appName;
        this.key = key;
        this.data = data;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
