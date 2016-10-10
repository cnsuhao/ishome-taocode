package com.mcookies.qxy.common.MecScore;

import org.springframework.stereotype.Service;


import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 德育班级评分表*/
@Service
public class MecScoreService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MecScoreService.class);

    public MecScoreDao getDao(){
        return getMySqlSession().getMapper(MecScoreDao.class);
    }

	/**
	 * 分页 获取德育班级评分倒序列表信息
	 * 
	 * @param formParamPageModel
	 */
	public PageVOSupport doSelectPageMecScoreDESC(PageVOSupport formParamPageModel) {
		
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		formParamPageModel.setPageListData(getDao().doSelectPageMecScoreDESC(formParamPageModel));
		return formParamPageModel;
	}
	
	/**
	 * 分页 获取德育班级评分正序列表信息
	 * 
	 * @param formParamPageModel
	 */
	public PageVOSupport doSelectPageMecScoreASC(PageVOSupport formParamPageModel) {
		
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		formParamPageModel.setPageListData(getDao().doSelectPageMecScoreASC(formParamPageModel));
		return formParamPageModel;
	}
	
	/**
	 * 根据cid获取德育班级评分信息 
	 * @param mecScoreDBO
	 * @return
	 */
	public MecScorePVO doReadMecScore(MecScoreDBO mecScoreDBO) {
		return getDao().doReadMecScore(mecScoreDBO);
	}
	
	/**
	 * 分页 根据cid,termId获取班级评分详情列表
	 * 
	 * @param formParamPageModel
	 */
	public PageVOSupport doSelectPageMecScore(PageVOSupport formParamPageModel) {
		
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		formParamPageModel.setPageListData(getDao().doSelectPageMecScore(formParamPageModel));
		return formParamPageModel;
	}
	
	/**
	 * 分页 根据cid,termId mecItemId，startTime，endTime获取班级评分详情列表
	 * 
	 * @param formParamPageModel
	 */
	public PageVOSupport doSelectPageMecScoreList(PageVOSupport formParamPageModel) {
		
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		formParamPageModel.setPageListData(getDao().doSelectPageMecScoreList(formParamPageModel));
		return formParamPageModel;
	}
	
	/**
	 * 根据mecScoreId班级评分id获取班级评分详情 
	 * @param mecScoreDBO
	 * @return
	 */
	public MecScorePVO doReadMecScoreBymecScoreId(MecScoreDBO mecScoreDBO) {
		return getDao().doReadMecScoreBymecScoreId(mecScoreDBO);
	}
	
	/**
	 * 分页 获取德育班级评分倒序列表信息
	 * 
	 * @param formParamPageModel
	 */
	public PageVOSupport doSelectPageMecScrDESC(PageVOSupport formParamPageModel) {
		
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		formParamPageModel.setPageListData(getDao().doSelectPageMecScrDESC(formParamPageModel));
		return formParamPageModel;
	}
	
	/**
	 * 分页 获取德育班级评分正序列表信息
	 * 
	 * @param formParamPageModel
	 */
	public PageVOSupport doSelectPageMecScrASC(PageVOSupport formParamPageModel) {
		
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		formParamPageModel.setPageListData(getDao().doSelectPageMecScrASC(formParamPageModel));
		return formParamPageModel;
	}
	
}
