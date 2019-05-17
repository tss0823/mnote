package com.loong.mnote.common.web;

import com.loong.mnote.common.constants.SystemConstant;
import com.loong.mnote.common.domain.WebResult;

/**
 * @author: sam
 * @date: 2018-12-19 14:53
 */
public class WebResultBuilder {

    public static WebResult successResult() {
        WebResult objectWebResult = new WebResult();
        return objectWebResult;
    }

//    public static WebResult successResult(String message) {
//        WebResult objectWebResult = new WebResult();
//        objectWebResult.setMessage(message);
//        return objectWebResult;
//    }
    public static WebResult successResult(Object data) {
        WebResult objectWebResult = new WebResult();
        objectWebResult.setData(data);
        return objectWebResult;
    }

    public static WebResult successResult(String message, Object data) {
        WebResult objectWebResult = new WebResult();
        objectWebResult.setMessage(message);
        objectWebResult.setData(data);
        return objectWebResult;
    }

    public static WebResult warnResult(String message) {
        WebResult objectWebResult = new WebResult(false, SystemConstant.ResponseCode.SYSTEM_WARN);
        objectWebResult.setMessage(message);
        return objectWebResult;
    }

    public static WebResult warnResult(String message, Object data) {
        WebResult objectWebResult = new WebResult(false, SystemConstant.ResponseCode.SYSTEM_WARN);
        objectWebResult.setMessage(message);
        objectWebResult.setData(data);
        return objectWebResult;
    }

    public static WebResult failedResult(String message) {
        WebResult objectWebResult = new WebResult(false, SystemConstant.ResponseCode.SYSTEM_ERROR);
        objectWebResult.setMessage(message);
        return objectWebResult;
    }

    public static WebResult failedResult(String message, Object data) {
        WebResult objectWebResult = new WebResult(false, SystemConstant.ResponseCode.SYSTEM_ERROR);
        objectWebResult.setMessage(message);
        objectWebResult.setData(data);
        return objectWebResult;
    }


}
