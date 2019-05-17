package com.loong.mnote.service.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loong.mnote.common.exception.BizException;
import com.loong.mnote.common.util.http.HttpNewUtils;
import com.loong.mnote.common.util.http.ResponseRes;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 自定义的监测
 */
@Component
public class HealthCheckComponent {

    @Value("${server.port}")
    private Integer serverPort;

    public boolean check() {
        ResponseRes responseRes = HttpNewUtils.get("http://127.0.0.1:"+serverPort+"/actuator/health");
        String bodyText = responseRes.getBodyText();
        JSONObject jsonObject = JSON.parseObject(bodyText);
        JSONObject detailObj = jsonObject.getJSONObject("details");
        Set<String> keySets = detailObj.keySet();
        for (String key : keySets) {

            JSONObject appObj = detailObj.getJSONObject(key);
            String status = appObj.getString("status");
//            if (StringUtils.equals(status,"DOWN")) {
//                throw new BizException(bodyText, HttpStatus.INTERNAL_SERVER_ERROR.value());
//            }
            if (StringUtils.equals(status,"DOWN") && !key.equals("elasticsearchRest")) {
                throw new BizException(bodyText, HttpStatus.INTERNAL_SERVER_ERROR.value());
            }

        }
        return true;
    }



}