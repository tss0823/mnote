package com.loong.mnote.common.enums;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel("国际化信息类型")
public enum LocalInfoTypeEnum {

    TIPS(0, "提示"),

    LABEL(1, "标签"),

    OTHER(2, "其他"),



    ;


    private int code;
    private String description;

    LocalInfoTypeEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }



    public static LocalInfoTypeEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (LocalInfoTypeEnum s : LocalInfoTypeEnum.values()) {
            if (code == s.getCode()) {
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
