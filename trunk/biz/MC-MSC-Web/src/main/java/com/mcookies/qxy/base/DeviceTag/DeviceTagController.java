package com.mcookies.qxy.base.DeviceTag;
import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 设备标签表*/
public class DeviceTagController extends MyControllerSupport {
    @Resource
    protected DeviceTagService DeviceTagService_;

}
