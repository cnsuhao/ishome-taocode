package com.ttnn.common.base.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceManager {

	public void init() {

	}

	public IService getService(String BID) {
		// 定义业务控制器
		IService ic = null;
		// 加载控制器配置表
		try {
			ic = (IService) Class.forName("").newInstance();
		} catch (InstantiationException e) {
			// 实例化异常
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// 安全权限异常private方法
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// 无法找到指定的类异常
			e.printStackTrace();
		}
		// 无特殊定义，默认指定
		if (ic == null)
			ic = new DefaultServiceImpl();
		return ic;
	}
}
