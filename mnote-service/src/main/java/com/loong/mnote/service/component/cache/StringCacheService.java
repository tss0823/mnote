package com.loong.mnote.service.component.cache;

/**
 * Created by shan on 2016/3/25.
 */
public interface StringCacheService {

    /**
     * 设置对象，默认过期时间为永久
     * scope app
     * @param key
     * @param value
     */
    void set(String key, String value);

    void set(String key, String value, int period);

    /**
     * 设置对象
     * scope all app
     * @param key
     * @param value
     */
    void setGlobal(String key, String value);

    void setGlobal(String key, String value, int period);

    String get(String key);

    String getGlobal(String key);

    void remove(String key);

    void removeGlobal(String key);

}
