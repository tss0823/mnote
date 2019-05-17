package com.loong.mnote.common.constants;

/**
 * Created by shengshan.tang on 2015/11/24 at 16:45
 */
public interface SystemConstant {

    String LOCAL = "local";
    String LOCAL_H5 = "accept-language";
    String DEFAULT_LOCAL_VALUE = "zh";
    String TOKEN = "token";
    String RANDOM_SEED = "randomSeed";
    String DEVICE_ID = "deviceId";


    interface ResponseCode {
        String NORMAL = "00";
        String NOT_LOGIN = "01";
        String NOT_AUTHORITY = "02";
        String TIME_OUT = "03";
        String SYSTEM_WARN = "04";
        String SYSTEM_ERROR = "05";
        String NOT_BIND = "06";   //第三方登录的,手机账号未绑定
    }


    interface TuiSong {

        String dfGetuiTest = "df_getui_test";
        String dfGetuiProd = "df_getui_prod";

    }


}