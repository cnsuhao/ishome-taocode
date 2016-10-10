package com.mcookies.qxy.common.MepScore;

import org.springframework.stereotype.Service;

import com.mcookies.qxy.common.MecScore.MecScoreDBO;
import com.mcookies.qxy.common.MecScore.MecScorePVO;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 德育个人评分表*/
@Service
public class MepScoreService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MepScoreService.class);

    public MepScoreDao getDao(){
        return getMySqlSession().getMapper(MepScoreDao.class);
    }
    
	/**
	 * 分页 获取德育个人评分倒序列表信息
	 * 
	 * @param formParamPageModel
	 */
	public PageVOSupport doSelectPageMepScoreDESC(PageVOSupport formParamPageModel) {
		
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		formParamPageModel.setPageListData(getDao().doSelectPageMepScoreDESC(formParamPageModel));
		return formParamPageModel;
	}
	
	/**
	 * 分页 获取德育个人评分正序列表信息
	 * 
	 * @param formParamPageModel
	 */
	public PageVOSupport doSelectPageMepScoreASC(PageVOSupport formParamPageModel) {
		
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		formParamPageModel.setPageListData(getDao().doSelectPageMepScoreASC(formParamPageModel));
		return formParamPageModel;
	}
	
	/**
	 * 根据studentId获取德育个人评分信息 
	 * @param mepScorePVO
	 * @return
	 */
	public MepScorePVO doReadMepScore(MepScorePVO mepScorePVO) {
		return getDao().doReadMepScore(mepScorePVO);
	}
	
	/**
	 * 分页 根据studentId获取个人评分详情列表
	 * 
	 * @param formParamPageModel
	 */
	public PageVOSupport doSelectPageMepScore(PageVOSupport formParamPageModel) {
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		formParamPageModel.setPageListData(getDao().doSelectPageMepScore(formParamPageModel));
		return formParamPageModel;
	}
	/**
	 * 分页根据studentId, meItemId，startTime，endTime获取个人评分详情列表 
	 * 
	 * @param formParamPageModel
	 */
	public PageVOSupport doSelectPageMepScoreList(PageVOSupport formParamPageModel) {
		
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		formParamPageModel.setPageListData(getDao().doSelectPageMepScoreList(formParamPageModel));
		return formParamPageModel;
	}
	
	/**
	 * 根据mepScoreId班级评分id获取个人评分详情 
	 * @param mecScoreDBO
	 * @return
	 */
	public MepScorePVO doReadMepScoreBymepScoreId(MepScoreDBO mepScoreDBO) {
		return getDao().doReadMepScoreBymepScoreId(mepScoreDBO);
	}
	/**
	 *根据cid termId,gradeId,studentName获取德育个人评分信息
	 * @param mepScorePVO
	 * @return
	 */
	public List<MepScorePVO> doSeleDataPers(MepScorePVO mepScorePVO) {
		return getDao().doSeleDataPers(mepScorePVO);
	}
	
	

}


























