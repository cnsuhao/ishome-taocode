package com.mcookies.qxy.common.DeviceAlarm;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 设备报警表 */
public interface DeviceAlarmDao extends IDatabaseSupport {
	List<? extends FrameworkDataBean> doSelectPageDeviceAlarm(PageVOSupport formParamPageModel);
}
