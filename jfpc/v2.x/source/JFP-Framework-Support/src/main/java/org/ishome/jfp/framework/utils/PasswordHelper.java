package org.ishome.jfp.framework.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
/** 
 * 密码转换工具
 * @author Spook
 * @since 1.2.0
 * @version 1.2.0 2014/12/17
 */
public class PasswordHelper {

	/**
	 * @param args
	 * @return 
	 */
	public static String getPassword(String pass) {
		return new Md5PasswordEncoder().encodePassword(pass, null);
	}
	/**
	 * @param args
	 * @return 
	 */
	public static String getPassword(String userId, String pass) {
		return new Md5PasswordEncoder().encodePassword(userId + pass, null);
	}
}
