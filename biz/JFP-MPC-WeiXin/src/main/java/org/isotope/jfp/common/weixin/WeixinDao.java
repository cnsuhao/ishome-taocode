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

}
