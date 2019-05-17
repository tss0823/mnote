package com.loong.mnote.boss.controller;

import com.loong.mnote.common.domain.Pager;
import com.loong.mnote.common.domain.WebResult;
import com.loong.mnote.common.web.WebResultBuilder;
import com.loong.mnote.dal.domain.LocalInfo;
import com.loong.mnote.dal.param.LocalInfoParam;
import com.loong.mnote.dal.query.LocalInfoQuery;
import com.loong.mnote.service.LocalInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/localInfo/")
@Api( description="国际化信息")
public class LocalInfoController {

    @Autowired
    private LocalInfoService localInfoService;


    @ApiOperation(value = "保存",response = WebResult.class)
    @PostMapping(path = "save",produces = "application/json")
    public Object save(@RequestBody LocalInfoParam localInfoParam) {
        localInfoService.saveByParam(localInfoParam);
        return WebResultBuilder.successResult();
    }




    @ApiOperation(value = "分页查询国际化信息",response = WebResult.class)
    @GetMapping(path = "/findByPage",produces = "application/json")
    public Object findByPage(LocalInfoQuery localInfoQuery) {
        WebResult webResult = new WebResult();
        Pager<LocalInfo> pager = localInfoService.findByPage(localInfoQuery);
        return WebResultBuilder.successResult(pager);
    }

}
