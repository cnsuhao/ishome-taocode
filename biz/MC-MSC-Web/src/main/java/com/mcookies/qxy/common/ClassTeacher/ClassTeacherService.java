package com.mcookies.qxy.common.ClassTeacher;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 班级教师关联表 */
@Service
public class ClassTeacherService extends MyServiceSupport {

	public ClassTeacherDao getDao() {
		return getMySqlSession().getMapper(ClassTeacherDao.class);
	}

}
