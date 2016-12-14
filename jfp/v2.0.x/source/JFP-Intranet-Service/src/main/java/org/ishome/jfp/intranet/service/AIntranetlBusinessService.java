package org.ishome.jfp.intranet.service;

import java.util.List;

import org.ishome.jfp.framework.beands.RESTResultBean;
import org.ishome.jfp.framework.biz.ISCheck;
import org.ishome.jfp.framework.biz.ISInit;
import org.ishome.jfp.framework.biz.ISProcess;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.mq.IMedMqService;
import org.ishome.jfp.framework.utils.HttpServiceHelper;
import org.ishome.jfp.intranet.constants.IHISConstants;
import org.ishome.jfp.intranet.service.biz.utils.MyJsonData;

/**
 * 日常对接业务超类
 * 
 * @author Spook
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public abstract class AIntranetlBusinessService extends MyJsonData implements IHISConstants, ISFrameworkConstants, ISInit,ISCheck,ISProcess {
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
