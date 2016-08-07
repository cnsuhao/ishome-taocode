package com.mcookies.qxy.common.StudentRfid;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 学生一卡通关联表 */
public interface StudentRfidDao extends IDatabaseSupport {
	List<? extends FrameworkDataBean> doSelectPageStudentByrfid(PageVOSupport formParamPageModel);
}
