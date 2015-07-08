package org.ishome.jfp.bds.thread.sync;

import javax.annotation.Resource;

import org.ishome.jfp.bds.constants.IHDSConstants;
import org.ishome.jfp.common.Hospital.HospitalInfoService;
import org.ishome.jfp.framework.biz.ISCheck;
import org.ishome.jfp.framework.biz.ISInit;
import org.ishome.jfp.framework.biz.ISSave;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.job.HospitalJobKeyService;
import org.ishome.jfp.framework.utils.BeanFactoryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 获取医院数据 基于线程池 <br>
 * 抽象实现
 * 
 * @author Spook
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public abstract class AHospitalDockingSynchronizationThread extends Thread implements IHDSConstants, ISFrameworkConstants, ISCheck, ISInit, ISSave {

	private static final Logger logger = LoggerFactory.getLogger(AHospitalDockingSynchronizationThread.class);

	//医院作业
	@Resource
	protected HospitalInfoService HospitalInfoService_;
	/**
	 * 需要运行的业务名称
	 */
	String bizName;
	/**
	 * 医院ID
	 */
	protected String hosId;

	/**
	 * 接口数据内容
	 */
	protected String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	/**
	 * 医院数据对接实体业务
	 * 
	 * @param hosId
	 *            医院ID
	 */
	public AHospitalDockingSynchronizationThread(String bizName, String hosId, HospitalInfoService jobService) {
		this.bizName = bizName;
		this.hosId = hosId;
		this.jobService = jobService;
	}

	protected HospitalInfoService jobService;

	/**
	 * 线程处理模式
	 */
	public void run() {
		try {
			if(HospitalInfoService_==null)
				HospitalInfoService_ = BeanFactoryHelper.getBean("hospitalInfoService");
			logger.debug(JOB_SAVE_START);
			logger.debug(hosId);
			HospitalJobKeyService.setSyncJobType(hosId, bizName, JOB_FLAG_RUNNING);
			// 初始化
			if (doInit() && doCheck())
				// 处理数据
				if (doSave()) {
					// 退出任务
					logger.debug(JOB_SAVE_END);
				}
		} catch (Exception e) {
			e.printStackTrace();
			HospitalJobKeyService.setSyncJobType(hosId, bizName, JOB_FLAG_ERROR);
			logger.error(JOB_SAVE_CANCEL);
		}
	}
}
