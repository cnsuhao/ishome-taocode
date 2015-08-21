package com.hundsun.med.common.Hospital;

import java.util.List;

import javax.annotation.Resource;

import org.ishome.jfp.framework.beands.common.FrameworkDataBean;
import org.ishome.jfp.framework.cache.mq.MQHelper;
import org.ishome.jfp.framework.cache.utils.redis.IMedMqService;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.constants.pub.ISJobConstants;
import org.ishome.jfp.framework.utils.EmptyHelper;
import org.springframework.stereotype.Service;

import com.hundsun.med.beans.HospitalCloudAccessMonitor.HospitalCloudAccessMonitorDBO;
import com.hundsun.med.beans.HospitalCloudAccessMonitor.HospitalCloudAccessMonitorService;
import com.hundsun.med.beans.HospitalCloudAccessRule.HospitalCloudAccessRuleDBO;
import com.hundsun.med.beans.HospitalCloudAccessRule.HospitalCloudAccessRuleService;

/**
 * 医院对接参数获取
 * 
 * @author fucy
 * 
 */
@Service
public class HospitalSyncConfigService implements ISFrameworkConstants, ISJobConstants {
	/**
	 * 获得医院令牌(云端使用)
	 * 
	 * @param hosId
	 * @return
	 */
	public String getHospitalToken(String hosId) {
		HospitalSyncConfigBean config = HospitalInfoService.getHosSyncConfig(hosId);
		// String token = config.getPuk();
		// if (EmptyHelper.isEmpty(token)) {
		// token = PKHelper.creatPUKey();
		// setHospitalToken(hosId, token);
		// }
		return config.getPuk();
	}

	/**
	 * 加载医院令牌到Redis(云端使用)
	 * 
	 * @param hosId
	 * @param key
	 *            医院MD5加密后的私钥
	 * @return
	 */
	public boolean setHospitalToken(String hosId, String token) {
		HospitalSyncConfigBean config = HospitalInfoService.getHosSyncConfig(hosId);
		if (config == null) {
			ICacheService mq = MQHelper.getMqService();
			config = HospitalInfoService.getHosSyncConfigFromDB(hosId, mq);
			if (config == null)
				return false;
		}
		config.setToken(token);
		HospitalInfoService.setHosSyncConfig(config);
		return true;
	}

	/**
	 * 获得医院私钥(云端使用)
	 * 
	 * @param hosId
	 * @return
	 */
	public String getHospitalPrivateSecurityKeyInClound(String hosId) {
		HospitalSyncConfigBean config = HospitalInfoService.getHosSyncConfig(hosId);
		return config.getPrimaryKey();
	}

	/**
	 * 加载医院私钥到Redis(云端使用)
	 * 
	 * @param hosId
	 * @param key
	 *            医院MD5加密后的私钥
	 * @return
	 */
	public boolean setHospitalPrivateSecurityKeyInClound(String hosId, String key) {
		HospitalSyncConfigBean config = HospitalInfoService.getHosSyncConfig(hosId);
		if (config == null) {
			ICacheService mq = MQHelper.getMqService();
			config = HospitalInfoService.getHosSyncConfigFromDB(hosId, mq);
			if (config == null)
				return false;
		}
		config.setPrimaryKey(key);
		HospitalInfoService.setHosSyncConfig(config);
		return true;
	}

	/**
	 * 获得对接业务加密状态
	 * 
	 * @param hosId
	 * @param bizName
	 * @return
	 */
	public boolean getBizEncryptionType(String hosId, String bizName) {
		HospitalSyncConfigBean config = HospitalInfoService.getHosSyncConfig(hosId);
		if (config == null) {
			ICacheService mq = MQHelper.getMqService();
			config = HospitalInfoService.getHosSyncConfigFromDB(hosId, mq);
			if (config == null)
				return false;
		}
		HospitalCloudAccessRuleDBO bizConfig = config.getBizConfigs().get(bizName);
		if (bizConfig == null) {
			ICacheService mq = MQHelper.getMqService();
			config = HospitalInfoService.getHosSyncConfigFromDB(hosId, mq);
			bizConfig = config.getBizConfigs().get(bizName);
			if (bizConfig == null) {
				return false;
			}
		}

		if (ONE.equals(bizConfig.getFb3()))
			return true;
		return false;
	}

