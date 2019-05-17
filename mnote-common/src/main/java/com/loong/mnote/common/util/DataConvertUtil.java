package com.loong.mnote.common.util;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据转换工具类
 */
public class DataConvertUtil {

    private static final Logger logger = LoggerFactory.getLogger(DataConvertUtil.class);

    public static <Target, Source> Target convert(Source src, Target target) {
        org.springframework.beans.BeanUtils.copyProperties(src, target);
        return target;
    }

    public static <Target, Source> Target convert(Source src, Class<Target> targetClass) {
        Target target;
        try {
            target = targetClass.newInstance();
            convert(src, target);
        } catch (Exception e) {
            logger.error("ERROR@DataConvertUtils.convertList, {}, returning null", e);
            return null;
        }
        return target;
    }

    public static <Source, Target> List<Target> convertList(List<Source> sourceList, Class<Target> targetClass) {
        if (CollectionUtils.isEmpty(sourceList)) {
            return new ArrayList<>();
        }
        return sourceList.stream().map(source -> {
            Target target;
            try {
                target = targetClass.newInstance();
            } catch (Exception e) {
                logger.error("ERROR@DataConvertUtils.convertList, {}, returning null", e);
                return null;
            }
            convert(source, target);
            return target;
        }).collect(Collectors.toList());
    }

}
