package org.isotope.jfp.framework.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;

/**
 * 
 * 类描述：时间处理工具类
 * 
 * @version: 1.0
 * @version: 2015年7月16日 上午10:59:58
 */
public class DateUtils {

	/**
	 * yyyy-MM-dd
	 */
	public static String FORMAT_yyyy_MM_dd = "yyyy-MM-dd";

	/**
	 * yyyy-MM-dd
	 */
	public static String FORMAT_yyyy_MM_dd_HH_mm_ss_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

	public static String FORMAT_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

	/**
	 * yyyy/MM/dd HH:mm:ss
	 */
	public static String FORMAT_yyyyMMdd_HH_mm_ss_ = "yyyy/MM/dd HH:mm:ss";

	/**
	 * yyyy/MM/dd
	 */
	public static String FORMAT_yyyyMMdd_ = "yyyy/MM/dd";

	public static String FORMAT_yyyyMMddHHmmss = "yyyyMMddHHmmss";
	public static String FORMAT_yyyyMMdd = "yyyyMMdd";
	public static String REG_yyyyMMddHHmmss = "^\\d{14}$";
	public static String REG_yyyyMMdd = "^\\d{8}$";
	public static String REG_yyyy_MM_dd_HH_mm_ss = "^\\d{4}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D+\\d{1,2}\\D*$";

	public static String REG_yyyy_MM_dd = "^[0-9]{4}-[0-9]{2}-[0-9]{2}$";

	public static void main(String[] args) throws ParseException {

		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat(
				"yyyy/MM/dd HH:MM:ss");

		java.util.Date date = format.parse("2016/09/01 00:00:00");

		Timestamp tm = new Timestamp(date.getTime());
		System.out.println(tm);
		// 强转

	}

	/**
	 * 
	 * 方法描述：格式化日期
	 * 
	 * @param
	 * @return:
	 * @version: 1.0
	 * @version: 2015年7月16日 上午11:00:12
	 */
	public static String formatDate(Date date, String format) {
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(date);
	}

	/**
	 * 
	 * 方法描述：格式化为日期
	 * 
	 * @param:
	 * @return:
	 * @version: 1.0
	 * @version: 2015年12月2日 下午3:32:33
	 */
	public static Date parseDate(String date) {

		try {
			String format = FORMAT_yyyyMMdd_HH_mm_ss_;
			if (date.length() == 10) 
				format = FORMAT_yyyyMMdd_;

			SimpleDateFormat sf = new SimpleDateFormat(format);
			java.util.Date udate = sf.parse(date);

			return new Date(udate.getTime());
		} catch (ParseException e) {
			// e.printStackTrace();
			return null;
		}
	}


}
