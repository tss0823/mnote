package com.loong.mnote.common.util;

import io.swagger.models.auth.In;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateTimeUtil {

    private static final String TIME_FORMATE = "yyyy-MM-dd HH:mm:ss";

    private static final String DATE = "yyyy-MM-dd";

    private static final String TIME = "HH:mm:ss";

    /**
     * 生成当前时间
     * @return
     */
    public static Timestamp getNowTime() {
        return new Timestamp(System.currentTimeMillis());
    }

    /**
     * 计算向前推进或者向后推进的时间字符串
     * @param type 正数代表向前，负数代表向后
     * @param unit 时间单位
     * @return
     */
    public static String frontOrAfterTime(Integer type, ChronoUnit unit) {
        // 正数代表当前时间往前，ChronoUnit里面有年，月,日等计量，根据需求可以修改
        LocalDateTime ldt = LocalDateTime.now().minus(type, unit);
        Date date = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
        SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMATE);
        return sdf.format(date);
    }

    /**
     * 时间戳转日期
     * @param seconds
     * @param format
     * @return
     */
    public static String timeStamp2Date(String seconds,String format) {
        if(seconds == null || seconds.isEmpty() || seconds.equals("null")){
            return "";
        }
        if(format == null || format.isEmpty()){
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds+"000")));
    }

    /**
     * 日期转时间戳
     * @param date_str
     * @param format
     * @return
     */
    public static String date2TimeStamp(String date_str,String format){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime()/1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 取得当前时间戳（精确到秒）
     * @return
    */
    public static String timeStamp(){
        long time = System.currentTimeMillis();
        String t = String.valueOf(time/1000);
        return t;
    }

    /**
     * 日期转换星期
     * @param datetime
     * @return
     */
    public static String dateToWeek(String datetime, String formate) {
        SimpleDateFormat f = new SimpleDateFormat(formate);
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(datetime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 日期转换数字的星期
     * @param dateTime
     * @return
     */
    public static Integer dateToWeekFormateInt(String dateTime, String formate) {
        SimpleDateFormat f = new SimpleDateFormat(formate);
        Integer[] weekDays = { 7, 1, 2, 3, 4, 5, 6 };
        Calendar cal = Calendar.getInstance(); // 获得一个日历
        Date datet = null;
        try {
            datet = f.parse(dateTime);
            cal.setTime(datet);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1; // 指示一个星期中的某天。
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * 获取日期字符串 年月日格式
     * @return
     */
    public static String getDateStr() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE);
        String dateNowStr = sdf.format(d);
        return dateNowStr;
    }

    /**
     * 获取时间字符串 小时分钟秒
     * @return
     */
    public static String getTimeStr() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(TIME);
        String dateNowStr = sdf.format(d);
        return dateNowStr;
    }

    /**
     * 获取指定日期的前一天时间
     * @param day
     * @return
     */
    public static String dayAfter(String day) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day1 = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day1 - 1);

        String dayAfter = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayAfter;
    }

    /**
     * 判断两个日期是否在同一周内
     * @param date1 日期一
     * @param date2 日期二
     * @return
     */
    public static boolean isThisWeek(String date1, String date2) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        Date d2 = null;
        try
        {
            d1 = format.parse(date1);
            d2 = format.parse(date2);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setFirstDayOfWeek(Calendar.MONDAY);//西方周日为一周的第一天，咱得将周一设为一周第一天
        cal2.setFirstDayOfWeek(Calendar.MONDAY);
        cal1.setTime(d1);
        cal2.setTime(d2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (subYear == 0)// subYear==0,说明是同一年
        {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) //subYear==1,说明cal比cal2大一年;java的一月用"0"标识，那么12月用"11"
        {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11)//subYear==-1,说明cal比cal2小一年
        {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

}
