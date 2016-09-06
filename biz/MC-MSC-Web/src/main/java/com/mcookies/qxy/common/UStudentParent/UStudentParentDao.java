package com.mcookies.qxy.common.UStudentParent;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.IDatabaseSupport;

import com.mcookies.qxy.common.Class.ClassPVO;
import com.mcookies.qxy.common.ClassStudent.ClassStudentPVO;

/** 学生家长关联表 */
public interface UStudentParentDao extends IDatabaseSupport {

	void deleteByStudentId(UStudentParentDBO studentParent);

	void updateIsDefaultFalse(UStudentParentDBO sp);

	List<? extends FrameworkDataBean> doSelectPageByTermIdAndCidAndStudentId(PageVOSupport pageModel);
	
	List<UStudentParentPVO> doFindByParentId(UStudentParentPVO uStudentParentPVO);
	
	UStudentParentDBO doReadByParentId(UStudentParentDBO uStudentParentDBO);
}
