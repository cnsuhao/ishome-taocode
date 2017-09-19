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
			for (int i = 0; i < 10; i++) {
				String puk = creatPUKey();
				System.out.println("puk=="+puk+","+puk.length());
//				System.out.println(System.currentTimeMillis());
//				System.out.println(currentTime("yyMMddHHmmss")+","+(""+currentTime("yyMMddHHmmss")).length());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static int serverId = 10;

	public static int getServerId() {
		return serverId;
	}
	public static void setServerId(int serverId) {
		PKHelper.serverId = serverId;
	}
	private static final long defaultNum = 100l;
	
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

	private static long systemTimeMillis = defaultNum;
	/**
	 * 获得一个数据记录的主键Key<br>
	 * 时间+序号
	 * 
	 * @return 全局唯一主键
	 */
	public synchronized static String creatTimeKey() {
		//日期+（小时分钟）+流水号
		primaryUniqueKey = new StringBuilder(18);
		long currentTimeMillis = currentTime("yyMMddHHmmss");// currentTimeMillis
		// 同步判定
		if (currentTimeMillis > systemTimeMillis) {
			synchronizedNum = defaultNum;
			systemTimeMillis = currentTimeMillis;
		} else {
			synchronizedNum++;
		}
		primaryUniqueKey.append(systemTimeMillis);
		primaryUniqueKey.append(synchronizedNum);
		primaryUniqueKey.append(serverId);
		return primaryUniqueKey.toString();
	}

	private static long systemUniqueMillis = defaultNum;
	/**
	 * 获得一个数据记录的主键Key<br>
	 * 时间+序号
	 * 
	 * @return 全局唯一主键
	 */
	public synchronized static String creatUniqueKey() {
		primaryUniqueKey = new StringBuilder(25);
		long currentTimeMillis = System.currentTimeMillis();// currentTimeMillis
		// 同步判定
		if (currentTimeMillis > systemUniqueMillis) {
			synchronizedNum = defaultNum;
			systemUniqueMillis = currentTimeMillis;
		} else {
			synchronizedNum++;
		}
		primaryUniqueKey.append(systemUniqueMillis);
		primaryUniqueKey.append(synchronizedNum);
		primaryUniqueKey.append(serverId);
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
