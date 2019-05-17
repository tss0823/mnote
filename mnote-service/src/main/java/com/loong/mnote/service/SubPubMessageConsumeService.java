package com.loong.mnote.service;

import com.loong.mnote.common.constants.SubPubConstants;
import com.loong.mnote.service.component.AuthComponent;
import com.loong.mnote.service.component.SystemConfigComponent;
import com.loong.mnote.service.component.redis.SubPubMessage;
import com.loong.mnote.service.component.redis.SubPubMessageConsume;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: sam
 * @date: 2019-02-01 20:05
 */
@Service
public class SubPubMessageConsumeService implements SubPubMessageConsume {

    @Autowired
    private SystemConfigComponent systemConfigComponent;

    @Autowired
    private AuthComponent authComponent;

    @Override
    public void consume(SubPubMessage subPubMessage) {
        if(!systemConfigComponent.getAppName().equals(subPubMessage.getAppName())){
            return;
        }

        if(StringUtils.equals(SubPubConstants.AUTH_DATA,subPubMessage.getKey())){
            authComponent.initData();
        }

    }
}
