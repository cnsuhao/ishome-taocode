package com.mcookies.qxy.common.ClassTeacher;

import java.util.List;

import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 班级教师关联表 */
public interface ClassTeacherDao extends IDatabaseSupport {

	List<ClassTeacherPVO> findByCid(ClassTeacherDBO classTeacher);
	List<ClassTeacherPVO> doSelectTeacherName(ClassTeacherDBO classTeacher);

}
