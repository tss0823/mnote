package com.loong.mnote.web.controller;

import com.loong.mnote.common.domain.WebResult;
import com.loong.mnote.common.web.BaseController;
import com.loong.mnote.common.web.WebResultBuilder;
import com.loong.mnote.service.InitDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: sam
 * @date: 2019-01-10 15:28
 */
@RestController
@RequestMapping(path = "/tools/")
@Api( description="手工操作管理 (author:Sam)")
@Validated
public class ToolsController extends BaseController {

    @Autowired
    private InitDataService initDataService;

    @ApiOperation(value = "初始化数据", nickname = "sam@mnote.com", response = WebResult.class)
    @PostMapping(path = "initData", produces = "application/json")
    public Object initData(HttpServletRequest request) {
        initDataService.initAuthData();
        return WebResultBuilder.successResult();
    }
}
