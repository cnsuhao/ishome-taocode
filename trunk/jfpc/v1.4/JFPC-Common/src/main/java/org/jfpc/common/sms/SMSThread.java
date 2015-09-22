package org.jfpc.common.sms;

import javax.annotation.Resource;


public class SMSThread {

	@Resource
	SMSService scf;

	public void run() {

		try {
//			// 获得数据
//			SMSBean sms = null;
//
//			// 发送短信
//			scf.sendMessage(sms);

//			// 数据持久化
//			if ("8".equals(sms.getSendType()) || "9".equals(sms.getSendType())) {
//
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
