package org.isotope.jfp.mpc.weixin.client;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.mpc.weixin.service.MyWeixinCompanyGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 微信企业号用户组管理
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
@Service
public class WeiXinGroupClient {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	MyWeixinCompanyGroupService MyWeixinGroupBusiness_;

	/**
	 * 删除一个企业用户组（数据来源于接口）
	 * 
	 * @return
	 */
	public RESTResultBean companyIdGroupIdDeletePOST(String companyId, String groupId) {
		RESTResultBean result = new RESTResultBean();
		result.setResult(MyWeixinGroupBusiness_.companyIdGroupIdDelete(companyId, groupId));
		return result;
	}

	/**
	 * 同步一个企业（数据来源于DB监控）
	 * 
	 * @return
	 */
	public RESTResultBean companyIdSyncPOST(String companyId) {
		RESTResultBean result = new RESTResultBean();
		result.setResult(MyWeixinGroupBusiness_.companyIdGroupIdSync(companyId));
		return result;
	}

	/**
	 * 同步一个企业用户组（数据来源于DB监听）
	 * 
	 * @return
	 */
	public RESTResultBean companyIdGroupIdSyncPOST(String companyId, String groupId) {
		RESTResultBean result = new RESTResultBean();
		result.setResult(MyWeixinGroupBusiness_.companyGroupIdSync(companyId, groupId));
		return result;
	}
}
