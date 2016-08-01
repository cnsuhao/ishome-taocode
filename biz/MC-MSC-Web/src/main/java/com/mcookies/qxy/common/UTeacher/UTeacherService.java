package com.mcookies.qxy.common.UTeacher;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 教师表 */
@Service
public class UTeacherService extends MyServiceSupport {

	public UTeacherDao getDao() {
		return getMySqlSession().getMapper(UTeacherDao.class);
	}

}
