package com.mcookies.qxy.biz.device;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mcookies.qxy.common.DeviceAlarm.DeviceAlarmService;
import com.mcookies.qxy.common.DeviceHeartbeat.DeviceHeartbeatDBO;
import com.mcookies.qxy.common.DeviceHeartbeat.DeviceHeartbeatService;
import com.mcookies.qxy.common.DeviceTag.DeviceTagDBO;
import com.mcookies.qxy.common.DeviceTag.DeviceTagService;
import com.mcookies.qxy.common.LogAttendance.LogAttendanceDBO;
import com.mcookies.qxy.common.LogAttendance.LogAttendanceService;
import com.mcookies.qxy.common.LogSecurity.LogSecurityDBO;
import com.mcookies.qxy.common.LogSecurity.LogSecurityService;

@Service("DeviceHeartbeatThread")
public class DeviceHeartbeatThread extends Thread {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
	 * Redis缓存
	 */
	@Resource
	protected ICacheService myCache;
	@Resource
	protected DeviceHeartbeatService DeviceHeartbeatService_;
	@Resource
	protected DeviceTagService DeviceTagService_;
	@Resource
	protected DeviceAlarmService DeviceAlarmService_;
	@Resource
	protected LogAttendanceService LogAttendanceService_;
	@Resource
	protected LogSecurityService LogSecurityService_;

	SendTimecardDataPVO deviceData;

	public SendTimecardDataPVO getDeviceData() {
		return deviceData;
	}

	public void setDeviceData(SendTimecardDataPVO deviceData) {
		this.deviceData = deviceData;
	}

	public void run() {
		saveDeviceDatas(deviceData);
	}

	private void saveDeviceDatas(SendTimecardDataPVO data) {

		logger.debug("刷卡记录处理开始 =====>>>>>");
		try {

			// 检查设备的正确性
			{
				DeviceTagDBO dt = null;
				// 判断缓存里面是否存在
				// 不存在的场合数据库加载
				// 设备无效的场合直接返回
				if (data == null) {
					logger.error("该设备无效" + data.getUsername());
					return;
				}
				// 设备用户密码错误的场合返回
				if (dt.getPassword().equals(data.getPassword())==false) {
					logger.error("设备用户密码错误" + data.getUsername());
					return;
				}
				// 记录设备心跳
				{
					DeviceHeartbeatDBO dh = new DeviceHeartbeatDBO();
					DeviceHeartbeatService_.doInsert(dh);
				}
				long curTime = System.currentTimeMillis();
				// 计算最后报警
				{
					long last = Long.parseLong(dt.getLastLoginTime());

				}
			}
			// 用户提交
			if (EmptyHelper.isNotEmpty(data.getDatas())) {
				ArrayList<JSONObject> datas = new ArrayList<JSONObject>();
				// 数据格式化
				{

				}
				LogAttendanceDBO la;
				LogSecurityDBO ls;
				for (JSONObject l : datas) {
					// 保存学生刷卡信息
					if (true) {
						la = new LogAttendanceDBO();
						// 存在学生的场合
						LogAttendanceService_.doInsert(la);
					} else {
						ls = new LogSecurityDBO();
						LogSecurityService_.doInsert(ls);
					}
				}
			}
			logger.debug("刷卡记录处理结束 <<<<<=====");
		} catch (Exception e) {
			logger.error("日志保存失败", e);
			myCache.offerObjectInList("", JSON.toJSONString(data), false);
		}

	}
}
