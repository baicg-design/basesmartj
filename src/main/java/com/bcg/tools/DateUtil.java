package com.bcg.tools;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Auther: baicg
 * @Date: 2018/12/23 00:13
 * @Description:日期工具类
 */
public class DateUtil {

    public static String pattern = "yyyy-MM-dd";
    public static SimpleDateFormat formatter = new SimpleDateFormat(pattern);
    public static SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
    public static SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

    public static DateTimeFormatter getDateTimeFormatter() {
        return dateFormatter;
    }

    /**
     * 获取现在时间
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(new Date());
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Date getNowDateShort() {
        String dateString = formatter.format(new Date());
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(new Date());
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return返回字符串格式 yyyyMMddHHmmss
     */
    public static String getStringAllDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String dateString = formatter.format(new Date());
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        String dateString = formatter.format(new Date());
        return dateString;
    }

    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyymmdd
     */
    public static String getStringDateShorts() {
        String dateString = formatter1.format(new Date());
        return dateString;
    }



}
