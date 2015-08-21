package com.hundsun.med.hdp.service.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static final DateFormat FORMAT_DAY_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat FORMAT_DAY = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public static String getDayFormat(Date date){
		if(date != null)
			return FORMAT_DAY.format(date);
		return null;
	}
	
	public static String getDayTimeFormat(Date date){
		if(date != null)
			return FORMAT_DAY_TIME.format(date);
		return null;
	}
	
	/*
	 * 将字符串解析为yyyy-MM-dd的日期格式
	 */
	public static Date changeStringToDate(String str){
		if(str!=null){
			try {
				return FORMAT_DAY.parse(str);
			} catch (ParseException e) {
				System.out.println("解析日期出错！");
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/*
	 * 将字符串解析为yyyy-MM-dd HH:mm:ss的日期格式
	 */
	public static Date changeStringToDateTime(String str){
		if(str!=null){
			try {
				return FORMAT_DAY_TIME.parse(str);
			} catch (ParseException e) {
				System.out.println("解析日期出错！");
				e.printStackTrace();
			}
		}
		return null;
	}

	/*
	 * 通过yyyy-MM-dd 样式的字符串得到一个星期中的读几天
	 * 2,3,4,5,6,7,1
	 * 1,2,3,4,5,6,7
	 */
	@SuppressWarnings("static-access")
	public static Integer changeDateToDayType(String str) {
		Calendar c = Calendar.getInstance();
		Date date = changeStringToDate(str);
		c.setTime(date);
		Integer in = c.get(c.DAY_OF_WEEK);
		switch (in) {
		case 1:
			in = 7;
			break;
		case 2:
			in = 1;
			break;
		case 3:
			in = 2;
			break;
		case 4:
			in = 3;
			break;
		case 5:
			in = 4;
			break;
		case 6:
			in = 5;
			break;
		case 7:
			in = 6;
			break;
		}
		return in;
	}
	
	/*
	 * 通过yyyy-MM-dd HH:mm:ss样式的字符串得到一个星期中的读几天
	 */
	@SuppressWarnings("static-access")
	public static Integer convantDateToDayType(String str){
		Calendar c=Calendar.getInstance();
		Date date=changeStringToDateTime(str);
		c.setTime(date);
	    Integer in =c.get(c.DAY_OF_WEEK);
	    switch (in) {
		case 1:
			in = 7;
			break;
		case 2:
			in = 1;
			break;
		case 3:
			in = 2;
			break;
		case 4:
			in = 3;
			break;
		case 5:
			in = 4;
			break;
		case 6:
			in = 5;
			break;
		case 7:
			in = 6;
			break;
		}
		return in;
	}
	
	/** 
	* 获得指定日期的任意一天 
	* @param specifiedDay 
	* @return 
	*/ 
	public static String getSpecifiedDayAfter(String specifiedDay,int num) {
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		
		c.set(Calendar.DATE, day + num);

		String dayAfter = new SimpleDateFormat("yyyy-MM-dd")
				.format(c.getTime());
		return dayAfter;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 7; i++) {
			System.out.println(getSpecifiedDayAfter("2014-3-31",i));
		}
	}
}
