package org.ishome.jfp.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式化
 * 
 * @author Spook
 * @since 0.1.0 
 * @version 0.1.0 2014/2/8
 */
public class DateHelper {
	
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
	public static String currentTimeMillis0() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		return format.format(new Date());
	}

	/**
	 * yyMMddHHmmss
	 * 
	 * @return
	 */
	public static String currentTimeMillis1() {
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		return format.format(new Date());
	}

	/**
	 * yyyy/MM/dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String currentTimeMillis2() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return format.format(new Date());
	}

	/**
	 * yy/MM/dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String currentTimeMillis3() {
		SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
		return format.format(new Date());
	}

	/**
	 * yyyy/MM/dd HH:mm:ss.sss
	 * 
	 * @return
	 */
	public static String currentTimeMillis4() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.sss");
		return format.format(new Date());
	}
	/**
	 * yy/MM/dd HH:mm:ss.sss
	 * 
	 * @return
	 */
	public static String currentTimeMillis5() {
		SimpleDateFormat format = new SimpleDateFormat("yy/MM/dd HH:mm:ss.sss");
		return format.format(new Date());
	}
	
	/**
	 * yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String currentTimeMillisCN1() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}
	
	/**
	 * yy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String currentTimeMillisCN2() {
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		return format.format(new Date());
	}
	/**
	 * yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String currentTimeMillisCN3() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(new Date());
	}
	/**
	 * yy-MM-dd
	 * 
	 * @return
	 */
	public static String currentTimeMillisCN4() {
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
		return format.format(new Date());
	}
	/**
	 * yyyy-MM-dd HH:mm:ss.sss
	 * 
	 * @return
	 */
	public static String currentTimeMillisCN5() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.sss");
		return format.format(new Date());
	}
	/**
	 * yy-MM-dd HH:mm:ss.sss
	 * 
	 * @return
	 */
	public static String currentTimeMillisCN6() {
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd HH:mm:ss.sss");
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
	 * yyyyMMdd
	 * 
	 * @return
	 */
	public static String currentDate3() {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return format.format(new Date());
	}

	/**
	 * yyyy/MM/dd
	 * 
	 * @return
	 */
	public static String currentDate4() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
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
	
	public static void main(String[] args) {
		System.out.print(currentTimeMillis4());
	}
}
