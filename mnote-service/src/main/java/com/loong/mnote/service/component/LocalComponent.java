package com.loong.mnote.service.component;

import com.loong.mnote.common.enums.AppTypeEnum;
import com.loong.mnote.common.enums.LocalInfoTypeEnum;
import com.loong.mnote.service.AbstractService;
import com.loong.mnote.service.LocalInfoDetailService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContext;

import javax.servlet.http.HttpServletRequest;

/**
 * 本地化工具支撑
 *
 * @author: sam
 * @date: 2018-12-20 14:04
 */
@Component
public class LocalComponent extends AbstractService {


    @Autowired
    private LocalInfoDetailService localInfoDetailService;

    @Autowired
    private SystemConfigComponent systemConfigComponent;

    public String getLanguageText(HttpServletRequest request, String key) {
        RequestContext requestContext = new RequestContext(request);
        String language = requestContext.getLocale().getLanguage();

        //先加载db
        String appName = systemConfigComponent.getAppName();
        AppTypeEnum appTypeEnum = AppTypeEnum.getByCode(appName);
        String dbValue = localInfoDetailService.findByKey(appTypeEnum.ordinal(), LocalInfoTypeEnum.LABEL.getCode(), language, key);
        if (StringUtils.isNotEmpty(dbValue)) {
            return dbValue;
        }

        //然后加载本地message property 配置
        try {
            String value = requestContext.getMessage(key);
            return value;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return key;
        }
    }
}
