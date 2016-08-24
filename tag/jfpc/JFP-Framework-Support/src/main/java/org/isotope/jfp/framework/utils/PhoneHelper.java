package org.isotope.jfp.framework.utils;

/**
 * 手机号码隐藏转换方法
 * 
 * @author Spook
 * @since 0.2.1
 * @version 0.1.0 2014/11/28
 * 
 */
public class PhoneHelper {

	/**
	 * @param args
	 */
	public static String getProtectedPhoneNumber(String phone) {
		if(phone.length()!=11)
			return phone;
		StringBuffer s = new StringBuffer();
		s.append(phone.charAt(0));
		s.append(phone.charAt(1));
		s.append(phone.charAt(2));
		s.append("*");//phone.charAt(3)
		s.append("*");//phone.charAt(4)
		s.append("*");//phone.charAt(5)
		s.append("*");//phone.charAt(6)
		s.append(phone.charAt(7));
		s.append(phone.charAt(8));
		s.append(phone.charAt(9));
		s.append(phone.charAt(10));
		return s.toString();
	}

}
