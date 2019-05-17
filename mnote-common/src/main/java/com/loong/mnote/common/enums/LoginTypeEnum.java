package com.loong.mnote.common.enums;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel("登录类型")
public enum LoginTypeEnum {

    PWD(0, "密码"),

    CHECK_CODE(1, "验证码"),

    WECHAT(2, "微信"),

    FACEBOOK(3, "脸书"),



    ;


    private int code;
    private String description;

    LoginTypeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static LoginTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (LoginTypeEnum s : LoginTypeEnum.values()) {
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

}
