package com.upg.ceci.biz.captcha;

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
public class CaptchaCodeImpl implements ISCaptcha, ISFrameworkConstants {
	@Resource
	protected ICacheService mq;

	@Override
	public boolean addCodeInJobList(String jobid, String filename) {
		return mq.offerObjectInList(CODE_LIST, jobid + SEMICOLON + filename, false);
	}

	@Override
	public Object getRestltWithJob(String jobid) {
		return mq.getObject(jobid, false);
	}

	@Override
	public boolean putRestltWithJob(String jobid, String result) {
		return mq.putObject(jobid, result, 30, false);
	}

	@Override
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
	@Override
	public Object loadJobWithList() {
		return mq.peekFirstObjectInList(CODE_LIST);
	}

}
