package com.hundsun.his.service;

import java.util.List;

import com.hundsun.his.constants.IHISConstants;
import com.hundsun.his.service.biz.utils.MyJsonData;
import com.hundsun.med.framework.beands.RESTResultBean;
import com.hundsun.med.framework.biz.ISCheck;
import com.hundsun.med.framework.biz.ISInit;
import com.hundsun.med.framework.biz.ISProcess;
import com.hundsun.med.framework.constants.ISFrameworkConstants;
import com.hundsun.med.framework.mq.IMedMqService;
import com.hundsun.med.framework.utils.HttpServiceHelper;

/**
 * 日常对接业务超类
 * 
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public abstract class AHospitalBusinessService extends MyJsonData implements IHISConstants, ISFrameworkConstants, ISInit,ISCheck,ISProcess {
	protected IMedMqService mqService;		
	protected Object message;// 服务器返回待处理业务数据
	
	/**
	 * 参数初始化
	 * @param httpService
	 * @param cloundApiConfig
	 * @param pool
	 */
	public void doInit( 
			HttpServiceHelper httpService,
			IMedMqService mq,
			Object msg){
		this.mqService = mq;
		this.message = msg;
	}
	
	/**
	 * 返回对接业务数据给云端<br>
	 * 根据配置加密
	 * 
	 * @return
	 */
	public RESTResultBean getRESTResultBean() {
		rs = new RESTResultBean();// 服务器返回结果
		rs.setCode(this.getReturnCode());
		rs.setMessage(this.getReturnMessage());
		rs.setResult(this.getReturnObject());
		return rs;
	}
	
	protected List<Object> messages;// 服务器返回待处理业务数据
	protected RESTResultBean rs;// 服务器返回结果
	
	String bizClassNmae;
	
	public String getBizClassNmae() {
		return bizClassNmae;
	}

	public void setBizClassNmae(String bizClassNmae) {
		this.bizClassNmae = bizClassNmae;
	}	
	
}
