package com.cabletech.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;
/**
 * 
 * @author zyh 2012-5-25 时间工具类
 *
 */
public class DateUtils {
	/** 日期格式格式化*/
	public static SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 按格式获得当前日期 格式为""yyyy-MM-dd HH:mm:ss""
	 * @return
	 */
	public static String getNowDateString(){
		return format.format(new Date());
	}
	/**
	 * 获得指定字符串的date对象
	 * @param dateString 日期字符串
	 * @return 
	 */
	public static Date getDate(String dateString){
		Date  date;
		try {
			date=format.parse(dateString);
			return date;
		} catch (Exception e) {
			Logger.getLogger("Date getDate",e.getMessage());
		}
		return null;
	}
	/**
	 * 获得指定时间的字符串对象
	 * @param date 日期
	 * @return 
	 */
	public static String getDateString(Date date){
		return format.format(date);
	} 
	/**
	 * 获得当前时间的前一天
	 * @return
	 */
	public static String getLastDay(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return format.format(cal.getTime());

	}
	/**
	 * 获得当前时间的前一月
	 * @return
	 */
	public static String getLastMonth(){
		Calendar calendar=Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		return format.format(calendar.getTime());
		 
	}
}
