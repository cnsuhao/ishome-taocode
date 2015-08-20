package com.hundsun.med.framework.sms.utils;

import org.ishome.jfp.framework.constants.ISFrameworkConstants;


/**
 * 系统唯一识别ID
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class SNHelper implements ISFrameworkConstants {
	
	private static long systemTimeMillis = 0l;
	private static long synchronizedNum = 0l;
	
	private static StringBuilder primaryUniqueKey;

	public static void main(String[] args) {
		try {
			for (int i = 0; i < 10000; i++) {
				String puk = creatPUKey();
				System.out.println(puk);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static String machinecode = "1";
	/**
	 * 获得一个数据记录的主键Key<br>
	 * 时间+序号
	 * 
	 * @return 全局唯一主键
	 */
	public synchronized static String creatPUKey() {
		
		primaryUniqueKey = new StringBuilder(25);
		long currentTimeMillis = System.nanoTime();// currentTimeMillis
		// 同步判定
		if (currentTimeMillis > systemTimeMillis) {
			synchronizedNum = 0l;
			systemTimeMillis = currentTimeMillis;
		} else {
			synchronizedNum++;
		}
		primaryUniqueKey.append(machinecode);
		primaryUniqueKey.append(systemTimeMillis);
		primaryUniqueKey.append(synchronizedNum);
		return primaryUniqueKey.toString();
	}
}
