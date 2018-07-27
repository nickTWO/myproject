package com.hyt.myproject.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期处理公共类
 */
public class DateUtils {
    public static final String FULL_TRADITION_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String SHORT_TRADITION_PATTERN = "yyyy-MM-dd";
    public static final String FULL_PATTERN = "yyyyMMddHHmmss";

    /**
     * 一天的毫秒数
     */
    private final static Long DAY_MILLISECOND = 86400000L;

    public static String getNow(String formate) {
        return new SimpleDateFormat(formate).format(new Date(System.currentTimeMillis()));
    }

    /**
     * 格式化时间
     */
    public static String formatDate(final Date date, String format) {
        if (null == date || StringUtils.isBlank(format))
            return null;
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 根据两个 Calendar （日历） 对象返回它们之间的 （day）天数差
     *
     * @param date1 日历1 减数
     * @param date2 日历2 被减数
     * @return day（天数差）
     */
    public static Long getCompareDays(Date date1, Date date2) {
        // 得到从 1970年01月01日到 calendar1 所经过的天数
        long time1 = date1.getTime() / DAY_MILLISECOND;
        // 得到从 1970年01月01日到 calendar2 所经过的天数
        long time2 = date2.getTime() / DAY_MILLISECOND;
        // 将两个经过的时间减出来就得到了它们之间的时间差
        return time1 - time2;
    }

    /**
     * 返回两个日期之间的秒数
     *
     * @param start
     * @param end
     * @return
     */
    public static long getNumberOfSecondsBetween(Date start, Date end) {
        if (start == null || end == null)
            return -1;
        long millisec = end.getTime() - start.getTime();
        return millisec / (1000);
    }

    /**
     * 加减多少秒
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addSecond(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.SECOND, amount);
        return c.getTime();
    }

    /**
     * 从当前时间加多少天
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDays(final Date date, int days) {
        if (days == 0)
            return date;
        if (date == null)
            return null;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);

        return new Date(cal.getTime().getTime());
    }

    /**
     * 从当前时间加多少月
     *
     * @param date
     * @param month
     * @return
     * @author xyc
     */
    public static Date addMonth(final Date date, int month) {
        if (month == 0)
            return date;
        if (date == null)
            return null;
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, month);
        return new Date(cal.getTime().getTime());
    }

    /**
     * 得到一个时间延后或前移几天的时间,
     * nowdate为时间,
     * delay为前移或后延的天数
     */
    public static String getLastDay(Date nowdate, String delay) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String mdate = "";
            long myTime = (nowdate.getTime() / 1000) - Integer.parseInt(delay) * 24 * 60 * 60;//减就是前移
            nowdate.setTime(myTime * 1000);
            mdate = format.format(nowdate);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 得到一个时间延后或前移几天的时间,
     * nowdate为时间,
     * delay为前移或后延的天数
     */
    public static String getNextDay(Date nowdate, String delay) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String mdate = "";
            long myTime = (nowdate.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;//加就是前移
            nowdate.setTime(myTime * 1000);
            mdate = format.format(nowdate);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    public static List<Date[]> splitTimeByHours(Date start, Date end, int hours) {
        List<Date[]> dl = new ArrayList<Date[]>();
        while (start.compareTo(end) < 0) {
            Date _end = addHours(start, hours);
            if (_end.compareTo(end) > 0) {
                _end = end;
            }
            Date[] dates = new Date[]{(Date) start.clone(), (Date) _end.clone()};
            dl.add(dates);

            start = _end;
        }
        return dl;
    }

    public static Date addMonths(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, amount);
        return c.getTime();
    }

    public static Date addHours(Date date, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY, amount);
        return c.getTime();
    }

    /**
     * 解析date格式
     *
     * @param date
     * @return
     * @desc 主要解决linux与windows对"2013-08-24 12:25:32"这种parse的时候出现的异常
     */
    public static Date dateFormat(String date) {
        Date dt = new Date();
        String[] parts = date.split("-");
        if (parts.length == 3) {
            int years = Integer.parseInt(parts[0]);
            int months = Integer.parseInt(parts[1]) - 1;
            int days = Integer.parseInt(parts[2].substring(0, 2));
            int hours = Integer.parseInt(parts[2].substring(3, 5));
            int minutes = Integer.parseInt(parts[2].substring(6, 8));
            int seconds = Integer.parseInt(parts[2].substring(9, 11));
            GregorianCalendar gc = new GregorianCalendar(years, months,
                    days, hours, minutes, seconds);
            dt = gc.getTime();
        }

        return dt;
    }

    /**
     * 获取当前日期
     *
     * @return 系统日期
     */
    public static String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
        TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");//获取中国的时区
        sdf.setTimeZone(timeZoneChina);
        String st = sdf.format(Calendar.getInstance().getTime());
        return st;
    }
}
