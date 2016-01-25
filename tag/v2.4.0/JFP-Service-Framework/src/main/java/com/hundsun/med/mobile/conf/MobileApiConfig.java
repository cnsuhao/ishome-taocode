package com.hundsun.med.mobile.conf;

import java.util.Map;

import com.hundsun.med.framework.constants.ISFrameworkConstants;
import com.hundsun.med.mobile.constants.IMSRConstants;

/**
 * 云端服务配置
 * 
 * @author fucy
 * @version 2.3.1 2015/6/19 开启网闸穿透模式<remoteFlag>
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public class MobileApiConfig  implements IMSRConstants, ISFrameworkConstants {	
	
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
}
