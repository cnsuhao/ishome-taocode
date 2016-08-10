package com.mcookies.qxy.common.UStudent;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 学生表 */
@Service
public class UStudentService extends MyServiceSupport {

	public UStudentDao getDao() {
		return getMySqlSession().getMapper(UStudentDao.class);
	}

	public List<? extends FrameworkDataBean> doSelectListBycid(FrameworkDataBean formParam){
		return getDao().doSelectListBycid(formParam);
	}


	/**
	 * 根据studentId或number学号查询
	 * @param student
	 * @return
	 */
	public UStudentPVO findByIdOrNumber(UStudentPVO student) {
		return getDao().findByIdOrNumber(student);
	}

	/**
	 * 根据studentId或number学号查询，多了年级名称
	 * @param student
	 * @return
	 */
	public UStudentPVO findByIdOrNumberWithGrade(UStudentPVO student) {
		return getDao().findByIdOrNumberWithGrade(student);
	}
	
}
