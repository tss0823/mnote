package com.loong.mnote.common.util;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtils {


    private static ThreadLocal<HttpServletResponse> responseThreadLocal = new ThreadLocal<>();

    public static HttpServletResponse getResponse() {
        return responseThreadLocal.get();
    }

    public static void setResponse(HttpServletResponse response) {
        responseThreadLocal.set(response);
    }

    public static void clean(){
        responseThreadLocal.remove();
    }
}
