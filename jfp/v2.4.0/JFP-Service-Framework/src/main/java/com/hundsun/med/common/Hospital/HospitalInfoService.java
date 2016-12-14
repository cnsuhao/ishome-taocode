package com.hundsun.med.common.Hospital;

import java.util.HashMap;

import javax.annotation.Resource;

import org.ishome.jfp.framework.beands.common.FrameworkDataBean;
import org.ishome.jfp.framework.cache.mq.MQHelper;
import org.ishome.jfp.framework.cache.utils.redis.IMedMqService;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.constants.pub.ISJobConstants;
import org.ishome.jfp.framework.utils.BeanFactoryHelper;
import org.ishome.jfp.framework.utils.EmptyHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.hundsun.med.beans.HospitalCloudAccessMonitor.HospitalCloudAccessMonitorDBO;
import com.hundsun.med.beans.HospitalCloudAccessMonitor.HospitalCloudAccessMonitorService;
import com.hundsun.med.beans.HospitalCloudAccessRule.HospitalCloudAccessRuleDBO;
import com.hundsun.med.beans.HospitalCloudAccessRule.HospitalCloudAccessRuleService;

/**
 * 医院配置参数缓存
 * 
 * @author fucy
 * 
 */
@Service
public class HospitalInfoService implements ISFrameworkConstants, ISJobConstants {

	/**
	 * 获得医院对接配置参数（全体）<br>
	 * 缓存中获取
	 * @param hosId
	 *            医院ID
	 * @return
	 */
	public static HospitalSyncConfigBean getHosSyncConfig(String hosId) {
		HospitalSyncConfigBean config;
		ICacheService mq = MQHelper.getMqService();
		// 缓存中获取
		config = (HospitalSyncConfigBean) mq.findObjectInMap(CONFIG_KEY, hosId);
		// 数据库重新获取
		if (config == null) {
			return getHosSyncConfigFromDB(hosId, mq);
		}
		return config;
	}
	/**
	 * 获得医院对接配置参数（全体）<br>
	 * 数据库里面获取
	 * @param hosId
	 *            医院ID
	 * @return
	 */
	public static HospitalSyncConfigBean getHosSyncConfigFromDB(String hosId, ICacheService mq) {
		HospitalSyncConfigBean config = new HospitalSyncConfigBean();
		// 获取基本配置
		HospitalCloudAccessMonitorService _HospitalCloudAccessMonitorService = BeanFactoryHelper.getBean("hospitalCloudAccessMonitorService");
		HospitalCloudAccessMonitorDBO dbo = new HospitalCloudAccessMonitorDBO();
		dbo.setHosId(hosId);
		dbo = (HospitalCloudAccessMonitorDBO) _HospitalCloudAccessMonitorService.doRead(dbo);
		if (dbo == null)
			return config;
		BeanUtils.copyProperties(dbo, config);
		
		config.setHosId(hosId);

		// 获取业务配置
		HospitalCloudAccessRuleService _HospitalCloudAccessRuleService = BeanFactoryHelper.getBean("hospitalCloudAccessRuleService");
		HashMap<String, HospitalCloudAccessRuleDBO> bizConfigs = new HashMap<String, HospitalCloudAccessRuleDBO>();
		HospitalCloudAccessRuleDBO formParamBean = new HospitalCloudAccessRuleDBO();
		formParamBean.setHosId(hosId);
		for (FrameworkDataBean ldbo : _HospitalCloudAccessRuleService.doSelectData(formParamBean)) {
			formParamBean = (HospitalCloudAccessRuleDBO) ldbo;
			bizConfigs.put(formParamBean.getModuleType(), formParamBean);
		}
		config.setBizConfigs(bizConfigs);
		// 加入缓存
		setHosSyncConfig(config);
		return config;
	}
	
	/**
	 * 获得对接业务配置
	 * 
	 * @param hosId
	 * @param bizName
	 * @return
	 */
	public static HospitalCloudAccessRuleDBO getBizSyncConfigBean(String hosId, String bizName) {
		HospitalSyncConfigBean config = getHosSyncConfig(hosId);
		if (config == null)
			return null;
		HospitalCloudAccessRuleDBO bizConfig = config.getBizConfigs().get(bizName);
		if (bizConfig == null)
			return null;
		return bizConfig;
	}
	
