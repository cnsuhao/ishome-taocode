package com.mcookies.qxy.common.ClassCourse;
import java.util.List;
import java.util.Map;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 班级课程教师关联表*/
@Service
public class ClassCourseService extends MyServiceSupport {

    public ClassCourseDao getDao(){
        return getMySqlSession().getMapper(ClassCourseDao.class);
    }

	public List<ClassCourseDBO> doSelectUseDayList(Map<String,Object> param){
		return getDao().doSelectUseDayList(param);
	}
	
	public List<ClassCourseDBO> doSelectCourseOnDay(MyDataBaseObjectSupport paramBean){
		return getDao().doSelectCourseOnDay(paramBean);
	}
}
