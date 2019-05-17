package com.loong.mnote.web.controller;

import com.loong.mnote.common.domain.WebResult;
import com.loong.mnote.common.enums.LoginTypeEnum;
import com.loong.mnote.common.enums.SmsCodeTypeEnum;
import com.loong.mnote.common.enums.UserTypeEnum;
import com.loong.mnote.common.enums.YesNoEnum;
import com.loong.mnote.common.web.WebResultBuilder;
import com.loong.mnote.dal.domain.DataConfig;
import com.loong.mnote.dal.query.DataConfigQuery;
import com.loong.mnote.service.DataConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: sam
 * @date: 2019-01-09 09:31
 */
@RestController
@RequestMapping(path = "/common/")
@Api(description="通用API")
public class CommonController {

    @Autowired
    private DataConfigService dataConfigService;

    @ApiOperation(value = "获取系统所有枚举列表",nickname = "sam@mnote.com",response = WebResult.class)
    @GetMapping(path = "enumList",produces = "application/json")
    public Object enumList() {
        WebResult webResult = WebResultBuilder.successResult();
        webResult.put(getEnumKey(SmsCodeTypeEnum.class), SmsCodeTypeEnum.values());
        webResult.put(getEnumKey(UserTypeEnum.class), UserTypeEnum.values());
        webResult.put(getEnumKey(LoginTypeEnum.class), LoginTypeEnum.values());

        return webResult;
    }



    @ApiOperation(value = "获取系统数据配置列表",nickname = "sam@mnote.com",response = WebResult.class)
    @GetMapping(path = "dataConfigList",produces = "application/json")
    public Object dataConfigList() {
        DataConfigQuery query = new DataConfigQuery();
        query.setEnable(YesNoEnum.YES.isBoole());
        List<DataConfig> dataList = dataConfigService.findAll(query);
        return WebResultBuilder.successResult(dataList);
    }


    private String getEnumKey(Class cls) {
        String code = cls.getSimpleName();
        return StringUtils.uncapitalize(code);
    }
}
