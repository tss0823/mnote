package com.loong.mnote.web.controller;

import com.loong.mnote.common.domain.WebResult;
import com.loong.mnote.common.web.WebResultBuilder;
import com.loong.mnote.service.component.HealthCheckComponent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: sam
 * @date: 2019-01-26 14:55
 */
@RestController
@RequestMapping
@Api(description="健康监测API")
public class HealthController {

    @Autowired
    private HealthCheckComponent healthCheckComponent;

    @ApiOperation(value = "就绪探针状态",nickname = "sam@mnote.com",response = WebResult.class)
    @GetMapping(path = "readyStatus",produces = "application/json")
    public Object readyStatus() {
        WebResult webResult = WebResultBuilder.successResult();
        //db,cache,search,mq,third ..
        boolean status = healthCheckComponent.check();
        webResult.setData(status);
        return webResult;
    }

    @ApiOperation(value = "存活探针状态",nickname = "sam@mnote.com",response = WebResult.class)
    @GetMapping(path = "liveStatus",produces = "application/json")
    public Object liveStatus() {
        WebResult webResult = WebResultBuilder.successResult();
        boolean status = healthCheckComponent.check();
        webResult.setData(status);
        return webResult;
    }

    @ApiOperation(value = "redirectHttpStatus",nickname = "sam@mnote.com",response = ResponseEntity.class)
    @GetMapping(path = "redirectHttpStatus",produces = "application/json")
    public Object redirectHttpStatus(@RequestParam Integer httpStatus) {
        return new ResponseEntity("error", HttpStatus.valueOf(httpStatus));
    }


}
