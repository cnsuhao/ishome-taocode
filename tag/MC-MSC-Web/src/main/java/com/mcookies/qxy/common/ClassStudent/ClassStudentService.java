package com.mcookies.qxy.common.ClassStudent;

import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 班级学生关联表 */
@Service
public class ClassStudentService extends MyServiceSupport {
	public ClassStudentDao getDao() {
		return getMySqlSession().getMapper(ClassStudentDao.class);
	}

	public void deleteByStudentId(ClassStudentDBO classStudent) {
		getDao().deleteByStudentId(classStudent);
	}

	public void updateCid(ClassStudentDBO classStudent) {
		getDao().updateCid(classStudent);
	}

}
