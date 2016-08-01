package com.mcookies.qxy.common.DeviceAlarm;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 设备报警表 */
public class DeviceAlarmController extends MyControllerSupport {
	@Resource
	protected DeviceAlarmService DeviceAlarmService_;

}
