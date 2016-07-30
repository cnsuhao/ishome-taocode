package com.mcookies.qxy.base.UStudentExt;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 学生信息扩展表 */
@Service
public class UStudentExtService extends MyServiceSupport {

	public UStudentExtDao getDao() {
		return getMySqlSession().getMapper(UStudentExtDao.class);
	}

}
