package org.isotope.jfp.common.login;

import java.util.HashMap;
import java.util.List;

import org.isotope.jfp.framework.beans.user.LoginerBean;
import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;

/** 软件包信息 */
public interface LoginDao extends ISFrameworkConstants {

	/**
	 * 获得所有第三方合作对象
	 * 
	 * @return
	 */
	public List<HashMap<?, ?>> loadSecurityCode();

	/**
	 * 用户登录
	 * 
	 * @param loginer
	 * @return
	 */
	public List<UserBean> readLoginerByAccount(HashMap<String, String> loginer);
	public List<UserBean> readLoginerByOpenId(String authorizerRefreshToken);
	public int creatLoginerByOpenId(String authorizerRefreshToken);

	/**
	 * 判断用户是否登录 0没有1登录2多次
	 * 
	 * @param puk
	 * @return
	 */
	public List<UserBean> checkLogIn(LoginerBean loginer);

	/**
	 * 登记用户登录Token信息
	 * 
	 * @param puk
	 * @return
	 */
	public int doLoginToken(LoginerBean loginer);
	public int doLogoutToken(LoginerBean loginer);

	/**
	 * 更新登录用户企业信息
	 * 
	 * @param puk
	 * @return
	 */
	public int doUpdateByToken(LoginerBean loginer);

	/**
	 * 登记用户登录Token信息
	 * 
	 * @param puk
	 * @return
	 */
	public int doLoginInfo(LoginerBean loginer);

	/**
	 * 登记用户登录Token信息
	 * 
	 * @param puk
	 * @return
	 */
	public int doLoginLog(LoginerBean loginer);

	/**
	 * 获得企业用户信息
	 * 
	 * @param puk
	 * @return
	 */
	public HashMap<?, ?> getCompanyLoginer(String puk);

	/**
	 * 获得会员用户信息
	 * 
	 * @param puk
	 * @return
	 */
	public HashMap<?, ?> getMemberLoginer(String puk);

	/**
	 * 清空用户登录信息
	 * 
	 * @param puk
	 * @return
	 */
	public int doLogOut(LoginerBean loginer);

	/**
	 * 检查token合法性
	 * 
	 * @param token
	 * @return
	 */
	public UserBean doCheckToken(String token);
}
