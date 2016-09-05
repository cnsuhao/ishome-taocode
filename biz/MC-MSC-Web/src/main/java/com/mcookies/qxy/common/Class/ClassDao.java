package com.mcookies.qxy.common.Class;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.IDatabaseSupport;

import com.mcookies.qxy.common.News.NewsPVO;

/** 班级表*/
public interface ClassDao extends IDatabaseSupport{
	
	List<? extends FrameworkDataBean> doSelectPageWorkTime(PageVOSupport formParamPageModel);
	
	List<? extends FrameworkDataBean> doSelectPageClass(PageVOSupport formParamPageModel);
	
	List<? extends FrameworkDataBean> doSelectPageClass2(PageVOSupport formParamPageModel);

	List<ClassDBO> findNewsClassers(NewsPVO news);
	List<ClassPVO> findNewsClassersAndGrade(NewsPVO news);

	List<ClassPVO> findByTermIdAndTid(ClassPVO clz);

	ClassPVO findOneByCid(ClassPVO clz);

}
