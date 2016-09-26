package org.isotope.jfp.mpc.weixin.token;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.constants.ISWeixinConstants;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.mpc.weixin.beans.sender.WeiXinCompanySenderBean;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinCompanyTokenBean;
import org.isotope.jfp.mpc.weixin.txapi.TxWeixinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import me.chanjar.weixin.common.exception.WxErrorException;

/**
 * 微信企业Token对接
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
@Service("WeiXinCompanyTokenService")
public class WeiXinCompanyTokenService implements ISFrameworkConstants, ISWeixinConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	// 缓存定义
	@Resource
	ICacheService myCatch;

	/**
	 * 基于缓存加载企业Token，由企业id+管理组凭证密钥为唯一标识
	 */
	public WeiXinCompanyTokenBean loadCompanyToken(WeiXinCompanySenderBean company) {
		if (company == null)
			return null;
		myCatch.selectDB(9);
		String key = TOKEN_Company + company.getCompanyId();// + ":" + TOKEN_CorpSecret + company.getCorpSecret()
		WeiXinCompanyTokenBean comanyToken = JSONObject.parseObject((String) myCatch.getObject(key, false), WeiXinCompanyTokenBean.class);
		if (comanyToken == null) {
			comanyToken = loadWeixinCompanyToken(company);
			if (comanyToken == null) {
				return null;
			}
			myCatch.putObject(key, JSONObject.toJSONString(comanyToken), 0, false);
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
		// getAccessToken,登陆后，仅仅得到使用token内容
		WeiXinCompanyTokenBean config = new WeiXinCompanyTokenBean();
		config.setCorpId(comany.getAppId());
		config.setCorpSecret(comany.getAppSecret());
		TxWeixinService wxCpService = new TxWeixinService(config);
		try {
			wxCpService.getAccessToken(true);
			// TODO
			config.setCorpSecret(EMPTY);
			return config;
		} catch (WxErrorException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public static void main(String args[]) {
		WeiXinCompanySenderBean company = new WeiXinCompanySenderBean();
		company.setAppId("wxc213dac5f211edf9");
		company.setAppSecret("cl6aPpCpsaxhQxNhmZ8KSqGNNi-hjGhJQylYDHkeTMnqjzNJI6djqCy8vWZh9nD9");
		WeiXinCompanyTokenService service = new WeiXinCompanyTokenService();
		// LcxupWo-hVKn0A7i-pofvyZ2dVsLTJc2Pp6MscPOOyv9IMxwda2BqphjF7QiocVo
		WeiXinCompanyTokenBean token = service.loadWeixinCompanyToken(company);
		System.out.println(token.getAccessToken());
		System.out.println(token);
	}
}
