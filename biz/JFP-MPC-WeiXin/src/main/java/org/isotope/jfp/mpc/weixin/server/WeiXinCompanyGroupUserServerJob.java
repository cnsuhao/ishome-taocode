package org.isotope.jfp.mpc.weixin.server;

import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.mpc.weixin.service.MyWeixinCompanyService;
import org.isotope.jfp.mpc.weixin.service.MyWeixinGroupService;
import org.isotope.jfp.mpc.weixin.service.MyWeixinUserService;

/**
 * 微信企业用户管理<br>
 * 通讯录管理（用户、用户组、企业）
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
		company.companyIdSync();
	}

	/**
	 * 加载全部企业微信号用户组配置信息
	 */
	public void loadCompanyGroup() {
		MyWeixinGroupService group = BeanFactoryHelper.getBean(MyWeixinGroupService.class.getSimpleName());
		group.companyIdGroupIdSync();
	}

	/**
	 * 加载全部企业微信号用户配置信息
	 */
	public void loadCompanyUser() {
		MyWeixinUserService user = BeanFactoryHelper.getBean(MyWeixinUserService.class.getSimpleName());
		user.companyIdUserIdSync();
	}

}
