package org.isotope.jfp.mpc.weixin.token;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.constants.ISWeixinConstants;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.mpc.weixin.beans.WeiXinCompanyGroupReceverBean;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinCompanyGroupTokenBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

/**
 * 微信企业Token对接
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
@Service("WeiXinCompanyGroupTokenService")
public class WeiXinCompanyGroupTokenService implements ISWeixinConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	// 缓存定义
	@Resource
	ICacheService myCatch;

	/**
	 * 加载企业Token
	 */
	public WeiXinCompanyGroupTokenBean loadCompanyGroupToken(WeiXinCompanyGroupReceverBean group) {
		if (group == null)
			return null;
		myCatch.selectDB(9);
		WeiXinCompanyGroupTokenBean comanyGroupToken = JSONObject.parseObject((String) myCatch.getObject(TOKEN_Group + group.getGroupId(), false), WeiXinCompanyGroupTokenBean.class);
		if (comanyGroupToken == null) {
			comanyGroupToken = loadWeixinCompanyGroupToken(group);
			if (comanyGroupToken == null) {
				return null;
			}
			myCatch.putObject(TOKEN_Group + group.getGroupId(), JSONObject.toJSONString(comanyGroupToken), 0, false);
		}
		myCatch.init();
		return comanyGroupToken;
	}

	/**
	 * 基于APi获得用户Token
	 * 
	 * @param comany
	 * @return
	 */
	public WeiXinCompanyGroupTokenBean loadWeixinCompanyGroupToken(WeiXinCompanyGroupReceverBean group) {
		WeiXinCompanyGroupTokenBean comanyGroupToken = null;
		return comanyGroupToken;
	}
}
