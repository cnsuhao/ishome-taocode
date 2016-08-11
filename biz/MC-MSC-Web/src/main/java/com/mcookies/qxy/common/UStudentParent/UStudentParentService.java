package com.mcookies.qxy.common.UStudentParent;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 学生家长关联表 */
@Service
public class UStudentParentService extends MyServiceSupport {

	public UStudentParentDao getDao() {
		return getMySqlSession().getMapper(UStudentParentDao.class);
	}

	public void deleteByStudentId(UStudentParentDBO studentParent) {
		getDao().deleteByStudentId(studentParent);
	}

	/**
	 * 修改studentId对应的学生家长关系的默认为0
	 * @param sp
	 */
	public void updateIsDefaultFalse(UStudentParentDBO sp) {
		getDao().updateIsDefaultFalse(sp);
	}

}
