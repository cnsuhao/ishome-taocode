package com.mcookies.qxy.common.DeviceHeartbeat;

import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 设备心跳表 */
@Service
public class DeviceHeartbeatService extends MyServiceSupport {

	public DeviceHeartbeatDao getDao() {
		return getMySqlSession().getMapper(DeviceHeartbeatDao.class);
	}

}
