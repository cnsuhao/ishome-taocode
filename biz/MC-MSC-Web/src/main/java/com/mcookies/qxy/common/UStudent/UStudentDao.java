package com.mcookies.qxy.common.UStudent;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 学生表 */
public interface UStudentDao extends IDatabaseSupport {
	
	List<? extends FrameworkDataBean> doSelectListBycid(FrameworkDataBean formParam);

	UStudentPVO findByIdOrNumber(UStudentPVO student);

	UStudentPVO findByIdOrNumberWithGrade(UStudentPVO student);

	List<? extends FrameworkDataBean> doSelectPageByCid(PageVOSupport pageModel);

	List<? extends FrameworkDataBean> doSelectPageByCidWithClassAndGrade(PageVOSupport pageModel);

	List<? extends FrameworkDataBean> doSelectPageByParentIdAndTermIdAndStudentID(PageVOSupport pageModel);
	
	List<? extends FrameworkDataBean> doselectPageStudent(PageVOSupport pageModel);
}
