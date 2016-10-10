package com.mcookies.qxy.common.SLabel;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.support.IDatabaseSupport;
import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

/** 教工标签表 */
public interface SLabelDao extends IDatabaseSupport {
	List<? extends FrameworkDataBean> doselectTeacherLabel(MyDataBaseObjectSupport paramBean);
}
