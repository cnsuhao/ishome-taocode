package org.ishome.jfp.pdp.conf;

import javax.annotation.Resource;

import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.mq.IMedMqService;
import org.ishome.jfp.framework.support.ISSecuritySupport;
import org.ishome.jfp.framework.utils.BeanFactoryHelper;
import org.ishome.jfp.pdp.constants.IHDPConstants;
import org.springframework.stereotype.Service;


/**
 * 云端服务配置
 * 
 * @author Spook
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
@Service
public class HospitalSecyrityConfig implements IHDPConstants, ISFrameworkConstants {
	// 加密工具
	@Resource
	ISSecuritySupport securitySupport;

	public ISSecuritySupport getSecuritySupport() {
		return securitySupport;
	}

	public void setSecuritySupport(ISSecuritySupport securitySupport) {
		this.securitySupport = securitySupport;
	}
	@Resource
	IMedMqService IMedMqService_;// 缓存队列

	// /////////////////////////////////////////加密解密处理///////////////////////////////////////////////////////
	public IMedMqService getMqService() {
		if (IMedMqService_ == null)
			IMedMqService_ = BeanFactoryHelper.getMqService();
		return IMedMqService_;
	}

	public void setMqService(IMedMqService iMedMqService) {
		this.IMedMqService_ = iMedMqService;
	}

	/** 
     * 对接验证KEY(私钥）
     */
	public String getPrimaryKey() {
		return (String) getMqService().getObject(MQ_KEY_SEC);
	}

	/**
	 * 获得对接业务加密状态
	 * 
	 * @param hosId
	 * @param bizName
	 * @return
	 */
	public boolean getBizEncryptionType(String bizName) {
		//1开通2关闭
		if (ONE.equals(getMqService().getObject(bizName)))
			return true;
		return false;
	}

	/**
	 * 加密
	 */
	public String encryption(String bizName, String jsonData) {
		if (getBizEncryptionType(bizName))
			return securitySupport.encryption(getPrimaryKey(), jsonData);
		return jsonData;
	}

	/**
	 * 解密
	 */
	public String decryption(String bizName, String jsonData) {
		if (getBizEncryptionType(bizName))
			return securitySupport.decryption(getPrimaryKey(), jsonData);
		return jsonData;
	}
}
