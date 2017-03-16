package org.isotope.jfp.mpc.weixin.client;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.mpc.weixin.service.MyWeixinCompanyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 微信企业号企业管理
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
@Service
public class WeiXinCompanyClient {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	MyWeixinCompanyService MyWeixinCompanyBusiness_;

	/**
	 * 同步一个企业（数据来源于DB监控）
	 * 
	 * @return
	 */
	public RESTResultBean companyIdSyncPOST() {
		RESTResultBean result = new RESTResultBean();
		result.setResult(MyWeixinCompanyBusiness_.companyTokenSync());
		return result;
	}

	/**
	 * 同步一个企业（数据来源于DB监控）
	 * 
	 * @return
	 */
	public RESTResultBean companyIdSyncPOST(String companyId) {
		RESTResultBean result = new RESTResultBean();
		result.setResult(MyWeixinCompanyBusiness_.companyTokenSync(companyId));
		return result;
	}
}
