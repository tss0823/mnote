package com.loong.mnote.dal.param;

import com.loong.mnote.common.domain.BaseDomain;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel
public class AppDeviceParam extends BaseDomain {



    @NotBlank(message = "设备号不能为空")
    @ApiModelProperty(notes = "设备ID",required = true)
    private String deviceId;

    @NotBlank(message = "平台不能为空")
    @ApiModelProperty(notes = "平台（IOS,Android)",required = true,example = "IOS")
    private String platform;

    @NotBlank(message = "应用名称不能为空")
    @ApiModelProperty(notes = "应用名称",required = true,example = "pay")
    private String appName;

    @NotBlank(message = "设备号不能为空")
    @ApiModelProperty(notes = "应用版本号",required = true,example = "1.0.0")
    private String appVersion;

    @NotBlank(message = "设备号不能为空")
    @ApiModelProperty(notes = "系统名称",required = true,example = "iphone")
    private String systemName;

    @NotBlank(message = "设备号不能为空")
    @ApiModelProperty(notes = "系统版本号",required = true,example = "8.0")
    private String systemVersion;

    @NotNull(message = "设备号不能为空")
    @ApiModelProperty(notes = "设备屏幕宽度",required = true,example = "1334")
    private Integer deviceWidth;


    @NotNull(message = "设备号不能为空")
    @ApiModelProperty(notes = "设备屏幕高度",required = true,example = "760")
    private Integer deviceHeight;

    @NotBlank(message = "设备号不能为空")
    @ApiModelProperty(notes = "设备版本号",example = "HUAWEI CLT-AL01",name="设备版本号")
    private String deviceVersion;


    @NotBlank(message = "设备号不能为空")
    @ApiModelProperty(notes = "区域简",required = true,example = "zh")
    private String local;



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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
