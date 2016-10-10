package com.mcookies.qxy.common.MepScore;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.IDatabaseSupport;

import com.mcookies.qxy.common.MecScore.MecScoreDBO;
import com.mcookies.qxy.common.MecScore.MecScorePVO;

/** 德育个人评分表*/
public interface MepScoreDao extends IDatabaseSupport{

	List<? extends FrameworkDataBean> doSelectPageMepScoreDESC(PageVOSupport formParamPageModel);
	List<? extends FrameworkDataBean> doSelectPageMepScoreASC(PageVOSupport formParamPageModel);
	MepScorePVO doReadMepScore(MepScorePVO mepScorePVO);
	List<? extends FrameworkDataBean> doSelectPageMepScore(PageVOSupport formParamPageModel);
	List<? extends FrameworkDataBean> doSelectPageMepScoreList(PageVOSupport formParamPageModel);
	MepScorePVO doReadMepScoreBymepScoreId(MepScoreDBO mepScoreDBO);
	List<MepScorePVO> doSeleDataPers(MepScorePVO mepScorePVO);
}
