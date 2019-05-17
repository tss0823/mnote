package com.loong.mnote.common.enums;

/**
 * @author: lhd
 * @date: 2019/1/2 11:45
 */
public enum ErrorCodeEnum {

    SYSTEM_ERROR("SYSTEM_ERROR", "内部服务异常"),
    REQUEST_ERROR("REQUEST_ERROR", "请求无效"),
    DATA_ERROR("DATA_ERROR", "数据异常"),
    DATA_LIMIT("DATA_LIMIT", "数据限制"),
    ;

    private String code;

    private String msg;

    ErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
