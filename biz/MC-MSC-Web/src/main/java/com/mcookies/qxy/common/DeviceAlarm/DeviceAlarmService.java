package com.mcookies.qxy.common.DeviceAlarm;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 设备报警表 */
@Service
public class DeviceAlarmService extends MyServiceSupport {

	public DeviceAlarmDao getDao() {
		return getMySqlSession().getMapper(DeviceAlarmDao.class);
	}
	/**
	 * 分页获取设备的报警信息
	 * 
	 * @param formParamPageModel
	 */
	public PageVOSupport doSelectPageDeviceAlarm(PageVOSupport formParamPageModel) {
		// 设定企业ID
		FrameworkDataBean formParamBean = formParamPageModel.getFormParamBean();
		formParamPageModel.setPageListData(getDao().doSelectPageDeviceAlarm(formParamPageModel));
		return formParamPageModel;
	}
}
