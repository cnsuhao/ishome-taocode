package com.hundsun.med.hdp.service.sec;

import java.util.Map.Entry;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hundsun.med.access.hao.hospital.SecurityTestHAO;
import com.hundsun.med.framework.beands.RESTResultBean;
import com.hundsun.med.framework.biz.ISInit;
import com.hundsun.med.framework.biz.ISTask;
import com.hundsun.med.framework.constants.ISFrameworkConstants;
import com.hundsun.med.framework.mq.IMedMqService;
import com.hundsun.med.framework.utils.EmptyHelper;
import com.hundsun.med.framework.utils.HttpServiceHelper;
import com.hundsun.med.hdp.conf.CloundApiConfig;
import com.hundsun.med.hdp.conf.HospitalSecyrityConfig;
import com.hundsun.med.hdp.conf.MyJsonData;
import com.hundsun.med.hdp.constants.IHDPConstants;

/**
 * 心跳业务处理（医院端） 获得加密配置
 * 
 * @author fucy
 * @version 2.0.0
 * @see <DoctorAccessSENDThread>
 * @since 2.0.0 2015/1/19
 */
public class HospitalHeartController extends MyJsonData implements Runnable, IHDPConstants, ISFrameworkConstants, ISInit, ISTask {
	private static final Logger logger = LoggerFactory.getLogger(HospitalHeartController.class);

	public HospitalHeartController() {
		setBizName(MQ_KEY_HEART);
	}

	HttpServiceHelper HttpService_ = new HttpServiceHelper();
	@Resource
	protected IMedMqService IMedMqService_;
	@Resource
	protected CloundApiConfig CloundApiConfig_;
	@Resource
	protected HospitalSecyrityConfig HospitalSecyrityConfig_;

	/**
	 * 参数初始化
	 * 
	 * @param httpService
	 * @param cloundApiConfig
	 * @param pool
	 */
	public void doInit(HttpServiceHelper httpService, CloundApiConfig cloundApiConfig, HospitalSecyrityConfig hospitalSecyrityConfig, IMedMqService mq) {
		this.HttpService_ = httpService;
		this.CloundApiConfig_ = cloundApiConfig;
		this.HospitalSecyrityConfig_ = hospitalSecyrityConfig;
		this.IMedMqService_ = mq;
	}

	/**
	 * 线程处理模式
	 */
	public void run() {
		try {
			logger.debug(JOB_SAVE_START);
			// 数据处理
			if (doInit()) {
				logger.debug(JOB_SEND_END);
				return;
			}
		} catch (Exception e) {
			logger.error(JOB_SAVE_CANCEL, e);
			e.printStackTrace();
		}
	}

	@Override
	public boolean doProcessRepeat() throws Exception {
		run();
		return true;
	}

	@Override
	public boolean doProcessOnce(String hosId) throws Exception {
		run();
		return true;
	}

	/**
	 * 先判断状态在发送数据<br>
	 * 明文返回所有数据
	 */
	@Override
	public boolean doInit() throws Exception {
		logger.debug(JOB_START);
		// 接收当前时段内，服务器等待处理的数据
		String respose = HttpServiceHelper.doHttpPOST(CloundApiConfig_.getCloundServiceUrl(Clound_HeartBusiness), EMPTY);
		RESTResultBean rs = JSON.parseObject(respose, RESTResultBean.class);
		if (ZERO.equals(rs.getCode())) {
			if (EmptyHelper.isEmpty(rs.getResult()))
				return false;

			// 解密后转换
			JSONObject json = JSON.parseObject(HospitalSecyrityConfig_.decryption(null, (String)rs.getResult()));

			// 加密Key
			IMedMqService_.putObject(MQ_KEY_SEC, json.get(MQ_KEY_SEC));

			// 加密配置(spring-task.xml)
			for (Entry<String, String> entry : CloundApiConfig_.getMonitorConfig().entrySet()) {
				IMedMqService_.putObject(entry.getKey(), json.get(entry.getKey()));
			}

			// 加密测试数据
			if (EmptyHelper.isNotEmpty(json.get(MQ_KEY_HOS)))
				doProcess((String) json.get(MQ_KEY_SEC), (String) json.get(MQ_KEY_HOS));
			logger.debug(JOB_END);
			// 处理加密测试业务
			return true;
		}
		logger.debug(JOB_CANCEL);
		return false;
	}

	/**
	 * 业务处理
	 */
	public boolean doProcess(String encryptionKey, String encryptionData) throws Exception {
		SecurityTestHAO message = new SecurityTestHAO();
		// 修正心跳
		message.updateUuu();
		// 处理
		message.setTestData(HospitalSecyrityConfig_.getSecuritySupport().decryption(encryptionKey, encryptionData));

		// 回传结果
		HttpServiceHelper.doHttpPOST(CloundApiConfig_.getCloundServiceUrl(Clound_SecurityBusiness), JSON.toJSONString(message));

		return true;
	}
}
