package com.loong.mnote.common.enums;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel("用户类型")
public enum UserTypeEnum {


    MEMBER(0, "会员"),

    BOSS(1, "后台"),

    OPEN(2, "开放平台"),



    ;


    private int code;
    private String description;

    UserTypeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static UserTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (UserTypeEnum s : UserTypeEnum.values()) {
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
