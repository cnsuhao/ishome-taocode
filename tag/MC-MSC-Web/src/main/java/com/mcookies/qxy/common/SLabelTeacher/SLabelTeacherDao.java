package com.mcookies.qxy.common.SLabelTeacher;

import org.isotope.jfp.framework.support.IDatabaseSupport;
import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

/** 教工标签关联表 */
public interface SLabelTeacherDao extends IDatabaseSupport {
	int doDeleteBytid(MyDataBaseObjectSupport paramBean);
}
