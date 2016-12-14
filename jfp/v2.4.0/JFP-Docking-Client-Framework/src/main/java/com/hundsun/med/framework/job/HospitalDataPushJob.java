package com.hundsun.med.framework.job;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.hundsun.med.framework.biz.ISTask;
import com.hundsun.med.framework.constants.ISFrameworkConstants;
import com.hundsun.med.framework.constants.ISJobConstants;
import com.hundsun.med.framework.mq.IMedMqService;

/**
 * 基于处理多线程业务，用于获取接口数据内容 <br>
 * 前置机使用
 * 
 * @author fucy
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
		try {
			// 运行接收
			if (service != null)
				if (StringUtils.isEmpty(hosId))
					service.doProcessRepeat();
				else
					service.doProcessOnce(hosId);

		} catch (Exception e) {
		}
	}
}
