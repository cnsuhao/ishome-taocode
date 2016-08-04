package org.isotope.jfp.framework.utils.token;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;

/**
 * 业务请求Token数据算法
 * 
 * @author Spook
 * @since 2.3.1
 * @version 2.3.1 2015/6/23
 * @see BusinessTokenBean
 */
public class BusinessTokenHelper implements ISFrameworkConstants {

	//
	///{companyId}														18
	//--------/{userId}													18
	//-----------------/{bizName}										8
	//---------------------------/{encryType}							1
	//---------------------------------------/{clientTimestamp}			8 (MMDDH24)	
	public static void main(String[] args) {
		System.out.println(getBizTokenData("987654321", "123456789", "123456789abcdefg"));
		System.out.println(getUserID(getBizTokenData("987654321", "123456789", "123456789abcdefg")));
		System.out.println(getCompanyId(getBizTokenData("987654321", "123456789", "123456789abcdefg")));
		System.out.println(getBizId(getBizTokenData("987654321", "123456789", "123456789abcdefg")));
		System.out.println(getEncryType(getBizTokenData("987654321", "123456789", "123456789abcdefg")));
		System.out.println(getLoginDateTime(getBizTokenData("987654321", "123456789", "123456789abcdefg")));
	}
	
	/**
	 * 
	 * @param companyId
	 * @param userId
	 * @param bizName
	 * @return
	 */
	public static String getBizToken(String companyId, String userId, String bizName) {
		return getBizTokenData(companyId, userId, bizName + encryType() + clientTimestamp());
	}
	
	/**
	 * 
	 * @param companyId
	 * @param userId
	 * @param bizName
	 * @param encryType E:加密,D:解密
	 * @return
	 */
	public static String getBizToken(String companyId, String userId, String bizName, String encryType) {
		return getBizTokenData(companyId, userId, bizName + encryType + clientTimestamp());
	}
	
	/**
	 * yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String encryType() {
		return "E";
	}
	
	/**
	 * yyyyMMddHHmmss
	 * 
	 * @return
	 */
	public static String clientTimestamp() {
		SimpleDateFormat format = new SimpleDateFormat("MMddHHmm");
		return format.format(new Date());
	}
	/**
	 * 获得业务请求Key
	 * @param companyId
	 * @param userId
	 * @param bizName
	 * @param encryType  E:加密,D:解密
	 * @param clientTimestamp
	 * @return
	 */
	public static String getBizToken(String companyId, String userId, String bizName, String encryType, String clientTimestamp) {
		return getBizTokenData(companyId, userId, bizName + encryType + clientTimestamp);
	}

	/**
	 * 交叉混淆，可以正序或者倒序，可以奇数和偶数
	 * 
	 * @param userid
	 *            用户ID
	 * @param companyid
	 *            企业ID	 
	 * @param sessionid 混淆数据穿
	 * @return tonkenString
	 */
	public static String getBizTokenData(String companyid, String userid, String sessionid) {
		StringBuilder tonkenKey = new StringBuilder(54);
		int length = userid.length() > companyid.length() ? userid.length() : companyid.length();
		length = length > sessionid.length() ? length : sessionid.length();
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
				companyId.append(userToken.charAt(i));
			if (i % 3 == 1 && userToken.charAt(i) != DOWN_LINE2)
				userId.append(userToken.charAt(i));
			if (i % 3 == 2 && userToken.charAt(i) != DOWN_LINE2)
				loginDateTimeId.append(userToken.charAt(i));
		}
		try{
			String t = loginDateTimeId.toString();
			String bizName = t.substring(0, 8);
			String encryType = t.substring(8, 9);
			String rRequestDateTime = t.substring(9);
			return new String[] { userId.toString(), companyId.toString(), bizName, encryType, rRequestDateTime };
		}catch(Exception e){
			
		}
		return new String[] { userId.toString(), companyId.toString(), "", "", loginDateTimeId.toString() };
	}

	/**
	 * 根据Token获得企业ID
	 * 
	 * @param userToken
	 * @return
	 */
	public static String getCompanyId(String userToken) {
		return getBizTokenData(userToken)[2];
	}

	/**
	 * 根据企业ID获得用户ID
	 * 
	 * @param userToken
	 * @return
	 */
	public static String getUserID(String userToken) {
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
