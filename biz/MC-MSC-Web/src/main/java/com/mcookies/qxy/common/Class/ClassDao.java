package com.mcookies.qxy.common.Class;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 班级表*/
public interface ClassDao extends IDatabaseSupport{
	
	List<ClassPVO> doSelectWorkTime(ClassDBO dbo);
	
	List<? extends FrameworkDataBean> doSelectPageClass(PageVOSupport formParamPageModel);
	
	List<? extends FrameworkDataBean> doSelectPageClass2(PageVOSupport formParamPageModel);

}
