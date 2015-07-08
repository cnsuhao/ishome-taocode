package org.ishome.jfp.pdp.service.biz;

import org.ishome.jfp.framework.beands.RESTResultBean;
import org.ishome.jfp.framework.beands.ext.BaseHAO;
import org.ishome.jfp.framework.biz.ISInit;
import org.ishome.jfp.framework.biz.ISProcess;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.mq.IMedMqService;
import org.ishome.jfp.framework.mq.redis.MyRedis;
import org.ishome.jfp.framework.utils.BeanFactoryHelper;
import org.ishome.jfp.framework.utils.EmptyHelper;
import org.ishome.jfp.framework.utils.HttpServiceHelper;
import org.ishome.jfp.pdp.conf.CloundApiConfig;
import org.ishome.jfp.pdp.conf.HospitalSecyrityConfig;
import org.ishome.jfp.pdp.conf.MyJsonData;
import org.ishome.jfp.pdp.constants.IHDPConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * 日常对接业务超类
 * 
 * @author Spook
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public class HospitalBusinessInitSupport extends MyJsonData implements Runnable, IHDPConstants, ISFrameworkConstants, ISInit, ISProcess {

	private static final Logger logger = LoggerFactory.getLogger(HospitalBusinessInitSupport.class);

	protected HttpServiceHelper httpService;
	protected IMedMqService mqService;	
	protected HospitalSecyrityConfig hospitalSecyrityConfig;	
	protected CloundApiConfig cloundApiConfig;
	
	/**
	 * 参数初始化
	 * @param httpService
	 * @param cloundApiConfig
	 * @param pool
	 */
	public void doInit( 
			HospitalSecyrityConfig hospitalSecyrityConfig,
			HttpServiceHelper httpService,
			CloundApiConfig cloundApiConfig,
			IMedMqService mq){
		this.hospitalSecyrityConfig = hospitalSecyrityConfig;
		this.httpService = httpService;
		this.cloundApiConfig = cloundApiConfig;
		this.mqService = mq;
	}
	
	/**
	 * 线程处理模式
	 */
	public void run() {
		try {
			logger.debug(JOB_SAVE_START);
			// 数据处理
			if (doInit() && doProcess()) {
				logger.debug(JOB_SEND_END);
				return;
			}
		} catch (Exception e) {
			logger.error(JOB_SAVE_CANCEL + getBizName(), e);
		}
	}
	
	protected JSONArray messages;// 服务器返回待处理业务数据
	protected RESTResultBean rs;// 服务器返回结果
	
	/**
	 * 先判断状态在发送数据
	 */
	@Override
	public boolean doInit() throws Exception {
		logger.debug(JOB_START);
		// 接收当前时段内，服务器等待处理的数据
		String respose = HttpServiceHelper.doHttpPOST(cloundApiConfig.getBizApiUrl(getBizName()), ZERO);
		rs = JSON.parseObject(respose, RESTResultBean.class);
		if(logger.isInfoEnabled())
			logger.info("=====>>>>>" + getBizName() + "<<<<<=====" + rs);
		if (ZERO.equals(rs.getCode())) {
			if (EmptyHelper.isEmpty(rs.getResult()))
				return false;
			// 优先解密处理 //逐条加密
			respose = hospitalSecyrityConfig.decryption(getBizName(), (String) rs.getResult());
			// 数据解密处理后二次解析(优先数据转换)
			if (ONE.equals(cloundApiConfig.getRemoteFlag())) {// 开启网闸模式
				messages = JSONArray.parseArray(respose);
				return doRemoteProcess();
			} else {// 标准开发
				messages = JSONArray.parseArray(respose);
			}
			if (messages.size() == 0)
				return false;
			else
				return true;
		}
		logger.debug(JOB_CANCEL);
		return false;
	}
	
	String bizClassNmae;
	
	public String getBizClassNmae() {
		return bizClassNmae;
	}

	public void setBizClassNmae(String bizClassNmae) {
		this.bizClassNmae = bizClassNmae;
	}

	/**
	 * 业务处理(仅仅激活业务子线程，不做业务处理)
	 */
	@Override
	public boolean doProcess() throws Exception {
		// 整理数据
		//每一个业务请求具有唯一标识
		for(int i=0;i< messages.size();i++){
			AHospitalProcessService rl = BeanFactoryHelper.getBean(getBizClassNmae());
			rl.doInit(hospitalSecyrityConfig, httpService, cloundApiConfig, mqService);
			
			rl.doInit(MyRedis.getClassFromRedis((String)messages.get(i)));
			
			(new Thread(rl)).start();
		}
		return true;
	}
	
	/**
	 * 医院直接处理模式
	 */
	public boolean doRemoteProcess() {
		for (int i = 0; i < messages.size(); i++) {
			BaseHAO hao = (BaseHAO) MyRedis.getClassFromRedis((String)messages.get(i));
			try {
				// 获得医院处理结果
				String response = HttpServiceHelper.doHttpPOST(cloundApiConfig.getHospitalUrl() + getBizName(), JSON.toJSONString(hao));
				// 提交处理结果到云端
				response = HttpServiceHelper.doHttpPOST(cloundApiConfig.getBizResultApiUrl(getBizName(), hao.getPuk()), hospitalSecyrityConfig.decryption(bizName, response));
				rs = JSON.parseObject(response, RESTResultBean.class);
				if (ZERO.equals(rs.getCode())) {
					logger.debug(JOB_END);
				} else {
					logger.error(JOB_SAVE_CANCEL + "..." + rs);
				}
			} catch (Exception e) {
				logger.error(JOB_SAVE_CANCEL + "..." + getBizName() + "..." + hao, e);
			}
		}
		logger.debug(JOB_SEND_END + "doRemoteProcess");
		//中断原始处理
		return false;
	}
}
