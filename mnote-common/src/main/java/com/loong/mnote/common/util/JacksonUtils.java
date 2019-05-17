package com.loong.mnote.common.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.util.List;

/**
 * 用jack json
 * @author: shengshan.tang
 * @date: 2018/9/15 下午6:20
 */
public class JacksonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    static{
        mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.registerModule(new Jdk8Module()).registerModule(new ParameterNamesModule()).registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

//        SerializationConfig serializationConfig = mapper.getSerializationConfig().withSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
//        mapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
//        mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
//        mapper.setSerializationConfig(serializationConfig);
    }

    /**
     * Fastjson list对象元素不能被正常解析，改成Jackjson ok
     * @param json
     * @param cls  没有被反序列化的对象必须默认构造函数，不然解析报错
     * @param <T>
     * @return
     */
    public static<T> List<T> toList(String json, Class cls){
//        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class,cls);
        try{
            return mapper.readValue(json, javaType);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static<T> T toObject(String json, Class<T> cls){
//        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.readValue(json, cls);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public static String toJson(Object object){
//        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
//        String result = toJson(Arrays.asList(new String[]{"a", "b"}));

    }

}
