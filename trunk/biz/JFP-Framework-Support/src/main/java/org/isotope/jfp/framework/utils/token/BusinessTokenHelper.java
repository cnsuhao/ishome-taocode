package org.isotope.jfp.framework.utils.token;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;

/**
 * 业务请求Token数据算法
 * 
 * @author Spook
 * @since 2.3.1
 * @version 3.2.1 2018/8/8
 * @version 2.3.1 2015/6/23
 * @see BusinessTokenBean
 */
public class BusinessTokenHelper implements ISFrameworkConstants {

	public static void main(String[] args) {
		System.out.println(getBizTokenData("987654321", "123456789", "abcdefghi"));
		System.out.println(getBizTokenData(getBizTokenData("987654321", "123456789", "abcdefghi"))[0]);
		System.out.println(getBizTokenData(getBizTokenData("987654321", "123456789", "abcdefghi"))[1]);
		System.out.println(getBizTokenData(getBizTokenData("987654321", "123456789", "abcdefghi"))[2]);
	}

	/**
	 * 交叉混淆，可以正序或者倒序，可以奇数和偶数
	 * 
	 * @param userid
	 *            用户ID
	 * @param companyid
	 *            企业ID
	 * @param logintime
	 *            混淆数据穿
	 * @return tonkenString
	 */
	public static String getBizTokenData(String companyid, String userid, String logintime) {
		StringBuilder tonkenKey = new StringBuilder(54);
		int length = userid.length() > companyid.length() ? userid.length() : companyid.length();
		length = length > logintime.length() ? length : logintime.length();
		for (int i = 0; i < length; i++) {
			// 第一段
			if (i < companyid.length())
				tonkenKey.append(companyid.charAt(i));
			else
				tonkenKey.append(DOWN_LINE);
			// 第二段
			if (i < userid.length())
				tonkenKey.append(userid.charAt(i));
			else
				tonkenKey.append(DOWN_LINE);
			// 第三段
			if (EmptyHelper.isEmpty(logintime))
				continue;
			if (i < logintime.length())
				tonkenKey.append(logintime.charAt(i));
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
		StringBuilder loginTime = new StringBuilder(54);
		for (int i = 0; i < userToken.length(); i++) {
			if (i % 3 == 0 && userToken.charAt(i) != DOWN_LINE2)
				companyId.append(userToken.charAt(i));
			if (i % 3 == 1 && userToken.charAt(i) != DOWN_LINE2)
				userId.append(userToken.charAt(i));
			if (i % 3 == 2 && userToken.charAt(i) != DOWN_LINE2)
				loginTime.append(userToken.charAt(i));
		}
		return new String[] { companyId.toString(), userId.toString(), loginTime.toString() };
	}

}
