package com.loong.mnote.common.web;

import com.loong.mnote.common.constants.SystemConstant;
import com.loong.mnote.common.domain.WebResult;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 专门处理参数问题的异常
 */
@RestControllerAdvice
public class ResponseExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ResponseExceptionHandler.class);

    @ExceptionHandler
    public WebResult exceptionHandler(MethodArgumentNotValidException e) {
        WebResult webResult = new WebResult(false, SystemConstant.ResponseCode.SYSTEM_ERROR);
        //只处理第一个消息
        FieldError fieldError1 = e.getBindingResult().getFieldError();
        if(fieldError1 != null){
            webResult.setMessage(fieldError1.getDefaultMessage());
        }
        webResult.setData(ExceptionUtils.getStackTrace(e));
//        logger.error(ExceptionUtils.getStackTrace(e));
        return webResult;
    }
}