package com.mcookies.qxy.biz.device;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.support.MyControllerSupport;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
/** 设备心跳(刷卡) */
public class DeviceHeartbeatController extends MyControllerSupport {

	/**
	 * 新闻栏目新增接口 /column
	 */
	@RequestMapping(value = "/column", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public RESTResultBean columnPOST(@RequestBody SendTimecardDataPVO datas) {
		RESTResultBean result = new RESTResultBean();
		DeviceHeartbeatThread dhbt = BeanFactoryHelper.getBean("DeviceHeartbeatThread");
		dhbt.setDeviceData(datas);
		dhbt.start();

		return result;
	}
}
