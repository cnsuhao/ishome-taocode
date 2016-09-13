package org.isotope.jfp.mpc.weixin.service;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.WeixinService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 微信数据管理服务<br>
 * 企业用户组信息
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 * 
 */
@Service
public class MyWeixinGroupService implements ISFrameworkConstants {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	WeixinService WeixinService_;
	@Resource
	WeixinService TXWeixinService_;

	public Object companyIdGroupIdAdd(String companyId, String groupId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object companyIdGroupIdDelete(String companyId, String groupId) {
		// TODO Auto-generated method stub`
		return null;
	}

	public Object companyIdGroupIdSync(String companyId, String groupId) {
		// TODO Auto-generated method stub
		return null;
	}

}
