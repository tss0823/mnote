package com.loong.mnote.common.enums;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel("用户状态")
public enum UserStatusEnum {
    INIT(0, "待审核"),

    PASS(1, "审核通过"),

    NOT_PASS(2, "审核不通过"),;

    private int code;
    private String description;

    UserStatusEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static UserStatusEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (UserStatusEnum s : UserStatusEnum.values()) {
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
