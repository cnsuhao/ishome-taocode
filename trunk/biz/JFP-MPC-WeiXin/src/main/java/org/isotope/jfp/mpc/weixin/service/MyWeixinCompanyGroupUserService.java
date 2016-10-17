package org.isotope.jfp.mpc.weixin.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.WeiXinCompanyDBO;
import org.isotope.jfp.common.weixin.WeiXinCompanyGroupUserDBO;
import org.isotope.jfp.common.weixin.WeixinService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyGroupUserReceverBean;
import org.isotope.jfp.mpc.weixin.token.WeiXinCompanyTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 微信数据管理服务<br>
 * 企业用户信息(接收者)
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 * 
 */
@Service("MyWeixinCompanyGroupUserService")
public class MyWeixinCompanyGroupUserService implements ISFrameworkConstants {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	WeixinService WeixinService_;
	@Resource
	WeiXinCompanyTokenService WeiXinCompanyTokenService_;

	public WeiXinCompanyGroupUserReceverBean loadWeiXinUserReceverBean(String companyId, String userId) {
		WeiXinCompanyGroupUserDBO companyUserDBO = loadWeiXinCompanyUserDBO(companyId, userId);
		if (EmptyHelper.isNotEmpty(companyUserDBO)) {
			WeiXinCompanyGroupUserReceverBean recever = new WeiXinCompanyGroupUserReceverBean();
			recever.setCompanyId(companyUserDBO.getCompanyId());
			recever.setUserId(companyUserDBO.getUserId());
			recever.setWxId(companyUserDBO.getWxId());
			return recever;
		}
		return null;
	}

	public WeiXinCompanyGroupUserDBO loadWeiXinCompanyUserDBO(String companyId, String userId) {
		HashMap<String, String> companyUser = new HashMap<String, String>();
		companyUser.put("companyId", companyId);
		companyUser.put("userId", userId);
		List<WeiXinCompanyGroupUserDBO> companyUsers = WeixinService_.loadCompanyUser(companyUser);
		if (companyUsers != null && companyUsers.size() == 1) {
			return companyUsers.get(0);
		}
		return null;
	}

	//////////////////////// 用户删除//////////////////////////////
	public String companyIdUserIdDelete(String companyId, String userId) {
		// 企业
		MyWeixinCompanyService companyService = BeanFactoryHelper.getBean(MyWeixinCompanyService.class.getSimpleName());
		WeiXinCompanyDBO company = companyService.loadWeiXinCompanyDBO(companyId);
		// 用户
		WeiXinCompanyGroupUserDBO companyGroupUser = this.loadWeiXinCompanyUserDBO(companyId, userId);

		return WeiXinCompanyTokenService_.deleteCompanyGroupUser(company, companyGroupUser);
	}

	//////////////////////////////////////////////////////
	/**
	 * 获得用户微信ID<br>
	 * 添加一个用户组
	 * 
	 * @param companyUser
	 * @return
	 */
	public String companyUserIdSync(WeiXinCompanyGroupUserReceverBean companyGroupUser) {
		WeiXinCompanyGroupUserDBO companyGroupUserDBO = new WeiXinCompanyGroupUserDBO();
		BeanUtils.copyProperties(companyGroupUser, companyGroupUserDBO);
		return companyGroupUserIdSync(companyGroupUserDBO);
	}

	public String companyGroupUserIdSync(WeiXinCompanyGroupUserDBO companyGroupUser) {
		// 企业
		MyWeixinCompanyService companyService = BeanFactoryHelper.getBean(MyWeixinCompanyService.class.getSimpleName());
		WeiXinCompanyDBO company = companyService.loadWeiXinCompanyDBO(companyGroupUser.getCompanyId());
		// Token
		WeiXinCompanyTokenService token = BeanFactoryHelper.getBean(WeiXinCompanyTokenService.class.getSimpleName());

		String wxId = token.addCompanyGroupUser(company, companyGroupUser);
		if (EmptyHelper.isEmpty(wxId)) {
			return NINE;
		} else {
			companyGroupUser.setWxId(wxId);
			WeixinService_.updateCompanyUserById(companyGroupUser);
		}
		return ZERO;
	}

	public String companyGroupUserIdSync(String companyId, String groupId, String userId) {
		HashMap<String, String> companyGroupUser = new HashMap<String, String>();
		if (EmptyHelper.isNotEmpty(companyId))
			companyGroupUser.put("companyId", companyId);
		if (EmptyHelper.isNotEmpty(groupId))
			companyGroupUser.put("groupId", groupId);
		if (EmptyHelper.isNotEmpty(userId))
			companyGroupUser.put("userId", userId);
		//无条件补充
		if (EmptyHelper.isEmpty(companyGroupUser))
			companyGroupUser.put("wxIdIsNull", ONE);
		List<WeiXinCompanyGroupUserDBO> companyGroupUsers = null;
		
		if (EmptyHelper.isNotEmpty(groupId))
			companyGroupUsers = WeixinService_.loadCompanyGroupUser(companyGroupUser);
		else if (EmptyHelper.isNotEmpty(userId)) 
			companyGroupUsers = WeixinService_.loadCompanyUser(companyGroupUser);
		else if (companyGroupUser.containsKey("wxIdIsNull")) 
			companyGroupUsers = WeixinService_.loadCompanyUser(companyGroupUser);
		
		if (companyGroupUsers != null) {
			for (WeiXinCompanyGroupUserDBO user : companyGroupUsers) {
				if (NINE.equals(companyGroupUserIdSync(user))) {
					logger.error(user.getCompanyId() + "." + user.getUserId() + "===>>>不能获取微信Token");
				}
			}
		}
		return ZERO;
	}

	public String companyUserIdSync(String companyId, String userId) {
		return companyGroupUserIdSync(companyId, EMPTY, userId);
	}

	public String companyGroupUserIdSync(String companyId, String groupId) {
		return companyGroupUserIdSync(companyId, groupId, EMPTY);
	}

	public Object companyUserIdSync() {
		return companyGroupUserIdSync(EMPTY, EMPTY, EMPTY);
	}

}
