package org.isotope.jfp.framework.token;

import javax.servlet.http.HttpServletRequest;

import org.isotope.jfp.framework.beans.user.TokenBean;
import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.biz.common.ISToken;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.cache.session.SessionHelper;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.framework.utils.HttpRequestHelper;
import org.isotope.jfp.framework.utils.PKHelper;
import org.isotope.jfp.framework.utils.TokenBusinessHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 业务请求Token数据算法
 * 
 * @author Spook
 * @version 4.1.3 2017/04/15
 * @version 4.1.1 2016/12/12
 * @version 3.2.1 2016/08/28
 * @version 2.3.1 2015/06/23
 * @since 2.3.1 2015/06/23
 */
public class TokenBusinessSupport extends TokenBean implements ISFrameworkConstants, ISToken {

	public String getBarId() {
		if (curUser == null)
			return null;
		return curUser.getCompanyId();
	}

	public String getUserId() {
		if (curUser == null)
			return null;
		return curUser.getUserId();
	}

	///////////////////////////////////////////////////////////////////////
	public static void main(String[] args) throws Exception {
		String token = getTokenBiz("111222", "000000", "223344");
		System.out.println((token));// 102102103203204204
		System.out.println(build(token));// {"clientTimestamp":"000000","ipAdress":"111222","sessionId":"223344","token":"102102103203204204"}
	}

	///////////////////////////////////////////////////////////////////////
	public static final String TOKEN = "TOKEN:";

	public static String makeClientTimestamp() {
		return DateHelper.currentTimeMillis0();
	}

