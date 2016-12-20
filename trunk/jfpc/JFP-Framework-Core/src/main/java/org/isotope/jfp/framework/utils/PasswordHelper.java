package org.isotope.jfp.framework.utils;

import org.isotope.jfp.framework.security.value.MD5SecurityHelper;

/**
 * 用户密码混淆验证
 * 
 * @author Spook
 * @version 4.1.1 2016/12/20
 * @version 2.0.1 2012/11/14
 * @since 2.0.1 2012/11/14
 */
public class PasswordHelper {
	public static void main(String[] args) throws Exception {
		String pass = "abc@123";
		String md5 = MD5SecurityHelper.encrypt(pass);
		System.out.println(md5);
		String pas = getPassword(pass);
		System.out.println(pas);
		System.out.println(pas.length());
	}

	/**
	 * MD5加盐
	 * 
	 * @param pass
	 * @return
	 * @throws Exception
	 */
	public static String getPassword(String pass) throws Exception {
		if (pass.length() < 3)
			throw new Exception("密码太短，请输入大于三位密码");
		String md5 = MD5SecurityHelper.encrypt(pass);
		char[] ps = md5.toCharArray();
		for (int i = 0; i < ps.length; i = i + 2) {
			if (i > ps.length - 3)
				break;
			char a1 = ps[i];
			char a2 = ps[i + 1];
			ps[i] = a2;
			ps[i + 1] = a1;
		}
		ps[0] = md5.charAt(5);
		ps[1] = md5.charAt(10);
		ps[2] = md5.charAt(15);
		ps[ps.length - 10] = pass.charAt(0);
		ps[ps.length - 5] = pass.charAt(1);
		ps[ps.length - 1] = pass.charAt(2);
		return new String(ps);
	}

}
