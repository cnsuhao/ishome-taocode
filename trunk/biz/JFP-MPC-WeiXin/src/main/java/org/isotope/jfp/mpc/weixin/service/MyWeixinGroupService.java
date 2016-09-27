package org.isotope.jfp.mpc.weixin.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.WeiXinCompanyGroupDBO;
import org.isotope.jfp.common.weixin.WeixinService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyGroupReceverBean;
import org.isotope.jfp.mpc.weixin.beans.sender.WeiXinCompanySenderBean;
import org.isotope.jfp.mpc.weixin.token.WeiXinCompanyTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 微信数据管理服务<br>
 * 企业用户组信息(接收者)
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 * 
 */
@Service("MyWeixinGroupService")
public class MyWeixinGroupService implements ISFrameworkConstants {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	WeixinService WeixinService_;
	@Resource
	WeiXinCompanyTokenService WeiXinCompanyTokenService_;

	public WeiXinCompanyGroupReceverBean loadWeiXinCompanyGroupReceverBean(String companyId, String groupId) {
		HashMap<String, String> companyGroup = new HashMap<String, String>();
		companyGroup.put("companyId", companyId);
		companyGroup.put("groupId", groupId);
		List<WeiXinCompanyGroupDBO> companyGroups = WeixinService_.loadCompanyGroup(companyGroup);
		if (companyGroups != null && companyGroups.size() == 1) {
			WeiXinCompanyGroupDBO companyGroupDBO = companyGroups.get(0);
			WeiXinCompanyGroupReceverBean recever = new WeiXinCompanyGroupReceverBean();
			recever.setCompanyId(companyGroupDBO.getCompanyId());
			recever.setGroupName(companyGroupDBO.getGroupName());
			recever.setWxId(companyGroupDBO.getWxId());
			return recever;
		}
		return null;
	}

	/**
	 * 获得企业用户组ID
	 * 
	 * @param companyGroup
	 * @return
	 */
	public String companyIdGroupIdSync(String companyId, String groupId) {
		HashMap<String, String> companyGroup = new HashMap<String, String>();
		companyGroup.put("companyId", companyId);
		companyGroup.put("groupId", groupId);
		List<WeiXinCompanyGroupDBO> companyGroups = WeixinService_.loadCompanyGroup(companyGroup);
		if (companyGroups != null && companyGroups.size() == 1) {
			return companyGroupIdSync(companyGroups.get(0));
		} else
			return ONE;
	}

	/**
	 * 获得企业用户组ID
	 * 
	 * @param companyGroup
	 * @return
	 */
	public String companyGroupIdSync(WeiXinCompanyGroupDBO companyGroup) {
		// 企业
		MyWeixinCompanyService company = BeanFactoryHelper.getBean(MyWeixinCompanyService.class.getSimpleName());
		WeiXinCompanySenderBean sender = company.loadWeiXinCompanySenderBean(companyGroup.getCompanyId());
		// 用户组
		WeiXinCompanyGroupReceverBean recever = new WeiXinCompanyGroupReceverBean();
		// Token
		WeiXinCompanyTokenService token = BeanFactoryHelper.getBean(WeiXinCompanyTokenService.class.getSimpleName());

		String wxId = token.loadWeixinCompanyGroupId(sender, recever);
		if (EmptyHelper.isEmpty(wxId)) {
			return NINE;
		} else {
			companyGroup.setWxId(wxId);
			WeixinService_.updateCompanyGroupById(companyGroup);
		}
		return ZERO;
	}

	public String companyGroupIdSync() {
		HashMap<String, String> companyGroup = new HashMap<String, String>();
		companyGroup.put("wxIdIsNull", ONE);
		List<WeiXinCompanyGroupDBO> companyGroups = WeixinService_.loadCompanyGroup(companyGroup);
		if (companyGroups != null) {
			for (WeiXinCompanyGroupDBO group : companyGroups) {
				if (NINE.equals(companyGroupIdSync(group))) {
					logger.error(group.getCompanyId() + "." + group.getGroupId() + "===>>>不能获取微信Token");
				}
			}
		}
		return ZERO;
	}

	public String companyIdGroupIdSync(String companyId) {
		HashMap<String, String> companyGroup = new HashMap<String, String>();
		companyGroup.put("companyId", companyId);
		companyGroup.put("wxIdIsNull", ONE);
		List<WeiXinCompanyGroupDBO> companyGroups = WeixinService_.loadCompanyGroup(companyGroup);
		if (companyGroups != null) {
			for (WeiXinCompanyGroupDBO group : companyGroups) {
				if (NINE.equals(companyGroupIdSync(group))) {
					logger.error(group.getCompanyId() + "." + group.getGroupId() + "===>>>不能获取微信Token");
				}
			}
		}

		return ZERO;
	}

	///////////////////////////////////////////////////////////////////////////////

	public String companyGroupIdAdd(WeiXinCompanyGroupReceverBean companyGroup) {
		MyWeixinCompanyService company = BeanFactoryHelper.getBean(MyWeixinCompanyService.class.getSimpleName());
		WeiXinCompanySenderBean sender = company.loadWeiXinCompanySenderBean(companyGroup.getCompanyId());
		return WeiXinCompanyTokenService_.addCompanyGroup(sender, companyGroup);
	}

	public String companyIdGroupIdAdd(String companyId, String groupId) {
		MyWeixinCompanyService company = BeanFactoryHelper.getBean(MyWeixinCompanyService.class.getSimpleName());
		WeiXinCompanySenderBean sender = company.loadWeiXinCompanySenderBean(companyId);
		WeiXinCompanyGroupReceverBean recever = this.loadWeiXinCompanyGroupReceverBean(companyId, groupId);
		return WeiXinCompanyTokenService_.addCompanyGroup(sender, recever);
	}

	public String companyGroupIdDelete(WeiXinCompanyGroupReceverBean companyGroup) {
		MyWeixinCompanyService company = BeanFactoryHelper.getBean(MyWeixinCompanyService.class.getSimpleName());
		WeiXinCompanySenderBean sender = company.loadWeiXinCompanySenderBean(companyGroup.getCompanyId());
		return WeiXinCompanyTokenService_.deleteCompanyGroup(sender, companyGroup);
	}

	public String companyIdGroupIdDelete(String companyId, String groupId) {
		MyWeixinCompanyService company = BeanFactoryHelper.getBean(MyWeixinCompanyService.class.getSimpleName());
		WeiXinCompanySenderBean sender = company.loadWeiXinCompanySenderBean(companyId);
		WeiXinCompanyGroupReceverBean recever = this.loadWeiXinCompanyGroupReceverBean(companyId, groupId);
		return WeiXinCompanyTokenService_.deleteCompanyGroup(sender, recever);
	}
}
