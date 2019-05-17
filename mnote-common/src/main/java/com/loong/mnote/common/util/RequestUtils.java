package com.loong.mnote.common.util;

import com.loong.mnote.common.constants.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by shengshan.tang on 2015/12/16 at 17:35
 */
public class RequestUtils {


    public static HttpServletRequest getRequest() {
        // 获取HttpServletRequest对象
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if(requestAttributes == null){
            return null;
        }
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        return request;
    }

    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if(StringUtils.indexOf(ip,",") != -1){
            ip = ip.split(",")[0].trim();
        }
        return ip;
    }

//    public static String getHeaderValue(HttpServletRequest request,String headerName){
//
//    }

    public static String getLocal(HttpServletRequest request){
        String local = request.getHeader(SystemConstant.LOCAL);
        if (StringUtils.isEmpty(local)) {
            local = request.getParameter(SystemConstant.LOCAL);
        }
        if (StringUtils.isEmpty(local)) {
            local = request.getParameter(SystemConstant.LOCAL_H5);
        }
        if (StringUtils.isEmpty(local)) {
            local = SystemConstant.DEFAULT_LOCAL_VALUE;
        }
        return local;

    }
}
