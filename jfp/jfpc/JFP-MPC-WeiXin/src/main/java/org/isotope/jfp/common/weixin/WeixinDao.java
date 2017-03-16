package org.isotope.jfp.common.weixin;

import java.util.HashMap;
import java.util.List;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

/** 微信相关信息 */
public interface WeixinDao {
	/**
	 * 获得微信企业号表数据
	 */
	public List<WeiXinCompanyDBO> loadCompany(HashMap<String, String> comyany);

	/**
	 * 获得微信企业号用户组数据
	 */
	public List<WeiXinCompanyGroupDBO> loadCompanyGroup(HashMap<String, String> companyGroup);

	/**
	 * 获得微信企业号标签数据
	 */
	public List<WeiXinCompanyTagDBO> loadCompanyTag(HashMap<String, String> companyTag);

	/**
	 * 获得微信企业号用户组用户数据
	 */
	public List<WeiXinCompanyGroupUserDBO> loadCompanyGroupUser(HashMap<String, String> companyGroupUser);
	/**
	 * 获得微信企业号关注用户数据
	 */
	public List<WeiXinCompanyGroupUserDBO> loadCompanyUser(HashMap<String, String> companyGroupUser);

	//////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 更新企业用户组微信ID
	 * 
	 * @param comyany
	 * @return
	 */
	public int updateCompanyGroupById(WeiXinCompanyGroupDBO companyGroup);

	/**
	 * 更新企业标签组ID
	 * 
	 * @param comyany
	 * @return
	 */
	public int updateCompanyTagById(WeiXinCompanyTagDBO companyTag);

	/**
	 * 更新企业用户微信ID
	 * 
	 * @param comyany
	 * @return
	 */
	public int updateCompanyUserById(WeiXinCompanyGroupUserDBO companyUser);
	
	public int doInsert(MyDataBaseObjectSupport swxdbo);
}