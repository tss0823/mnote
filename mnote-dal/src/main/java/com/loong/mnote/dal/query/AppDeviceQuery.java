package com.loong.mnote.dal.query;

import com.loong.mnote.common.domain.BaseQuery;

import javax.persistence.Column;

public class AppDeviceQuery extends BaseQuery {

    @Column(length = 50,nullable = false,columnDefinition = "设备ID")
    private String deviceId;

    @Column(length = 20,nullable = false,columnDefinition = "平台（IOS,Android）")
    private String platform;

    @Column(length = 50,nullable = false,columnDefinition = "应用名称")
    private String appName;

    @Column(length = 50,nullable = false,columnDefinition = "应用版本号")
    private String appVersion;


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
