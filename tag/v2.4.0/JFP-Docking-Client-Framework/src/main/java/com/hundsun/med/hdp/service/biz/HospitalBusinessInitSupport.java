package com.hundsun.med.hdp.service.biz;

import org.apache.http.conn.ConnectTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hundsun.med.access.hao.BaseHAO;
import com.hundsun.med.framework.beands.RESTResultBean;
import com.hundsun.med.framework.biz.ISInit;
import com.hundsun.med.framework.biz.ISProcess;
import com.hundsun.med.framework.constants.ISFrameworkConstants;
import com.hundsun.med.framework.mq.IMedMqService;
import com.hundsun.med.framework.mq.redis.MyRedis;
import com.hundsun.med.framework.utils.BeanFactoryHelper;
import com.hundsun.med.framework.utils.EmptyHelper;
import com.hundsun.med.framework.utils.HttpServiceHelper;
import com.hundsun.med.hdp.conf.CloundApiConfig;
import com.hundsun.med.hdp.conf.HospitalSecyrityConfig;
import com.hundsun.med.hdp.conf.MyJsonData;
import com.hundsun.med.hdp.constants.IHDPConstants;

/**
 * 日常对接业务超类
 * 
 * @author fucy
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
			//logger.error(JOB_SAVE_CANCEL + getBizName(), e);
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
		try{
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
		}catch(ConnectTimeoutException e){
			//可能是服务器阻塞
		}
		logger.debug(JOB_CANCEL);
		return false;
	}
	
	String bizClassName;
	
	public String getBizClassName() {
		return bizClassName;
	}

	public void setBizClassName(String bizClassName) {
		this.bizClassName = bizClassName;
	}

	/**
	 * 业务处理(仅仅激活业务子线程，不做业务处理)
	 */
	@Override
	public boolean doProcess() throws Exception {
		// 整理数据
		//每一个业务请求具有唯一标识
		for(int i=0;i< messages.size();i++){
			AHospitalProcessService rl = BeanFactoryHelper.getBean(getBizClassName());
			rl.doInit(hospitalSecyrityConfig, httpService, cloundApiConfig, mqService);
			//透传支持
			if(cloundApiConfig.getAnonymousConfig()!=null&&cloundApiConfig.getAnonymousConfig().containsKey(getBizName()))
				rl.doInit(messages.get(i));
			if(cloundApiConfig.getMonitorConfig().containsKey(getBizName()))
				rl.doInit(MyRedis.getClassFromRedis((String)messages.get(i)));
			else
				rl.doInit(messages.get(i));				
			
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
				response = HttpServiceHelper.doHttpPOST(cloundApiConfig.getBizResultApiUrl(getBizName(), hao.getPuk()), hospitalSecyrityConfig.decryption(getBizName(), response));
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
