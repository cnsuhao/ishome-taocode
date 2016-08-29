package org.isotope.jfp.common.login;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.PKHelper;
import org.isotope.jfp.framework.utils.token.UserCacheHelper;
import org.isotope.jfp.persistent.LogLoginer.LogLoginerDBO;
import org.isotope.jfp.persistent.LogLoginer.LogLoginerService;
import org.isotope.jfp.persistent.TkAuthorization.TkAuthorizationDBO;
import org.isotope.jfp.persistent.TkAuthorization.TkAuthorizationService;
import org.isotope.jfp.persistent.TkLoginer.TkLoginerDBO;
import org.isotope.jfp.persistent.TkLoginer.TkLoginerService;

import com.alibaba.fastjson.JSON;

public class LoginService extends MyServiceSupport implements ISFrameworkConstants {

	public LoginDao getLoginDao() {
		return getMySqlSession().getMapper(LoginDao.class);
	}

	/**
	 * 读取用户信息
	 * 
	 * @param loginer
	 */
	public List<UserBean> readLoginer(HashMap<String, String> loginer) {
		return getLoginDao().readLoginer(loginer);
	}

	protected List<UserBean> readTeacherLoginer(HashMap<String, String> loginer) {
		return getLoginDao().readTeacherLoginer(loginer);
	}

	protected List<UserBean> readParentLoginer(HashMap<String, String> loginer) {
		return getLoginDao().readParentLoginer(loginer);
	}

	protected List<UserBean> readStudentLoginer(HashMap<String, String> loginer) {
		return getLoginDao().readStudentLoginer(loginer);
	}

	/**
	 * 创建用户信息
	 * 
	 * @param authorizerRefreshToken
	 */
	protected UserBean creatLoginerByOpenId(UserBean loginer) {
		UserBean user = new UserBean();
		user.setSchoolId(loginer.getSchoolId());
		user.setUserType(loginer.getUserType());
		// 创建用户表
		{
			LogLoginerDBO loniner = new LogLoginerDBO();
			loniner.setAccount(loginer.getOpenId());
			makeDataOperationTime(loniner);
			getLoginDao().creatLoginerByOpenId(loniner);
			user.setUserId(loniner.getUid());
		}

		// 保存第三方授权Token表
		TkAuthorizationDBO tad = new TkAuthorizationDBO();
		tad.setAuthorizerRefreshToken(loginer.getOpenId());
		tad.setUid(user.getUserId());
		tad.setSid(user.getSchoolId());
		tad.setType(Integer.parseInt(user.getUserType()));
		makeDataOperationTime(tad);
		TkAuthorizationService_.doInsert(tad);

		return user;
	}

	@Resource
	TkAuthorizationService TkAuthorizationService_;

	/**
	 * 设置操作人和时间
	 * @param LogLoginer
	 */
	private void makeDataOperationTime(MyDataBaseObjectSupport LogLoginer) {
		String t = DateHelper.currentTimeMillis4();
		LogLoginer.setCreateTime(t);
		LogLoginer.setCreator(10000L);
		LogLoginer.setUpdateTime(t);
		LogLoginer.setUpdator(10000L);
	}

	/**
	 * 转换登录日志
	 * @param LogLoginer
	 * @param user
	 */
	public void makeLoginLog(LogLoginerDBO LogLoginer, UserBean user) {
		LogLoginer.setPuk(PKHelper.creatPUKey());
		LogLoginer.setUid(user.getUserId());
		LogLoginer.setProductId("QXY");
		LogLoginer.setActType(0);

		makeDataOperationTime(LogLoginer);
	}

	/**
	 * 用户登录Token
	 * 
	 * @param loginer
	 */
	public void doLoginToken(UserBean loginer, boolean dbSave) {
		// Token制作
		loginer.getToken();
		// 缓存登录信息
		UserCacheHelper.saveUser(loginer);
		if (dbSave) {
			// 数据库保存
			TkLoginerDBO tkl = new TkLoginerDBO();
			tkl.setToken(loginer.getToken());
			tkl.setJson(JSON.toJSONString(loginer));
			TkLoginerService_.doInsert(tkl);
		}
	}

	@Resource
	TkLoginerService TkLoginerService_;

	/**
	 * 用户登出
	 * 
	 * @param loginer
	 */
	public void doLogoutToken(UserBean loginer) {
		UserCacheHelper.removeUser(loginer.getToken());
		TkLoginerDBO tkl = new TkLoginerDBO();
		tkl.setToken(loginer.getToken());
		TkLoginerService_.doDelete(tkl);
	}

	/**
	 * 
	 * @param loginer
	 */
	@Resource
	LogLoginerService LogLoginerService_;

	protected void doLoginLog(LogLoginerDBO LogLoginer) {
		LogLoginerService_.doInsert(LogLoginer);
	}

}
