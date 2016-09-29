package com.mcookies.qxy.common.MecScore;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.IDatabaseSupport;


/** 德育班级评分表*/
public interface MecScoreDao extends IDatabaseSupport{

	List<? extends FrameworkDataBean> doSelectPageMecScoreDESC(PageVOSupport formParamPageModel);
	List<? extends FrameworkDataBean> doSelectPageMecScoreASC(PageVOSupport formParamPageModel);
	MecScorePVO doReadMecScore(MecScoreDBO mecScoreDBO);
	List<? extends FrameworkDataBean> doSelectPageMecScore(PageVOSupport formParamPageModel);
	List<? extends FrameworkDataBean> doSelectPageMecScoreList(PageVOSupport formParamPageModel);
	MecScorePVO doReadMecScoreBymecScoreId(MecScoreDBO mecScoreDBO);
	List<? extends FrameworkDataBean> doSelectPageMecScrDESC(PageVOSupport formParamPageModel);
	List<? extends FrameworkDataBean> doSelectPageMecScrASC(PageVOSupport formParamPageModel);
}
