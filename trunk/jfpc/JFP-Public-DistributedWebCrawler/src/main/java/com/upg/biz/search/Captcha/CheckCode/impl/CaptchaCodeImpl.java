package com.upg.biz.search.Captcha.CheckCode.impl;

import javax.annotation.Resource;

import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.springframework.stereotype.Service;

/**
 * 基于LIST进行数据缓存
 * 
 * @author 001745
 *
 */
@Service
public class CaptchaCodeImpl implements ISFrameworkConstants {
	/**
	 * 验证码队列
	 */
	public static final String CODE_LIST = "CODE:LIST";	
	
	@Resource
	protected ICacheService mq;
	
	public boolean addCodeInJobList(String jobid, String filename) {
		return mq.offerObjectInList(CODE_LIST, jobid + SEMICOLON + filename, false);
	}
	
	public Object getRestltWithJob(String jobid) {
		return mq.getObject(jobid, false);
	}

	public boolean putRestltWithJob(String jobid, String result) {
		return mq.putObject(jobid, result, 30, false);
	}

	public boolean lockJobOnList(String jobid, String ipAdress) {
		// 获得锁定状态
		String lock = jobid + ".lock";
		if (EmptyHelper.isEmpty(mq.getObject(lock, false))) {
			mq.putObject(lock, ipAdress, 15, false);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * jobid + SEMICOLON + filename
	 * /resources/upload/20160317/1.jpg
	 */
	public Object loadJobWithList() {
		return mq.pollFirstObjectInList(CODE_LIST);
	}

}
