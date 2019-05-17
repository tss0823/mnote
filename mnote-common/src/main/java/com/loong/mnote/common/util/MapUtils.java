package com.loong.mnote.common.util;

import java.util.*;

/**
 * @author: sam
 * @date: 2019-01-10 15:09
 */
public class MapUtils {


    /**
     * 把元素放到map value集合中，value为空自动创建
     * @param map
     * @param key
     * @param value
     * @param <K>
     * @param <V>
     */
    public static<K,V> void putListElement(Map<K, List<V>> map, K key, V value){
        List<V> dataList = map.get(key);
        if(dataList == null){
            dataList = new ArrayList<>();
            map.put(key,dataList);
        }
        dataList.add(value);
    }

    /**
     * 把元素放到map value集合中，value为空自动创建
     * @param map
     * @param key
     * @param value
     * @param <K>
     * @param <V>
     */
    public static<K,V> void putSetElement(Map<K, Set<V>> map, K key, V value){
        Set<V> dataList = map.get(key);
        if(dataList == null){
            dataList = new HashSet<>();
            map.put(key,dataList);
        }
        dataList.add(value);
    }
}
