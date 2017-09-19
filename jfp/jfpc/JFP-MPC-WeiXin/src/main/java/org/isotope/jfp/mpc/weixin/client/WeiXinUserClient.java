package org.isotope.jfp.mpc.weixin.client;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.mpc.weixin.service.MyWeixinCompanyGroupUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 微信企业号用户管理
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
@Service
public class WeiXinUserClient {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	MyWeixinCompanyGroupUserService MyWeixinUserBusiness_;

	/**
	 * 增加一个企业用户（数据来源于DB）
	 * 
	 * @return
	 */
	public RESTResultBean companyIdUserIdAddPOST(String companyId, String userId) {
		RESTResultBean result = new RESTResultBean();
		result.setResult(MyWeixinUserBusiness_.companyUserIdSync(companyId, userId));
		return result;
	}

	/**
	 * 增加一个企业用户组用户（数据来源于DB）
	 * 
	 * @return
	 */
	public RESTResultBean companyIdGroupIdUserIdAddPOST(String companyId, String groupId, String userId) {
		RESTResultBean result = new RESTResultBean();
		result.setResult(MyWeixinUserBusiness_.companyGroupUserIdSync(companyId, groupId, userId));
		return result;
	}

	/**
	 * 删除一个企业用户（数据来源于接口）
	 * 
	 * @return
	 */
	public RESTResultBean companyIdUserIdDeletePOST(String companyId, String userId) {
		RESTResultBean result = new RESTResultBean();
		result.setResult(MyWeixinUserBusiness_.companyIdUserIdDelete(companyId, userId));
		return result;
	}

	/**
	 * 同步一个企业用户（数据来源于DB监听）
	 * 
	 * @return
	 */
	public RESTResultBean companyIdUserIdSyncPOST(String companyId, String userId) {
		RESTResultBean result = new RESTResultBean();
		result.setResult(MyWeixinUserBusiness_.companyUserIdSync(companyId, userId));
		return result;
	}
}
