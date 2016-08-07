package com.mcookies.qxy.common.UTeacher;

import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

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
}
