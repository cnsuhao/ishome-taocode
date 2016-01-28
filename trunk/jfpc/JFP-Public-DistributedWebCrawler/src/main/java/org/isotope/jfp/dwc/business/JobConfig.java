package org.isotope.jfp.dwc.business;

import java.util.HashMap;
/**
 * 抓取任务配置
 * Distributed Web Crawler
 * @author ISHome
 * @version 0.0.1
 * @since 0.0.1
 */
public class JobConfig {
	// 待处理业务区域
	private HashMap<String, String> monitorConfig = new HashMap<String, String>();

	public HashMap<String, String> getMonitorConfig() {
		return monitorConfig;
	}

	public void setMonitorConfig(HashMap<String, String> monitorConfig) {
		this.monitorConfig = monitorConfig;
	}
	
	public void addMonitorConfig(String monitor , String Config) {
		monitorConfig.put(monitor, Config);
	}
	
	public String getJob(String monitor){
		return monitorConfig.get(monitor);
	}
}
