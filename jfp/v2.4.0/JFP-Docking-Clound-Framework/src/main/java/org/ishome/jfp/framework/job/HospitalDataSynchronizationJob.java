package org.ishome.jfp.framework.job;

import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.ishome.jfp.framework.beands.common.FrameworkDataBean;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.constants.pub.ISJobConstants;
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
public class HospitalDataSynchronizationJob implements ISJobConstants, ISFrameworkConstants {
	private Logger logger = LoggerFactory.getLogger(HospitalDataSynchronizationJob.class);

	/**
	 * 日常监听业务
	 */
	LinkedHashMap<String, String> monitorConfig;

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
	
	@Resource
	HospitalSyncConfigService HospitalSyncConfigService_;
	@Resource
	IMedMqService myMqService;
	
	/**
	 * 激活所有医院的对接线程
	 */
	public void doProcess() {
		logger.debug(JOB_SAVE_START);
		List<FrameworkDataBean> hosIdList = HospitalSyncConfigService_.loadJobs();

		if (hosIdList.isEmpty()) {
			logger.info("没有要对接的医院");
			return ;
		}
		HospitalCloudAccessMonitorDBO hm;
		// 开启线程组来处理所有医院数据
		for (FrameworkDataBean hos : hosIdList) {
			hm = (HospitalCloudAccessMonitorDBO) hos;
			if(NINE.equals(hm.getStatus()))
				continue;
			HospitalDataSynchronizationJobThread hsjt = new HospitalDataSynchronizationJobThread(HospitalSyncConfigService_,myMqService);
			hsjt.setWaitTimeMinute(getWaitTimeMinute());
			hsjt.setHosId(hm.getHosId());
			hsjt.setMonitorConfig(monitorConfig);
			hsjt.start();
		}
		logger.debug(JOB_SAVE_END);

		return ;
	}
}
