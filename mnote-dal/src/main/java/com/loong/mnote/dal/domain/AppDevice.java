package com.loong.mnote.dal.domain;

import com.loong.mnote.common.domain.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

@Entity(name="app_device")
@ApiModel
public class AppDevice extends BaseEntity {


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @ApiModelProperty(notes = "ID")
//    private Long id;

    @ApiModelProperty(notes = "设备ID")
    @Column(length = 50,nullable = false)
    private String deviceId;

    @ApiModelProperty(notes = "平台（IOS,Android）")
    @Column(length = 20,nullable = false)
    private String platform;

    @ApiModelProperty(notes = "应用名称")
    @Column(length = 50,nullable = false)
    private String appName;

    @ApiModelProperty(notes = "应用版本号")
    @Column(length = 50,nullable = false)
    private String appVersion;

    @ApiModelProperty(notes = "系统名称")
    @Column(length = 100,nullable = false)
    private String systemName;

    @ApiModelProperty(notes = "系统版本号")
    @Column(length = 100,nullable = false)
    private String systemVersion;

    @ApiModelProperty(notes = "设备屏幕宽度")
    @Column(length = 4,nullable = false)
    private Integer deviceWidth;

    @ApiModelProperty(notes = "设备屏幕高度")
    @Column(length = 4,nullable = false)
    private Integer deviceHeight;

    @ApiModelProperty(notes = "设备版本号")
    @Column(length = 50,nullable = false)
    private String deviceVersion;

    @ApiModelProperty(notes = "客户端IP")
    @Column(length = 50)
    private String clientIp;

    @ApiModelProperty(notes = "区域简称")
    @Column(name="[local]",length = 50,nullable = false)
    private String local;


//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceVersion() {
        return deviceVersion;
    }

    public void setDeviceVersion(String deviceVersion) {
        this.deviceVersion = deviceVersion;
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

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public Integer getDeviceWidth() {
        return deviceWidth;
    }

    public void setDeviceWidth(Integer deviceWidth) {
        this.deviceWidth = deviceWidth;
    }

    public Integer getDeviceHeight() {
        return deviceHeight;
    }

    public void setDeviceHeight(Integer deviceHeight) {
        this.deviceHeight = deviceHeight;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
