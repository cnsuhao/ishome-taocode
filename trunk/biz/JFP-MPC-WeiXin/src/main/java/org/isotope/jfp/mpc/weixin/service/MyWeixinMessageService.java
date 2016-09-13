package org.isotope.jfp.mpc.weixin.service;

import javax.annotation.Resource;

import org.isotope.jfp.common.weixin.WeixinService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.mpc.weixin.beans.WeiXinMessageValueBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 微信数据管理服务<br>
 * 消息信息
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 * 
 */
@Service
public class MyWeixinMessageService implements ISFrameworkConstants {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	WeixinService WeixinService_;
	@Resource
	WeixinService TXWeixinService_;

	public String sendToCompanyId(String companyId, WeiXinMessageValueBean message) {
		// TODO Auto-generated method stub
		return null;
	}

	public String sendToCompanyIdGroupId(String companyId, String groupId, WeiXinMessageValueBean message) {
		// TODO Auto-generated method stub
		return null;
	}

	public String sendToCompanyIdUserId(String companyId, String userId, WeiXinMessageValueBean message) {
		// TODO Auto-generated method stub
		return null;
	}

}
