package org.isotope.jfp.framework.utils;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/** 
 * 图片压缩工具类 提供的方法中可以设定生成的 缩略图片的大小尺寸、压缩尺寸的比例、图片的质量等 
 *  
 * @date 2012/11/14 
 * @versions 1.0 
 */  
public class PasswordHelper {

	/**
	 * @param args
	 * @return 
	 */
	public static String getPassword(String pass) {
		return new Md5PasswordEncoder().encodePassword(pass, null);
	}

}
