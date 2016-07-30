package com.mcookies.qxy.base.DeviceHeartbeat;
import javax.annotation.Resource;

import org.jfpc.framework.support.MyControllerSupport;
import org.springframework.stereotype.Controller;

@Controller
/** 设备心跳表*/
public class DeviceHeartbeatController extends MyControllerSupport {
    @Resource
    protected DeviceHeartbeatService DeviceHeartbeatService_;


}
