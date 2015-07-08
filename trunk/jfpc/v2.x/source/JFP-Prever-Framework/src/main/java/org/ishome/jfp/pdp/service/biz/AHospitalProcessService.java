package org.ishome.jfp.pdp.service.biz;

import org.ishome.jfp.framework.beands.RESTResultBean;
import org.ishome.jfp.framework.biz.ISCheck;
import org.ishome.jfp.framework.biz.ISSend;
import org.ishome.jfp.framework.utils.EmptyHelper;
import org.ishome.jfp.framework.utils.HttpServiceHelper;
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
public abstract class AHospitalProcessService extends HospitalBusinessInitSupport implements ISCheck, ISSend {
	private static final Logger logger = LoggerFactory.getLogger(AHospitalProcessService.class);

	/**
	 * 参数初始化
	 * 
	 * @param httpService
	 * @param cloundApiConfig
	 * @param threadPool
	 */
	public void doInit(Object msg) {
		this.message = msg;
		this.setReturnCode(ZERO);
		this.setReturnMessage(INIT_MESSAGE);
		this.setReturnObject(EMPTY);
	}

	protected RESTResultBean rs;// 服务器返回结果
	
	/**
	 * 线程处理模式
	 */
	public void run() {
		try {
			logger.debug(JOB_SAVE_START);
			// 数据处理
			if (doInit() // 初始化业务处理
					&& doCheck() // 进行业务校验
					&& doProcess() // 处理业务内容
			) {
				// 处理成功
				logger.debug(JOB_SEND_END);
				if(INIT_MESSAGE.equals(this.getReturnMessage())){
				this.setReturnMessage(SUCCESS_MESSAGE);
				}
			} else {
				logger.debug(JOB_SAVE_CANCEL);
				if (EmptyHelper.isEmpty(this.getReturnCode()))
					this.setReturnCode("9099");
				if (EmptyHelper.isEmpty(this.getReturnMessage()))
					this.setReturnMessage(ERROR_MESSAGE);
				// return;
			}
			// 返回结果
			doSend();
		} catch (Exception e) {
			logger.error(JOB_SAVE_CANCEL + "..." + getBizName() + "..." + getPuk(), e);
			// 定义为标准错误返回
			{
				try {
					this.setReturnCode("9999");
					this.setReturnMessage(FAILED_MESSAGE);
					doSend();
				} catch (Exception es) {
					es.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 返回对接业务数据给云端<br>
	 * 根据配置加密
	 * 
	 * @return
	 */
	protected String getJsonData() {
		rs = new RESTResultBean();// 服务器返回结果
		rs.setCode(this.getReturnCode());
		rs.setMessage(this.getReturnMessage());
		rs.setResult(this.getReturnObject());
		return JSON.toJSONString(rs);
	}

	/**
	 * 数据回传
	 */
	@Override
	public boolean doSend() throws Exception {
		String response = HttpServiceHelper.doHttpPOST(cloundApiConfig.getBizResultApiUrl(getBizName(), getPuk()), hospitalSecyrityConfig.decryption(bizName, this.getJsonData()));
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

	String puk;

	public String getPuk() {
		return puk;
	}

	public void setPuk(String puk) {
		this.puk = puk;
	}

	protected Object message;// 服务器返回待处理业务数据

}
