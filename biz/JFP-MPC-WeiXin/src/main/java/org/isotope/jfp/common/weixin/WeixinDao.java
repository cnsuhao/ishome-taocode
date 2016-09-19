package org.isotope.jfp.common.weixin;

import java.util.HashMap;
import java.util.List;

/** 微信相关信息 */
public interface WeixinDao {
	/**
	 * 获得微信企业号表数据
	 */
	public List<WeiXinCompanyDBO> loadCompany(HashMap<String, String> comyany);

	/**
	 * 获得微信企业号关注用户表数据
	 */
	public List<WeixinUserDBO> loadCompanyUser(HashMap<String, String> companyUser);

	
	/**
	 * 获得微信企业号部门关注用户表数据
	 */
	public List<WeixinUserDBO> loadCompanyDeptUser(HashMap<String, String> companyDeptUser);

	
	/**
	 * 获得微信企业号标签关注用户表数据
	 */
	public List<WeixinUserDBO> loadCompanyTagUser(HashMap<String, String> companyTagUser);

	// 获得全部企业
	// 获得企业所有班级
	// 获得班级全部家长
}
