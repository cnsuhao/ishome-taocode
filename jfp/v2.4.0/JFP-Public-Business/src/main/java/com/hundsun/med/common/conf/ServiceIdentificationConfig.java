package com.hundsun.med.common.conf;

import java.util.Map;

/**
 * 新旧系统业务标识
 * @author fucy
 *
 */
public class ServiceIdentificationConfig {
	
	/**
	 * 系统业务标识配置
	 */
	private Map<String,String> serviceIdentification;
	
	public Map<String, String> getServiceIdentification() {
		return serviceIdentification;
	}

	public void setServiceIdentification(Map<String, String> serviceIdentification) {
		this.serviceIdentification = serviceIdentification;
	}
	
	/**
	 * 根据名称获取ID
	 * @param serviceName
	 * @return
	 */
	public String getServiceId(String serviceName){
		return serviceName;
	}
	/**
	 * 根据ID获取名称
	 * @param serviceName
	 * @return
	 */
	public String getServiceName(String serviceId){
		return serviceId;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
