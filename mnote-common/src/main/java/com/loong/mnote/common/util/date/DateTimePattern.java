package com.loong.mnote.common.util.date;

/**
 * @author: lhd
 * @date: 2019/1/6 16:52
 */
public enum DateTimePattern implements Pattern {

    /* 年月日 时分秒*/
    yyyy_MM_dd_HH_mm_ss("yyyy-MM-dd HH:mm:ss"),

    yyyy1MM1dd_HH_mm_ss("yyyy/MM/dd HH:mm:ss"),

    yyyy年MM月dd日_HH时mm分ss秒("yyyy年MM月dd日 HH:mm:ss"),

    yyyyMMddHHmmss("yyyyMMddHHmmss");

    ;

    private String pattern;

    DateTimePattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return pattern;
    }
}
