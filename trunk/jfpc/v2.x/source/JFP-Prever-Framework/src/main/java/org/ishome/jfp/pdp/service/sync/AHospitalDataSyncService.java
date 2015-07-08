package org.ishome.jfp.pdp.service.sync;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.ishome.jfp.framework.beands.RESTResultBean;
import org.ishome.jfp.framework.biz.ISCheck;
import org.ishome.jfp.framework.biz.ISInit;
import org.ishome.jfp.framework.biz.ISProcess;
import org.ishome.jfp.framework.biz.ISSend;
import org.ishome.jfp.framework.biz.ISTask;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.mq.IMedMqService;
import org.ishome.jfp.framework.utils.EmptyHelper;
import org.ishome.jfp.framework.utils.HttpServiceHelper;
import org.ishome.jfp.pdp.conf.CloundApiConfig;
import org.ishome.jfp.pdp.conf.HospitalSecyrityConfig;
import org.ishome.jfp.pdp.conf.MyJsonData;
import org.ishome.jfp.pdp.constants.IHDPConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 基于HDSThreadPool处理多线程业务，用于获取接口数据内容
 * 
 * @author Spook
 * @version 2.0.0
 * @see <HospitalDataPushJob>
 * @since 2.0.0 2015/1/19
 */
public abstract class AHospitalDataSyncService extends MyJsonData implements IHDPConstants, ISFrameworkConstants, ISCheck, ISInit, ISSend, ISTask, ISProcess {
	private static final Logger logger = LoggerFactory.getLogger(AHospitalDataSyncService.class);

	protected RESTResultBean rs;// 服务器返回结果

	HttpServiceHelper httpService = new HttpServiceHelper();

	@Resource
	protected IMedMqService mqService;
	@Resource
	protected HospitalSecyrityConfig hospitalSecyrityConfig;
	@Resource
	protected CloundApiConfig cloundApiConfig;

	@Override
	public boolean doProcessRepeat() throws Exception {
		return doProcessOnce(null);
	}

	@Override
	public boolean doProcessOnce(String hosId) throws Exception {
		logger.debug(JOB_SEND_START);
		// 数据处理
		if (doInit() && doCheck() && doProcess() && doSend()) {
			logger.debug(JOB_SEND_END);
			return true;
		}
		this.setReturnCode(ONE);
		this.setReturnMessage("数据同步失败");
		logger.debug(JOB_SEND_CANCEL);
		return false;
	}

	/**
	 * 先判断状态在发送数据
	 */
	@Override
	public boolean doInit() throws Exception {
		// 判断服务器处理结果
		logger.debug(JOB_START);
		// 判断服务器业务处理状态
		String response = HttpServiceHelper.doHttpPOST(cloundApiConfig.getSyncCheckApiUrl(getBizName()), EMPTY);
		rs = JSON.parseObject(response, RESTResultBean.class);
		if (ZERO.equals(rs.getCode())) {
			// 服务器通知开始对接
			if (EmptyHelper.isEmpty(rs.getResult()))
				return true;
			else if (JOB_FLAG_WAITING.equals(rs.getResult()))
				return true;
			// 服务器对接处理失败
			else if (JOB_FLAG_ERROR.equals(rs.getResult()))
				return true;
		}
		logger.debug(JOB_CANCEL);
		return false;
	}

	/**
	 * 数据回传
	 */
	@Override
	public boolean doSend() throws Exception {
		String response = HttpServiceHelper.doHttpPOST(cloundApiConfig.getSyncApiUrl(getBizName()), getJsonData(), getHttpParam());
		rs = JSON.parseObject(response, RESTResultBean.class);
		if (ZERO.equals(rs.getCode())) {
			logger.debug(JOB_END);
		} else {
			logger.debug(rs.getCode());
			logger.debug(JOB_CANCEL);
			return false;
		}
		return true;
	}

	private Map<String, String> getHttpParam() {
		HashMap<String, String> param = new HashMap<String, String>();
		param.put("doNow", getDoNow());
		return param;
	}

	/**
	 * 是否立刻处理
	 */
	String doNow = ZERO;

	public String getDoNow() {
		return doNow;
	}

	public void setDoNow(String doNow) {
		this.doNow = doNow;
	}

	/**
	 * 返回对接业务数据给云端<br>
	 * 根据配置加密
	 * 
	 * @return
	 */
	protected String getJsonData() {
		RESTResultBean rs = new RESTResultBean();// 服务器返回结果
		rs.setCode(this.getReturnCode());
		rs.setMessage(this.getReturnMessage());
		rs.setResult(hospitalSecyrityConfig.decryption(bizName, JSON.toJSONString(this.getReturnObject())));
		return JSON.toJSONString(rs);
	}

	// protected String getJsonData() {
	// return hospitalSecyrityConfig.decryption(bizName,
	// JSON.toJSONString(returnObject));
	// }
}
