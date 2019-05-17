package com.loong.mnote.service.component.redis;

/**
 * @author: sam
 * @date: 2019-02-01 20:02
 */
public interface SubPubMessageConsume {

    void consume(SubPubMessage subPubMessage);
}
