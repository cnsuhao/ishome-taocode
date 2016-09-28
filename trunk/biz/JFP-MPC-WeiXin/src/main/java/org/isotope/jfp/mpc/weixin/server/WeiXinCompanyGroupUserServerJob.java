package org.isotope.jfp.mpc.weixin.server;

import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.mpc.weixin.service.MyWeixinCompanyService;
import org.isotope.jfp.mpc.weixin.service.MyWeixinCompanyGroupService;
import org.isotope.jfp.mpc.weixin.service.MyWeixinCompanyGroupUserService;

/**
 * 微信企业用户管理<br>
 * 通讯录管理（用户、用户组、企业）<br>
 * 同步通讯录微信号ID
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 */
public class WeiXinCompanyGroupUserServerJob {
	/**
	 * 加载全部企业微信号配置信息
	 */
	public void loadCompany() {
		MyWeixinCompanyService company = BeanFactoryHelper.getBean(MyWeixinCompanyService.class.getSimpleName());
		company.companyTokenSync();
	}

	/**
	 * 加载全部企业微信号用户组配置信息
	 */
	public void loadCompanyGroup() {
		MyWeixinCompanyGroupService group = BeanFactoryHelper.getBean(MyWeixinCompanyGroupService.class.getSimpleName());
		group.companyGroupIdSync();
	}

	/**
	 * 加载全部企业微信号用户配置信息
	 */
	public void loadCompanyUser() {
		MyWeixinCompanyGroupUserService user = BeanFactoryHelper.getBean(MyWeixinCompanyGroupUserService.class.getSimpleName());
		user.companyUserIdSync();
	}

}
