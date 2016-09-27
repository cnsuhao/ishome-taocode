package com.mcookies.qxy.common.Task;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 作业表*/
public interface TaskDao extends IDatabaseSupport{
	
	public List<TaskPVO> doSelectTaskTOtermIdAndCid(PageVOSupport PageModel);
	
	public List<TaskPVO> doSelectTaskInfoTOtermIdAndTid(PageVOSupport PageModel);
	
	public List<TaskPVO> doSearch(PageVOSupport PageModel);
	
	public TaskPVO doSelectTaskInfo(TaskDBO dbo);
	

}
