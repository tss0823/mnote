package com.loong.mnote.common.util.date;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

/**
 * @author: lhd
 * @date: 2019/1/6 17:20
 */
public class DateHelper {

    /**
     * Date 转成 LocalDateTime（默认时区）
     * @param date  日期
     * @return  返回转换后的本地日期时间LocalDateTime
     */
    public static LocalDateTime getLocalDateTime(Date date) {
        return getLocalDateTime(date, ZoneId.systemDefault());
    }

    /**
     * java.util.Date 转成 java.time.LocalDateTime
     * @param date      日期
     * @param zoneId    时区
     * @return  返回转换后的本地日期时间LocalDateTime
     */
    public static LocalDateTime getLocalDateTime(Date date, ZoneId zoneId) {
        return LocalDateTime.ofInstant(date.toInstant(), zoneId);
    }

    /**
     * java.time.LocalDateTime 转成 java.util.Date
     * @param localDateTime 本地日期时间
     * @return 返回转换后的Date
     */
    public static Date getDate(LocalDateTime localDateTime) {
        return getDate(localDateTime, ZoneId.systemDefault());
    }

    /**
     * java.time.LocalDateTime 转成 java.util.Date
     * @param localDateTime 本地日期时间
     * @param zoneId    时区
     * @return 返回转换后的Date
     */
    public static Date getDate(LocalDateTime localDateTime, ZoneId zoneId) {
        Instant instant = localDateTime.atZone(zoneId).toInstant();

        return Date.from(instant);
    }

    /**
     * java.time.LocalDate 转成 java.util.Date
     * @param localDate 本地时间值包含日期"年月日"，忽略时间
     * @return  返回转换后日期Date
     */
    public static Date getDate(LocalDate localDate) {
        return getDate(localDate, ZoneId.systemDefault());
    }

    /**
     * java.time.LocalDate 转成 java.util.Date
     * @param localDate 本地时间值包含日期"年月日"，忽略时间
     * @param zoneId    时区
     * @return  返回转换后日期Date
     */
    public static Date getDate(LocalDate localDate, ZoneId zoneId) {
        Instant instant = localDate.atStartOfDay().atZone(zoneId).toInstant();

        return Date.from(instant);
    }

    /**
     * java.util.Date 转成 java.time.LocalDate
     * @param date  日期
     * @return  返回转换后日期LocalDate
     */
    public static LocalDate getLocalDate(Date date) {
        LocalDateTime localDateTime = DateHelper.getLocalDateTime(date);
        return localDateTime.toLocalDate();
    }

    /**
     * java.util.Date 转成 java.time.LocalDate
     * @param date  日期
     * @return  返回转换后日期LocalDate
     */
    public static LocalDate getLocalDate(Date date, ZoneId zoneId) {
        LocalDateTime localDateTime = DateHelper.getLocalDateTime(date, zoneId);
        return localDateTime.toLocalDate();
    }

    /**
     * 加
     * @param zoneId     时区
     * @param date       日期
     * @param chronoType 时间类型
     * @param amount     数值
     * @return
     */
    public static Date plus(ZoneId zoneId, Date date, ChronoType chronoType, long amount) {
        Objects.requireNonNull(date,"date is null");

        LocalDateTime localDateTime = getLocalDateTime(date, zoneId);
        // 调用 JDK7 time API（注意：此处必须重新赋值，方法会返回新对象）
        localDateTime = localDateTime.plus(amount, chronoType.getTemporalUnit());
        return getDate(localDateTime);
    }

    // 加
    public static Date plus(Date date, ChronoType type, long amountToAdd) {
        //1.0 使用系统默认时区
        ZoneId zoneId = ZoneId.systemDefault();

        return plus(zoneId, date, type, amountToAdd);
    }
}
