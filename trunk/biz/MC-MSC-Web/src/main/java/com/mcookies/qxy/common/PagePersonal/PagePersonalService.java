package com.mcookies.qxy.common.PagePersonal;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 个人权限页面表 */
@Service
public class PagePersonalService extends MyServiceSupport {

	public PagePersonalDao getDao() {
		return getMySqlSession().getMapper(PagePersonalDao.class);
	}

}
