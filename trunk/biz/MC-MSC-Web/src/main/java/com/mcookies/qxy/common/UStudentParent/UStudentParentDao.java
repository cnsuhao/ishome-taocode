package com.mcookies.qxy.common.UStudentParent;

import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 学生家长关联表 */
public interface UStudentParentDao extends IDatabaseSupport {

	void deleteByStudentId(UStudentParentDBO studentParent);

	void updateIsDefaultFalse(UStudentParentDBO sp);

}
