package org.isotope.jfp.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WeekHelper {
	// ------------------------------
	// 类名称:isWeekSame
	// 包含方法:1.isSameDate
	// 2.main
	// 作者:lzueclipse
	// 时间:2005-11-13
	// ------------------------------
	// ----------------------------
	// 方法名称：isSameDate(String date1,String date2)
	// 功能描述：判断date1和date2是否在同一周
	// 输入参数：date1,date2
	// 输出参数：
	// 返 回 值：false 或 true
	// 其它说明：主要用到Calendar类中的一些方法
	// -----------------------------
	public static boolean isSameDate(String date1, String date2) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date d1 = null;
			Date d2 = null;
			d1 = format.parse(date1);
			d2 = format.parse(date2);
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(d1);
			cal2.setTime(d2);
			int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
			// subYear==0,说明是同一年
			if (subYear == 0) {
				if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
					return true;
			}
			// 例子:cal1是"2005-1-1"，cal2是"2004-12-25"
			// java对"2004-12-25"处理成第52周
			// "2004-12-26"它处理成了第1周，和"2005-1-1"相同了
			// 大家可以查一下自己的日历
			// 处理的比较好
			// 说明:java的一月用"0"标识，那么12月用"11"
			else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) {
				if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
					return true;
			}
			// 例子:cal1是"2004-12-31"，cal2是"2005-1-1"
			else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11) {
				if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
					return true;

			}
		} catch (Exception e) {
		}
		return false;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 测试1
		boolean a = isSameDate("2005-1-1", "2005-1-3");
		if (a) {
			System.out.println("2005-1-1和2005-1-3是同一周！");
		} else {
			System.out.println("2005-1-1和2005-1-3不是同一周！");
		}

		// 测试2
		boolean b = isSameDate("2005-1-1", "2004-12-25");
		if (b) {
			System.out.println("2005-1-1和2004-12-25是同一周！");
		} else {
			System.out.println("2005-1-1和2004-12-25不是同一周！");
		}

		boolean c = isSameDate("2004-12-25", "2005-1-1");
		if (c) {
			System.out.println("2004-12-25和2005-1-1是同一周！");
		} else {
			System.out.println("2004-12-25和2005-1-1不是同一周！");
		}

		// 测试3
		boolean d = isSameDate("2005-1-1", "2004-12-26");
		if (d) {
			System.out.println("2005-1-1和2004-12-26是同一周！");
		} else {
			System.out.println("2005-1-1和2004-12-26不是同一周！");
		}

		boolean e = isSameDate("2004-12-26", "2005-1-1");
		if (e) {
			System.out.println("2004-12-26和2005-1-1是同一周！");
		} else {
			System.out.println("2004-12-26和2005-1-1不是同一周！");
		}

	}
}
