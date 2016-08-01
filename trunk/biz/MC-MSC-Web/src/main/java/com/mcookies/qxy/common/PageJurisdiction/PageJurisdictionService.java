package com.mcookies.qxy.common.PageJurisdiction;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 页面权限管理表 */
@Service
public class PageJurisdictionService extends MyServiceSupport {

	public PageJurisdictionDao getDao() {
		return getMySqlSession().getMapper(PageJurisdictionDao.class);
	}

}
