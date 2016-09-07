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
	 * 获得微信企业号关注用户表数据
	 */
	public List<WeixinUserDBO> loadCompanyUser(HashMap<String, String> companyUser) {
		return getWeixinDao().loadCompanyUser(companyUser);
	}
}
