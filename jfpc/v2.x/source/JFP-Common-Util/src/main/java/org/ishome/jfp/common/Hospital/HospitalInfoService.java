package org.ishome.jfp.common.Hospital;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.ishome.jfp.beans.HospitalCloudAccessMonitor.HospitalCloudAccessMonitorDBO;
import org.ishome.jfp.beans.HospitalCloudAccessMonitor.HospitalCloudAccessMonitorService;
import org.ishome.jfp.beans.HospitalCloudAccessRule.HospitalCloudAccessRuleDBO;
import org.ishome.jfp.beans.HospitalCloudAccessRule.HospitalCloudAccessRuleService;
import org.ishome.jfp.framework.beands.FrameworkDataBean;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.constants.ISJobConstants;
import org.ishome.jfp.framework.mq.IMedMqService;
import org.ishome.jfp.framework.utils.BeanFactoryHelper;
import org.ishome.jfp.framework.utils.EmptyHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


/**
 * 医院状态检查
 * 
 * @author Spook
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
		IMedMqService mq = BeanFactoryHelper.getMqService();
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
	public static HospitalSyncConfigBean getHosSyncConfigFromDB(String hosId, IMedMqService mq) {
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
		IMedMqService mq = BeanFactoryHelper.getMqService();
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
			IMedMqService mq = BeanFactoryHelper.getMqService();
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
			IMedMqService mq = BeanFactoryHelper.getMqService();
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
			IMedMqService mq = BeanFactoryHelper.getMqService();
			config = getHosSyncConfigFromDB(hosId, mq);
			if (config == null)
				return false;
		}
		HospitalCloudAccessRuleDBO bizConfig = config.getBizConfigs().get(bizName);
		if (bizConfig == null) {
			IMedMqService mq = BeanFactoryHelper.getMqService();
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
			IMedMqService mq = BeanFactoryHelper.getMqService();
			config = getHosSyncConfigFromDB(hosId, mq);
			if (config == null)
				return false;
		}
		HospitalCloudAccessRuleDBO bizConfig = config.getBizConfigs().get(bizName);
		if (bizConfig == null) {
			IMedMqService mq = BeanFactoryHelper.getMqService();
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
			IMedMqService mq = BeanFactoryHelper.getMqService();
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
			IMedMqService mq = BeanFactoryHelper.getMqService();
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
			IMedMqService mq = BeanFactoryHelper.getMqService();
			config = getHosSyncConfigFromDB(hosId, mq);
			if (config == null)
				return false;
		}
		HospitalCloudAccessRuleDBO bizConfig = config.getBizConfigs().get(bizName);
		if (bizConfig == null) {
			IMedMqService mq = BeanFactoryHelper.getMqService();
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

	///////////////////////////////////////////以下内容用于同步业务处理///////////////////////////////////////////////////
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
	public List<FrameworkDataBean> loadJobByHospital(String hosId) {
		// TODO
		// (缓存加速)
		// Map<医院ID,List<业务>>
		HospitalCloudAccessRuleDBO dbo = new HospitalCloudAccessRuleDBO();
		dbo.setHosId(hosId);
		dbo.setAccessFlag(ONE);
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
