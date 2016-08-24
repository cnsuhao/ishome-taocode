package org.isotope.jfp.framework.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * 密码md5加密
 * 
 * @date 2012/11/14
 * @versions 1.0
 */
public class PasswordHelper {
	public static void main(String[] args) {
		System.out.println(getPassword("123456"));
	}

	/**
	 * MD5加盐
	 * 
	 * @param pass
	 * @return
	 */
	public static String getPassword(String pass) {
		String md5 = new Md5PasswordEncoder().encodePassword(pass, null);
		//System.out.println(md5);
		char[] ps = md5.toCharArray();
		ps[5] = pass.charAt(0);
		ps[10] = pass.charAt(1);
		ps[15] = pass.charAt(2);
		return new String(ps);
	}

}
