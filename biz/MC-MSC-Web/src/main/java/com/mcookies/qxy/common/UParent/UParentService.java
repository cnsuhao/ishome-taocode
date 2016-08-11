package com.mcookies.qxy.common.UParent;

import java.util.List;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

import com.mcookies.qxy.common.UStudent.UStudentDBO;

/** 家长表 */
@Service
public class UParentService extends MyServiceSupport {

	public UParentDao getDao() {
		return getMySqlSession().getMapper(UParentDao.class);
	}

	/**
	 * 根据studentId和isUse查找学生家长信息
	 * @param student
	 * @return
	 */
	public List<UParentPVO> findByStudentId(UStudentDBO student) {
		return getDao().findByStudentId(student);
	}

}
