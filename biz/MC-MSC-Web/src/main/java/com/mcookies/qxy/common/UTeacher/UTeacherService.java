package com.mcookies.qxy.common.UTeacher;

import java.util.List;

import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

import com.mcookies.qxy.common.ClassTeacher.ClassTeacherDBO;
import com.mcookies.qxy.common.News.NewsPVO;
import com.mcookies.qxy.common.OaRule.OaRulePVO;
import com.mcookies.qxy.common.SDutyScheduling.SDutySchedulingPVO;

/** 教师表 */
@Service
public class UTeacherService extends MyServiceSupport {

	public UTeacherDao getDao() {
		return getMySqlSession().getMapper(UTeacherDao.class);
	}
	
	/***
	 * 分页查询标签下的教工
	 * @param formParamPageModel
	 * @return
	 */
	public PageVOSupport doSelectPageTeacherOnLabel(PageVOSupport formParamPageModel) {
		formParamPageModel.setPageListData(getDao().doSelectPageTeacherOnLabel(formParamPageModel));
		return formParamPageModel;
	}
	
	/**
	 * 通过cid查询班主任信息
	 * @param classTeacher
	 * @return
	 */
	public UTeacherDBO findClassLeader(ClassTeacherDBO classTeacher) {
		return getDao().findClassLeader(classTeacher);
	}

	/**
	 * 根据tids查询值日值周的教师
	 * @param each
	 * @return
	 */
	public List<UTeacherDBO> findSchedulingTeachers(SDutySchedulingPVO s) {
		return getDao().findSchedulingTeachers(s);
	}
	
	/**
	 * 根据leaderTids查询值日值周的领导
	 * @param each
	 * @return
	 */
	public List<UTeacherDBO> findSchedulingLeaders(SDutySchedulingPVO s) {
		return getDao().findSchedulingLeaders(s);
	}

	/**
	 * 根据news的newsReader查询，为空返回全部
	 * @param news
	 * @return
	 */
	public List<UTeacherDBO> findNewsReaders(NewsPVO news) {
		return getDao().findNewsReaders(news);
	}

	public List<UTeacherPVO> findByTermIdAndCid(UTeacherPVO teacher) {
		return getDao().findByTermIdAndCid(teacher);
	}
	
	/**
	 * 模板规则，查询tids对应的教师列表(id1;id2;id3;) 为空返回sid下的全部
	 * @param news
	 * @return
	 */
	public List<UTeacherDBO> findByTids(OaRulePVO oaRule) {
		return getDao().findByTids(oaRule);
	}
}
