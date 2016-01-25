package com.ttnn.common.util;

import com.ttnn.common.base.bean.TokenBean;

/**
 * Token数据混淆算法
 * @author Spook
 * @version 1.0
 * @since 1.0
 */
public class TokenUtil {
	
	/**
	 * 交叉混淆，可以正序或者倒序，可以奇数和偶数
	 * 
	 * @param userid 用户ID
	 * @param customid 产品ID
	 * @return tonkenString
	 */
	public static String getToken(TokenBean tokenBean) {
		return getToken(tokenBean.getUserId(),tokenBean.getProductId());
	}
	
	/**
	 * 交叉混淆，可以正序或者倒序，可以奇数和偶数
	 * 
	 * @param userid 用户ID
	 * @param customid 产品ID
	 * @return tonkenString
	 */
	public static String getToken(String userid, String customid) {
		String curDate=DateUtil.currentTimeMillis0();
		StringBuilder tonkenKey = new StringBuilder(54);
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

	/**
	 * 根据产品ID获得用户ID
	 * @param token
	 * @return
	 */
	public static String getUserID(String token) {
		// not login in
		if (token == null) {
			return null;
		}
		StringBuilder UID = new StringBuilder(54);
		for (int i = 0; i < token.length(); i++) {
			if (i % 3 == 0 && token.charAt(i) != ',')
				UID.append(token.charAt(i));
		}
		return UID.toString();
	}

	/**
	 * 根据Token获得产品ID
	 * @param token
	 * @return
	 */
	public static String getProductId(String token) {
		// not login in
		if (token == null) {
			return null;
		}
		StringBuilder VER = new StringBuilder(36);
		for (int i = 0; i < token.length(); i++) {
			if (i % 3 == 1 && token.charAt(i) != ',')
				VER.append(token.charAt(i));
		}
		return VER.toString();
	}

}
