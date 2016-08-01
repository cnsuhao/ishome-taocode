package com.mcookies.qxy.common.DeviceTag;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 设备标签表 */
@Service
public class DeviceTagService extends MyServiceSupport {
	protected static final Logger logger = LoggerFactory.getLogger(DeviceTagService.class);

	public DeviceTagDao getDao() {
		return getMySqlSession().getMapper(DeviceTagDao.class);
	}

}
