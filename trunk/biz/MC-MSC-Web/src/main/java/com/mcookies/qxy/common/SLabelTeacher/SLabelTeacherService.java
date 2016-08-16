package com.mcookies.qxy.common.SLabelTeacher;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 教工标签关联表 */
@Service
public class SLabelTeacherService extends MyServiceSupport {

	public SLabelTeacherDao getDao() {
		return getMySqlSession().getMapper(SLabelTeacherDao.class);
	}
	
	public int doDeleteBytid(MyDataBaseObjectSupport paramBean){
		return getDao().doDeleteBytid(paramBean);
	}

}
