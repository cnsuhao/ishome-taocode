package org.isotopes.jfp.framework.common.util;

import org.isotopes.jfp.ISSystemConstants;

/**
 * 系统唯一识别ID
 * 
 * @author Spook
 * @since 0.1
 * @version 0.1
 */
public class PKUtil implements ISSystemConstants {

	public static void main(String[] args) {
		try {
			System.out.println("1".charAt(0));
			System.out.println("" + (10 % 2));
			System.out.println(getPUKey(getTokenKey("6534234df457ghdfg4543")));
			for (int i = 0; i < 1; i++)
				System.out.println(getPUKey());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 交叉混淆，可以正序或者倒序，可以奇数和偶数
	 * @param ss1
	 * @param ss2
	 * @return
	 */
	public static String getObscureString(String ss1, String ss2) {
		StringBuilder tonkenKey = new StringBuilder(36);
		int length = ss1.length() > ss2.length() ? ss1.length() : ss2.length();
		for (int i = 0; i < length; i++) {
			if (i < ss1.length())
				tonkenKey.append(ss1.charAt(i));
			else
				tonkenKey.append("%");
			if (i < ss2.length())
				tonkenKey.append(ss2.charAt(i));
			else
				tonkenKey.append("%");
		}
		return tonkenKey.toString();// username+"|"+ver;
	}

	public static String getObscureSs1(String obscureString) {
		// not login in
		if (obscureString == null) {
			return null;
		}
		StringBuilder ss1 = new StringBuilder(36);
		for (int i = 0; i < obscureString.length(); i++) {
			if (i % 2 == 0 && obscureString.charAt(i) != '%')
				ss1.append(obscureString.charAt(i));
		}
		return ss1.toString();
	}

	public static String getObscureSs2(String obscureString) {
		// not login in
		if (obscureString == null) {
			return null;
		}
		StringBuilder ss2 = new StringBuilder(36);
		for (int i = 0; i < obscureString.length(); i++) {
			if (i % 2 == 1 && obscureString.charAt(i) != '%')
				ss2.append(obscureString.charAt(i));
		}
		return ss2.toString();
	}
	
	
	
	/**
	 * 获得一个安全Key<p>
	 * 交叉混淆，可以正序或者倒序，可以奇数和偶数
	 * @return 全局唯一主键
	 */
	public synchronized static String getTokenKey(String UID) {
		return getObscureString(UID,"" + System.currentTimeMillis());
	}

	public synchronized static String getPUKey(String tokenKey) {
		return getObscureSs1(tokenKey);
	}

	private static long systemTimeMillis = 0l;
	private static long synchronizedNum = 0l;
	private static StringBuilder primaryUniqueKey;

	/**
	 * 还原db数据信息
	 * 
	 * @param dbKey
	 * @return #1 数据产生时间起点，#2数据产生序列号
	 */
	public static String[] splitDbkey(String dbKey) {
		return dbKey.replaceFirst(DBKEY_SPLIT, BASE_SPLIT).split(BASE_SPLIT);
	}

	/**
	 * 获得一个数据记录的主键Key
	 * 
	 * @return 全局唯一主键
	 */
	public synchronized static String getPUKey() {
		primaryUniqueKey = new StringBuilder(25);
		long currentTimeMillis = System.currentTimeMillis();
		// 同步判定
		if (currentTimeMillis > systemTimeMillis) {
			synchronizedNum = 0l;
			systemTimeMillis = currentTimeMillis;
		} else {
			synchronizedNum++;
		}
		primaryUniqueKey.append(systemTimeMillis).append(DBKEY_SPLIT).append(synchronizedNum);
		return primaryUniqueKey.toString();
	}
	
	/**
	 * 获得一个数据记录的主键Key
	 * 
	 * @return 全局唯一主键
	 */
	public synchronized static String getNumber() {
		primaryUniqueKey = new StringBuilder(25);
		long currentTimeMillis = System.currentTimeMillis();
		// 同步判定
		if (currentTimeMillis > systemTimeMillis) {
			synchronizedNum = 0l;
			systemTimeMillis = currentTimeMillis;
		} else {
			synchronizedNum++;
		}
		primaryUniqueKey.append(systemTimeMillis).append(synchronizedNum);
		return primaryUniqueKey.toString();
	}
}
