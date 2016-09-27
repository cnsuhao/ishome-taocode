package org.isotope.jfp.common.weixin;

import java.util.HashMap;
import java.util.List;

import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 微信相关信息 */
@Service
public class WeixinService extends MyServiceSupport implements ISFrameworkConstants {

	public WeixinDao getWeixinDao() {
		return getMySqlSession().getMapper(WeixinDao.class);
	}

	/**
	 * 获得微信企业号表数据
	 */
	public List<WeiXinCompanyDBO> loadCompany(HashMap<String, String> comyany) {
		return getWeixinDao().loadCompany(comyany);
	}

	/**
	 * 获得微信企业号用户组数据
	 */
	public List<WeiXinCompanyGroupDBO> loadCompanyGroup(HashMap<String, String> companyGroup) {
		return getWeixinDao().loadCompanyGroup(companyGroup);
	}

	/**
	 * 获得微信企业号标签数据
	 */
	public List<WeiXinCompanyTagDBO> loadCompanyTag(HashMap<String, String> companyTag) {
		return getWeixinDao().loadCompanyTag(companyTag);
	}

	/**
	 * 获得微信企业号关注用户表数据
	 */
	public List<WeiXinCompanyUserDBO> loadCompanyUser(HashMap<String, String> companyUser) {
		return getWeixinDao().loadCompanyUser(companyUser);
	}

	/**
	 * 更新企业微信ID
	 * 
	 * @param comyany
	 * @return
	 */
	public int updateCompanyById(WeiXinCompanyDBO comyany) {
		return getWeixinDao().updateCompanyById(comyany);
	}

	/**
	 * 更新企业用户组微信ID
	 * 
	 * @param comyany
	 * @return
	 */
	public int updateCompanyGroupById(WeiXinCompanyGroupDBO companyGroup) {
		return getWeixinDao().updateCompanyGroupById(companyGroup);
	}

	/**
	 * 更新企业标签组ID
	 * 
	 * @param comyany
	 * @return
	 */
	public int updateCompanyTagById(WeiXinCompanyTagDBO companyTag) {
		return getWeixinDao().updateCompanyTagById(companyTag);
	}

	/**
	 * 更新企业用户微信ID
	 * 
	 * @param comyany
	 * @return
	 */
	public int updateCompanyUserById(WeiXinCompanyUserDBO companyUser) {
		return getWeixinDao().updateCompanyUserById(companyUser);
	}
}
