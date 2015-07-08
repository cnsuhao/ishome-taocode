package org.ishome.jfp.bds.service.biz.impl;

import javax.annotation.Resource;

import org.ishome.jfp.bds.service.biz.ACloundBusinessSupport;
import org.ishome.jfp.framework.support.ISPhonePushSupport;
import org.ishome.jfp.framework.support.ISSMSSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 发送通知给用户
 * 
 * @author Spook
 * 
 */
@Service("PatientNoticeDockingImpl")
public class PatientNoticeDockingImpl extends ACloundBusinessSupport {
	protected static final Logger logger = LoggerFactory.getLogger(PatientNoticeDockingImpl.class);
	//PatientNoticeHAO param;
	@Resource
	ISSMSSupport sms;
	@Resource
	ISPhonePushSupport push;

	@Override
	public boolean doProcess() throws Exception {
		
		return false;
	}

	@Override
	public boolean doCheck() throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean doInit() throws Exception {
		//param = JSON.parseObject(message, PatientNoticeHAO.class);
		return true;
	}

}
