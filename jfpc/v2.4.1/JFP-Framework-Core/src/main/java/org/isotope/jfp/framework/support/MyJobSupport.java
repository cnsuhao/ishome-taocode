package org.isotope.jfp.framework.support;

import javax.annotation.Resource;

import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISJobConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;

/**
 * 定时作业服务超类
 * 
 * @author Spook
 * @version 2.4.1.20151110
 * @since 2.4.1
 */
public class MyJobSupport implements ISJobConstants, ISFrameworkConstants {

	// 缓存队列
	@Resource
	protected ICacheService myMqService;

	/**
	 * 进程阻塞时间（分钟）
	 */
	private int waitTimeMinute = 30;

	/**
	 * 任务Key
	 */
	private String jobKey = "JOBKEY";

	public int getWaitTimeMinute() {
		return waitTimeMinute;
	}

	public void setWaitTimeMinute(int waitTimeMinute) {
		this.waitTimeMinute = waitTimeMinute;
	}

	public String getJobKey() {
		return jobKey;
	}

	public void setJobKey(String jobKey) {
		this.jobKey = jobKey;
	}

	/**
	 * 设置任务为执行状态
	 * 
	 * @param jobName
	 */
	protected boolean startLock() {
		myMqService.putObject(jobKey, JOB_FLAG_RUNNING, 60 * waitTimeMinute, false);
		return true;
	}

	/**
	 * 设置任务开始执行
	 * 
	 * @param jobName
	 */
	protected boolean checkLock() {
		return EmptyHelper.isEmpty(myMqService.getObject(jobKey, false));
	}

	/**
	 * 设置任务开始执行
	 * 
	 * @param jobName
	 */
	protected boolean errorLock() {
		myMqService.putObject(jobKey, JOB_FLAG_ERROR, 15, false);
		return true;
	}

	/**
	 * 设置任务开始执行
	 * 
	 * @param jobName
	 */
	protected boolean endLock() {
		myMqService.putObject(jobKey, JOB_FLAG_SUCCESS, 15, false);
		return true;
	}
}
