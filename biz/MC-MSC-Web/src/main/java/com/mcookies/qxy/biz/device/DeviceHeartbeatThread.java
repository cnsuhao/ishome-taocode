package com.mcookies.qxy.biz.device;

import javax.annotation.Resource;

import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mcookies.qxy.common.DeviceAlarm.DeviceAlarmService;
import com.mcookies.qxy.common.DeviceHeartbeat.DeviceHeartbeatDBO;
import com.mcookies.qxy.common.DeviceHeartbeat.DeviceHeartbeatService;
import com.mcookies.qxy.common.DeviceTag.DeviceTagDBO;
import com.mcookies.qxy.common.DeviceTag.DeviceTagService;
import com.mcookies.qxy.common.LogAttendance.LogAttendanceDBO;
import com.mcookies.qxy.common.LogAttendance.LogAttendanceService;
import com.mcookies.qxy.common.LogSecurity.LogSecurityDBO;
import com.mcookies.qxy.common.LogSecurity.LogSecurityService;
import com.mcookies.qxy.common.StudentRfid.StudentRfidDBO;
import com.mcookies.qxy.common.StudentRfid.StudentRfidService;

@Service("DeviceHeartbeatThread")
public class DeviceHeartbeatThread implements Runnable, ISFrameworkConstants {
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
	@Resource
	protected StudentRfidService StudentRfidService_;

	/**
	 * 数据包
	 */
	SendTimecardDataPVO deviceData;

	public void setDeviceData(SendTimecardDataPVO deviceData) {
		this.deviceData = deviceData;
	}

	/**
	 * 心跳时间
	 */
	int heartSecond = 10;

	public void setHeartSecond(int heartSecond) {
		this.heartSecond = heartSecond;
	}

	public void run() {
		saveDeviceDatas(deviceData);
	}

	final static String DeviceUserName = "DeviceUserName:";
	final static String DeviceDatas = "DeviceDatas";

	private void saveDeviceDatas(SendTimecardDataPVO param) {
		logger.debug("刷卡记录处理开始 =====>>>>>"+param);
		long curTime = System.currentTimeMillis();
		try {
			// 检查设备的正确性
			DeviceTagDBO dtd = null;
			{
				// 判断缓存里面是否存在
				myCache.selectDB(8);
				Object c = myCache.getObject(DeviceUserName + param.getDevID(), false);
				myCache.init();
				// 不存在的场合数据库加载
				if (EmptyHelper.isEmpty(c)) {
					dtd = new DeviceTagDBO();
					dtd.setPuk(ONE);
					dtd.setSid(Long.parseLong(deviceData.getEnterpriseID()));
					dtd.setDeviceId(param.getDevID());
					dtd = (DeviceTagDBO) DeviceTagService_.doRead(dtd);
				} else {
					dtd = JSON.parseObject((String) c, DeviceTagDBO.class);
				}
				// 设备无效的场合直接返回
				if (EmptyHelper.isEmpty(dtd)||EmptyHelper.isEmpty(dtd.getDeviceId())) {
					logger.error("该设备无效=====>>>>>" + param.getDevID());
					return;
				}else {
					dtd.setLastLoginTime("" + curTime);
					myCache.putObject(DeviceUserName + param.getDevID(), JSON.toJSONString(dtd), 0, false);
				}
				// 记录设备心跳
				{
					DeviceHeartbeatDBO dh = new DeviceHeartbeatDBO();
					dh.setSid(dtd.getSid());
					dh.setDeviceId(dtd.getDeviceId());
					dh.setSendTime(DateHelper.currentTimeMillis2());
					dh.setIsUse(0);
					DeviceHeartbeatService_.doInsert(dh);
				}
				// 计算最后报警
				{
					long lastTime = Long.parseLong(dtd.getLastLoginTime());
					if ((lastTime - curTime) > heartSecond * 60 * 1000) {
						logger.error("该设备异常=====>>>>>" + dtd.getDeviceId());
						return;
					}
				}
			}
			// 1111111111,002720004,999999999;2222222222,002720004,888888888
			String dd = param.getDatas();
			// 用户提交
			if (EmptyHelper.isNotEmpty(dd)) {
				LogAttendanceDBO la;
				LogSecurityDBO ls;
				StudentRfidDBO sr;
				String[] datas = dd.split(SEMICOLON);
				// 数据格式化
				for (String l : datas) {
					// rfid号1,学校编号1,time
					String[] sd = l.split(COMMA);
					sr = new StudentRfidDBO();
					sr.setRfid(Long.parseLong(sd[0]));
					sr.setSid(Long.parseLong(sd[1]));
					
					// 检查rfid卡号有效性
					sr = (StudentRfidDBO) StudentRfidService_.doRead(sr);
					// 保存学生刷卡信息
					if (sr != null) {
						la = new LogAttendanceDBO();
						la.setRfid(sr.getRfid());
						la.setDeviceId(dtd.getDeviceId());
						la.setMarkTime(DateHelper.currentTimeMillis2());
						//TODO
						la.setFlag(0);//0-进来；1-出去；2-出现
						la.setSourceJson(l);
						// 存在学生的场合
						LogAttendanceService_.doInsert(la);
					} else {
						ls = new LogSecurityDBO();
						//TODO
						LogSecurityService_.doInsert(ls);
					}
				}
			}
			logger.debug("刷卡记录处理结束 <<<<<=====");
		} catch (Exception e) {
			logger.error("日志保存失败", e);
			myCache.selectDB(8);
			myCache.offerObjectInList("", JSON.toJSONString(param), false);
			myCache.init();
		}

	}
}
