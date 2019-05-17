package com.loong.mnote.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;

/**
 * 新闻语言枚举
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel("本地化枚举")
public enum LocalEnum {

    CHINESE("zh", "中文"),
    ENGLISH("en", "英文"),
    ;
    private String code;
    private String value;

    LocalEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
