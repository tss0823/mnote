package com.loong.mnote.common.util;

import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author: sam
 * @date: 2019-02-21 14:54
 */
public class SignUtils {

    public static String sign(Object param,String privateKey){
        //私钥签名
        String paramJson = JacksonUtils.toJson(param);
        TreeMap<String,Object> treeMap = JacksonUtils.toObject(paramJson, TreeMap.class);
        String content = treeMap.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.joining("&"));
        String sign = RSAUtils.sign(content.getBytes(), privateKey);
        return content + "&sign="+sign;
    }

    public static boolean verify(Object param,String publicKey){
        //公钥验签
        String paramJson = JacksonUtils.toJson(param);
        TreeMap<String,Object> treeMap = JacksonUtils.toObject(paramJson, TreeMap.class);
        String sign = treeMap.get("sign").toString();
        treeMap.remove("sign");
        String content = treeMap.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.joining("&"));
        boolean status = RSAUtils.verify(content.getBytes(), publicKey, sign);
        return status;
    }

}
