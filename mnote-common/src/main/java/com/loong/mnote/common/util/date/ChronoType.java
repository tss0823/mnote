package com.loong.mnote.common.util.date;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

/**
 * @author: lhd
 * @date: 2019/1/6 17:26
 */
public enum ChronoType {

    YEARS(0L, "年"),
    MONTHS(0L, "月"),
    WEEKS(604800000L, "周"),
    DAYS(86400000L, "日"),
    HOURS(3600000L, "时"),
    MINUTES(60000L, "分"),
    SECONDS(1000L, "秒"),
    MILLIS(1L, "毫秒"),
    MICROS(0L, "微秒"),
    NANOS(0L, "纳秒");

    /* 毫秒数 */
    public final long millis;
    /* 计时单位 */
    private final String unit;
    /* 单位（JDK8定义）*/
    private final TemporalUnit temporalUnit;

    ChronoType(long millis, String unit) {
        this.millis = millis;
        this.unit = unit;

        switch (unit) {
            case "年":
                temporalUnit = ChronoUnit.YEARS;
                break;
            case "月":
                temporalUnit = ChronoUnit.MONTHS;
                break;
            case "周":
                temporalUnit = ChronoUnit.WEEKS;
                break;
            case "日":
                temporalUnit = ChronoUnit.DAYS;
                break;
            case "时":
                temporalUnit = ChronoUnit.HOURS;
                break;
            case "分":
                temporalUnit = ChronoUnit.MINUTES;
                break;
            case "秒":
                temporalUnit = ChronoUnit.SECONDS;
                break;
            case "毫秒":
                temporalUnit = ChronoUnit.MILLIS;
                break;
            case "微秒":
                temporalUnit = ChronoUnit.MICROS;
                break;
            case "纳秒":
                temporalUnit = ChronoUnit.NANOS;
                break;
            default:
                temporalUnit = null;
        }
    }

    /**
     * 获取对应单位
     * @return  返回对应的 JDK 日期时间单位
     */
    public final TemporalUnit getTemporalUnit() {
        return temporalUnit;
    }

    /**
     * 获取一个单位对应毫秒数
     * @return  返回毫秒数
     */
    public long getMillis() {
        return millis;
    }

    /**
     * 获取单位表示
     * @return  单位
     */
    public String getUnit() {
        return unit;
    }
}
