package org.isotope.jfp.framework.support;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.BusinessTokenBean;
import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.framework.utils.token.UserCacheHelper;
import org.isotope.jfp.persistent.TkLoginer.TkLoginerDBO;
import org.isotope.jfp.persistent.TkLoginer.TkLoginerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.JSON;

/**
 * 画面控制层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2016/8/8
 */
public class MyControllerSupport extends MyFrameworkSupport {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 分页参数
	 */
	@Resource
	protected PageVOSupport pageModel;

	/**
	 * Redis缓存
	 */
	@Resource
	protected ICacheService myCache;

	/**
	 * 当前用户
	 */
	private UserBean loginer;

	public UserBean getLoginer() {
		return loginer;
	}

	@Resource
	TkLoginerService TkLoginerService_;

	// 检查用户Token
	public boolean doCheckToken(String token) {
		token = "910820738640559462371283192";
		loginer = UserCacheHelper.checkUser(token);
		if (EmptyHelper.isEmpty(loginer)) {
			TkLoginerDBO tkl = new TkLoginerDBO();
			tkl.setToken(token);
			tkl = (TkLoginerDBO) TkLoginerService_.doRead(tkl);
			if (EmptyHelper.isNotEmpty(loginer)) {
				loginer = JSON.parseObject(tkl.getJson(), UserBean.class);
			}
		}
		if (EmptyHelper.isEmpty(loginer)) {// 临时代码
			// 获得用户信息
			loginer = new UserBean();
			BusinessTokenBean b2b = BusinessTokenBean.build(token);
			BeanUtils.copyProperties(b2b, loginer);
		}
		// 缓存Session
		super.setUserData(loginer);
		return true;
	}

	public RESTResultBean tokenFail() {
		RESTResultBean result = new RESTResultBean();
		result.setStatus(12);
		result.setInfo("token is timeout");
		return result;
	}

}