	/**
	 * 获得业务请求Key
	 * 
	 * @param barId
	 * @param userId
	 * @param clientTimestamp
	 * @return
	 * @throws Exception
	 */
	public static String getTokenBiz(String ipAdress, String clientTimestamp, String sessionId) throws Exception {
		// 随意组合顺序
		return TokenBusinessHelper.getBizTokenData(sessionId, ipAdress + clientTimestamp);
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
	public static String getTokenBiz(TokenBusinessSupport tokenBiz) throws Exception {
		return getTokenBiz(tokenBiz.getIpAdress(), tokenBiz.getClientTimestamp(), tokenBiz.getSessionId());
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

		return TokenBusinessHelper.getBizTokenData(userToken, DATA_NUM);
	}

	public static int DATA_NUM = 2;

	//
	/// {serverId} 17
	// -----------------/{bizId} 17
	// ---------------------------------------/{clientTimestamp} 17 (DDH24mmSS)
	/**
	 * 获得 一个authorizer_refresh_token（58位）
	 * 
	 * @return tonkenString(企业ID+用户ID+请求时间)
	 * @throws Exception
	 */
	public static TokenBusinessSupport build(String bizToken) throws Exception {
		TokenBusinessSupport tokenBean = new TokenBusinessSupport();
		if (bizToken.length() > 24) {
			String[] ds = getBizTokenData(bizToken);
			tokenBean.setSessionId(ds[0]);
			String t = ds[1];
			if (t.length() > 12) {
				String ipAdress = t.substring(0, 12);
				String clientTimestamp = t.substring(12);
				tokenBean.setIpAdress(ipAdress);
				tokenBean.setClientTimestamp(clientTimestamp);
			}
		} else {
			tokenBean.setToken(bizToken);
		}
		return tokenBean;
	}

	/**
	 * 系统默认TOKEN用户
	 * 
	 * @param hdp
	 * @return
	 * @throws Exception
	 */
	public static TokenBusinessSupport defaultToken(String hdp) throws Exception {
		return defaultToken(null, hdp);
	}

	/**
	 * 系统默认TOKEN用户
	 * 
	 * @param hdp
	 * @return
	 * @throws Exception
	 */
	public static TokenBusinessSupport defaultToken(HttpServletRequest request, String hdp) throws Exception {
		TokenBusinessSupport tokenBean = new TokenBusinessSupport();
		tokenBean.setSessionId(PKHelper.creatUUKey());
		if (request == null) {
			tokenBean.setIpAdress(HttpRequestHelper.getClientRemoteIPAddr(request, true));
		} else {
			tokenBean.setIpAdress(HttpRequestHelper.getServerLocalIPAddr(true));
		}
		tokenBean.setWaitTimeSecond(300);
		tokenBean.setClientTimestamp();
		tokenBean.setCurUser(defaultUser(hdp));
		SessionHelper.setSessionAttribute(tokenBean);
		return tokenBean;
	}

	public static UserBean defaultUser(String hdp) throws Exception {
		UserBean curUser = new UserBean(hdp);
		curUser.setUserId(PKHelper.creatPUKey());
		curUser.setUserNameCN(GUEST);
		curUser.setCompanyId(SYSTEM);
		curUser.setCompanyNameCN(GUEST);
		curUser.setRoolType(ZERO);
		return curUser;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	///////////////////////////////////////////////////////////////////
	@Override
	public boolean checkToken() throws Exception {
		try {
			// 基于请求本身进行校验
			if (myHttpServletRequest != null) {
				// 声明新的Token
				TokenBusinessSupport tokenBean = new TokenBusinessSupport();
				tokenBean.setSessionId(myHttpServletRequest.getSession().getId());
				tokenBean.setIpAdress(HttpRequestHelper.getClientRemoteIPAddr(myHttpServletRequest, true));
				tokenBean.setClientTimestamp();
				// 比较登录时候的SessionID
				if (tokenBean.getSessionId().equals(this.getSessionId()) == false) {
					return false;
				}
				// 比较IP地址
				if (tokenBean.getIpAdress().equals(this.getIpAdress()) == false) {
					return false;
				}
			}
			// 基于缓存中心进行校验
			if (myCacheService != null) {
				// 获得历史Token
				String tokenKey = TOKEN + getToken();
				String tokenCatch = (String) myCacheService.getObject(tokenKey, false);
				if (logger.isDebugEnabled())
					logger.debug(" doCheck.checkToken()=====tokenCatch>>>>>" + tokenCatch);
				// 是否需要支持可变Token
				if (EmptyHelper.isNotEmpty(tokenCatch)) {
					// 获得Token内容
					curUser = JSON.parseObject(tokenCatch, UserBean.class);
					logger.debug("currentUser==>>>" + curUser);
					// 缓存当前线程Loginer
					SessionHelper.setSessionAttribute(this);
					myCacheService.expire(tokenKey, waitTimeSecond);
					return true;
				}
			}
		} catch (Exception r) {
		}
		return false;
	}

	/**
	 * @see waitTimeSecond
	 */
	@Override
	public boolean saveToken() throws Exception {
		// 当前线程缓存用户对象
		SessionHelper.setSessionAttribute(this);
		return myCacheService.putObject(TOKEN + getToken(), JSON.toJSONString(curUser), waitTimeSecond, false);
	}

	/**
	 * 更新Token令牌内容，立刻生效
	 */
	public boolean chageToken() throws Exception {
		String oldToken = getToken();
		// 更新时间
		this.setClientTimestamp(makeClientTimestamp());

		// 删除原有数据
		myCacheService.deleteObject(oldToken);
		super.setToken(EMPTY);// 废弃原有

		return saveToken();
	}

	/**
	 * 删除token
	 */
	public boolean removeToken() throws Exception {
		// 删除原有数据
		myCacheService.deleteObject(getToken());
		return true;
	}

	///////////////////////////////////////////////////////////////////
	/**
	 * 返回Token令牌内容
	 */
	public String getToken() {
		if (EmptyHelper.isEmpty(super.getToken()))
			try {
				super.setToken(getTokenBiz(this));
			} catch (Exception e) {
				e.printStackTrace();
			}
		return super.getToken();
	}

	/**
	 * 缓存队列
	 */
	protected ICacheService myCacheService;

	public void setMyCacheService(ICacheService myCacheService) {
		this.myCacheService = myCacheService;
	}

	/**
	 * 网络请求
	 */
	protected HttpServletRequest myHttpServletRequest;

	public void setMyHttpServletRequest(HttpServletRequest myHttpServletRequest) {
		this.myHttpServletRequest = myHttpServletRequest;
	}

	/**
	 * 进程阻塞时间（秒）
	 */
	protected int waitTimeSecond = 3600;

	public void setWaitTimeSecond(int waitTimeSecond) {
		this.waitTimeSecond = waitTimeSecond;
	}

	private UserBean curUser;

	public void setCurUser(UserBean curUser) {
		this.curUser = curUser;
	}

	public UserBean currentUser() {
		return curUser;
	}

}