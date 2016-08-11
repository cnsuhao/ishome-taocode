package com.mcookies.qxy.common.UParent;

import java.util.List;

import org.isotope.jfp.framework.support.IDatabaseSupport;

import com.mcookies.qxy.common.UStudent.UStudentDBO;

/** 家长表 */
public interface UParentDao extends IDatabaseSupport {

	List<UParentPVO> findByStudentId(UStudentDBO student);

}
