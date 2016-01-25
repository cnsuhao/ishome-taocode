package org.ishome.jfp.bds.service.sync;

import javax.annotation.Resource;

import org.ishome.jfp.bds.conf.CloundSecyrityConfig;
import org.ishome.jfp.bds.constants.IHDSConstants;
import org.ishome.jfp.common.Hospital.HospitalInfoService;
import org.ishome.jfp.framework.biz.ISTask;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.mq.IMedMqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


/**
 * 基于HDSThreadPool处理多线程业务，用于获取接口数据内容
 * 
 * @author Spook
 * @version 2.0.0
 * @see <HospitalDataSynchronizationJob><HospitalAccessSaveThread>
 * @since 2.0.0 2015/1/19
 */
@Service
public abstract class AHospitalDataAccessService implements Runnable, IHDSConstants, ISFrameworkConstants, ISTask {
	private static final Logger logger = LoggerFactory.getLogger(AHospitalDataAccessService.class);
	// 缓存队列
	@Resource
	protected IMedMqService mq;
	//医院作业
	@Resource
	protected HospitalInfoService HospitalInfoService_;
	//安全相关类
	@Resource
	protected CloundSecyrityConfig CloundSecyrityConfig_;
	
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
	public void run() {
		String serviceName = this.getClass().getSimpleName();
		logger.debug(JOB_START + serviceName);
		try {
			if (StringUtils.isEmpty(hosId))
				doProcessRepeat();
			else
				doProcessOnce(hosId);	

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			logger.debug(JOB_END + serviceName);
		}
	
	}	
}
