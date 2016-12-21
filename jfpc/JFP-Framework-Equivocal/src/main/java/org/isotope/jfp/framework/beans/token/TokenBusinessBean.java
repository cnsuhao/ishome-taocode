package org.isotope.jfp.framework.beans.token;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.framework.utils.TokenBusinessHelper;

/**
 * 业务请求Token数据算法
 * 
 * @author Spook
 * @version 4.1.1 2016/12/12
 * @version 2.3.1 2015/06/23
 * @since 2.3.1 2015/06/23
 */
public class TokenBusinessBean extends UserBean implements ISFrameworkConstants {
	public TokenBusinessBean() {

	}

	public TokenBusinessBean(String serverId, String bizName, String encryType, String userId, String clientTimestamp) {
		this.serverId = serverId;
		this.bizName = bizName;
		this.encryType = encryType;
		this.setUserId(userId);
		this.clientTimestamp = clientTimestamp;
	}

	public static void main(String[] args) throws Exception {
		String token = getBizToken("12345678", "2223334", "1", "1234567890123456", "6543210987654321");
		System.out.println((token));
		System.out.println(build(token));
	}

	/**
	 * 获得业务请求Key
	 * 
	 * @param serverId
	 * @param userId
	 * @param bizName
	 * @param encryType
	 *            E:加密,D:解密
	 * @param clientTimestamp
	 * @return
	 * @throws Exception
	 */
	public static String getBizToken(String serverId, String bizName, String encryType, String userId, String clientTimestamp) throws Exception {
		return TokenBusinessHelper.getBizTokenData(serverId + bizName + encryType, userId, clientTimestamp);
	}

	/**
	 * 根据企业ID获得用户ID
	 * 
	 * @param userToken
	 * @return
	 * @throws Exception
	 */
	public static String[] getBizTokenData(String userToken) throws Exception {
		if (EmptyHelper.isEmpty(userToken))
			throw new Exception("不能存在空值");

		String[] token = TokenBusinessHelper.getBizTokenData(userToken, 3);

		String t = token[0];
		String serverId = t.substring(0, 8);
		String bizName = t.substring(8, 15);
		String encryType = t.substring(15);

		String userId = token[1];
		String requestDateTime = token[2];

//		System.out.println(serverId);
//		System.out.println(bizName);
//		System.out.println(encryType);
//		System.out.println(userId);
//		System.out.println(requestDateTime);
		return new String[] { serverId, bizName, encryType, userId, requestDateTime };
	}

	//
	/// {serverId} 8
	// -----------------/{bizName} 8
	// ---------------------------/{encryType} 1
	// --------/{userId} 17
	// ---------------------------------------/{clientTimestamp} 17 (DDH24mmSS)
	/**
	 * 获得 一个authorizer_refresh_token（58位）
	 * 
	 * @return tonkenString(企业ID+用户ID+[业务标识+加密模式+请求时间])
	 * @throws Exception
	 */
	public static TokenBusinessBean build(String bizToken) throws Exception {
		TokenBusinessBean tokenBean = new TokenBusinessBean();
		String[] ds = getBizTokenData(bizToken);
		tokenBean.setServerId(ds[0]);
		tokenBean.setBizName(ds[1]);
		tokenBean.setEncryType(ds[2]);
		tokenBean.setUserId(ds[3]);
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
	 * @throws Exception
	 */
	public static String getBizToken(TokenBusinessBean bizTokenBean) throws Exception {
		return TokenBusinessHelper.getBizTokenData(bizTokenBean.getServerId(), bizTokenBean.getUserId(), bizTokenBean.getBizName() + bizTokenBean.getEncryType(), bizTokenBean.getClientTimestamp());
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getToken() {
		if (EmptyHelper.isEmpty(token))
			try {
				token = TokenBusinessHelper.getBizTokenData(serverId, getUserId(), bizName + encryType, clientTimestamp);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return token;
	}

	/////////////////////////////////////////////
	// TODO
	public void chageToken() {
		SimpleDateFormat format = new SimpleDateFormat("YYMMddHHmmss");
		clientTimestamp = format.format(new Date());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 服务器ID
	 */
	private String serverId;
	/**
	 * 业务标识
	 */
	private String bizName;
	/**
	 * 加密模式
	 */
	private String encryType;
	/**
	 * 请求时间
	 */
	private String clientTimestamp;

	public String getServerId() {
		return serverId;
	}

	public void setServerId(String serverId) {
		this.serverId = serverId;
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

	public String getClientTimestamp() {
		return clientTimestamp;
	}

	public void setClientTimestamp(String clientTimestamp) {
		this.clientTimestamp = clientTimestamp;
	}
}