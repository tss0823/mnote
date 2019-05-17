package com.loong.mnote.common.util;

import com.loong.mnote.common.util.date.ChronoType;
import com.loong.mnote.common.util.date.DateHelper;
import com.loong.mnote.common.util.date.DatePattern;
import com.loong.mnote.common.util.date.Pattern;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: lhd
 * @date: 2019/1/6 16:36
 */
public class DateUtil {

    /**
     * 日期格式化
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, Pattern pattern) {
        if (null == date || null == pattern) {
            return null;
        }

        DateTimeFormatter dateTimeFormatter = Formatter.getDateTimeFormatter(pattern);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());

        return localDateTime.format(dateTimeFormatter);
    }

    /**
     * 字符串转日期
     * @param dateStr
     * @param pattern
     * @return
     */
    public static Date parse(String dateStr, Pattern pattern) {
        if (StringUtils.isBlank(dateStr) || null == pattern) {
            return null;
        }
        DateTimeFormatter dateTimeFormatter = Formatter.getDateTimeFormatter(pattern);

        if (pattern instanceof DatePattern) {
            LocalDate localDate = LocalDate.parse(dateStr, dateTimeFormatter);
            return DateHelper.getDate(localDate);
        }

        LocalDateTime localDateTime = LocalDateTime.parse(dateStr,dateTimeFormatter);
        return DateHelper.getDate(localDateTime);
    }

    /**
     * 获取当前日期
     * @return 返回日期，忽略时间
     */
    public static Date getCurrentDate() {
        return DateHelper.getDate(DateHelper.getLocalDate(new Date(),ZoneId.systemDefault()), ZoneId.systemDefault());
    }

    /**
     * 获取昨天日期
     * @return 返回日期，忽略时间
     */
    public static Date getYesterday() {
        return minusDays(getCurrentDate(),1);
    }

    /**
     * 获取明天日期
     * @return 返回日期，忽略时间
     */
    public static Date getTomorrow() {
        return plusDays(getCurrentDate(),1);
    }

    /**
     * 时间差（自然日）
     * @param start
     * @param end
     * @return
     */
    public static long betweenDays(Date start, Date end) {
        LocalDate localDateStart = DateHelper.getLocalDate(start);
        LocalDate localDateEnd = DateHelper.getLocalDate(end);
        return localDateEnd.toEpochDay() - localDateStart.toEpochDay();
    }

    /** 加 --------------------*/
    /**
     * +年
     * @param date
     * @param years
     * @return
     */
    public static Date plusYears(Date date, long years) {
        return DateHelper.plus(date, ChronoType.YEARS, years);
    }

    /**
     * +月
     * @param date
     * @param months
     * @return
     */
    public static Date plusMonths(Date date, long months) {
        return DateHelper.plus(date, ChronoType.MONTHS, months);
    }

    /**
     * +天
     * @param date
     * @param days
     * @return
     */
    public static Date plusDays(Date date, long days) {
        return DateHelper.plus(date, ChronoType.DAYS, days);
    }

    /**
     * +时
     * @param date
     * @param hours
     * @return
     */
    public static Date plusHours(Date date, long hours) {
        return DateHelper.plus(date, ChronoType.HOURS, hours);
    }

    /**
     * +分
     * @param date
     * @param minutes
     * @return
     */
    public static Date plusMinutes(Date date, long minutes) {
        return DateHelper.plus(date, ChronoType.MINUTES, minutes);
    }

    /**
     * +秒
     * @param date
     * @param seconds
     * @return
     */
    public static Date plusSeconds(Date date, long seconds) {
        return DateHelper.plus(date, ChronoType.SECONDS, seconds);
    }

    /** 减 --------------------*/
    /**
     * -年
     * @param date
     * @param years
     * @return
     */
    public static Date minusYears(Date date, long years) {
        return plusYears(date, -years);
    }

    /**
     * -月
     * @param date
     * @param months
     * @return
     */
    public static Date minusMonths(Date date, long months) {
        return plusMonths(date, -months);
    }

    /**
     * -天
     * @param date
     * @param days
     * @return
     */
    public static Date minusDays(Date date, long days) {
        return plusDays(date, -days);
    }

    /**
     * -时
     * @param date
     * @param hours
     * @return
     */
    public static Date minusHours(Date date, long hours) {
        return plusHours(date, -hours);
    }

    /**
     * -分
     * @param date
     * @param minutes
     * @return
     */
    public static Date minusMinutes(Date date, long minutes) {
        return plusMinutes(date, -minutes);
    }

    /**
     * -秒
     * @param date
     * @param seconds
     * @return
     */
    public static Date minusSeconds(Date date, long seconds) {
        return plusSeconds(date, -seconds);
    }

    // 格式化对象
    private static class Formatter {
        // 单个线程内，对格式化对象进行缓存，省去反复创建销毁对象实例
        private static ThreadLocal<Map<String,DateTimeFormatter>> threadLocalFormatter = new ThreadLocal<>();

        private static DateTimeFormatter getDateTimeFormatter(Pattern pattern) {
            // 1. 根据格式化模式，从线程上下文获取DateTimeFormatter
            Map<String,DateTimeFormatter> formatterMap = threadLocalFormatter.get();
            if (null == formatterMap) {
                threadLocalFormatter.set(new ConcurrentHashMap<>());
                formatterMap = threadLocalFormatter.get();
            }

            // 2. 如果对应格式化对象不存在则新建
            DateTimeFormatter formatter = formatterMap.get(pattern.getPattern());
            if (null == formatter) {
                formatter = DateTimeFormatter.ofPattern(pattern.getPattern());
                formatterMap.put(pattern.getPattern(),formatter);
            }
            return formatter;
        }
    }
}
