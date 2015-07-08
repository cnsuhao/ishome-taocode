package org.ishome.jfp.framework.job;

import javax.annotation.Resource;

import org.ishome.jfp.framework.biz.ISTask;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.constants.ISJobConstants;
import org.ishome.jfp.framework.mq.IMedMqService;
import org.ishome.jfp.framework.utils.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


/**
 * 基于处理多线程业务，用于获取接口数据内容
 * 
 * @author Spook
 * @version 2.0.0
 * @see <HospitalAccessSaveThread>
 * @since 2.0.0 2015/1/19
 */
public class HospitalDataPushJob implements ISFrameworkConstants, ISJobConstants {

	protected static final Logger logger = LoggerFactory.getLogger(HospitalDataPushJob.class);
	
	// 缓存队列
	@Resource
	IMedMqService mq;

	/**
	 * 需要运行的业务名称
	 */
	String bizName;

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	ISTask service;

	public ISTask getService() {
		return service;
	}

	public void setService(ISTask service) {
		this.service = service;
	}

	/**
	 * 需要独立运行的医院ID
	 */
	String hosId;

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	/**
	 * 自动运行
	 */
	public void doProcess() {
		// 启动限定
		if (isRunning() == false) {
			running();
			//String serviceName = service.getClass().getSimpleName();
			// logger.debug(JOB_START + serviceName);
			try {
				// 运行接收
				if (service != null)
					if (StringUtils.isEmpty(hosId))
						service.doProcessRepeat();
					else
						service.doProcessOnce(hosId);
				// else
				// logger.debug(JOB_CANCEL + serviceName);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				process();
				// logger.debug(JOB_END + serviceName);
			}
		}
	}

	/**
	 * 在集群环境下，判断定时任务是否已经被其他服务器执行
	 * 
	 * @param jobName
	 * @return true-已在执行 false-未执行
	 */
	protected boolean isRunning() {
		/**
		 * 避免多台机器并发导致重复执行，在此设置随机时间阻塞
		 */
		try {
			long sleepMs = HospitalJobKeyService.getRandomMS();
			Thread.sleep(sleepMs);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String type = (String) mq.getObject(HospitalJobKeyService.getJobKey(bizName), false);
		if (StringUtils.isEmpty(type))
			return false;
		String[] flag = type.split(BACKSLASH2);

		if (flag.length == 1) {
			return false;
		} else if (JOB_FLAG_RUNNING.equals(flag)) {
			// 判断运行时间进行解锁
			if ((System.currentTimeMillis() - Long.parseLong(flag[1])) > 5 * 60 * 1000) {
				running();
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 设置任务为执行状态
	 * 
	 * @param jobName
	 */
	protected void running() {
		mq.putObject(HospitalJobKeyService.getJobKey(bizName), JOB_FLAG_RUNNING + BACKSLASH2 + DateHelper.currentTimestamp());
	}

	/**
	 * 设置任务开始执行
	 * 
	 * @param jobName
	 */
	protected void process() {
		mq.putObject(HospitalJobKeyService.getJobKey(bizName), JOB_FLAG_WAITING + BACKSLASH2 + DateHelper.currentTimestamp());
	}

}