	/**
	 * 设置医院对接配置参数
	 * 
	 * @param hosId
	 *            医院ID
	 * @return
	 */
	public static boolean setHosSyncConfig(HospitalSyncConfigBean config) {
		ICacheService mq = MQHelper.getMqService();
		if (mq == null)
			return false;
		mq.addObjectInMap(CONFIG_KEY, config.getHosId(), config);
		return true;
	}

	// ///////////////////////////////////////////////////////////////////////////////////
	/**
	 * 获得医院令牌(云端使用)
	 * 
	 * @param hosId
	 * @return
	 */
	public String getHospitalToken(String hosId) {
		HospitalSyncConfigBean config = getHosSyncConfig(hosId);
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
		HospitalSyncConfigBean config = getHosSyncConfig(hosId);
		if (config == null) {
			ICacheService mq = MQHelper.getMqService();
			config = getHosSyncConfigFromDB(hosId, mq);
			if (config == null)
				return false;
		}
		config.setToken(token);
		setHosSyncConfig(config);
		return true;
	}

	/**
	 * 获得医院私钥(云端使用)
	 * 
	 * @param hosId
	 * @return
	 */
	public String getHospitalPrivateSecurityKeyInClound(String hosId) {
		HospitalSyncConfigBean config = getHosSyncConfig(hosId);
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
		HospitalSyncConfigBean config = getHosSyncConfig(hosId);
		if (config == null) {
			ICacheService mq = MQHelper.getMqService();
			config = getHosSyncConfigFromDB(hosId, mq);
			if (config == null)
				return false;
		}
		config.setPrimaryKey(key);
		setHosSyncConfig(config);
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
		HospitalSyncConfigBean config = getHosSyncConfig(hosId);
		if (config == null) {
			ICacheService mq = MQHelper.getMqService();
			config = getHosSyncConfigFromDB(hosId, mq);
			if (config == null)
				return false;
		}
		HospitalCloudAccessRuleDBO bizConfig = config.getBizConfigs().get(bizName);
		if (bizConfig == null) {
			ICacheService mq = MQHelper.getMqService();
			config = getHosSyncConfigFromDB(hosId, mq);
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
		HospitalSyncConfigBean config = getHosSyncConfig(hosId);
		if (config == null) {
			ICacheService mq = MQHelper.getMqService();
			config = getHosSyncConfigFromDB(hosId, mq);
			if (config == null)
				return false;
		}
		HospitalCloudAccessRuleDBO bizConfig = config.getBizConfigs().get(bizName);
		if (bizConfig == null) {
			ICacheService mq = MQHelper.getMqService();
			config = getHosSyncConfigFromDB(hosId, mq);
			bizConfig = config.getBizConfigs().get(bizName);
			if (bizConfig == null) {				
				bizConfig = new HospitalCloudAccessRuleDBO();
				bizConfig.setModuleType(bizName);
			}
		}
		if (encryptionType)
			bizConfig.setFb3(ONE);
		config.getBizConfigs().put(bizName, bizConfig);
		setHosSyncConfig(config);
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
		HospitalSyncConfigBean config = getHosSyncConfig(hosId);
		if (config == null) {
			ICacheService mq = MQHelper.getMqService();
			config = getHosSyncConfigFromDB(hosId, mq);
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
			config = getHosSyncConfigFromDB(hosId, mq);
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
		HospitalSyncConfigBean config = getHosSyncConfig(hosId);
		if (config == null) {
			ICacheService mq = MQHelper.getMqService();
			config = getHosSyncConfigFromDB(hosId, mq);
			if (config == null)
				return false;
		}
		HospitalCloudAccessRuleDBO bizConfig = config.getBizConfigs().get(bizName);
		if (bizConfig == null) {
			ICacheService mq = MQHelper.getMqService();
			config = getHosSyncConfigFromDB(hosId, mq);
			bizConfig = config.getBizConfigs().get(bizName);
			if (bizConfig == null) {				
				bizConfig = new HospitalCloudAccessRuleDBO();
				bizConfig.setModuleType(bizName);
				bizConfig.setAccessType(TWO);
			}
		}
		bizConfig.setAccessFlag(accessflag);
		config.getBizConfigs().put(bizName, bizConfig);
		setHosSyncConfig(config);
		return true;
	}

	// ////////////////////////////////////////////////////////////
	// ////////////////////////////////////////////////////////////////////////

	@Resource
	HospitalCloudAccessRuleService HospitalCloudAccessRuleService_;

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
			HospitalSyncConfigBean config = getHosSyncConfig(hosId);
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
}
