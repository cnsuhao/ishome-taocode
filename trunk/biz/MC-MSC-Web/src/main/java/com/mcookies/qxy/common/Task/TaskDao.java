package com.mcookies.qxy.common.Task;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 作业表*/
public interface TaskDao extends IDatabaseSupport{
	
	List<? extends FrameworkDataBean> doSelectTaskInfoTOtermIdAndCid(PageVOSupport formParamPageModel);
	
	List<? extends FrameworkDataBean> doSelectTaskInfoTOtermIdAndTid(PageVOSupport formParamPageModel);
	
	List<? extends FrameworkDataBean> doSearch(PageVOSupport formParamPageModel);
	
	TaskPVO doSelectTaskInfo(TaskDBO dbo);
	

}
