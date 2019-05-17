package com.loong.mnote.service;

import com.loong.mnote.common.constants.SubPubConstants;
import com.loong.mnote.service.component.SystemConfigComponent;
import com.loong.mnote.service.component.redis.MessagePublisher;
import com.loong.mnote.service.component.redis.SubPubMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: sam
 * @date: 2019-02-01 16:45
 */
@Service
public class InitDataService extends AbstractService {


    @Autowired
    private SystemConfigComponent systemConfigComponent;

    @Autowired
    private MessagePublisher messagePublisher;

    public void initAuthData(){
        SubPubMessage subPubMessage = new SubPubMessage();
        subPubMessage.setAppName(systemConfigComponent.getAppName());
        subPubMessage.setKey(SubPubConstants.AUTH_DATA);
        messagePublisher.publish(subPubMessage);
    }
}
