package org.isotope.fpm.common.util;

import org.isotope.fpm.common.bean.TokenBean;

/**
 * Token数据混淆算法
 * @author Spook
 * @version 1.0
 * @since 3.0
 */
public class TokenUtil {
	public static TokenBean checkToken(String tonkenString) {
		TokenBean tb = new TokenBean();
		
		
		return tb;
	}
	/**
	 * 交叉混淆，可以正序或者倒序，可以奇数和偶数
	 * 
	 * @param userid
	 * @param customid
	 * @return tonkenString
	 */
	public static String getToken(String userid, String customid) {
		String curDate=DateUtil.currentTimeMillis0();
		StringBuilder tonkenKey = new StringBuilder(36);
		int length = userid.length() > customid.length() ? userid.length() : customid.length();
		length = length > curDate.length() ? length : curDate.length();
		for (int i = 0; i < length; i++) {
			if (i < userid.length())
				tonkenKey.append(userid.charAt(i));
			else
				tonkenKey.append(",");
			if (i < customid.length())
				tonkenKey.append(customid.charAt(i));
			else
				tonkenKey.append(",");
			if (i < curDate.length())
					tonkenKey.append(curDate.charAt(i));
				else
					tonkenKey.append(",");
		}
		return tonkenKey.toString();// username+"|"+ver;
	}

	public static String getUserID(String token) {
		// not login in
		if (token == null) {
			return null;
		}
		StringBuilder UID = new StringBuilder(36);
		for (int i = 0; i < token.length(); i++) {
			if (i % 2 == 0 && token.charAt(i) != ',')
				UID.append(token.charAt(i));
		}
		return UID.toString();
	}

	public static String getVersion(String token) {
		// not login in
		if (token == null) {
			return null;
		}
		StringBuilder VER = new StringBuilder(36);
		for (int i = 0; i < token.length(); i++) {
			if (i % 2 == 1 && token.charAt(i) != ',')
				VER.append(token.charAt(i));
		}
		return VER.toString();
	}

}