	/**
	 * 设定对接业务加密状态（基于缓存）
	 * 
	 * @param hosId
	 * @param bizName
	 * @param encryptionType
	 * @return
	 */
	public boolean setBizEncryptionType(String hosId, String bizName, boolean encryptionType) {
		HospitalSyncConfigBean config = HospitalInfoService.getHosSyncConfig(hosId);
		if (config == null) {
			ICacheService mq = MQHelper.getMqService();
			config = HospitalInfoService.getHosSyncConfigFromDB(hosId, mq);
			if (config == null)
				return false;
		}
		HospitalCloudAccessRuleDBO bizConfig = config.getBizConfigs().get(bizName);
		if (bizConfig == null) {
			ICacheService mq = MQHelper.getMqService();
			config = HospitalInfoService.getHosSyncConfigFromDB(hosId, mq);
			bizConfig = config.getBizConfigs().get(bizName);
			if (bizConfig == null) {				
				bizConfig = new HospitalCloudAccessRuleDBO();
				bizConfig.setModuleType(bizName);
			}
		}
		if (encryptionType)
			bizConfig.setFb3(ONE);
		config.getBizConfigs().put(bizName, bizConfig);
		HospitalInfoService.setHosSyncConfig(config);
		return true;
	}

	/**
	 * 获得对接业务开启状态（1:对接打开 2：对接关闭 3：模拟对接9：无需对接）（基于缓存）
	 * 
	 * @param hosId
	 * @param bizName
	 * @return
	 */
	public String getBizSynchronizationType(String hosId, String bizName) {
		HospitalSyncConfigBean config = HospitalInfoService.getHosSyncConfig(hosId);
		if (config == null) {
			ICacheService mq = MQHelper.getMqService();
			config = HospitalInfoService.getHosSyncConfigFromDB(hosId, mq);
			if (config == null)
				return TWO;
		}
		//对接中断
		if(TWO.equals(config.getStatus()))
			return TWO;
		//对接业务关闭
		HospitalCloudAccessRuleDBO bizConfig = config.getBizConfigs().get(bizName);
		if (bizConfig == null) {
			ICacheService mq = MQHelper.getMqService();
			config = HospitalInfoService.getHosSyncConfigFromDB(hosId, mq);
			bizConfig = config.getBizConfigs().get(bizName);
			if (bizConfig == null) {
				return NINE;
			}
		}
		return bizConfig.getAccessFlag();
	}

	/**
	 * 设定对接业务开启状态（1:对接打开 2：对接关闭 3：模拟对接9：无需对接）（基于缓存）
	 * 
	 * @param hosId
	 * @param bizName
	 * @param accessflag
	 * @return
	 */
	public boolean setBizSynchronizationType(String hosId, String bizName, String accessflag) {
		HospitalSyncConfigBean config = HospitalInfoService.getHosSyncConfig(hosId);
		if (config == null) {
			ICacheService mq = MQHelper.getMqService();
			config = HospitalInfoService.getHosSyncConfigFromDB(hosId, mq);
			if (config == null)
				return false;
		}
		HospitalCloudAccessRuleDBO bizConfig = config.getBizConfigs().get(bizName);
		if (bizConfig == null) {
			ICacheService mq = MQHelper.getMqService();
			config = HospitalInfoService.getHosSyncConfigFromDB(hosId, mq);
			bizConfig = config.getBizConfigs().get(bizName);
			if (bizConfig == null) {				
				bizConfig = new HospitalCloudAccessRuleDBO();
				bizConfig.setModuleType(bizName);
				bizConfig.setAccessType(TWO);
			}
		}
		bizConfig.setAccessFlag(accessflag);
		config.getBizConfigs().put(bizName, bizConfig);
		HospitalInfoService.setHosSyncConfig(config);
		return true;
	}

