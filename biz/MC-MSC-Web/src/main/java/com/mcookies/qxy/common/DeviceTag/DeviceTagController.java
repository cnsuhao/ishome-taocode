package com.mcookies.qxy.common.DeviceTag;

import javax.annotation.Resource;

import org.isotope.jfp.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 设备标签表 */
public class DeviceTagController extends MyControllerSupport {
	@Resource
	protected DeviceTagService DeviceTagService_;

}
