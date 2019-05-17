package com.loong.mnote.common.enums;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel("注册类型")
public enum RegisterTypeEnum {


    MOBILE(0, "手机"),

    EMAIL(1, "邮箱"),




    ;


    private int code;
    private String description;

    RegisterTypeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static RegisterTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (RegisterTypeEnum s : RegisterTypeEnum.values()) {
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
        //国际化，重写desc TODO

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
