package com.loong.mnote.service.component.cache;

import redis.clients.jedis.ShardedJedis;

/**
 * Created by shan on 2017/5/10.
 */
public interface JedisExecuteHandler {

    void execute(ShardedJedis jedis, String key);
}
