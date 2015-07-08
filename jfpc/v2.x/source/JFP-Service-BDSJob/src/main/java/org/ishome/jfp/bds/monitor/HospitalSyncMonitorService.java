package org.ishome.jfp.bds.monitor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.ishome.jfp.bds.service.sync.AHospitalDataAccessService;
import org.ishome.jfp.beans.HospitalCloudAccessMonitor.HospitalCloudAccessMonitorDBO;
import org.ishome.jfp.beans.HospitalCloudAccessMonitor.HospitalCloudAccessMonitorService;
import org.ishome.jfp.beans.HospitalCloudAccessRule.HospitalCloudAccessRuleDBO;
import org.ishome.jfp.common.Hospital.HospitalInfoService;
import org.ishome.jfp.common.Hospital.HospitalSyncConfigBean;
import org.ishome.jfp.framework.job.HospitalJobKeyService;
import org.ishome.jfp.framework.mq.redis.MyRedis;
import org.ishome.jfp.framework.sms.SMSTemplateConfig;
import org.ishome.jfp.framework.support.ISSMSSupport;
import org.ishome.jfp.framework.utils.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 监控对接业务服务
 * @author caiwy
 * @version 1.0.0
 * @since 1.0.0 2015/4/17
 */
@Service
public class HospitalSyncMonitorService extends AHospitalDataAccessService {

	private static final Logger logger = LoggerFactory.getLogger(HospitalSyncMonitorService.class);

	HospitalSyncConfigBean config;

	@Resource
	HospitalCloudAccessMonitorService hospitalCloudAccessMonitorService;
	
	int errorSecond = 30;
	
	@Resource
	ISSMSSupport sms;
	@Resource
	SMSTemplateConfig myTemplateConfig;
	
	/***
	 * 业务:查询缓存中所有医院的 时间如果它的更新时间已超时 一分钟 则发短信提醒管理员 并且把云端状态置为2(关闭)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean doProcessRepeat() throws Exception {
		logger.debug(JOB_SAVE_START);
		List<String> infos = (List<String>) mq.getAllObjectInMap(HospitalJobKeyService.CONFIG_KEY);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// HospitalCloudAccessMonitorService hospitalCloudAccessMonitorService =
		// BeanFactoryHelper.getBean("hospitalCloudAccessMonitorService");
		if(null==config) return false;
		for (String inf : infos) {
			config = (HospitalSyncConfigBean) MyRedis.getClassFromRedis(inf);
			Date d = dateFormat.parse(config.getUpdateTime());
			long nowDate = new Date().getTime();
			long nowDate1 = nowDate - 10 * 1000;// 如果超过10秒
			if( nowDate1 > 0 ){
				//获得所有对接业务
				HashMap<String,HospitalCloudAccessRuleDBO> configs = config.getBizConfigs();

				for (Map.Entry<String, HospitalCloudAccessRuleDBO> entry : configs.entrySet()) {
					String key = entry.getKey();
					key = HospitalJobKeyService.getBizJobDataKeyName(config.getHosId(), key + Bussiness_Start);
					mq.deleteObject(key);
					logger.debug(config.getHosId()+"xxxxxxx超时清理请求xxxxxxx");
				}
			}
			
			long nowDate2 = nowDate - errorSecond * 60 * 1000;// 如果超过一分钟
			if (nowDate2 > d.getTime()) {
				// 1、短信提醒
				boolean phonePush = sms.send(config.getHosId(), config.getAdminPhone(), 
						myTemplateConfig.getMessageWithTemplate("QZJJK", config.getHosName(),""+errorSecond));
				if(phonePush ==false){
					logger.debug(config.getHosId()+"xxxxxxx对接运行失败xxxxxxx");
					return false;
				}
				// 2、更新数据库为0
				HospitalCloudAccessMonitorDBO dbo = new HospitalCloudAccessMonitorDBO();
				dbo.setId(config.getId());
				dbo.setHosId(config.getHosId());
				dbo.setStatus("2");// 1正常使用2已经停用
				dbo.setUpdateTime(DateHelper.currentTimeMillisCN1());
				hospitalCloudAccessMonitorService.doUpdate(dbo);
				
				//关闭对接
				config.setStatus(TWO);
				HospitalInfoService.setHosSyncConfig(config);
			}
		}
		logger.debug(JOB_SAVE_END);
		return true;
	}

	@Override
	public boolean doProcessOnce(String hosId) throws Exception {

		return false;
	}

}
