/*
 * @(#)CalendarUtils.java 2014-1-8 下午12:51:41
 * Copyright 2014 刘志坚, Inc. All rights reserved. 8637.com
 * PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * dateTime之后的某天在下一个月的当前周的最后一天的日期：
 * System.out.println(dateTime.plusDays(45).plusMonths(1).dayOfWeek().withMaximumValue().toString("E MM/dd/yyyy HH:mm:ss.SSS");
 */
package com.zttx.web.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;

import com.zttx.web.consts.ApplicationConst;

/**
 * <p>File：CalendarUtils.java</p>
 * <p>Title: 日期，日历处理工具类</p>
 * <p>Description:主要功能为运用joda-time对日期、时间进行计算和处理</p>
 * <p>Copyright: Copyright (c) 2014 2014-1-8 下午12:51:41</p>
 * <p>Company: 8637.com</p>
 * @author 刘志坚
 * @version 1.0
 */
public class CalendarUtils
{
    private static final Logger logger = Logger.getLogger(CalendarUtils.class);
    
    // 私有构造器，防止类的实例化
    private CalendarUtils()
    {
        super();
    }
    
    /**
     * 根据系统时间获取当前年份
     * @return int 当前年份
     */
    public static int getCurrentYear()
    {
        DateTime dt = new DateTime();
        return dt.getYear();
    }
    
    /**
     * 获得给定日期的年份
     * @param date 给定日期
     * @return int 年份
     */
    public static int getYear(Date date)
    {
        if (null == date)
        {
            throw new NullPointerException("日期参数为null");
        }
        else
        {
            DateTime dt = new DateTime(date);
            return dt.getYear();
        }
    }
    
    /**
     * 根据指定的日期字符串，获取日期年份
     * @param date 指定的日期字符串
     * @return int 日期年份
     */
    public static int getYear(String date)
    {
        int year = 0;
        if (StringUtils.isBlank(date))
        {
            throw new NullPointerException("日期参数为Null");
        }
        else
        {
            DateTime dt = null;
            try
            {
                dt = new DateTime(date);
                year = dt.getYear();
            }
            catch (IllegalArgumentException e)
            {
                logger.error(e.getMessage());
            }
            return year;
        }
    }
    
    /**
     * 根据系统时间获取当前月份
     * @return int 当前月份
     */
    public static int getCurrentMonth()
    {
        DateTime dt = new DateTime();
        return dt.getMonthOfYear();
    }
    
    /**
     * 根据指定日期对象获取当前月份
     * @param date Date对象
     * @return int 当前月份
     */
    public static int getMonth(Date date)
    {
        if (null == date)
        {
            throw new NullPointerException("日期参数为null");
        }
        else
        {
            DateTime dt = new DateTime(date);
            return dt.getMonthOfYear();
        }
    }
    
    /**
     * 根据日期字符串取得当前的月份
     * @param date 正确的日期格式字符串
     * @return int 当前月份
     */
    public static int getMonth(String date)
    {
        int month = 0;
        if (StringUtils.isBlank(date))
        {
            throw new NullPointerException("日期参数为Null");
        }
        else
        {
            DateTime dt = null;
            try
            {
                dt = new DateTime(date);
                month = dt.getMonthOfYear();
            }
            catch (IllegalArgumentException e)
            {
                logger.error(e.getMessage());
            }
            return month;
        }
    }
    
    /**
     * 根据系统时间获取当前日期
     * @return int 当前日期
     */
    public static int getCurrentDate()
    {
        DateTime dt = new DateTime();
        return dt.getDayOfMonth();
    }
    
    /**
     * 根据系统时间取得当前小时数
     * @return int 当前小时
     */
    public static int getCurrentHour()
    {
        DateTime dt = new DateTime();
        return dt.getHourOfDay();
    }
    
    /**
     * 根据系统时间取得当前分钟数
     * @return int 当前分钟数
     */
    public static int getCurrentMinute()
    {
        DateTime dt = new DateTime();
        return dt.getMinuteOfHour();
    }
    
