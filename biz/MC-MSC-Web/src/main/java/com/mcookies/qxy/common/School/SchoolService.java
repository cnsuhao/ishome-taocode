package com.mcookies.qxy.common.School;

import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 学校表 */
@Service
public class SchoolService extends MyServiceSupport {

	public SchoolDao getDao() {
		return getMySqlSession().getMapper(SchoolDao.class);
	}

}
