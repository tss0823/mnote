package com.loong.mnote.test.service.component;

import com.loong.mnote.service.component.redis.MessagePublisher;
import com.loong.mnote.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * @author: sam
 * @date: 2019-02-01 16:27
 */
public class RedisSubscribePublishTest extends BaseTest {

    @Autowired
    private MessagePublisher messagePublisher;

    @Test
    public void test1(){
        String message = "Message " + UUID.randomUUID();
//        messagePublisher.publish(message);

    }

}