    /**
     * 根据系统时间取得当前秒数
     * @return int 当前秒数
     */
    public static int getCurrentSecond()
    {
        DateTime dt = new DateTime();
        return dt.getSecondOfMinute();
    }
    
    /**
     * 根据给定日期对象取得日期
     * @param date Date对象
     * @return int date对象的日期
     */
    public static int getDate(Date date)
    {
        if (null == date)
        {
            throw new NullPointerException("日期参数为Null");
        }
        else
        {
            DateTime dt = new DateTime(date);
            return dt.getDayOfMonth();
        }
    }
    
    /**
     * 根据给定的日期格式字符串取当该日期
     * @param date 日期格式字符串
     * @return int 日期
     */
    public static int getDate(String date)
    {
        int day = 0;
        if (StringUtils.isBlank(date))
        {
            throw new NullPointerException("日期参数为Null");
        }
        else
        {
            DateTime dt = null;
            try
            {
                dt = new DateTime(date);
                day = dt.getDayOfMonth();
            }
            catch (IllegalArgumentException e)
            {
                logger.error(e.getMessage());
            }
            return day;
        }
    }
    
    /**
     * 根据年、月、日、时、分来构造Date对象
     * @param year 年
     * @param monthOfYear 月
     * @param dayOfMonth 日
     * @param hourOfDay 时
     * @param minuteOfHour 分
     * @return Date Date
     */
    public static Date getDate(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour)
    {
        DateTime dt = new DateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour);
        return dt.toDate();
    }
    
    /**
     * 根据年、月、日、时、分来构造指定日期格式的日期字符串
     * @param year 年,
     * @param monthOfYear 月
     * @param dayOfMonth 日
     * @param hourOfDay 时
     * @param minuteOfHour 分
     * @param format 日期格式
     * @return String 指定日期格式的日期字符串
     */
    public static String getDate(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, String format)
    {
        DateTime dt = new DateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour);
        return dt.toString(format);
    }
    
    /**
     * 根据年、月、日、时、分、秒来构造Date对象
     * @param year 年
     * @param monthOfYear 月
     * @param dayOfMonth 日
     * @param hourOfDay 时
     * @param minuteOfHour 分
     * @param secondOfMinute 秒
     * @return Date Date
     */
    public static Date getDate(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute)
    {
        DateTime dt = new DateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute);
        return dt.toDate();
    }
    
    /**
     * 根据年、月、日、时、分、秒返回指定格式的日期字符串
     * @param year 年
     * @param monthOfYear 月
     * @param dayOfMonth 日
     * @param hourOfDay 时
     * @param minuteOfHour 分
     * @param secondOfMinute 秒
     * @param format 格式
     * @return String 指定格式的日期字符串
     */
    public static String getDate(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minuteOfHour, int secondOfMinute, String format)
    {
        DateTime dt = new DateTime(year, monthOfYear, dayOfMonth, hourOfDay, minuteOfHour, secondOfMinute);
        return dt.toString(format);
    }
    
    /**
     * 将日期对象以指定的日期格式字符串返回
     * @param date Date
     * @param format 日期格式
     * @return String 指定的日期格式字符串
     */
    public static String getDate(Date date, String format)
    {
        if (null == date)
        {
            throw new NullPointerException("日期参数为Null");
        }
        else
        {
            DateTime dt = new DateTime(date);
            return dt.toString(format);
        }
    }
    
    /**
     * 将当前时间以指定的日期格式以字符串形式返回
     * @param format 日期格式
     * @return String 指定的日期格式
     */
    public static String getCurrentDate(String format)
    {
        if (StringUtils.isBlank(format))
        {
            throw new NullPointerException("日期参数为Null");
        }
        else
        {
            DateTime dt = new DateTime();
            return dt.toString(format);
        }
    }
    
    /**
     * 将指定时间加上指定秒数，返回长整数时间戳
     * @param time 指定的长整数时间
     * @param amouont 加上的秒数
     * @return Long 结果
     */
    public static Long addSecond(Long time, int amouont)
    {
        if (null == time)
        {
            throw new NullPointerException("日期参数为null");
        }
        else
        {
            DateTime dt = new DateTime(time);
            return dt.plusSeconds(amouont).getMillis();
        }
    }
    
    /**
     * 将指定时间加上指定分钟数，返回长整数时间戳
     * @param time 指定的长整数时间
     * @param amouont 加上的分钟数
     * @return Long 结果
     */
    public static Long addMinute(Long time, int amount)
    {
        if (null == time)
        {
            throw new NullPointerException("日期参数为null");
        }
        else
        {
            DateTime dt = new DateTime(time);
            return dt.plusMinutes(amount).getMillis();
        }
    }
    
    /**
     * 将指定时间加上指定小时数，返回长整数时间戳
     * @param time 指定的长整数时间
     * @param amouont 加上的小时数
     * @return Long 结果
     */
    public static Long addHour(Long time, int amount)
    {
        if (null == time)
        {
            throw new NullPointerException("日期参数为null");
        }
        else
        {
            DateTime dt = new DateTime(time);
            return dt.plusHours(amount).getMillis();
        }
    }
    
    /**
     * 将指定时间加上指定天数，返回长整数时间戳
     * @param time 指定的长整数时间
     * @param amouont 加上的天数
     * @return Long 结果
     */
    public static Long addDay(Long time, int amount)
    {
        if (null == time)
        {
            throw new NullPointerException("日期参数为null");
        }
        else
        {
            DateTime dt = new DateTime(time);
            return dt.plusDays(amount).getMillis();
        }
    }
    
    /**
     * 将指定的Date对象加上指定分钟数，并返回指定格式的日期字符串
     * @param date Date对象
     * @param amount 指定分钟数
     * @param format 日期格式
     * @return String 返回指定格式的日期字符串
     */
    public static String addMinute(Object date, int amount, String format)
    {
        if (null == date)
        {
            throw new NullPointerException("日期参数为null");
        }
        else
        {
            DateTime dt = new DateTime(date);
            return dt.plusMinutes(amount).toString(format);
        }
    }
    
    /**
     * 将指定的Date对象加上指定小时数，并返回指定格式的日期字符串
     * @param date Date对象
     * @param amount 指定小时数
     * @param format 日期格式
     * @return String 返回指定格式的日期字符串
     */
    public static String addHour(Object date, int amount, String format)
    {
        if (null == date)
        {
            throw new NullPointerException("日期参数为null");
        }
        else
        {
            DateTime dt = new DateTime(date);
            return dt.plusHours(amount).toString(format);
        }
    }
    
    /**
     * 将指定的Date对象加上指定天数，并返回指定格式的日期字符串
     * @param date Date对象
     * @param amount 指定天数
     * @param format 日期格式
     * @return String 返回指定格式的日期字符串
     */
    public static String addDay(Object date, int amount, String format)
    {
        if (null == date)
        {
            throw new NullPointerException("日期参数为null");
        }
        else
        {
            DateTime dt = new DateTime(date);
            return dt.plusDays(amount).toString(format);
        }
    }
    
    /**
     * 将指定时间加上指定天数，返回长整数时间戳
     * @param date 时间对象
     * @param amouont 加上的天数
     * @return Long 结果
     */
    public static Long addDay(Date date, int amount)
    {
        if (null == date)
        {
            throw new NullPointerException("日期参数为null");
        }
        else
        {
            DateTime dt = new DateTime(date);
            return dt.plusDays(amount).getMillis();
        }
    }
    
    /**
     * 将指定的Date对象加上指定周数，并返回指定格式的日期字符串
     * @param date Date对象
     * @param amount 指定周数
     * @param format 日期格式
     * @return String 返回指定格式的日期字符串
     */
    public static String addWeek(Object date, int amount, String format)
    {
        if (null == date)
        {
            throw new NullPointerException("日期参数为null");
        }
        else
        {
            DateTime dt = new DateTime(date);
            return dt.plusWeeks(amount).toString(format);
        }
    }
    
    /**
     * 将指定的Date对象加上指定月数，并返 回指定格式的日期字符串
     * @param date Date对象
     * @param amount 指定的月数
     * @param format 日期格式
     * @return String 返 回指定格式的日期字符串
     */
    public static String addMonth(Object date, int amount, String format)
    {
        if (null == date)
        {
            throw new NullPointerException("日期参数为null");
        }
        else
        {
            DateTime dt = new DateTime(date);
            return dt.plusMonths(amount).toString(format);
        }
    }
    
    /**
     * 将指定的Date对象加上指定的年数，并返回指定的日期格式字符串
     * @param date Date对象
     * @param amount 指定的年数
     * @param format 日期格式
     * @return String 返 回指定格式的日期字符串
     */
    public static String addYear(Object date, int amount, String format)
    {
        if (null == date)
        {
            throw new NullPointerException("日期参数为null");
        }
        else
        {
            DateTime dt = new DateTime(date);
            return dt.plusYears(amount).toString(format);
        }
    }
    
    /**
     * 根据DateTime对象判断该月是否为润月
     * @param dt DateTime
     * @return boolean true：是，false：否
     */
    public static boolean monthIsLeap(DateTime dt)
    {
        Property property = dt.monthOfYear();
        return property.isLeap();
    }
    
    /**
     * 判断当前月是否为润月
     * @return boolean true：是，false：否
     */
    public static boolean monthIsLeap()
    {
        DateTime dt = new DateTime();
        return monthIsLeap(dt);
    }
    
    /**
     * 根据指定的日期对象判断是否为润月
     * @param date Date
     * @return boolean true：是，false：否
     */
    public static boolean monthIsLeap(Date date)
    {
        if (null == date)
        {
            throw new NullPointerException("日期参数为Null");
        }
        else
        {
            DateTime dt = new DateTime(date);
            return monthIsLeap(dt);
        }
    }
    
    /**
     * 根据指定的字符串格式的日期判断该月是否为润月
     * @param date 字符串格式的日期
     * @return boolean true：是，false：否
     */
    public static boolean monthIsLeap(String date)
    {
        if (StringUtils.isBlank(date))
        {
            throw new NullPointerException("日期参数为Null");
        }
        else
        {
            DateTime dt = new DateTime(date);
            return monthIsLeap(dt);
        }
    }
    
    /**
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int secondDiff(Object date1, Object date2)
    {
        if (null == date1 || null == date2)
        {
            throw new NullPointerException("日期参数为Null");
        }
        else
        {
            DateTime dt1 = new DateTime(date1);
            DateTime dt2 = new DateTime(date2);
            Period period = new Period(dt1, dt2, PeriodType.seconds());
            return period.getSeconds();
        }
    }
    
    /**
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static int minuteDiff(Object date1, Object date2)
    {
        return 0;
    }
    
    public static int hourDiff(Object date1, Object date2)
    {
        return 0;
    }
    
    /**
     * 计算两个日期对象间隔的天数(date2-date1)
     * @param date1 Date或字符串格式的日期
     * @param date2 Date或字符串格式的日期
     * @return int 间隔天数
     */
    public static int dateDiff(Object date1, Object date2)
    {
        if (null == date1 || null == date2)
        {
            throw new NullPointerException("日期参数为Null");
        }
        else
        {
            DateTime dt1 = new DateTime(date1);
            DateTime dt2 = new DateTime(date2);
            Period period = new Period(dt1, dt2, PeriodType.days());
            return period.getDays();
        }
    }
    
    /**
     * 计算两个日期对象间隔的周数(date2-date1)
     * @param date1 Date或字符串格式的日期
     * @param date2 Date或字符串格式的日期
     * @return int 间隔周数
     */
    public static int weekDiff(Object date1, Object date2)
    {
        if (null == date1 || null == date2)
        {
            throw new NullPointerException("日期参数为Null");
        }
        else
        {
            DateTime dt1 = new DateTime(date1);
            DateTime dt2 = new DateTime(date2);
            Period period = new Period(dt1, dt2, PeriodType.weeks());
            return period.getWeeks();
        }
    }
    
    /**
     * 计算两个日期对象间隔的月数(date2-date1)
     * @param date1 Date或字符串格式的日期
     * @param date2 Date或字符串格式的日期
     * @return int 间隔月数
     */
    public static int monthDiff(Object date1, Object date2)
    {
        if (null == date1 || null == date2)
        {
            throw new NullPointerException("日期参数为Null");
        }
        else
        {
            DateTime dt1 = new DateTime(date1);
            DateTime dt2 = new DateTime(date2);
            Period period = new Period(dt1, dt2, PeriodType.months());
            return period.getMonths();
        }
    }
    
    /**
     * 计算两个日期对象间隔的年数(date2-date1)
     * @param date1 Date或字符串格式的日期
     * @param date2 Date或字符串格式的日期
     * @return int 间隔年数
     */
    public static int yearDiff(Object date1, Object date2)
    {
        if (null == date1 || null == date2)
        {
            throw new NullPointerException("日期参数为Null");
        }
        else
        {
            DateTime dt1 = new DateTime(date1);
            DateTime dt2 = new DateTime(date2);
            Period period = new Period(dt1, dt2, PeriodType.years());
            return period.getYears();
        }
    }
    
    /**
     * 取得当前月的最后一天并以指定格式的字符串返回
     * @param format 指定的日期格式
     * @return String 指定格式的日期字符串
     */
    public static String getLastDayOfMonth(String format)
    {
        /*
         * Calendar calendar = Calendar.getInstance(); int max =
         * calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
         * calendar.set(Calendar.DAY_OF_MONTH, max); DateTime dt = new
         * DateTime(calendar); return dt.toString(format);
         */
        DateTime dt = new DateTime();
        return dt.dayOfMonth().withMaximumValue().toString(format);
    }
    
    /**
     * 根据给定的日期参数，计算年龄
     * @param date Unix时间戳(精确到秒)
     * @return int 年龄
     */
    public static int getAge(long date)
    {
        Date now = new Date();
        Date birthDate = getTimeFromLong(date);
        int age = yearDiff(birthDate, now);
        return age;
    }
    
    /**
     * Date now = new Date();
     * @param date 日期对象
     * @return int 年龄
     */
    public static int getAge(Date date)
    {
        Date now = new Date();
        int age = yearDiff(date, now);
        return age;
    }
    
    /**
     * 将当前时间精确到秒并转化为long格式
     * @return iTime 整数格式的当前时间，精确到毫秒
     */
    public static long getCurrentLong()
    {
        long iTime = System.currentTimeMillis();
        return iTime;
    }
    
    /**
     * 根据长整型时间戳返回指定格式的日期字符串
     * @param lTime 长整型时间戳
     * @param format 日期格式
     * @return String 指定格式的日期字符串
     */
    public static String getTimeFromLong(long lTime, String format)
    {
        DateTime dt = new DateTime(lTime);
        return dt.toString(format);
    }
    
    /**
     * 根据长整型时间戳返回指定格式的日期字符串 排除lTime 为0的情况
     * @param lTime 长整型时间戳
     * @param format 日期格式
     * @return String 指定格式的日期字符串
     * @author 罗盛平
     */
    public static String getStringTime(Long lTime, String format)
    {
        if (!lTime.equals(0l))
        {
            DateTime dt = new DateTime(lTime);
            return dt.toString(format);
        }
        return null;
    }
    
    /**
     * 根据长整型时间戳返回日期对象
     * @param lTime 长整型时间戳
     * @return Date 日期对象
     * @author 郭小亮
     */
    public static Date getTimeFromLong(long longTimestamp)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(longTimestamp);
        return cal.getTime();
    }
    
    /**
     * 根据日期格式的字符串取得长整数时间戳
     * @param date 日期格式的字符串
     * @return long 长整数时间戳
     */
    public static long getLongFromTime(String date)
    {
        long lTime = 0L;
        if (StringUtils.isNotBlank(date))
        {
            DateTime dt = new DateTime(date);
            lTime = dt.toDate().getTime();
        }
        return lTime;
    }
    
    /**
     * 根据日期格式的字符串取得长整数时间戳
     * @param date
     * @param format 指定格式 如：yyyy-MM-dd HH:mm:ss
     * @return long 长整数时间戳
     * @author 吴万杰
     */
    public static long getLongFromTime(String date, String format)
    {
        long lTime = 0L;
        if (StringUtils.isNotBlank(date))
        {
            DateTime time = DateTimeFormat.forPattern(format).parseDateTime(date);
            lTime = time.toDate().getTime();
        }
        return lTime;
    }
    
    /**
     * 根据日期对象取得长整数时间戳
     * @param date Date
     * @return long 长整数时间戳
     */
    public static long getLongFromTime(Date date)
    {
        long lTime = 0L;
        if (null != date)
        {
            DateTime dt = new DateTime(date);
            lTime = dt.toDate().getTime();
        }
        return lTime;
    }
    
    /**
     * 获取以日 的long 类型
     * @param time
     * @return
     * @author 鲍建明
     */
    public static long getDateLong(long time)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return CalendarUtils.getLongFromTime(format.format(time));
    }
    
    /**
     * 切割日期字符串
     * @param date 日期
     * @param format 分割符
     * @return Sring 
     * @author 鲍建明
     */
    public static String splitDate(String date, String format)
    {
        if (StringUtils.isBlank(date) || StringUtils.isBlank(format)) { throw new NullPointerException("日期参数为Null"); }
        StringBuffer sb = new StringBuffer();
        String[] result = date.split(format);
        for (int i = 0; i < result.length; i++)
        {
            sb.append(result[i]);
        }
        return sb.toString();
    }
    
    /**
     * 根据年份间隔获取毫秒时间
     * @param year 间隔年份
     * @return  Long 毫秒值
     * @author 罗盛平
     */
    public static Long getLongTime(Integer year)
    {
        Long time = (365L * 60L * 60L * 24L * 1000L) * Long.valueOf(year.toString());
        return time;
    }
    
    /**
     * 传入时间到当前时间的时长
     * @param time
     * @return
     * @author 施建波
     */
    public static String getDateLengthStr(Long time)
    {
        StringBuilder dateSb = new StringBuilder();
        Long currentTime = getCurrentLong();
        if (currentTime > time)
        {
            Long tempTime = currentTime.longValue() - time.longValue();
            Long dateTime = 24L * 60L * 60L * 1000L;
            if (0 >= tempTime)
            {
                dateSb.append("1天");
            }
            else
            {
                Long tiemLength = 365L * 30L * dateTime.longValue();
                Long startTime = tempTime.longValue() / tiemLength.longValue();
                Long endTime = tempTime % tiemLength.longValue();
                if (0 < startTime)
                {
                    dateSb.append(startTime).append("年");
                }
                if (0 < endTime)
                {
                    tempTime = endTime;
                    tiemLength = 30L * dateTime.longValue();
                    startTime = tempTime.longValue() / tiemLength.longValue();
                    endTime = tempTime % tiemLength.longValue();
                    if (0 < startTime)
                    {
                        dateSb.append(startTime).append("个月");
                    }
                    if (0 < endTime)
                    {
                        startTime = endTime / dateTime;
                        startTime = (0 < startTime) ? startTime : 1;
                        dateSb.append(startTime).append("天");
                    }
                }
            }
        }
        return dateSb.toString();
    }
    
    /**
     * 将yyyy-MM-dd HH:mm:ss格式的字符串时间转化为日期格式
     * @param time yyyy-MM-dd HH:mm:ss格式的字符串
     * @return Date Date
     */
    public static Date parseStringToDate(String time)
    {
        DateTime dt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss").parseDateTime(time);
        ;
        return dt.toDate();
    }
    
    /**
     * 将当前日期精确到秒并转化为long格式
     * @return
     * @author 施建波
     */
    public static Long getCurrentDayLong()
    {
        DateTime dateTime = new DateTime();
        return dateTime.withTimeAtStartOfDay().getMillis();
    }
    
    public static void main(String[] args)
    {
        Long long1 = CalendarUtils.addDay(CalendarUtils.getCurrentLong(), -3);
        System.out.println(long1);
        System.out.println(CalendarUtils.getStringTime(1420732799999L, ApplicationConst.DATE_FORMAT_YMDHMS));
    }
}
