package com.loong.mnote.service.interceptor;

import com.loong.mnote.common.constants.SystemConstant;
import com.loong.mnote.common.domain.WebResult;
import com.loong.mnote.common.exception.BizException;
import com.loong.mnote.common.util.RequestUtils;
import com.loong.mnote.common.util.ResponseUtils;
import com.loong.mnote.service.UserService;
import com.loong.mnote.service.component.AuthComponent;
import com.loong.mnote.service.component.LocalComponent;
import com.loong.mnote.service.component.SystemConfigComponent;
import com.loong.mnote.service.component.auth.UserManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: sam
 * @date: 2018-12-20 14:56
 */
@Aspect
@Component
public class SystemAspect {

    Logger logger = LoggerFactory.getLogger(SystemAspect.class);

    @Autowired
    private LocalComponent localComponent;

    @Autowired
    private SystemConfigComponent systemConfigComponent;

    @Autowired
    private AuthComponent authComponent;

    @Autowired
    private UserService userService;

    @Value("${api.host}")
    private String currentHttpUrl;

    @Around("@annotation(org.springframework.web.bind.annotation.GetMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.ExceptionHandler)")
    public Object controllerMethodCall(ProceedingJoinPoint joinPoint) {
        Object proceed;
        try {
            //鉴权入口
            HttpServletRequest request = RequestUtils.getRequest();
            boolean checkAuthState = authComponent.checkAuth(request);
            if (!checkAuthState) {
                throw new BizException("没有权限");
            }
            //end


            proceed = joinPoint.proceed();
            if (proceed instanceof WebResult) {
                WebResult webResult = (WebResult) proceed;
                // prod print
                if (systemConfigComponent.isProd()) {
                    webResult.setData("");
                }
                localChanged(webResult);
            }
        } catch (Throwable t) {
            //遇到异常
            WebResult webResult = new WebResult(false, SystemConstant.ResponseCode.SYSTEM_ERROR);
            //业务中异常
            if (t instanceof BizException) {
                BizException bizException = (BizException) t;
                webResult.setMessage(bizException.getMessage());
                webResult.setCode(bizException.getCode());
                //not prod print
                if (!systemConfigComponent.isProd()) {
                    webResult.setData(bizException.getData());
                }
                //返回http status 的场景
                if(bizException.getHttpStatus() != HttpStatus.OK.value()){
                    String redirectUrl =  "redirectHttpStatus?httpStatus="+bizException.getHttpStatus();
                    HttpServletResponse response = ResponseUtils.getResponse();
                    response.setHeader("Location",currentHttpUrl+"/"+redirectUrl);
                }
                //未预知异常
            } else {
                //index 唯一索引消息处理
                if (t.getCause() instanceof ConstraintViolationException) {
                    String constraintName = ((ConstraintViolationException) t.getCause()).getConstraintName();
                    webResult.setMessage("${" + constraintName + "}");
                } else {
                    webResult.setMessage(t.getMessage());
                }
                //not prod print
                if (!systemConfigComponent.isProd()) {
                    webResult.setData(ExceptionUtils.getStackTrace(t));
                }
            }
            localChanged(webResult);
            logger.error(ExceptionUtils.getStackTrace(t));
            return webResult;
        } finally {
            UserManager.clean();
        }
        return proceed;
    }

    private void localChanged(WebResult webResult) {
        String message = webResult.getMessage();
        if (StringUtils.isNotEmpty(message)) {
            if (message.trim().startsWith("${") && message.endsWith("}")) {
                String key = message.substring(2, message.length() - 1);
                String languageText = localComponent.getLanguageText(RequestUtils.getRequest(), key);
                webResult.setMessage(languageText);
            }
        }
    }


}
