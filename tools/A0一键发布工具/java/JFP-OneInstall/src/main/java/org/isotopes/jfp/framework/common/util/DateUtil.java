package org.isotopes.jfp.framework.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式化
 * 
 * @author Spook
 * @since 0.1
 * @version 0.1
 */
public class DateUtil {
	
	/**
	 * 系统时间戳
	 * 
	 * @return
	 */
	public static String currentTimestamp() {
		return ""+System.currentTimeMillis();
	}
	
	/**
	 * yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String currentTimeMillis3() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(new Date());
	}

	/**
	 * yyMMddHHmmss
	 * 
	 * @return
	 */
	public static String currentTimeMillis4() {
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		return format.format(new Date());
	}

	/**
	 * yy/MM/dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String currentTimeMillis2() {
		SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		return format.format(new Date());
	}

	/**
	 * yyyy/MM/dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String currentTimeMillis1() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return format.format(new Date());
	}

	/**
	 * yyMMdd
	 * 
	 * @return
	 */
	public static String currentDate1() {
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		return format.format(new Date());
	}

	/**
	 * yy/MM/dd
	 * 
	 * @return
	 */
	public static String currentDate2() {
		SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd");
		return format.format(new Date());
	}

	/**
	 * HHmmss
	 * 
	 * @return
	 */
	public static String currentTime1() {
		SimpleDateFormat format = new SimpleDateFormat("HHmmss");
		return format.format(new Date());
	}

	/**
	 * HH:mm:ss
	 * 
	 * @return
	 */
	public static String currentTime2() {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
		return format.format(new Date());
	}
}