	// ////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////
	/**
	 * 检查医院接口是否开通
	 */
	public boolean checkCogradient(String hosId) {
		return checkCogradient(null, hosId, null);
	}

	/**
	 * 检查医院接口是否开通（单一业务）
	 */
	public boolean checkCogradient(String hosId, String bizName) {
		return checkCogradient(null, hosId, bizName);
	}

	/**
	 * 检查医院接口是否开通（单一业务）
	 */
	public boolean checkCogradient(String clientIp, String hosId, String bizName) {
		// 对接前置机合法性检查
		if (EmptyHelper.isNotEmpty(clientIp)) {
			// TODO
			return true;
		}
		if (EMPTY.equals(bizName)) {
			HospitalSyncConfigBean config = HospitalInfoService.getHosSyncConfig(hosId);
			if (config == null)
				return false;
			return true;
		}
		// 1:对接打开 2：对接关闭 3：模拟对接9：无需对接
		if (ONE.equals(getBizSynchronizationType(hosId, bizName)))
			return true;
		else
			return false;
	}

	///////////////////////////////////////////以下内容用于同步业务处理///////////////////////////////////////////////////

	@Resource
	HospitalCloudAccessRuleService HospitalCloudAccessRuleService_;
	@Resource
	HospitalCloudAccessMonitorService HospitalCloudAccessMonitorService_;

	/**
	 * 获取医院对接业务记录
	 * 
	 * @return
	 */
	public List<FrameworkDataBean> loadJobs() {
		HospitalCloudAccessMonitorDBO dbo = new HospitalCloudAccessMonitorDBO();
		return HospitalCloudAccessMonitorService_.doSelectData(dbo);
	}

	
	/**
	 * 获取医院对接业务记录
	 * 
	 * @return
	 */
	public List<FrameworkDataBean> loadJobByBizName(String bizName, String flag) {
		HospitalCloudAccessRuleDBO dbo = new HospitalCloudAccessRuleDBO();
		dbo.setModuleType(bizName);
		dbo.setAccessFlag(flag);
		return HospitalCloudAccessRuleService_.doSelectData(dbo);
	}

	/**
	 * 获取医院对接业务记录
	 * 
	 * @return
	 */
	public List<FrameworkDataBean> loadJobsByHospital(String hosId) {
		// Map<医院ID,List<业务>>
		HospitalCloudAccessRuleDBO dbo = new HospitalCloudAccessRuleDBO();
		dbo.setHosId(hosId);
		return HospitalCloudAccessRuleService_.doSelectData(dbo);
	}

	/**
	 * 
	 * @param hosId
	 * @param bizName
	 * @return
	 */
	public int loadJobByHospital(String hosId, String bizName) {
		try {
			HospitalCloudAccessRuleDBO dbo = new HospitalCloudAccessRuleDBO();
			dbo.setHosId(hosId);
			dbo.setModuleType(bizName);
			dbo.setAccessFlag(ONE);
			return Integer.parseInt(HospitalCloudAccessRuleService_.doSelectData(dbo).get(0).getFb1());
		} catch (Exception e) {
			return 100;
		}
	}

	/**
	 * 获取医院对接业务记录
	 * 
	 * @return
	 */
	public List<FrameworkDataBean> loadHospitalJob(String hosId, String bizName) {
		HospitalCloudAccessRuleDBO dbo = new HospitalCloudAccessRuleDBO();
		dbo.setHosId(hosId);
		dbo.setModuleType(bizName);
		dbo.setAccessFlag(ONE);
		return HospitalCloudAccessRuleService_.doSelectData(dbo);
	}

	/**
	 * 获取开通对接业务的医院
	 * 
	 * @return
	 */
	public List<FrameworkDataBean> loadJobHospital() {
		HospitalCloudAccessRuleDBO dbo = new HospitalCloudAccessRuleDBO();
		dbo.setAccessFlag(ONE);
		return HospitalCloudAccessRuleService_.doSelectData(dbo);
	}
}
