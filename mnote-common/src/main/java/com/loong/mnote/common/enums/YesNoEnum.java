package com.loong.mnote.common.enums;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel("是否")
public enum YesNoEnum {


    YES(1, true,"是"),

    NO(0,false, "否"),



    ;


    private int code;
    private boolean boole;
    private String description;

    YesNoEnum(int code,boolean boole, String description) {
        this.code = code;
        this.description = description;
    }

    public static YesNoEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (YesNoEnum s : YesNoEnum.values()) {
            if (s.getCode() == code) {
                return s;
            }
        }
        return null;
    }

    public static YesNoEnum getByBoole(Boolean boole) {
        if (boole == null) {
            return null;
        }
        for (YesNoEnum s : YesNoEnum.values()) {
            if (s.isBoole() == boole) {
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

    public boolean isBoole() {
        return boole;
    }

    public void setBoole(boolean boole) {
        this.boole = boole;
    }

    public String getDescription() {
        //国际化，重写desc TODO

        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
