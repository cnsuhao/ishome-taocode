package com.mcookies.qxy.biz.device;

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
	 * 设备通信
	 */
	@RequestMapping(value = "/signCard.do", method = RequestMethod.GET)
	@ResponseBody
	public ResultBean signCardGET(@RequestBody String datas, SendTimecardDataPVO param) {
		ResultBean result = new ResultBean();
		param.setDatas(datas);

		if ("getconfig".equals(param.getMethod())) {// 设备读取服务器上的配置信息
			result.setRequestInterval("30");			
		} else if ("swingcarddata".equals(param.getMethod())) { // 上传刷卡数据
			DeviceHeartbeatThread dhbt = BeanFactoryHelper.getBean("DeviceHeartbeatThread");
			dhbt.setDeviceData(param);
			(new Thread(dhbt)).start();
			result.setRequestInterval("");
		} else if ("upstatus".equals(param.getMethod())) {// 上传设备状态数据
			result.setRequestInterval("");
		}

		return result;
	}
	/**
	 * 设备通信
	 */
	@RequestMapping(value = "/signCard.do", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean signCardPOST(@RequestBody String datas, SendTimecardDataPVO param) {
		ResultBean result = new ResultBean();
		param.setDatas(datas);
		if ("getconfig".equals(param.getMethod())) {// 设备读取服务器上的配置信息
			result.setRequestInterval("30");			
		} else if ("swingcarddata".equals(param.getMethod())) { // 上传刷卡数据
			DeviceHeartbeatThread dhbt = BeanFactoryHelper.getBean("DeviceHeartbeatThread");
			dhbt.setDeviceData(param);
			(new Thread(dhbt)).start();
			result.setRequestInterval("");
		} else if ("upstatus".equals(param.getMethod())) {// 上传设备状态数据
			result.setRequestInterval("");
		}

		return result;
	}
	public class ResultBean {
		String message = "OK";
		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		String RequestInterval = "30";

		public String getRequestInterval() {
			return RequestInterval;
		}

		public void setRequestInterval(String requestInterval) {
			RequestInterval = requestInterval;
		}
	}
}
