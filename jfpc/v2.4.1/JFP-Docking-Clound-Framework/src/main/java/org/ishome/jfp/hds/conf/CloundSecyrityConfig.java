package org.ishome.jfp.hds.conf;

import javax.annotation.Resource;

import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.support.ISSecuritySupport;
import org.ishome.jfp.hds.constants.IHDSConstants;
import org.springframework.stereotype.Service;

/**
 * 云端服务配置
 * 
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
@Service
public class CloundSecyrityConfig implements IHDSConstants, ISFrameworkConstants {

	// 加密工具
	@Resource
	ISSecuritySupport securitySupport;
	
	/**
	 * 根据对接业务设定加密（强制使用）
	 */
	public String encryption(HospitalSyncConfigBean config, String jsonData, boolean encryptionType) {
		if (encryptionType == true){			
			return securitySupport.encryption(config.getPrimaryKey(), jsonData);
		}
		else
			return jsonData;
	}
	
	/**
	 * 根据对接业务设定加密(自动判断)
	 */
	public String encryption(HospitalSyncConfigBean config, String bizName, String jsonData) {
		if (getBizEncryptionType(config, bizName))
			return securitySupport.encryption(config.getPrimaryKey(), jsonData);
		return encryption(config, jsonData, getBizEncryptionType(config, bizName));
	}
	
	/**
	 * 获得对接业务配置
	 * 
	 * @param hosId
	 * @param bizName
	 * @return
	 */
	public HospitalCloudAccessRuleDBO getBizSyncConfigBean(HospitalSyncConfigBean config, String bizName) {
		if (config == null)
			return null;
		HospitalCloudAccessRuleDBO bizConfig = config.getBizConfigs().get(bizName);
		if (bizConfig == null)
			return null;
		return bizConfig;
	}
	
	/**
	 * 获得对接业务加密状态
	 * 
	 * @param hosId
	 * @param bizName
	 * @return
	 */
	public boolean getBizEncryptionType(HospitalSyncConfigBean config, String bizName) {
		if (config == null)
			return false;
		HospitalCloudAccessRuleDBO bizConfig = getBizSyncConfigBean(config,bizName);
		if (bizConfig == null)
			return false;
		if (ONE.equals(bizConfig.getFb3()))
			return true;
		return false;
	}

	/**
	 * 根据对接业务设定解密(自动判断)
	 */
	public String decryption(HospitalSyncConfigBean config, String bizName, String jsonData) {
		if (getBizEncryptionType(config, bizName))
			return securitySupport.decryption(config.getPrimaryKey(), jsonData);
		
		return jsonData;
	}

	/**
	 * 根据对接业务设定解密（强制使用）
	 */
	public String decryption(HospitalSyncConfigBean config, String jsonData, boolean encryptionType) {
		if (encryptionType == true)
			// 解密数据
			return securitySupport.decryption(config.getPrimaryKey(), jsonData);
		else
			return jsonData;
	}
	
}
