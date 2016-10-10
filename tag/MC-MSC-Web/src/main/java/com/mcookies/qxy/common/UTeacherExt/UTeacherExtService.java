package com.mcookies.qxy.common.UTeacherExt;

import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 教师信息扩展表 */
@Service
public class UTeacherExtService extends MyServiceSupport {

	public UTeacherExtDao getDao() {
		return getMySqlSession().getMapper(UTeacherExtDao.class);
	}

}
