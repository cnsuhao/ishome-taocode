package com.mcookies.qxy.common.SCourse;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 课表内容标签表 */
@Service
public class SCourseService extends MyServiceSupport {

	public SCourseDao getDao() {
		return getMySqlSession().getMapper(SCourseDao.class);
	}

}
