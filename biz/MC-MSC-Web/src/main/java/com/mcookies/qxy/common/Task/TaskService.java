package com.mcookies.qxy.common.Task;

import org.springframework.stereotype.Service;
import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 作业表 */
@Service
public class TaskService extends MyServiceSupport {
	protected static final Logger logger = LoggerFactory.getLogger(TaskService.class);

	@Override
	public TaskDao getDao() {
		return getMySqlSession().getMapper(TaskDao.class);
	}

	public PageVOSupport doSelectTaskInfoTOtermIdAndCid(PageVOSupport pageModel) {
		FrameworkDataBean formParamBean = pageModel.getFormParamBean();
		pageModel.setPageListData(getDao().doSelectTaskTOtermIdAndCid(pageModel));
		return pageModel;
	}

	public PageVOSupport doSelectTaskInfoTOtermIdAndTid(PageVOSupport pageModel) {
		FrameworkDataBean formParamBean = pageModel.getFormParamBean();
		pageModel.setPageListData(getDao().doSelectTaskInfoTOtermIdAndTid(pageModel));
		return pageModel;
	}
	
	public PageVOSupport doSearch(PageVOSupport pageModel) {
		FrameworkDataBean formParamBean = pageModel.getFormParamBean();
		pageModel.setPageListData(getDao().doSearch(pageModel));
		return pageModel;
	}

	public TaskPVO doSelectTaskInfo(TaskDBO dbo) {
		
		return getDao().doSelectTaskInfo(dbo);
	}

}
