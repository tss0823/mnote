package com.loong.mnote.service.interceptor;

import com.loong.mnote.common.util.RequestUtils;
import com.loong.mnote.common.util.ResponseUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Component
public class CustomInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(CustomInterceptor.class);

    //unimplemented methods comes here. Define the following method so that it     
    //will handle the request before it is passed to the controller.


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("preHandle");
        try{
            ResponseUtils.setResponse(response);
            //国际化
            String local = RequestUtils.getLocal(request);

            Locale locale = new Locale(local);
//        Locale newLocale = getLocale(request.getHeader("Accept-Language"));
            LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
            localeResolver.setLocale(request, response, locale);
            //end
        }catch (Exception e){
            logger.error("preHandle error",e);
        }finally {
        }

        //鉴权入口 TODO

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("afterCompletion");
        try{}catch (Exception e){
        }finally {
            ResponseUtils.clean();
        }
    }
}