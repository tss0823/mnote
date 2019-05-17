package com.loong.mnote.common.enums;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.loong.mnote.common.constants.CacheConstants;
import io.swagger.annotations.ApiModel;

import java.util.concurrent.TimeUnit;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel("验证码类型")
public enum SmsCodeTypeEnum {

    REGISTER(0, "注册", CacheConstants.USER_REGISTER_SMS, 1,TimeUnit.MINUTES),

    LOGIN(1, "登录", CacheConstants.USER_LOGIN_SMS, 1,TimeUnit.MINUTES),

    FORGET_PASSWORD(2, "忘记密码", CacheConstants.USER_FORGET_PWD_SMS, 1,TimeUnit.MINUTES),

    CHANGE_MOBILE(3, "修改手机号码", CacheConstants.USER_CHANGE_MOBILE_SMS, 1,TimeUnit.MINUTES),



    ;


    private int code;
    private String cacheKey;
    private int expireTime;
    private TimeUnit timeUnit;
    private String description;

    SmsCodeTypeEnum(int code, String description, String cacheKey, int expireTime, TimeUnit timeUnit) {
        this.code = code;
        this.description = description;
        this.cacheKey = cacheKey;
        this.expireTime = expireTime;
        this.timeUnit = timeUnit;
    }

    public static SmsCodeTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (SmsCodeTypeEnum s : SmsCodeTypeEnum.values()) {
            if (s.getCode() == code) {
                return s;
            }
        }
        return null;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCacheKey() {
        return cacheKey;
    }

    public void setCacheKey(String cacheKey) {
        this.cacheKey = cacheKey;
    }

    public int getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(int expireTime) {
        this.expireTime = expireTime;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }
}
