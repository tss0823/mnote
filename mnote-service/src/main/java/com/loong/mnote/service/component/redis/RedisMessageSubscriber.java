package com.loong.mnote.service.component.redis;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.util.List;

public class RedisMessageSubscriber implements MessageListener {

    private List<SubPubMessageConsume> subPubMessageConsumeList;

    private Jackson2JsonRedisSerializer jackson2JsonRedisSerializer;

    public RedisMessageSubscriber(List<SubPubMessageConsume> subPubMessageConsumeList, Jackson2JsonRedisSerializer jackson2JsonRedisSerializer) {

        this.subPubMessageConsumeList = subPubMessageConsumeList;
        this.jackson2JsonRedisSerializer = jackson2JsonRedisSerializer;
    }

    public void onMessage(Message message, byte[] pattern) {
        SubPubMessage subPubMessage = (SubPubMessage) jackson2JsonRedisSerializer.deserialize(message.getBody());
        for (SubPubMessageConsume subPubMessageConsume : subPubMessageConsumeList) {
            subPubMessageConsume.consume(subPubMessage);
        }
    }
}