package com.function.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class CommonUtil {

    /**
     * 获取UUID<br />
     * 返回UUID，做为主键使用
     * 
     * @return uuid（32位）
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    /**
     * 获取生命周期
     * 
     * @param param 原始日期
     * 
     * @return 老化时间
     */
    public static Date getAgingDate(Date date, int min) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        int minute = c.get(Calendar.MINUTE);
        c.set(Calendar.MINUTE, minute - min);

        return c.getTime();
    }
    
    /**
     * 获取当前日期<br />
     * 根据输入格式，返回当前日期
     * 
     * @param date 转换日期
     * @param dateFormatStr 日期格式，默认形式为yyyy-MM-dd HH:mm
     * @return 日期字符串
     */
    public static Date getNowDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
        Date now = new Date();
        try {
            now = dateFormat.parse(dateFormat.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return now;
    }
    
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
     * 获取当前日期<br />
     * 根据输入格式，返回当前日期
     * 
     * @param date 转换日期
     * @param dateFormatStr 日期格式，默认形式为yyyy-MM-dd HH:mm:ss
     * @return 日期字符串
     */
    public static String getDateToStr(Date date, String... dateFormatStr) {
        SimpleDateFormat dateFormat = null;
        if (dateFormatStr != null && dateFormatStr.length > 0) {
            dateFormat = new SimpleDateFormat(dateFormatStr[0]);
        } else {
            dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
        }

        return dateFormat.format(date);
    }
    
	/**
	 * 转换为utf-8
	 * @param gbk
	 * @return
	 */
    public static String toUTF8(String gbk){
    	String utf8=gbk;
    	try {
    		utf8=new String(gbk.getBytes("ISO-8859-1"),"UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return utf8;
    }
}