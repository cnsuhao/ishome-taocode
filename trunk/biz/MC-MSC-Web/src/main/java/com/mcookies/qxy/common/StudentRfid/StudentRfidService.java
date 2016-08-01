package com.mcookies.qxy.common.StudentRfid;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 学生一卡通关联表 */
@Service
public class StudentRfidService extends MyServiceSupport {

	public StudentRfidDao getDao() {
		return getMySqlSession().getMapper(StudentRfidDao.class);
	}

}
