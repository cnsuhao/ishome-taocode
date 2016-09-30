package com.mcookies.qxy.common.DeviceGroup;

import java.util.List;
import java.util.Map;

import org.isotope.jfp.framework.support.IDatabaseSupport;
public interface DeviceGroupDao extends IDatabaseSupport{
	
	List<DeviceGroupPVO> doSelectDeviceGroup(Map<String,Object> param);

}
