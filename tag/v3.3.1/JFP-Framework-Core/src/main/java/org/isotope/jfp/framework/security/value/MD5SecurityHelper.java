package org.isotope.jfp.framework.security.value;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * 加密解密工具类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 3.2.1 2016/8/9
 * @version 0.1.0 2014/2/8
 */
public class MD5SecurityHelper {

	/**
	 * 加密
	 * 
	 * @param plainText
	 *            需加密内容
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String plainText) throws Exception {
		return new Md5PasswordEncoder().encodePassword(plainText, null);
//		return PasswordHelper.getPassword(plainText);
	}

}
