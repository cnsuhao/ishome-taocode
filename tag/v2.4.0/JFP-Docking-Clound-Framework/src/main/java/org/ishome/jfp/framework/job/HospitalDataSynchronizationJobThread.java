package org.ishome.jfp.framework.job;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.ishome.jfp.framework.beands.common.FrameworkDataBean;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.constants.pub.ISJobConstants;
import org.ishome.jfp.framework.utils.BeanFactoryHelper;
import org.ishome.jfp.framework.utils.EmptyHelper;
import org.ishome.jfp.hds.service.sync.AHospitalDataAccessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于处理多线程业务，用于获取接口数据内容 <br>
 * 云端使用
 * 
 * @author fucy
 * @version 2.0.0
 * @see <HospitalAccessSaveThread>
 * @since 2.0.0 2015/1/19
 */
public class HospitalDataSynchronizationJobThread extends Thread implements ISJobConstants, ISFrameworkConstants {
	private Logger logger = LoggerFactory.getLogger(HospitalDataSynchronizationJobThread.class);

	HospitalSyncConfigService HospitalSyncConfigService_;
	IMedMqService myMqService;

	public HospitalDataSynchronizationJobThread(HospitalSyncConfigService hospitalSyncConfigService, IMedMqService medMqService) {
		HospitalSyncConfigService_ = hospitalSyncConfigService;
		myMqService = medMqService;
	}

	protected String hosId;

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	/**
	 * 需要运行的业务名称
	 */
	String bizName = HospitalJobKeyService.JOB_KEY;

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	/**
	 * 日常监听业务
	 */
	LinkedHashMap<String, String> monitorConfig = new LinkedHashMap<String, String>();

	public LinkedHashMap<String, String> getMonitorConfig() {
		return monitorConfig;
	}

	public void setMonitorConfig(LinkedHashMap<String, String> monitorConfig) {
		this.monitorConfig = monitorConfig;
	}

	public int waitTimeMinute = 30;

	public int getWaitTimeMinute() {
		return waitTimeMinute;
	}

	public void setWaitTimeMinute(int waitTimeMinute) {
		this.waitTimeMinute = waitTimeMinute;
	}

	/**
	 * 自动运行
	 */
	public void run() {
		try {
			// 线程竞争限制
			if (checkLock()) {
				// 设定进程限制
				startLock();
				// 开始业务处理
				doProcess();
				// 关闭进程限制
				endLock();
			}
		} catch (Exception e) {
			errorLock();
			logger.error(getBizName() + "对接失败..." + hosId + "..." + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 自动运行
	 * 
	 * @throws Exception
	 */
	public void doProcess() throws Exception {
		// 获取医院对接业务记录
		List<FrameworkDataBean> hosIdList = HospitalSyncConfigService_.loadJobsByHospital(this.getHosId());

		if (hosIdList.isEmpty()) {
			logger.info("没有要对接的医院业务");
			return;
		}
		logger.debug(JOB_SAVE_START);
		HospitalCloudAccessRuleDBO rule;
		// 按照配置来顺序执行业务
		for (Entry<String, String> entry : getMonitorConfig().entrySet()) {
			// 开启线程组来处理所有医院数据
			for (FrameworkDataBean tempRule : hosIdList) {
				rule = (HospitalCloudAccessRuleDBO) tempRule;
				// 对接标识 1:对接打开 2：对接关闭 3：模拟对接9：无需对接
				if (NINE.equals(rule.getAccessFlag()) || TWO.equals(rule.getAccessFlag()))
					continue;

				// 判断是否开启对应处理业务
				if (entry.getKey().equals(rule.getModuleType())) {
					// 获得对应线程
					AHospitalDataAccessService hbis = BeanFactoryHelper.getBean(entry.getValue());
					if (TWO.equals(rule.getEb3()))
						hbis.setUseOperateData(EMPTY);
					else
						hbis.setUseOperateData(ZERO);
					hbis.setBizName(entry.getKey());
					hbis.setHosId(hosId);
					hbis.doProcessOnce(hosId);
				}
			}
			// 释放CPU压力
			Thread.sleep(100);
		}
		logger.debug(JOB_SAVE_END);

		return;
	}

	/**
	 * 设置任务为执行状态
	 * 
	 * @param jobName
	 */
	protected boolean startLock() {
		myMqService.putObject(HospitalJobKeyService.getSyncResultKeyName(hosId, getBizName()), JOB_FLAG_RUNNING, 60 * waitTimeMinute, false);
		return true;
	}

	/**
	 * 设置任务开始执行
	 * 
	 * @param jobName
	 */
	protected boolean checkLock() {
		return EmptyHelper.isEmpty(myMqService.getObject(HospitalJobKeyService.getSyncResultKeyName(hosId, getBizName()), false));
	}

	/**
	 * 设置任务开始执行
	 * 
	 * @param jobName
	 */
	protected boolean errorLock() {
		myMqService.putObject(HospitalJobKeyService.getSyncResultKeyName(hosId, getBizName()), JOB_FLAG_ERROR, 15, false);
		return true;
	}

	/**
	 * 设置任务开始执行
	 * 
	 * @param jobName
	 */
	protected boolean endLock() {
		myMqService.putObject(HospitalJobKeyService.getSyncResultKeyName(hosId, getBizName()), JOB_FLAG_SUCCESS, 15, false);
		return true;
	}
}
