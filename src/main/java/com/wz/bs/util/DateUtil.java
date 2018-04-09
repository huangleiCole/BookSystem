/**
 * 此代码归黄磊所有,未经允许，禁止转载
 */
package com.wz.bs.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author 黄磊
 * @version 1.0
 * @CreateDate 2017年7月20日 上午10:00:19
 * 本类DateUtil.java是描述
 */
public class DateUtil {
	public static Date build(int year,int month,int day){
		return build(year, month, day,0,0,0);
	}
	//利用年、月、日、时、分、秒来构建一个日期
	public static Date build(int y,int m,int d,int h,int min,int s){
		//创建一个日期实例
		Calendar cal=Calendar.getInstance();
		//修改它的属性值
		cal.set(Calendar.YEAR, y);
		cal.set(Calendar.MONTH, m-1);
		cal.set(Calendar.DAY_OF_MONTH, d);
		cal.set(Calendar.HOUR_OF_DAY, h);
		cal.set(Calendar.MINUTE, min);
		cal.set(Calendar.SECOND, s);
		return cal.getTime();
	}
	/***********8
	 * 格式化日期，使用默认的格式，yyyy-MM-dd
	 * @param d
	 * @return
	 */
	public static String format(Date d){
		final String pattern="yyyy-MM-dd";
		return format(d,pattern);
	}
	//由客户指定格式来格式化日期
	public static String format(Date d ,String pattern){
		//创建SimpleDateFormat实例
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		//
		return sdf.format(d);
	}
	/***********
	 * 采用默认的格式：yyyy-MM-dd来解析日期字符串
	 * @param dateStr
	 * @return
	 */
	
	public static Date parse(String dateStr,String pattern){
		
		SimpleDateFormat sdf=new SimpleDateFormat(pattern);
		Date d=null;
		try {
			d=sdf.parse(dateStr);
			
		} catch (ParseException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return d;
	}
	public static Date parse(String dateStr){
		final String pattern="yyyy-MM-dd";
		return parse(dateStr,pattern);
	}
	
}
