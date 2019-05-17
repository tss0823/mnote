package com.loong.mnote.common.util.date;

/**
 * @author: lhd
 * @date: 2019/1/6 16:48
 */
public enum TimePattern implements Pattern {

    /* 时:分:秒 */
    HH_mm_ss("HH:mm:ss"),
    HHmmss("HHmmss"),

    /* 时:分 */
    HH_mm("HH:mm"),
    HHmm("HHmm"),

    /* 分:秒 */
    mm_ss("mm:ss"),
    mmss("mmss"),
    ;

    private String pattern;

    TimePattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return pattern;
    }
}
