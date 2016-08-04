package com.mcookies.qxy.common.DeviceTag;

import java.util.List;
import java.util.Map;

import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 设备标签表 */
public interface DeviceTagDao extends IDatabaseSupport {
	
	List<DeviceTagPVO> doSelectDevice(Map<String,Object> param);

}
