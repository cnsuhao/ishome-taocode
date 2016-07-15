package org.isotope.jfp.framework.support;

import java.util.Random;

import org.isotope.jfp.framework.biz.ISTask;
import org.isotope.jfp.framework.biz.common.ISProcess;
import org.isotope.jfp.framework.constants.pub.ISJobConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;

/**
 * 定时作业服务超类
 * 
 * @author Spook
 * @version 2.4.1.20151110
 * @since 2.4.1
 */
public class MyTaskSupport extends MyWorkSupport implements ISJobConstants, ISProcess, ISTask {

	/**
	 * 任务Key
	 */
	protected String jobKey = "JOBKEY";

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
	 * @throws InterruptedException
	 */
	protected boolean startLock() {
		myCacheService.putObject(jobKey, JOB_FLAG_RUNNING, waitTimeSecond, false);
		return true;
	}

	/**
	 * 设置任务开始执行
	 * 
	 * @param jobName
	 */
	protected boolean checkLock() {
		return EmptyHelper.isEmpty(myCacheService.getObject(jobKey, false));
	}

	/**
	 * 设置任务开始执行
	 * 
	 * @param jobName
	 */
	protected boolean errorLock() {
		myCacheService.putObject(jobKey, JOB_FLAG_ERROR, waitTimeSecond, false);
		return true;
	}

	/**
	 * 设置任务开始执行
	 * 
	 * @param jobName
	 */
	protected boolean endLock() {
		myCacheService.putObject(jobKey, JOB_FLAG_SUCCESS, waitTimeSecond, false);
		return true;
	}

	public boolean doProcess() throws Exception {
		// 防止并发，随机休眠
		{
			Random rd = new Random();
			Thread.sleep((long) (100 + rd.nextDouble() * 1000));
		}

		if (checkLock() == false)
			return false;
		startLock();
		try {
			doProcessRepeat();
		} finally {
			endLock();
		}
		return true;
	}

	/**
	 * 业务处理(重复运行)
	 */
	@Override
	public boolean doProcessRepeat() throws Exception {
		return false;
	}

	/**
	 * 业务处理(运行一次)
	 */
	@Override
	public boolean doProcessOnce(Object param) throws Exception {
		return false;
	}
}
