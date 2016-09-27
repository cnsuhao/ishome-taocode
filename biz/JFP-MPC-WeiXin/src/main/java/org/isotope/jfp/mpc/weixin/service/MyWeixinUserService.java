package org.isotope.jfp.mpc.weixin.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.WeiXinCompanyUserDBO;
import org.isotope.jfp.common.weixin.WeixinService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyGroupReceverBean;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyGroupUserReceverBean;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyUserReceverBean;
import org.isotope.jfp.mpc.weixin.beans.sender.WeiXinCompanySenderBean;
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
@Service("MyWeixinUserService")
public class MyWeixinUserService implements ISFrameworkConstants {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	WeixinService WeixinService_;
	@Resource
	WeiXinCompanyTokenService WeiXinCompanyTokenService_;

	public WeiXinCompanyUserReceverBean loadWeiXinUserReceverBean(String companyId, String userId) {
		HashMap<String, String> companyUser = new HashMap<String, String>();
		companyUser.put("companyId", companyId);
		companyUser.put("userId", userId);
		List<WeiXinCompanyUserDBO> companyUsers = WeixinService_.loadCompanyUser(companyUser);
		if (companyUsers != null && companyUsers.size() == 1) {
			WeiXinCompanyUserDBO companyUserDBO = companyUsers.get(0);
			WeiXinCompanyUserReceverBean recever = new WeiXinCompanyUserReceverBean();
			recever.setCompanyId(companyUserDBO.getCompanyId());
			recever.setUserId(companyUserDBO.getUserId());
			recever.setWxId(companyUserDBO.getWxId());
			return recever;
		}
		return null;
	}

	////////////////////////////////////////////////////
	public Object companyIdUserIdAdd(String companyId, String userId) {
		return companyIdGroupIdUserIdAdd(companyId, EMPTY, userId);
	}

	public String companyIdGroupIdUserIdAdd(String companyId, String groupId, String userId) {
		MyWeixinCompanyService company = BeanFactoryHelper.getBean(MyWeixinCompanyService.class.getSimpleName());
		// 企业
		WeiXinCompanySenderBean sender = company.loadWeiXinCompanySenderBean(companyId);
		// 用户
		WeiXinCompanyUserReceverBean companyUser = this.loadWeiXinUserReceverBean(companyId, userId);
		// 企业用户组用户
		WeiXinCompanyGroupUserReceverBean companyGroupUser = new WeiXinCompanyGroupUserReceverBean();

		BeanUtils.copyProperties(companyUser, companyGroupUser);
		companyGroupUser.setCompanyId(sender.getAppId());
		if (EmptyHelper.isNotEmpty(groupId)) {
			// 用户组
			MyWeixinGroupService group = BeanFactoryHelper.getBean(MyWeixinGroupService.class.getSimpleName());
			WeiXinCompanyGroupReceverBean companyGroup = group.loadWeiXinCompanyGroupReceverBean(companyId, groupId);
			companyGroupUser.setGroupid(companyGroup.getWxId());
		}

		return WeiXinCompanyTokenService_.addCompanyGroupUser(sender, companyGroupUser);
	}

	public String companyIdGroupIdUserIdAdd(WeiXinCompanyGroupUserReceverBean companyGroupUser) {
		MyWeixinCompanyService company = BeanFactoryHelper.getBean(MyWeixinCompanyService.class.getSimpleName());
		// 企业
		WeiXinCompanySenderBean sender = company.loadWeiXinCompanySenderBean(companyGroupUser.getCompanyId());

		return WeiXinCompanyTokenService_.addCompanyGroupUser(sender, companyGroupUser);
	}

	public String companyIdUserIdAdd(WeiXinCompanyUserReceverBean companyUser) {
		MyWeixinCompanyService company = BeanFactoryHelper.getBean(MyWeixinCompanyService.class.getSimpleName());
		WeiXinCompanySenderBean sender = company.loadWeiXinCompanySenderBean(companyUser.getCompanyId());
		WeiXinCompanyGroupUserReceverBean companyGroupUser = new WeiXinCompanyGroupUserReceverBean();

		BeanUtils.copyProperties(companyUser, companyGroupUser);
		companyGroupUser.setCompanyId(sender.getAppId());

		return WeiXinCompanyTokenService_.addCompanyGroupUser(sender, companyGroupUser);
	}

	//////////////////////////////////////////////////////

	public String companyIdUserIdDelete(String companyId, String userId) {
		return companyGroupIdUserIdDelete(companyId, EMPTY, userId);
	}

	public String companyGroupIdUserIdDelete(String companyId, String groupId, String userId) {
		MyWeixinCompanyService company = BeanFactoryHelper.getBean(MyWeixinCompanyService.class.getSimpleName());
		// 企业
		WeiXinCompanySenderBean sender = company.loadWeiXinCompanySenderBean(companyId);
		// 用户
		WeiXinCompanyUserReceverBean companyUser = this.loadWeiXinUserReceverBean(companyId, userId);
		// 企业用户组用户
		WeiXinCompanyGroupUserReceverBean companyGroupUser = new WeiXinCompanyGroupUserReceverBean();

		BeanUtils.copyProperties(companyUser, companyGroupUser);
		companyGroupUser.setCompanyId(sender.getAppId());
		if (EmptyHelper.isNotEmpty(groupId)) {
			// 用户组
			MyWeixinGroupService group = BeanFactoryHelper.getBean(MyWeixinGroupService.class.getSimpleName());
			WeiXinCompanyGroupReceverBean companyGroup = group.loadWeiXinCompanyGroupReceverBean(companyId, groupId);
			companyGroupUser.setGroupid(companyGroup.getWxId());
		}

		return WeiXinCompanyTokenService_.deleteCompanyGroupUser(sender, companyGroupUser);
	}

