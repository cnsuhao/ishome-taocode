package com.mcookies.qxy.common.UTeacher;

import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

import com.mcookies.qxy.common.ClassTeacher.ClassTeacherDBO;

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
}
