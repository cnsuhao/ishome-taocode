package com.mcookies.qxy.common.Results;

import org.springframework.stereotype.Service;

import com.mcookies.qxy.common.MecScore.MecScoreDBO;
import com.mcookies.qxy.common.MecScore.MecScorePVO;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 成绩表*/
@Service
public class ResultsService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(ResultsService.class);

    public ResultsDao getDao(){
        return getMySqlSession().getMapper(ResultsDao.class);
    }
	/**
	 * 分页 获取学生总成绩列表信息 
	 * @param formParamPageModel
	 */
	public PageVOSupport doSelectPageResultDESC(PageVOSupport formParamPageModel) {
		
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		formParamPageModel.setPageListData(getDao().doSelectPageResultDESC(formParamPageModel));
		return formParamPageModel;
	}
	
	/**
	 * 根据cid班级id，resultsTagId成绩标签id获取班级学生成绩
	 * @param ResultsDBO
	 * @return
	 */
	public ResultsPVO doReadResult(ResultsDBO resultsDBO) {
		return getDao().doReadResult(resultsDBO);
	}
	/**
	 * 根据学生id，学号id，resultsTagId成绩标签id获取班级学生总成绩列表
	 * @param ResultsDBO
	 * @return
	 */
	public List<ResultsPVO> doSelectResult(ResultsPVO resultsPVO) {
		return getDao().doSelectResult(resultsPVO);
	}
	/**
	 * 根据学生id，学号id，resultsTagId成绩标签id获取班级学生成绩
	 * @param ResultsDBO
	 * @return
	 */
	public ResultsPVO doReadResultByStudentId(ResultsPVO resultsPVO) {
		return getDao().doReadResultByStudentId(resultsPVO);
	}
	/**
	 * 根据学生id ,cid，resultsTagId成绩标签id获取班级学生各课程成绩列表
	 * @param ResultsDBO
	 * @return
	 */
	public List<ResultsPVO> doSelectByCidRtid(ResultsDBO resultsDBO) {
		return getDao().doSelectByCidRtid(resultsDBO);
	}
	/**
	 * 根据学生id，学号，resultsTagId成绩标签id获取班级学生成绩
	 * @param ResultsDBO
	 * @return
	 */
	public ResultsPVO doReadByRTIdSId(ResultsDBO resultsDBO) {
		return getDao().doReadByRTIdSId(resultsDBO);
	}
	/**
	 * 根据成绩标签id,班级id，resultsTagId成绩标签id获取班级学生成绩状态信息
	 * @param ResultsDBO
	 * @return
	 */
	public List<ResultsPVO>  findByRTIdCidCourseId(ResultsDBO resultsDBO) {
		return getDao().findByRTIdCidCourseId(resultsDBO);
	}
	/**
	 * 分页 获取学生总成科目分数正序排名列表信息
	 * @param formParamPageModel
	 */
	public PageVOSupport doSelectPageResASC(PageVOSupport formParamPageModel) {
		
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		formParamPageModel.setPageListData(getDao().doSelectPageResASC(formParamPageModel));
		return formParamPageModel;
	}
	/**
	 * 分页 获取学生总成科目分数倒序排名列表信息 
	 * @param formParamPageModel
	 */
	public PageVOSupport doSelectPageResDESC(PageVOSupport formParamPageModel) {
		
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		formParamPageModel.setPageListData(getDao().doSelectPageResDESC(formParamPageModel));
		return formParamPageModel;
	}
	/**
	 * 根据学生id，学号，resultsTagId成绩标签id获取班级学生成绩
	 * @param ResultsDBO
	 * @return
	 */
	public ResultsPVO findByRTIdCidCsId(ResultsDBO resultsDBO) {
		return getDao().findByRTIdCidCsId(resultsDBO);
	}
	/**
	 * 分页 获取获得年级信息-
	 * @param formParamPageModel
	 */
	public PageVOSupport doSelectPageGrade(PageVOSupport formParamPageModel) {
		
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		formParamPageModel.setPageListData(getDao().doSelectPageGrade(formParamPageModel));
		return formParamPageModel;
	}
	/**
	 * 获得班级信息
	 * @param ResultsDBO
	 * @return
	 */
	public List<ResultsPVO> doSelectClassList(ResultsPVO resultsPVO) {
		return getDao().doSelectClassList(resultsPVO);
	}
	/**
	 * 分页 获得年级班级信息-
	 * @param formParamPageModel
	 */
	public PageVOSupport doSelectPageGradeClassList(PageVOSupport formParamPageModel) {
		
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		formParamPageModel.setPageListData(getDao().doSelectPageGradeClassList(formParamPageModel));
		return formParamPageModel;
	}
	
	/**
	 * 根据cid获得年级和班级情况
	 * @param ResultsDBO
	 * @return
	 */
	public ResultsPVO doReadGradeClass(ResultsDBO resultsDBO) {
		return getDao().doReadGradeClass(resultsDBO);
	}
}