	public String companyIdUserIdDelete(WeiXinCompanyGroupUserReceverBean companyGroupUser) {
		MyWeixinCompanyService company = BeanFactoryHelper.getBean(MyWeixinCompanyService.class.getSimpleName());
		// 企业
		WeiXinCompanySenderBean sender = company.loadWeiXinCompanySenderBean(companyGroupUser.getCompanyId());

		return WeiXinCompanyTokenService_.deleteCompanyGroupUser(sender, companyGroupUser);
	}

	public String companyIdUserIdDelete(WeiXinCompanyUserReceverBean companyUser) {
		MyWeixinCompanyService company = BeanFactoryHelper.getBean(MyWeixinCompanyService.class.getSimpleName());
		WeiXinCompanySenderBean sender = company.loadWeiXinCompanySenderBean(companyUser.getCompanyId());
		WeiXinCompanyGroupUserReceverBean companyGroupUser = new WeiXinCompanyGroupUserReceverBean();

		BeanUtils.copyProperties(companyUser, companyGroupUser);
		companyGroupUser.setCompanyId(sender.getAppId());

		return WeiXinCompanyTokenService_.deleteCompanyGroupUser(sender, companyGroupUser);
	}

	//////////////////////////////////////////////////////
	/**
	 * 获得用户微信ID
	 * 
	 * @param companyUser
	 * @return
	 */
	public String companyUserIdSync(WeiXinCompanyGroupUserReceverBean companyGroupUser) {
		// 企业
		MyWeixinCompanyService company = BeanFactoryHelper.getBean(MyWeixinCompanyService.class.getSimpleName());
		WeiXinCompanySenderBean sender = company.loadWeiXinCompanySenderBean(companyGroupUser.getCompanyId());
		// Token
		WeiXinCompanyTokenService token = BeanFactoryHelper.getBean(WeiXinCompanyTokenService.class.getSimpleName());
		companyGroupUser.setCompanyId(sender.getAppId());

		String wxId = token.loadWeixinCompanyGroupUserId(sender, companyGroupUser);
		if (EmptyHelper.isEmpty(wxId)) {
			return NINE;
		} else {
			WeiXinCompanyUserDBO companyUser = new WeiXinCompanyUserDBO();
			BeanUtils.copyProperties(companyGroupUser, companyUser);
			companyUser.setWxId(wxId);
			companyUser.setCompanyId(sender.getCompanyId());
			WeixinService_.updateCompanyUserById(companyUser);
		}
		return ZERO;
	}

	public String companyGroupUserIdSync(WeiXinCompanyUserDBO companyUser) {
		// 企业
		MyWeixinCompanyService company = BeanFactoryHelper.getBean(MyWeixinCompanyService.class.getSimpleName());
		WeiXinCompanySenderBean sender = company.loadWeiXinCompanySenderBean(companyUser.getCompanyId());
		// Token
		WeiXinCompanyTokenService token = BeanFactoryHelper.getBean(WeiXinCompanyTokenService.class.getSimpleName());

		WeiXinCompanyGroupUserReceverBean companyGroupUser = new WeiXinCompanyGroupUserReceverBean();
		BeanUtils.copyProperties(companyUser, companyGroupUser);

		companyGroupUser.setCompanyId(sender.getAppId());

		String wxId = token.loadWeixinCompanyGroupUserId(sender, companyGroupUser);
		if (EmptyHelper.isEmpty(wxId)) {
			return NINE;
		} else {
			companyUser.setWxId(wxId);
			WeixinService_.updateCompanyUserById(companyUser);
		}
		return ZERO;
	}

	public String companyGroupUserIdSync(String companyId, String groupId) {
		return companyGroupUserIdSync(companyId, groupId, EMPTY);
	}

	public String companyGroupUserIdSync(String companyId, String groupId, String userId) {
		HashMap<String, String> companyUser = new HashMap<String, String>();
		if (EmptyHelper.isNotEmpty(companyId))
			companyUser.put("companyId", companyId);
		if (EmptyHelper.isNotEmpty(groupId))
			companyUser.put("groupId", groupId);
		if (EmptyHelper.isNotEmpty(userId))
			companyUser.put("userId", userId);
		if (EmptyHelper.isEmpty(companyUser))
			companyUser.put("wxIdIsNull", ONE);
		List<WeiXinCompanyUserDBO> companyUsers = WeixinService_.loadCompanyUser(companyUser);
		if (companyUsers != null) {
			for (WeiXinCompanyUserDBO user : companyUsers) {
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

	public Object companyUserIdSync() {
		return companyGroupUserIdSync(EMPTY, EMPTY, EMPTY);
	}

}
