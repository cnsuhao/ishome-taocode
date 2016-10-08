package com.mcookies.qxy.common.Results;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.IDatabaseSupport;

import com.mcookies.qxy.common.MecScore.MecScoreDBO;
import com.mcookies.qxy.common.MecScore.MecScorePVO;

/** 成绩表*/
public interface ResultsDao extends IDatabaseSupport{
	List<? extends FrameworkDataBean> doSelectPageResultDESC(PageVOSupport formParamPageModel);
	ResultsPVO doReadResult(ResultsDBO resultsDBO);
	List<ResultsPVO> doSelectResult(ResultsPVO resultsPVO);
	ResultsPVO doReadResultByStudentId(ResultsPVO resultsPVO);
	ResultsPVO doSelectByCidRtidStuId(ResultsDBO resultsDBO);
	ResultsPVO doReadByRTIdSId(ResultsDBO resultsDBO);
	List<ResultsPVO> findByRTIdCidCourseId(ResultsDBO resultsDBO);
	List<? extends FrameworkDataBean> doSelectPageResASC(PageVOSupport formParamPageModel);
	List<? extends FrameworkDataBean> doSelectPageResDESC(PageVOSupport formParamPageModel);
	ResultsPVO findByRTIdCidCsId(ResultsDBO resultsDBO);
	List<? extends FrameworkDataBean> doSelectPageGrade(PageVOSupport formParamPageModel);
	List<ResultsPVO> doSelectClassList(ResultsPVO resultsPVO);
	List<? extends FrameworkDataBean> doSelectPageGradeClassList(PageVOSupport formParamPageModel);
	ResultsPVO doReadGradeClass(ResultsDBO resultsDBO);
}
