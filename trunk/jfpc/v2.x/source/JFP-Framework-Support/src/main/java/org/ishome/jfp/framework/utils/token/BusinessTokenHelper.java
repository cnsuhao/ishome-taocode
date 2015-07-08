package org.ishome.jfp.framework.utils.token;

import org.ishome.jfp.framework.beands.BusinessTokenBean;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.utils.EmptyHelper;


/**
 * Token数据算法
 * 
 * @author Spook
 * @since 2.3.1
 * @version 2.3.1 2015/6/23
 */
public class BusinessTokenHelper implements ISFrameworkConstants {
	//
	///{hosId}															18
	//--------/{userId}													18
	//-----------------/{bizName}										8
	//---------------------------/{encryType}							1
	//---------------------------------------/{clientTimestamp}			8 (MMDDH24)
	
	/**
	 * 获得 一个Token
	 * 
	 * @return tonkenString(企业ID+用户ID+[业务标识+加密模式+请求时间])
	 */
	public static BusinessTokenBean getBizTokenBean(String bizToken) {
		BusinessTokenBean tokenBean = new BusinessTokenBean();	
		String[] ds = getBizTokenData(bizToken);
		tokenBean.setUserId(ds[0]);
		tokenBean.setCompanyId(ds[1]);
		tokenBean.setBizName(ds[2]);
		tokenBean.setEncryType(ds[3]);
		tokenBean.setRequestDateTime(ds[4]);
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
	public static String getBizToken(BusinessTokenBean bizTokenBean) {
		return getBizToken(bizTokenBean.getUserId(), 
				bizTokenBean.getCompanyId(),
				bizTokenBean.getBizName()+bizTokenBean.getEncryType()+bizTokenBean.getRequestDateTime());
	}
	
	public static void main(String[] args) {

		System.out.println(getBizTokenBean("ab1ab2ab3__4__5__6__7__8__9__a__b__c__d__e__f__g"));
		System.out.println(getBizToken("aaa", "bbb", "123456789abcdefg"));
		System.out.println(getUserID(getBizToken("aaa", "bbb", "123456789abcdefg")));
		System.out.println(getCompanyId(getBizToken("aaa", "bbb", "123456789abcdefg")));
		System.out.println(getBizId(getBizToken("aaa", "bbb", "123456789abcdefg")));
		System.out.println(getEncryType(getBizToken("aaa", "bbb", "123456789abcdefg")));
		System.out.println(getLoginDateTime(getBizToken("aaa", "bbb", "123456789abcdefg")));
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
	public static String getBizToken(String userid, String companyid, String sessionid) {
		StringBuilder tonkenKey = new StringBuilder(54);
		int length = userid.length() > companyid.length() ? userid.length() : companyid.length();
		length = length > sessionid.length() ? length : sessionid.length();
		for (int i = 0; i < length; i++) {
			//第一段
			if (i < userid.length())
				tonkenKey.append(userid.charAt(i));
			else
				tonkenKey.append(DOWN_LINE);
			//第二段
			if (i < companyid.length())
				tonkenKey.append(companyid.charAt(i));
			else
				tonkenKey.append(DOWN_LINE);
			//第三段
			if (EmptyHelper.isEmpty(sessionid))
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
	 * @param userToken
	 * @return
	 */
	public static String[] getBizTokenData(String userToken) {
		// not login in
		if (EmptyHelper.isEmpty(userToken)) {
			return new String[] { "", "", "", "", "" };
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
		String t = loginDateTimeId.toString();
		String bizName = t.substring(0, 8);
		String encryType = t.substring(8, 9);
		String rRequestDateTime = t.substring(9);
		return new String[] { userId.toString(), companyId.toString(), bizName, encryType, rRequestDateTime};
	}

	/**
	 * 根据企业ID获得用户ID
	 * 
	 * @param userToken
	 * @return
	 */
	public static String getUserID(String userToken) {
		return getBizTokenData(userToken)[0];
	}

	/**
	 * 根据Token获得企业ID
	 * 
	 * @param userToken
	 * @return
	 */
	public static String getCompanyId(String userToken) {
		return getBizTokenData(userToken)[1];
	}

	/**
	 * 根据Token获得登录时间
	 * 
	 * @param userToken
	 * @return
	 */
	public static String getBizId(String userToken) {
		return getBizTokenData(userToken)[2];
	}
	/**
	 * 根据Token获得登录时间
	 * 
	 * @param userToken
	 * @return
	 */
	public static String getEncryType(String userToken) {
		return getBizTokenData(userToken)[3];
	}
	/**
	 * 根据Token获得登录时间
	 * 
	 * @param userToken
	 * @return
	 */
	protected static String getLoginDateTime(String userToken) {
		return getBizTokenData(userToken)[4];
	}

}
