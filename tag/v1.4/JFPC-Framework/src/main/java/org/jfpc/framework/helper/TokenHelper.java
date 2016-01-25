package org.jfpc.framework.helper;

import org.apache.commons.lang.StringUtils;
import org.jfpc.constants.ISFrameworkConstants;
import org.jfpc.framework.bean.LoginerBean;
import org.jfpc.framework.bean.TokenBean;

/**
 * Token数据算法
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class TokenHelper implements ISFrameworkConstants{

	/**
	 * 获得一个安全Key
	 * 
	 * @return 全局唯一主键
	 */
	public synchronized static String getTokenKey(String PUK) {
		return getToken(PUK, PKHelper.creatPUKey());
	}

	public synchronized static String getPUKey(String tokenKey) {
		return getUserID(tokenKey);
	}

	/**
	 * 获得 一个Token
	 * 
	 * @return tonkenString
	 */
	public static TokenBean getTokenBean(String token) {
		TokenBean tokenBean = new TokenBean();
		tokenBean.setUserId(getUserID(token));
		tokenBean.setCompanyId(getCompanyId(token));
		tokenBean.setLoginDateTime(getLoginDateTime(token));
		return tokenBean;
	}

	/**
	 * 交叉混淆，可以正序或者倒序，可以奇数和偶数
	 * 
	 * @param userid
	 *            用户ID
	 * @param companyid
	 *            企业ID
	 * @return tonkenString
	 */
	public static String getToken(TokenBean tokenBean) {
		return getToken(tokenBean.getUserId(), tokenBean.getCompanyId());
	}
	
	/**
	 * 获得 一个Token
	 * 
	 * @return tonkenString
	 */
	public static LoginerBean getLoginerBean(String token) {
		LoginerBean loginerBean = new LoginerBean();
		loginerBean.setUserName(getUserID(token));
		loginerBean.setCompanyId(getCompanyId(token));
		loginerBean.setSecurityCode(getLoginDateTime(token));
		return loginerBean;
	}
	
	/**
	 * 交叉混淆，可以正序或者倒序，可以奇数和偶数
	 * 
	 * @param userid
	 *            用户ID
	 * @param companyid
	 *            企业ID
	 * @return tonkenString
	 */
	public static String getToken(LoginerBean loginerBean) {
		return getToken(loginerBean.getUserName(), loginerBean.getCompanyId(),loginerBean.getSecurityCode());
	}

	/**
	 * 交叉混淆，可以正序或者倒序，可以奇数和偶数
	 * 
	 * @param userid
	 *            用户ID
	 * @param companyid
	 *            企业ID
	 * @return tonkenString
	 */
	public static String getToken(String userid, String companyid) {
		String curDate = DateHelper.currentTimeMillis0();
		StringBuilder tonkenKey = new StringBuilder(54);
		int length = userid.length() > companyid.length() ? userid.length() : companyid.length();
		length = length > curDate.length() ? length : curDate.length();
		for (int i = 0; i < length; i++) {
			if (i < userid.length())
				tonkenKey.append(userid.charAt(i));
			else
				tonkenKey.append(DOWN_LINE);

			if (i < companyid.length())
				tonkenKey.append(companyid.charAt(i));
			else
				tonkenKey.append(DOWN_LINE);

			if (i < curDate.length())
				tonkenKey.append(curDate.charAt(i));
			else
				tonkenKey.append(DOWN_LINE);
		}
		return tonkenKey.toString();
	}
	public static void main(String[] args) {
		
			System.out.println(getToken("aaa","bbb","ccc"));
			System.out.println(getUserID(getToken("aaa","bbb","ccc")));
			System.out.println(getCompanyId(getToken("aaa","bbb","ccc")));
	}
	/**
	 * 交叉混淆，可以正序或者倒序，可以奇数和偶数
	 * 
	 * @param userid
	 *            用户ID
	 * @param companyid
	 *            企业ID
	 * @return tonkenString
	 */
	public static String getToken(String userid, String companyid,String sessionid) {
		StringBuilder tonkenKey = new StringBuilder(54);
		int length = userid.length() > companyid.length() ? userid.length() : companyid.length();
		length = length > sessionid.length() ? length : sessionid.length();
		for (int i = 0; i < length; i++) {
			if (i < userid.length())
				tonkenKey.append(userid.charAt(i));
			else
				tonkenKey.append(DOWN_LINE);

			if (i < companyid.length())
				tonkenKey.append(companyid.charAt(i));
			else
				tonkenKey.append(DOWN_LINE);
			if(StringUtils.isEmpty(sessionid))
				continue;
			if (i < sessionid.length())
				tonkenKey.append(sessionid.charAt(i));
			else
				tonkenKey.append(DOWN_LINE);
		}
		return tonkenKey.toString();
	}
	
	/**
	 * 根据企业ID获得用户ID
	 * 
	 * @param token
	 * @return
	 */
	public static String getUserID(String token) {
		// not login in
		if (StringUtils.isEmpty(token)) {
			return StringUtils.EMPTY;
		}
		StringBuilder userId = new StringBuilder(54);
		for (int i = 0; i < token.length(); i++) {
			if (i % 3 == 0 && token.charAt(i) != DOWN_LINE2)
				userId.append(token.charAt(i));
		}
		return userId.toString();
	}

	/**
	 * 根据Token获得企业ID
	 * 
	 * @param token
	 * @return
	 */
	public static String getCompanyId(String token) {
		// not login in
		if (StringUtils.isEmpty(token)) {
			return StringUtils.EMPTY;
		}
		StringBuilder productId = new StringBuilder(36);
		for (int i = 0; i < token.length(); i++) {
			if (i % 3 == 1 && token.charAt(i) != DOWN_LINE2)
				productId.append(token.charAt(i));
		}
		return productId.toString();
	}

	/**
	 * 根据Token获得登录时间
	 * 
	 * @param token
	 * @return
	 */
	public static String getLoginDateTime(String token) {
		// not login in
		if (token == null) {
			return null;
		}
		StringBuilder loginDateTime = new StringBuilder(36);
		for (int i = 0; i < token.length(); i++) {
			if (i % 3 == 2 && token.charAt(i) != DOWN_LINE2)
				loginDateTime.append(token.charAt(i));
		}
		return loginDateTime.toString();
	}

}
