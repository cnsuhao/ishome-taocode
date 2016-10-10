package com.mcookies.qxy.task;

import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.biz.common.ISInit;
import org.isotope.jfp.framework.mybatis.plugin.dialect.DefaultDialect;
import org.isotope.jfp.framework.support.MyTaskSupport;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.mcookies.qxy.biz.device.DeviceConstants;
import com.mcookies.qxy.common.DeviceAlarm.DeviceAlarmDBO;
import com.mcookies.qxy.common.DeviceAlarm.DeviceAlarmService;
import com.mcookies.qxy.common.DeviceTag.DeviceTagDBO;
import com.mcookies.qxy.common.DeviceTag.DeviceTagService;

/**
 * 设备监控
 * 
 * @author Spook
 * @since 0.0.1
 * @version 0.0.1 2016/8/8
 */
public class DeviceAlarmTaskJob extends MyTaskSupport implements ISInit, DeviceConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * 心跳时间
	 */
	int heartSecond = 10;

	public void setHeartSecond(int heartSecond) {
		this.heartSecond = heartSecond;
	}

	int monitorSize = 50;

	public int getMonitorSize() {
		return monitorSize;
	}

	public void setMonitorSize(int monitorSize) {
		this.monitorSize = monitorSize;
	}

	@Resource
	protected DeviceTagService DeviceTagService_;
	@Resource
	protected DeviceAlarmService DeviceAlarmService_;
	@Resource
	protected DefaultDialect defaultDialect;
	 
	/**
	 * 业务处理(重复运行)
	 */
	@Override
	public boolean doProcessRepeat() throws Exception {
		PageVOSupport pageModel = new PageVOSupport();
		pageModel.setDefaultDialect(defaultDialect);
		// 加载全部设备(每次监控50个设备)
		int page = 1;
		long curTime = System.currentTimeMillis();
		DeviceTagDBO devicedbo = new DeviceTagDBO();
		devicedbo.setPuk(ONE);
		devicedbo.setIsUse(1);

		pageModel.config();
		pageModel.setPageCurrent(page);
		pageModel.setPageLimit(monitorSize);
		pageModel.setFormParamBean(devicedbo);

		List<DeviceTagDBO> list = (List<DeviceTagDBO>) DeviceTagService_.doSelectPage(pageModel).getPageListData();

		DeviceTagDBO dtd = null;
		while (list != null && list.size() > 0) {
			// 监控每个设备信息
			for (DeviceTagDBO device : list) {
				// 判断缓存里面是否存在
				myCacheService.selectDB(8);
				Object c = myCacheService.getObject(DeviceUserName + device.getDeviceId(), false);
				myCacheService.init();
				if (EmptyHelper.isEmpty(c)) {
					// 缓存不存在，表明设备没有连接
					logger.warn("该设备没有连接=====>>>>>" + device.getDeviceId());
					DeviceAlarmDBO da = new DeviceAlarmDBO();
					da.setDeviceId(device.getDeviceId());
					da.setAlarmTime(DateHelper.currentTimeMillis2());
					da.setAlarmInformation("设备连接异常报警");
					da.setIsUse(0);
					da.setPuk(ONE);
					da.setSid(device.getSid());
					DeviceAlarmService_.doInsert(da);
					continue;
				} else {
					dtd = JSON.parseObject((String) c, DeviceTagDBO.class);
				}

				long lastTime = Long.parseLong(dtd.getLastLoginTime());
				if ((lastTime - curTime) > heartSecond * 60 * 1000) {
					logger.warn("该设备异常=====>>>>>" + device.getDeviceId());
					DeviceAlarmDBO da = new DeviceAlarmDBO();
					da.setDeviceId(device.getDeviceId());
					da.setAlarmTime(DateHelper.currentTimeMillis2());
					da.setAlarmInformation("设备通信时间异常报警");
					da.setIsUse(0);
					da.setPuk(ONE);
					da.setSid(device.getSid());
					DeviceAlarmService_.doInsert(da);
				}
			}

			// 监控下一批设备
			pageModel.config();
			page = page + 1;
			pageModel.setPageCurrent(page);
			pageModel.setPageLimit(monitorSize);
			pageModel.setFormParamBean(devicedbo);

			list = (List<DeviceTagDBO>) DeviceTagService_.doSelectPage(pageModel).getPageListData();
		}

		return true;
	}

	@Override
	public boolean doInit() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
}
