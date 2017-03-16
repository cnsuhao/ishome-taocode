package org.isotope.jfp.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;

/**
 * 系统唯一识别ID
 * 
 * @author Spook
 * @version 2.4.1 2015/11/10
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class PKHelper implements ISFrameworkConstants {
	public static void main(String[] args) {
		System.out.println(UUID.randomUUID());
		try {
			for (int i = 0; i < 100000; i++) {
				String puk = creatBarCodeKey();
				System.out.println("puk=="+puk+","+puk.length());
				//System.out.println(UUID.randomUUID());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static final long defaultNum = 10000l;
	
	private static long systemTimeMillis = defaultNum;
	private static long synchronizedNum = defaultNum;
	private static StringBuilder primaryUniqueKey;
	
	/**
	 * 获得一个PKID<br>
	 * 
	 * @return 全局唯一主键
	 */
	public synchronized static String creatPUKey() {
		return creatUniqueKey();
	}
	/**
	 * 系统时间戳
	 * 
	 * @return
	 */
	public static long currentTime(String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return Long.parseLong(format.format(new Date()));
	}
	/**
	 * 获得一个数据记录的主键Key<br>
	 * 时间+序号
	 * 
	 * @return 全局唯一主键
	 */
	public synchronized static String creatBarCodeKey() {
		//日期+（小时分钟）+流水号
		primaryUniqueKey = new StringBuilder(16);
		long currentTimeMillis = currentTime("yyMMddHHmmss");// currentTimeMillis
		// 同步判定
		if (currentTimeMillis > systemTimeMillis) {
			synchronizedNum = defaultNum;
			systemTimeMillis = currentTimeMillis;
		} else {
			synchronizedNum++;
		}
		primaryUniqueKey.append(systemTimeMillis);
//		primaryUniqueKey.append("_");
		primaryUniqueKey.append(synchronizedNum);
		return primaryUniqueKey.toString();
	}
	
	/**
	 * 获得一个数据记录的主键Key<br>
	 * 时间+序号
	 * 
	 * @return 全局唯一主键
	 */
	public synchronized static String creatUniqueKey() {
		primaryUniqueKey = new StringBuilder(25);
		long currentTimeMillis = System.nanoTime();// currentTimeMillis
		// 同步判定
		if (currentTimeMillis > systemTimeMillis) {
			synchronizedNum = defaultNum;
			systemTimeMillis = currentTimeMillis;
		} else {
			synchronizedNum++;
		}
		primaryUniqueKey.append(systemTimeMillis);
//		primaryUniqueKey.append("_");
		primaryUniqueKey.append(synchronizedNum);
		return primaryUniqueKey.toString();
	}
	
	/**
	 * 获得一个UUID<br>
	 * 
	 * @return 全局唯一主键
	 */
	public synchronized static String creatUUKey() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
