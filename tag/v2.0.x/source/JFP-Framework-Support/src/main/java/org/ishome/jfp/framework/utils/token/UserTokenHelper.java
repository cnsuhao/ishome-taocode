package org.ishome.jfp.framework.utils.token;

import org.ishome.jfp.framework.beands.LoginerBean;
import org.ishome.jfp.framework.beands.UserTokenBean;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.utils.DateHelper;
import org.ishome.jfp.framework.utils.EmptyHelper;
import org.ishome.jfp.framework.utils.PKHelper;


/**
 * Token数据算法
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class UserTokenHelper implements ISFrameworkConstants {
	public static void main(String[] args) {

		System.out.println(getUserToken("aaa", "bbb", "ccc"));
		System.out.println(getUserID(getUserToken("aaa", "bbb", "ccc")));
		System.out.println(getCompanyId(getUserToken("aaa", "bbb", "ccc")));
	}
	//--------------------------------------------------------------------------------------------------------------
	/**
	 * 获得 一个Token
	 * 
	 * @return tonkenString
	 */
	public static LoginerBean getLoginerBean(String userToken) {
		LoginerBean loginerBean = new LoginerBean();
		String[] ds = getUserTokenData(userToken);
		loginerBean.setUserName(ds[0]);
		loginerBean.setCompanyId(ds[1]);
		loginerBean.setSecurityCode(ds[2]);
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
	public static String getUserToken(LoginerBean loginerBean) {
		return getUserToken(loginerBean.getUserName(), loginerBean.getCompanyId(), loginerBean.getSecurityCode());
	}
	//--------------------------------------------------------------------------------------------------------------
	
	/**
	 * 获得一个安全Key
	 * 
	 * @return 全局唯一主键
	 */
	public synchronized static String getTokenKey(String PUK) {
		return getUserToken(PUK, PKHelper.creatPUKey());
	}

	public synchronized static String getPUKey(String tokenKey) {
		return getUserID(tokenKey);
	}

	/**
	 * 获得 一个Token
	 * 
	 * @return tonkenString
	 */
	public static UserTokenBean getUserTokenBean(String userToken) {
		UserTokenBean tokenBean = new UserTokenBean();		
		String[] ds = getUserTokenData(userToken);
		tokenBean.setUserId(ds[0]);
		tokenBean.setCompanyId(ds[1]);
		tokenBean.setLoginDateTime(ds[2]);
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
	public static String getUserToken(UserTokenBean userTokenBean) {
		return getUserToken(userTokenBean.getUserId(), userTokenBean.getCompanyId());
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
	public static String getUserToken(String userid, String companyid) {
		return getUserToken(userid, companyid, DateHelper.currentTimeMillis0());
	}

	/**
	 * 交叉混淆，可以正序或者倒序，可以奇数和偶数
	 * 
	 * @param userId
	 *            用户ID
	 * @param companyId
	 *            企业ID
	 * @return tonkenString
	 */
	public static String getUserToken(String userId, String companyId, String sessionId) {
		StringBuilder tonkenKey = new StringBuilder(54);
		int length = userId.length() > companyId.length() ? userId.length() : companyId.length();
		length = length > sessionId.length() ? length : sessionId.length();
		for (int i = 0; i < length; i++) {
			//第一段
			if (i < userId.length())
				tonkenKey.append(userId.charAt(i));
			else
				tonkenKey.append(DOWN_LINE);
			//第二段
			if (i < companyId.length())
				tonkenKey.append(companyId.charAt(i));
			else
				tonkenKey.append(DOWN_LINE);
			//第三段
			if (EmptyHelper.isEmpty(sessionId))
				continue;
			if (i < sessionId.length())
				tonkenKey.append(sessionId.charAt(i));
			else
				tonkenKey.append(DOWN_LINE);
		}
		return tonkenKey.toString();
	}

	/**
	 * 根据企业ID获得用户ID
	 * 
	 * @param userToken
	 * @return
	 */
	public static String[] getUserTokenData(String userToken) {
		// not login in
		if (EmptyHelper.isEmpty(userToken)) {
			return new String[] { "", "", "" };
		}
		StringBuilder userId = new StringBuilder(54);
		StringBuilder companyId = new StringBuilder(54);
		StringBuilder loginDateTimeId = new StringBuilder(54);
		for (int i = 0; i < userToken.length(); i++) {
			if (i % 3 == 0 && userToken.charAt(i) != DOWN_LINE2)
				userId.append(userToken.charAt(i));
			if (i % 3 == 1 && userToken.charAt(i) != DOWN_LINE2)
				companyId.append(userToken.charAt(i));
			if (i % 3 == 2 && userToken.charAt(i) != DOWN_LINE2)
				loginDateTimeId.append(userToken.charAt(i));
		}
		return new String[] { userId.toString(), companyId.toString(), loginDateTimeId.toString() };
	}

	/**
	 * 根据企业ID获得用户ID
	 * 
	 * @param userToken
	 * @return
	 */
	public static String getUserID(String userToken) {
		return getUserTokenData(userToken)[0];
	}

	/**
	 * 根据Token获得企业ID
	 * 
	 * @param userToken
	 * @return
	 */
	public static String getCompanyId(String userToken) {
		return getUserTokenData(userToken)[1];
	}

	/**
	 * 根据Token获得登录时间
	 * 
	 * @param userToken
	 * @return
	 */
	public static String getLoginDateTime(String userToken) {
		return getUserTokenData(userToken)[2];
	}

}
