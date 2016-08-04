package com.mcookies.qxy.common.DeviceAlarm;
import java.sql.Timestamp;

import javax.inject.Named;
 
@Named
/** 设备报警表*/
public class DeviceAlarmPVO extends DeviceAlarmDBO
{
	private Timestamp sendTime;

	public Timestamp getSendTime() {
		return sendTime;
	}

	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}

}
