package org.isotope.jfp.mpc.weixin.token;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.constants.ISWeixinConstants;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.mpc.weixin.beans.WeiXinCompanySenderBean;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinCompanyTokenBean;
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
@Service("WeiXinCompanyTokenService")
public class WeiXinCompanyTokenService implements ISWeixinConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 缓存定义
	@Resource
	ICacheService myCatch;

	/**
	 * 基于缓存加载企业Token
	 */
	public WeiXinCompanyTokenBean loadCompanyToken(WeiXinCompanySenderBean company) {
		if (company == null)
			return null;
		myCatch.selectDB(9);
		WeiXinCompanyTokenBean comanyToken = JSONObject.parseObject((String) myCatch.getObject(TOKEN_Company + company.getCompanyId(), false), WeiXinCompanyTokenBean.class);
		if (comanyToken == null) {
			comanyToken = loadWeixinCompanyToken(company);
			if (comanyToken == null) {
				return null;
			}
			myCatch.putObject(TOKEN_Company + company.getCompanyId(), JSONObject.toJSONString(comanyToken), 0, false);
		}
		myCatch.init();
		return comanyToken;
	}

	/**
	 * 基于APi获得用户Token
	 * 
	 * @param comany
	 * @return
	 */
	public WeiXinCompanyTokenBean loadWeixinCompanyToken(WeiXinCompanySenderBean comany) {
		WeiXinCompanyTokenBean comanyToken = null;
		return comanyToken;
	}
}
