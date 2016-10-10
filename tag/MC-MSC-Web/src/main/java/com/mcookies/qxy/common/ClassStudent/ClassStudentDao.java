package com.mcookies.qxy.common.ClassStudent;

import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 班级学生关联表 */
public interface ClassStudentDao extends IDatabaseSupport {

	void deleteByStudentId(ClassStudentDBO classStudent);

	void updateCid(ClassStudentDBO classStudent);

}
