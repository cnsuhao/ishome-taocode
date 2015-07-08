package org.ishome.jfp.pdp.service.server;

import org.ishome.jfp.framework.beands.ObjectBean;
import org.ishome.jfp.framework.beands.RESTResultBean;
import org.ishome.jfp.framework.beands.ext.BaseHAO;
import org.ishome.jfp.framework.biz.ISCheck;
import org.ishome.jfp.framework.biz.ISSend;
import org.ishome.jfp.framework.utils.HttpServiceHelper;
import org.ishome.jfp.pdp.conf.CloundApiConfig;
import org.ishome.jfp.pdp.service.biz.HospitalBusinessInitSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 对接实际业务超类
 * 
 * @author Spook
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public abstract class ACloundProcessService extends HospitalBusinessInitSupport implements ISCheck, ISSend {
	private static final Logger logger = LoggerFactory.getLogger(ACloundProcessService.class);
	
	/**
	 * 参数初始化
	 * 
	 * @param httpService
	 * @param cloundApiConfig
	 * @param pool
	 */
	public void doInit(HttpServiceHelper httpService, CloundApiConfig cloundApiConfig, BaseHAO msg) {
		this.httpService = httpService;
		this.cloundApiConfig = cloundApiConfig;
		this.message = msg;
	}

	/**
	 * 线程处理模式
	 */
	public void run() {
		try {
			logger.debug(JOB_SAVE_START);
			// 数据处理
			if (doInit() && doCheck() && doProcess() && doSend()) {
				logger.debug(JOB_SEND_END);
				return;
			}
			//RESTResultBean
		} catch (Exception e) {
			logger.error(JOB_SAVE_CANCEL, e);
			e.printStackTrace();
		}
	}
	
	ObjectBean PostData;
	
	public ObjectBean getPostData() {
		return PostData;
	}

	public void setPostData(ObjectBean postData) {
		PostData = postData;
	}

	/**
	 * 数据回传
	 */
	@Override
	public boolean doSend() throws Exception {
		String response = HttpServiceHelper.doHttpPOST(cloundApiConfig.getCloundServiceUrl(getBizName()), getPostData());
		rs = JSON.parseObject(response, RESTResultBean.class);
		if (ZERO.equals(rs.getCode())) {
			logger.debug(JOB_END);
		}else{
			logger.debug(rs.getCode());
			logger.debug(JOB_CANCEL);
			return false;
		}		
		return true;
	}

	protected BaseHAO message;// 服务器返回待处理业务数据

}
