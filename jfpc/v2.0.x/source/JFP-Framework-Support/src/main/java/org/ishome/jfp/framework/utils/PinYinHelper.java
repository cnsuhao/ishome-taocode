package org.ishome.jfp.framework.utils;
/**
 * 汉语拼音工具
 * 
 * @author Spook
 * @since 1.2.1
 * @version 1.1.0 2014/11/28
 * 
 */
public class PinYinHelper {

//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		System.out.println(getPinYinSample("获得汉字简拼"));
//		System.out.println(getPinYinFull("获得汉字简拼"));
//	}
//
//	/**
//	 * 获得汉字简拼
//	 * 
//	 * @param args
//	 */
//	public static String getPinYinSample(String str) {
//		return getPinYinSample(str, true);
//	}
//
//	/**
//	 * 获得汉字简拼
//	 * 
//	 * @param args
//	 */
//	public static String getPinYinSample(String str, boolean upCase) {
//		StringBuffer py = new StringBuffer();
//		String[] t = new String[10];
//		net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat t1 = new HanyuPinyinOutputFormat();
//		t1.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//		t1.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//		t1.setVCharType(HanyuPinyinVCharType.WITH_V);
//
//		try {
//			for (int i = 0; i < str.length(); i++) {
//				if ((str.charAt(i) >= 'a' && str.charAt(i) < 'z') || (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') || (str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
//					py.append(str.charAt(i));
//				} else {
//					t = PinyinHelper.toHanyuPinyinStringArray(str.charAt(i), t1);
//					py.append(t[0].charAt(0));
//				}
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		if (upCase)
//			return py.toString().toUpperCase();
//		else
//			return py.toString();
//	}
//
//	/**
//	 * 该方法返回一个字符串的拼音，对于要做敏感字 检查时应该一个字一个字来获取其拼音以免无法 得知每个字对应的拼音。
//	 * 
//	 * @param word
//	 * @return String
//	 */
//	public static String getPinYinFull(String str) {
//		return getPinYinFull(str, true);
//	}
//
//	/**
//	 * 该方法返回一个字符串的拼音，对于要做敏感字 检查时应该一个字一个字来获取其拼音以免无法 得知每个字对应的拼音。
//	 * 
//	 * @param word
//	 * @return String
//	 */
//	public static String getPinYinFull(String str, boolean upCase) {
//		StringBuffer py = new StringBuffer();
//		String[] t = new String[10];
//
//		net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat t1 = new HanyuPinyinOutputFormat();
//		t1.setCaseType(HanyuPinyinCaseType.LOWERCASE);
//		t1.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
//		t1.setVCharType(HanyuPinyinVCharType.WITH_V);
//
//		try {
//			for (int i = 0; i < str.length(); i++) {
//				if ((str.charAt(i) >= 'a' && str.charAt(i) < 'z') || (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') || (str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
//					py.append(str.charAt(i));
//				} else {
//					t = PinyinHelper.toHanyuPinyinStringArray(str.charAt(i), t1);
//					py.append(t[0]);
//				}
//			}
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//		if (upCase)
//			return py.toString().toUpperCase();
//		else
//			return py.toString();
//	}

}
