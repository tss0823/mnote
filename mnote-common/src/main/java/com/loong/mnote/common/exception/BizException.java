package com.loong.mnote.common.exception;

import com.loong.mnote.common.constants.SystemConstant;
import org.springframework.http.HttpStatus;

public class BizException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private Object data;  //扩展数据
    private String code = SystemConstant.ResponseCode.NORMAL;   //编号

    private Integer httpStatus = HttpStatus.OK.value();

    public BizException(String message, Exception ex) {
        super(message, ex);
    }

    public BizException(String message) {
        super(message);
    }
    public BizException(String message,Integer httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }


    public BizException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(String code, String message, String data) {
        super(message);
        this.code = code;
        this.data = data;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }
}