package com.loong.mnote.common.enums;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.StringUtils;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@ApiModel("应用类型")
public enum EnvTypeEnum {

    DEV("dev", "开发"),

    TEST("test", "测试"),

    PROD("prod", "生产"),



    ;


    private String code;
    private String description;

    EnvTypeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static EnvTypeEnum getByCode(String code) {
        if (code == null) {
            return null;
        }
        for (EnvTypeEnum s : EnvTypeEnum.values()) {
            if (StringUtils.equals(s.getCode(),code)) {
                return s;
            }
        }
        return null;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
