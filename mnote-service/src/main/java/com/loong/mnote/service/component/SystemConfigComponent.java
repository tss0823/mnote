package com.loong.mnote.service.component;

import com.loong.mnote.common.enums.EnvTypeEnum;
import com.loong.mnote.service.AbstractService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: sam
 * @date: 2018-12-19 14:33
 */
@Component
public class SystemConfigComponent  extends AbstractService {

    @Value("${appEnv}")
    private String appEnv;

    @Value("${appName}")
    private String appName;


    public String getAppEnv() {
        return appEnv;
    }

    public boolean isProd(){
        return StringUtils.equals(appEnv, EnvTypeEnum.PROD.getCode());

    }

    public String getAppName() {
        return appName;
    }
}
