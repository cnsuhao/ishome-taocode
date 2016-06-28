package com.upg.zx.capture.bean;

import java.util.HashMap;

public class ServiceConfig {
	// 待处理业务区域
	private HashMap<String, String> serviceConfig = new HashMap<String, String>();

	public HashMap<String, String> getServiceConfig() {
		return serviceConfig;
	}

	public void setServiceConfig(HashMap<String, String> serviceConfig) {
		this.serviceConfig = serviceConfig;
	}

	public String getServiceConfig(String key) {
		return serviceConfig.get(key);
	}
}
