package org.isotope.jfp.mpc.weixin.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.WeiXinCompanyDBO;
import org.isotope.jfp.common.weixin.WeiXinCompanyGroupDBO;
import org.isotope.jfp.common.weixin.WeixinService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.mpc.weixin.beans.recever.WeiXinCompanyGroupReceverBean;
import org.isotope.jfp.mpc.weixin.token.WeiXinCompanyTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
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
@Service("MyWeixinCompanyGroupService")
public class MyWeixinCompanyGroupService implements ISFrameworkConstants {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	WeixinService WeixinService_;
	@Resource
	WeiXinCompanyTokenService WeiXinCompanyTokenService_;

	public WeiXinCompanyGroupReceverBean loadWeiXinCompanyGroupReceverBean(String companyId, String groupId) {
		WeiXinCompanyGroupDBO companyGroupDBO = loadWeiXinCompanyGroupDBO(companyId, groupId);
		if (EmptyHelper.isNotEmpty(companyGroupDBO)) {
			WeiXinCompanyGroupReceverBean recever = new WeiXinCompanyGroupReceverBean();
			recever.setCompanyId(companyGroupDBO.getCompanyId());
			recever.setGroupName(companyGroupDBO.getGroupName());
			recever.setWxId(companyGroupDBO.getWxId());
			return recever;
		}
		return null;
	}

	public WeiXinCompanyGroupDBO loadWeiXinCompanyGroupDBO(String companyId, String groupId) {
		HashMap<String, String> companyGroup = new HashMap<String, String>();
		companyGroup.put("companyId", companyId);
		companyGroup.put("groupId", groupId);
		List<WeiXinCompanyGroupDBO> companyGroups = WeixinService_.loadCompanyGroup(companyGroup);
		if (companyGroups != null && companyGroups.size() == 1) {
			return companyGroups.get(0);
		}
		return null;
	}

	//////////////////////////////添加用户组/////////////////////////////////////////
	/**
	 * 获得企业用户组ID
	 * 
	 * @param companyGroup
	 * @return
	 */
	public String companyGroupIdSync(String companyId, String groupId) {
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
	 * 获得企业用户组ID<br>
	 * 添加一个用户组
	 * 
	 * @param companyGroup
	 * @return
	 */
	public String companyGroupIdSync(WeiXinCompanyGroupReceverBean companyGroup) {
		// 用户组
		WeiXinCompanyGroupDBO companyGroupDBO = new WeiXinCompanyGroupDBO();
		BeanUtils.copyProperties(companyGroup, companyGroupDBO);
		return companyGroupIdSync(companyGroupDBO);
	}

	public String companyGroupIdSync(WeiXinCompanyGroupDBO companyGroup) {
		// 企业
		MyWeixinCompanyService companyService = BeanFactoryHelper.getBean(MyWeixinCompanyService.class.getSimpleName());
		WeiXinCompanyDBO company = companyService.loadWeiXinCompanyDBO(companyGroup.getCompanyId());
		// Token
		WeiXinCompanyTokenService token = BeanFactoryHelper.getBean(WeiXinCompanyTokenService.class.getSimpleName());
		// 添加用户组
		String wxId = token.addCompanyGroup(company, companyGroup);
		if (EmptyHelper.isEmpty(wxId)) {
			return NINE;
		} else {
			companyGroup.setWxId(wxId);
			WeixinService_.updateCompanyGroupById(companyGroup);
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

	//////////////////////////////////删除用户组/////////////////////////////////////////////
	public String companyIdGroupIdDelete(String companyId, String groupId) {
		// 企业
		MyWeixinCompanyService companyService = BeanFactoryHelper.getBean(MyWeixinCompanyService.class.getSimpleName());
		WeiXinCompanyDBO company = companyService.loadWeiXinCompanyDBO(companyId);
		//用户组
		WeiXinCompanyGroupDBO companyGroup = this.loadWeiXinCompanyGroupDBO(companyId, groupId);
		return WeiXinCompanyTokenService_.deleteCompanyGroup(company, companyGroup);
	}
}
