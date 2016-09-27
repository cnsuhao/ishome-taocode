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

	public PageVOSupport doSelectPageTaskWithCid(PageVOSupport pageModel) {
		FrameworkDataBean formParamBean = pageModel.getFormParamBean();
		pageModel.setPageListData(getDao().doSelectPageTaskWithCid(pageModel));
		return pageModel;
	}

	public PageVOSupport doSelectPagetTaskWithCiddAndTid(PageVOSupport pageModel) {
		FrameworkDataBean formParamBean = pageModel.getFormParamBean();
		pageModel.setPageListData(getDao().doSelectPagetTaskWithCiddAndTid(pageModel));
		return pageModel;
	}
	
	public PageVOSupport doSelectPageSearch(PageVOSupport pageModel) {
		FrameworkDataBean formParamBean = pageModel.getFormParamBean();
		pageModel.setPageListData(getDao().doSelectPageSearch(pageModel));
		return pageModel;
	}

	public TaskPVO doSelectTaskInfo(TaskDBO dbo) {
		
		return getDao().doSelectTaskInfo(dbo);
	}

}
