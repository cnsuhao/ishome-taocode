package org.isotope.jfp.mpc.weixin.token;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.WeiXinCompanyDBO;
import org.isotope.jfp.common.weixin.constants.ISWeixinConstants;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyGroupReceverBean;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyGroupUserReceverBean;
import org.isotope.jfp.mpc.weixin.beans.sender.WeiXinCompanySenderBean;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinCompanyTokenBean;
import org.isotope.jfp.mpc.weixin.txapi.TxWeixinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
public class WeiXinCompanyTokenService implements ISFrameworkConstants, ISWeixinConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	/**
	 * 等待时间（小时）
	 */
	private int waitTime = 3600 * 6;

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime * 3600;
	}

	// 缓存定义
	@Resource
	ICacheService myCatch;

	/**
	 * 基于缓存加载企业Token，由企业id+管理组凭证密钥为唯一标识
	 */
	public WeiXinCompanyTokenBean loadCompanyToken(WeiXinCompanyDBO company) {
		WeiXinCompanySenderBean sender = new WeiXinCompanySenderBean();
		BeanUtils.copyProperties(company, sender);
		return loadWeixinCompanyToken(sender);
	}

	public WeiXinCompanyTokenBean loadCompanyToken(WeiXinCompanySenderBean company) {
		if (company == null)
			return null;
		myCatch.selectDB(9);
		String key = TOKEN_Company + company.getCompanyId();// + ":" +
															// TOKEN_CorpSecret
															// +
															// company.getCorpSecret()
		WeiXinCompanyTokenBean comanyToken = JSONObject.parseObject((String) myCatch.getObject(key, false), WeiXinCompanyTokenBean.class);
		if (comanyToken == null) {
			comanyToken = loadWeixinCompanyToken(company);
			if (comanyToken == null) {
				return null;
			}
			myCatch.putObject(key, JSONObject.toJSONString(comanyToken), waitTime, false);
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
	private WeiXinCompanyTokenBean loadWeixinCompanyToken(WeiXinCompanySenderBean comany) {
		// getAccessToken,登陆后，仅仅得到使用token内容
		WeiXinCompanyTokenBean config = new WeiXinCompanyTokenBean();
		config.setCorpId(comany.getAppId());
		config.setCorpSecret(comany.getAppSecret());
		TxWeixinService wxCpService = new TxWeixinService(config);
		try {
			wxCpService.getAccessToken(true);
			config.setCorpSecret(EMPTY);
			return config;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	///////////////////////////////////////////////////////////////////
	/**
	 * 获得用户组微信ID
	 * 
	 * @param recever
	 * @return
	 */
	public String loadWeixinCompanyGroupId(WeiXinCompanySenderBean company, WeiXinCompanyGroupReceverBean companyGroup) {
		
		return "loadWeixinCompanyGroupId";
	}

	/**
	 * 添加用户组
	 * 
	 * @param wxcsb
	 * @param group
	 * @return
	 */
	public String addCompanyGroup(WeiXinCompanySenderBean company, WeiXinCompanyGroupReceverBean group) {
		// TODO Auto-generated method stub
		return "addCompanyGroup";
	}

	/**
	 * 删除用户组
	 * 
	 * @param wxcsb
	 * @param group
	 * @return
	 */
	public String deleteCompanyGroup(WeiXinCompanySenderBean company, WeiXinCompanyGroupReceverBean group) {
		// TODO Auto-generated method stub
		return "deleteCompanyGroup";
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 获得用户微信ID
	 * 
	 * @param recever
	 * @return
	 */
	public String loadWeixinCompanyGroupUserId(WeiXinCompanySenderBean company, WeiXinCompanyGroupUserReceverBean companyGroupUser) {
		
		return "loadWeixinCompanyGroupUserId";
	}

	/**
	 * 添加用户组用户
	 * 
	 * @param wxcsb
	 * @param group
	 * @return
	 */
	public String addCompanyGroupUser(WeiXinCompanySenderBean company, WeiXinCompanyGroupUserReceverBean companyGroupUser) {
		// TODO Auto-generated method stub
		return "addCompanyGroupUser";
	}

	/**
	 * 删除用户组用户
	 * 
	 * @param wxcsb
	 * @param group
	 * @return
	 */
	public String deleteCompanyGroupUser(WeiXinCompanySenderBean company, WeiXinCompanyGroupUserReceverBean companyGroupUser) {
		// TODO Auto-generated method stub
		return "deleteCompanyGroupUser";
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
