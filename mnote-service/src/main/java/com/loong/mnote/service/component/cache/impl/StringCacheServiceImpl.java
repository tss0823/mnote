package com.loong.mnote.service.component.cache.impl;

import com.loong.mnote.service.component.cache.StringCacheService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by shan on 2016/3/25.
 */
@Service
public class StringCacheServiceImpl implements StringCacheService {

    int cacheSize = 800;

    //cache 使用LRU 策略
    Map<String,String> cacheMap = new LinkedHashMap(cacheSize){
        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return this.size() >= cacheSize;
        }
    };

    {
        cacheMap = Collections.synchronizedMap(cacheMap);
    }

    @Override
    public void set(String key, String value) {
        cacheMap.put(key,value);

    }


    @Override
    public void set(String key, String value, int period) {
        this.set(key,value);
//        throw new RuntimeException("not implement");
    }

    @Override
    public void setGlobal(String key, String value) {
        this.set(key,value);
//        throw new RuntimeException("not implement");
    }

    @Override
    public void setGlobal(String key, String value, int period) {
        this.set(key,value);
//        throw new RuntimeException("not implement");
    }

    @Override
    public String get(String key) {
        return cacheMap.get(key);
    }

    @Override
    public String getGlobal(String key) {
        return this.get(key);
//        throw new RuntimeException("not implement");
    }

    @Override
    public void remove(String key) {
        cacheMap.remove(key);
    }

    @Override
    public void removeGlobal(String key) {
        this.remove(key);
//        throw new RuntimeException("not implement");
    }
}
