package com.loong.mnote.common.util.date;

/**
 * @author: lhd
 * @date: 2019/1/6 16:55
 */
public enum DatePattern implements Pattern {

    /* 年月日 */
    yyyy_MM_dd("yyyy-MM-dd"),
    yyyy1MM1dd("yyyy/MM/dd"),
    yyyy年MM月dd日("yyyy年MM月dd日"),
    yyyyMMdd("yyyyMMdd"),

    /* 年月 */
    yyyy_MM("yyyy-MM"),
    yyyy1MM("yyyy/MM"),
    yyyy年MM月("yyyy年MM月"),
    yyyyMM("yyyyMM"),

    /* 月日 */
    MM_dd("MM-dd"),
    MM1dd("MM/dd"),
    MM月dd日("MM月dd日"),
    MMdd("MMdd");

    ;

    private String pattern;

    DatePattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return pattern;
    }
}
