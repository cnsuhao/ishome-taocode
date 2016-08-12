package com.mcookies.qxy.common.UStudentParent;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 学生家长关联表 */
public interface UStudentParentDao extends IDatabaseSupport {

	void deleteByStudentId(UStudentParentDBO studentParent);

	void updateIsDefaultFalse(UStudentParentDBO sp);

	List<? extends FrameworkDataBean> doSelectPageByTermIdAndCidAndStudentId(PageVOSupport pageModel);

}
