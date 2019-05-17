package com.loong.mnote.service.component.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;

public class RedisMessagePublisher implements MessagePublisher {
 
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private ChannelTopic topic;
 
    public RedisMessagePublisher() {
    }
 
    public RedisMessagePublisher(
      RedisTemplate<Object, Object> redisTemplate, ChannelTopic topic) {
      this.redisTemplate = redisTemplate;
      this.topic = topic;
    }
 
    public void publish(SubPubMessage message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}