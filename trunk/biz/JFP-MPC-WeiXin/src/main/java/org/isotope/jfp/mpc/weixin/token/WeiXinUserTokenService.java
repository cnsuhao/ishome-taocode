package org.isotope.jfp.mpc.weixin.token;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.constants.ISWeixinConstants;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.mpc.weixin.beans.WeiXinUserReceverBean;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinUserTokenBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

/**
 * 微信用户Token对接
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
@Service("WeiXinUserTokenService")
public class WeiXinUserTokenService implements ISWeixinConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	// 缓存定义
	@Resource
	ICacheService myCatch;

	/**
	 * 加载用户Token
	 */
	public WeiXinUserTokenBean loadUserToken(WeiXinUserReceverBean user) {
		if (user == null)
			return null;
		myCatch.selectDB(9);
		WeiXinUserTokenBean userToken = JSONObject.parseObject((String) myCatch.getObject(TOKEN_User + user.getUserId(), false), WeiXinUserTokenBean.class);
		if (userToken == null) {
			userToken = loadWeixinUserToken(user);
			if (userToken == null) {
				return null;
			}
			myCatch.putObject(TOKEN_User + user.getUserId(), JSONObject.toJSONString(userToken), 0, false);
		}
		myCatch.init();
		return userToken;
	}

	/**
	 * 基于APi获得用户Token
	 * 
	 * @param comany
	 * @return
	 */
	public WeiXinUserTokenBean loadWeixinUserToken(WeiXinUserReceverBean user) {
		WeiXinUserTokenBean userToken = null;
		return userToken;
	}
}
