package com.loong.mnote.service.component.redis;

public interface MessagePublisher {
    void publish(SubPubMessage message);
}