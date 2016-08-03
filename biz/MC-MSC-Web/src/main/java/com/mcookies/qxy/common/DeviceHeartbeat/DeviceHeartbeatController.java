package com.mcookies.qxy.common.DeviceHeartbeat;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 设备心跳表 */
public class DeviceHeartbeatController extends MyControllerSupport {
	@Resource
	protected DeviceHeartbeatService DeviceHeartbeatService_;

}
