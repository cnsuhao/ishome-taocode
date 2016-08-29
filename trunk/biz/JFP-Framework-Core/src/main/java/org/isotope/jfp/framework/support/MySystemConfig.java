package org.isotope.jfp.framework.support;

import java.util.HashMap;

/**
 * 系统配置定义
 * 
 * @author Spook
 * 
 * @since 3.3.1 20160826
 * @version 3.3.1 20160826
 */
public class MySystemConfig {

	HashMap<String, String> configs = new HashMap<String, String>();

	/**
	 * 获得一个系统配置定义
	 * 
	 * @param key
	 * @return
	 */
	public String getConfig(String key) {
		return configs.get(key);
	}

	/**
	 * 设置一个系统配置定义
	 * 
	 * @param key
	 * @param config
	 */
	public void setConfig(String key, String config) {
		this.configs.put(key, config);
	}

	/**
	 * 加载系统配置定义
	 * 
	 * @param configs
	 */
	public void setConfigs(HashMap<String, String> configs) {
		this.configs = configs;
	}

}
