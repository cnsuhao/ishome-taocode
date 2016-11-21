package org.isotope.jfp.framework.beans.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.isotope.jfp.framework.beans.user.TokenBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.framework.utils.token.BusinessTokenHelper;

/**
 * 业务请求Token数据算法
 * 
 * @author Spook
 * @since 2.3.1
 * @version 2.3.1 2015/6/23
 * @see BusinessTokenBean
 */
public class BusinessTokenBean extends TokenBean implements ISFrameworkConstants {

	public static void main(String[] args) {
		System.out.println(build("1212403_84_25_46_27_08_49_5"));
	}

	/**
	 * 获得业务请求Key
	 * 
	 * @param companyId
	 * @param userId
	 * @param bizName
	 * @param encryType
	 *            E:加密,D:解密
	 * @param clientTimestamp
	 * @return
	 */
	public static String getBizToken(String companyId, String userId, String bizName, String encryType, String clientTimestamp) {
		return BusinessTokenHelper.getBizTokenData(companyId, userId, bizName + encryType + clientTimestamp);
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
		String[] token = BusinessTokenHelper.getBizTokenData(userToken);
		String userId = token[0];
		String companyId = token[1];

		String t = token[2];
		String bizName = t.substring(0, 8);
		String encryType = t.substring(8, 9);
		String rRequestDateTime = t.substring(9);
		return new String[] { userId.toString(), companyId.toString(), bizName, encryType, rRequestDateTime };
	}

	//
	/// {companyId} 18
	// --------/{userId} 18
	// -----------------/{bizName} 8
	// ---------------------------/{encryType} 1
	// ---------------------------------------/{clientTimestamp} 8 (MMDDH24)
	/**
	 * 获得 一个Token
	 * 
	 * @return tonkenString(企业ID+用户ID+[业务标识+加密模式+请求时间])
	 */
	public static BusinessTokenBean build(String bizToken) {
		BusinessTokenBean tokenBean = new BusinessTokenBean();
		String[] ds = getBizTokenData(bizToken);
		tokenBean.setCompanyId(ds[0]);
		tokenBean.setUserId(ds[1]);
		tokenBean.setBizName(ds[2]);
		tokenBean.setEncryType(ds[3]);
		tokenBean.setClientTimestamp(ds[4]);
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
		return BusinessTokenHelper.getBizTokenData(bizTokenBean.getUserId(), bizTokenBean.getCompanyId(), bizTokenBean.getBizName() + bizTokenBean.getEncryType() + bizTokenBean.getClientTimestamp());
	}

	public String getToken() {
		if (EmptyHelper.isEmpty(token))
			token = BusinessTokenHelper.getBizTokenData(companyId, userId, bizName + encryType + clientTimestamp);
		return token;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 企业ID
	 */
	private String companyId;
	/**
	 * 用户ID
	 */
	private String userId;
	/**
	 * 请求时间
	 */
	private String clientTimestamp;
	/**
	 * 业务标识
	 */
	private String bizName;
	/**
	 * 加密模式
	 */
	private String encryType;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getClientTimestamp() {
		return clientTimestamp;
	}

	public void setClientTimestamp(String clientTimestamp) {
		this.clientTimestamp = clientTimestamp;
	}

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	public String getEncryType() {
		return encryType;
	}

	public void setEncryType(String encryType) {
		this.encryType = encryType;
	}

	/////////////////////////////////////////////
	//TODO
	public void chageToken() {
		SimpleDateFormat format = new SimpleDateFormat("YYMMddHHmmss");
		clientTimestamp = format.format(new Date());
	}
}