package com.mcookies.qxy.common.UStudentParent;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 学生家长关联表 */
@Service
public class UStudentParentService extends MyServiceSupport {

	public UStudentParentDao getDao() {
		return getMySqlSession().getMapper(UStudentParentDao.class);
	}

}
