package org.isotope.jfp.mpc.weixin.token;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.WeiXinCompanyDBO;
import org.isotope.jfp.common.weixin.WeiXinCompanyGroupDBO;
import org.isotope.jfp.common.weixin.WeiXinCompanyGroupUserDBO;
import org.isotope.jfp.common.weixin.constants.ISWeixinConstants;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.mpc.weixin.beans.sender.WeiXinCompanySenderBean;
import org.isotope.jfp.mpc.weixin.token.beans.WeiXinCompanyTokenBean;
import org.isotope.jfp.mpc.weixin.txapi.TxWeixinService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import me.chanjar.weixin.cp.bean.WxCpUser;

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
	public WeiXinCompanyTokenBean loadCompanyToken(WeiXinCompanySenderBean company) {
		WeiXinCompanyDBO sender = new WeiXinCompanyDBO();
		BeanUtils.copyProperties(company, sender);
		return loadWeixinCompanyToken(sender);
	}

	public WeiXinCompanyTokenBean loadCompanyToken(WeiXinCompanyDBO company) {
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
	private WeiXinCompanyTokenBean loadWeixinCompanyToken(WeiXinCompanyDBO comany) {
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
	 * 添加用户组
	 * 
	 * @param wxcsb
	 * @param group
	 * @return
	 */
	public String addCompanyGroup(WeiXinCompanyDBO company, WeiXinCompanyGroupDBO group) {
		TxWeixinService wxCpService = new TxWeixinService(loadCompanyToken(company));

		WxCpDepart dept = new WxCpDepart();
		dept.setOrder(Integer.parseInt(group.getGroupId()));
		dept.setName(group.getGroupName());
		dept.setParentId(Integer.parseInt(group.getParentId()));

		// 创建部门
		try {
			Integer id = wxCpService.departCreate(dept);
			return "" + id;
		} catch (WxErrorException e) {
			e.printStackTrace();
		}

		return "";
	}

	/**
	 * 删除用户组
	 * 
	 * @param wxcsb
	 * @param group
	 * @return
	 */
	public String deleteCompanyGroup(WeiXinCompanyDBO company, WeiXinCompanyGroupDBO group) {
		TxWeixinService wxCpService = new TxWeixinService(loadCompanyToken(company));
		// 删除部门
		try {
			wxCpService.departDelete(Integer.parseInt(group.getWxId()));
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return ZERO;
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 添加用户组用户
	 * 
	 * @param wxcsb
	 * @param group
	 * @return
	 */
	public String addCompanyGroupUser(WeiXinCompanyDBO company, WeiXinCompanyGroupUserDBO companyGroupUser) {
		TxWeixinService wxCpService = new TxWeixinService(loadCompanyToken(company));

		WxCpUser user = new WxCpUser();
		user.setUserId(companyGroupUser.getUserId());
		user.setName(companyGroupUser.getUserName());

		if (EmptyHelper.isNotEmpty(companyGroupUser.getGroupid())) {
			Integer[] departIds = { Integer.parseInt(companyGroupUser.getGroupid()) };
			user.setDepartIds(departIds);
		}

		// 创建用户
		try {
			wxCpService.userCreate(user);
			String wxId = user.getWeiXinId();
			return wxId;
		} catch (WxErrorException e) {
			e.printStackTrace();
		}

		return "";

	}

	/**
	 * 删除用户组用户
	 * 
	 * @param wxcsb
	 * @param group
	 * @return
	 */
	public String deleteCompanyGroupUser(WeiXinCompanyDBO company, WeiXinCompanyGroupUserDBO companyGroupUser) {
		TxWeixinService wxCpService = new TxWeixinService(loadCompanyToken(company));
		// 删除用户组用户
		try {
			wxCpService.departDelete(Integer.parseInt(companyGroupUser.getWxId()));
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
		return ZERO;
	}

	public static void main(String args[]) {
		WeiXinCompanyDBO company = new WeiXinCompanyDBO();
		company.setAppId("wxc213dac5f211edf9");
		company.setAppSecret("cl6aPpCpsaxhQxNhmZ8KSqGNNi-hjGhJQylYDHkeTMnqjzNJI6djqCy8vWZh9nD9");
		WeiXinCompanyTokenService service = new WeiXinCompanyTokenService();
		// LcxupWo-hVKn0A7i-pofvyZ2dVsLTJc2Pp6MscPOOyv9IMxwda2BqphjF7QiocVo
		WeiXinCompanyTokenBean token = service.loadWeixinCompanyToken(company);
		System.out.println(token.getAccessToken());
		System.out.println(token);
	}

}
