package com.mcookies.qxy.common.Task;

import org.springframework.stereotype.Service;

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

	public PageVOSupport doSelectTaskInfoTOtermIdAndCid(PageVOSupport formParamPageModel) {
		formParamPageModel.setPageListData(getDao().doSelectTaskTOtermIdAndCid(formParamPageModel));
		return formParamPageModel;
	}

	public PageVOSupport doSelectTaskInfoTOtermIdAndTid(PageVOSupport formParamPageModel) {
		formParamPageModel.setPageListData(getDao().doSelectTaskInfoTOtermIdAndTid(formParamPageModel));
		return formParamPageModel;
	}
	
	public PageVOSupport doSearch(PageVOSupport formParamPageModel) {
		formParamPageModel.setPageListData(getDao().doSearch(formParamPageModel));
		return formParamPageModel;
	}

	public TaskPVO doSelectTaskInfo(TaskDBO dbo) {
		
		return getDao().doSelectTaskInfo(dbo);
	}

}