package org.ishome.jfp.pdp.conf;

import java.util.HashMap;
import java.util.Map;

import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.pdp.constants.IHDPConstants;


/**
 * 云端服务配置
 * 
 * @author Spook
 * @version 2.3.1 2015/6/19 开启网闸穿透模式<remoteFlag>
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public class CloundApiConfig  implements IHDPConstants, ISFrameworkConstants {
	
	/**
	 * 医院开启网闸模式
	 */
	String remoteFlag;

	public String getRemoteFlag() {
		return remoteFlag;
	}

	public void setRemoteFlag(String remoteFlag) {
		this.remoteFlag = remoteFlag;
	}

	/**
	 * 医院对接ID
	 */
	String hospitalId;

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	/**
	 * 日常监听业务
	 */
	Map<String, String> monitorConfig;

	public Map<String, String> getMonitorConfig() {
		return monitorConfig;
	}

	public void setMonitorConfig(Map<String, String> monitorConfig) {
		this.monitorConfig = monitorConfig;
	}
	/**
	 * 云端服务接口地址
	 */
	Map<String, String> cloundApiConfig = new HashMap<String, String>();

	public Map<String, String> getCloundApiConfig() {
		return cloundApiConfig;
	}

	public void setCloundApiConfig(Map<String, String> cloundApiConfig) {
		this.cloundApiConfig = cloundApiConfig;
	}

	// ////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 
	 */
	String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * 创建日期（需要与数据库保持一致）
	 */
	String creatingDate;

	public String getCreatingDate() {
		return creatingDate;
	}

	public void setCreatingDate(String creatingDate) {
		this.creatingDate = creatingDate;
	}
	/**
	 * 医院接口服务器地址
	 */
	String hospitalUrl;
	public String getHospitalUrl() {
		return hospitalUrl;
	}

	public void setHospitalUrl(String hospitalUrl) {
		this.hospitalUrl = hospitalUrl;
	}

	/**
	 * 掌医服务器地址
	 */
	String serverUrl;
	
	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	/**
	 * 加密状态（动态设定，基于服务器获得）
	 */
	Map<String, String> encryptionType = new HashMap<String, String>();

	public String getEncryptionType(String bizName) {
		if(encryptionType.containsKey(bizName))
			return encryptionType.get(bizName);
		return DECRYPTION;
	}

	public void setEncryptionType(Map<String, String> encryptionType) {
		this.encryptionType = encryptionType;
	}
	
	// ////////////////////////////////////////////////////////////////////////////////////////////////
	protected String getUrl(String type,String bizName){
		StringBuffer sb = new StringBuffer();
		sb.append(getServerUrl());
		sb.append(cloundApiConfig.get(type));
		sb.append(BACKSLASH);
		sb.append(getHospitalId());
		sb.append(BACKSLASH);
		sb.append(bizName);
		return sb.toString();
	}

	/**
	 * 获得云端接口同步API请求地址（系统数据接收地址）
	 * 
	 * @param bizName
	 */
	public String getSyncApiUrl(String bizName) {
		if (cloundApiConfig.containsKey(API_SYNC_NORMAL))
			return getUrl(API_SYNC_NORMAL, bizName) + BACKSLASH + getEncryptionType(bizName);
		return null;
	}

	/**
	 * 获得云端接口同步API请求地址（系统处理结果）
	 * 
	 * @param bizName
	 */
	public String getSyncCheckApiUrl(String bizName) {
		if (cloundApiConfig.containsKey(API_SYNC_CHECK))
			return getUrl(API_SYNC_CHECK, bizName);
		return null;
	}

	/**
	 * 获得云端接口业务API请求地址（用户请求队列）
	 * 
	 * @param bizName
	 */
	public String getBizApiUrl(String bizName) {
		if (cloundApiConfig.containsKey(API_BIZ_NORMAL))
			return getUrl(API_BIZ_NORMAL, bizName);
		return null;
	}

	/**
	 * 获得云端接口业务API请求地址（回传业务处理结果）
	 * 需要处理加密解密
	 * 
	 * @param bizName
	 * @param operationId
	 * @return
	 */
	public String getBizResultApiUrl(String bizName, String operationId) {
		if (cloundApiConfig.containsKey(API_BIZ_RESULT))
			return getUrl(API_BIZ_RESULT, bizName) + BACKSLASH + operationId + BACKSLASH + getEncryptionType(bizName);
		return null;
	}
	
	/**
	 * 获得云端接口业务API请求地址（回传业务处理结果）
	 * 需要处理加密解密
	 * 
	 * @param bizName
	 * @param operationId
	 * @return
	 */
	public String getCloundServiceUrl(String bizName) {
		if (cloundApiConfig.containsKey(API_CLOUND_BIZ))
			return getUrl(API_CLOUND_BIZ, bizName) + BACKSLASH + getEncryptionType(bizName);
		return null;
	}
}
