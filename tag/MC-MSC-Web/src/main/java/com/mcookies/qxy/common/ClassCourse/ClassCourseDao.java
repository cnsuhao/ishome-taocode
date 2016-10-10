package com.mcookies.qxy.common.ClassCourse;

import java.util.List;
import java.util.Map;

import org.isotope.jfp.framework.support.IDatabaseSupport;
import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

/** 班级课程教师关联表*/
public interface ClassCourseDao extends IDatabaseSupport{
	List<ClassCourseDBO> doSelectUseDayList(Map<String,Object> param);
	List<ClassCourseDBO> doSelectCourseOnDay(MyDataBaseObjectSupport paramBean);
	
}
