package com.function.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * 
 * @author  wdl
 * @version 1.0 2012-12-12
 */
public class DateUtils {
           
    /**
     * 获取当前日期<br />
     * 根据输入格式，返回当前日期
     * 
     * @param date 转换日期
     * @param dateFormatStr 日期格式，默认形式为yyyy-MM-dd HH:mm
     * @return 日期字符串
     */
    public static Date getDate(String str) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        // 检测时间.
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            date = new Date();
        }

        return date;
    }
    
    /**
     * 返回指定格式的日期字符串
     * 
     * @param date 转换日期
     * @param format 转换格式
     * @return 转换后的字符串
     */
    public static String getDateStr(Date date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        return format.format(date);
    }
    
    /**
     * 获取当前整份<br />
     * 根据输入时间,返回当前整分时间
     * 
     * @param date 转换时间
     * @return 时间字符串 为HHmm
     */
    public static String getMin(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HHmm");
        
        return dateFormat.format(date);
    }
    
    /**
     * 获取时间差<br />
     * 根据输入两个时间值，返回时间差
     * 
     * @param date1 转换日期
	 * @param date2 转换日期
	 * 
     * @return 时间差（秒）
     */
    public static int getDateDiff(Date date1, Date date2) {
    	return (int) ((date1.getTime() - date2.getTime()) / 1000);
    }
    
    private DateUtils(){}
}