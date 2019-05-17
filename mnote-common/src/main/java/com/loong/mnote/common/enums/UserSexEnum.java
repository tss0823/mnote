package com.loong.mnote.common.enums;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel("用户性别")
public enum UserSexEnum {

    FEMALE(0, "女"),

    MALE(1, "男"),;

    private int code;
    private String description;

    UserSexEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static UserSexEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (UserSexEnum s : UserSexEnum.values()) {
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
