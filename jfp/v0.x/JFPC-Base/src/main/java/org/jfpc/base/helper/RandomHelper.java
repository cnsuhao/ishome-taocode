package org.jfpc.base.helper;

import java.util.Random;

/**
 * 随机数验证码
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class RandomHelper {
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++)
			// nextDouble
			System.out.println(strCompletion(20,"0",nextCode(12)).length());
	}
	
	/**
	 * 获取随机字符串(数字加字符)
	 * 
	 * @param length 字符串长度
	 * @return
	 */
	public static String getRandomString(int length) {
		StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for (int i = 0; i < length; i++) {
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}
	
	/**
	 * 根据长度获得随机数字
	 * 
	 * @param length
	 * @return
	 */
	public static String nextCode(int length) {
		Random rd = new Random();
		String code = "" + rd.nextDouble();
		// System.out.print(code+"=");
		if (code.length() <= length + 2)
			return code.substring(2);
		return code.substring(2, length + 2);
	}

	/**
	 * 数据补全
	 * @param length
	 * @param use
	 * @param had
	 * @return
	 */
	public static String strCompletion(int length, String use, String had) {
		StringBuilder sb = new StringBuilder();
		sb.append(had);
		while (sb.length() < length) {
			sb.insert(0, use);
		}

		return sb.toString();
	}

}
