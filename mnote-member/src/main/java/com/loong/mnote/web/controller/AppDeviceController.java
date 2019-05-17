package com.loong.mnote.web.controller;

import com.loong.mnote.common.util.BeanUtils;
import com.loong.mnote.common.util.RequestUtils;
import com.loong.mnote.common.web.WebResultBuilder;
import com.loong.mnote.dal.domain.AppDevice;
import com.loong.mnote.dal.param.AppDeviceParam;
import com.loong.mnote.service.AppDeviceService;
import com.loong.mnote.common.domain.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/appDevice/")
@Api(description="App设备API")
public class AppDeviceController {

    @Autowired
    private AppDeviceService appDeviceService;

    @Value("${appEnv}")
    private String appEnv;


    @ApiOperation(value = "第一次加载数据上报",nickname = "sam@mnote.com",response = WebResult.class)
    @PostMapping(path = "report",produces = "application/json")
    public Object report(@RequestBody AppDeviceParam appDeviceParam) {
        //数据校验 TODO
        AppDevice appDevice = BeanUtils.beanCopy(appDeviceParam, AppDevice.class);
        //clientIP
        String clientIpAddr = RequestUtils.getClientIpAddr(RequestUtils.getRequest());
        appDevice.setClientIp(clientIpAddr);
        appDeviceService.report(appDevice);
        return WebResultBuilder.successResult();
    }

}
