package org.jfpc.common.sms;

import org.jfpc.framework.support.MyDataBaseObjectSupport;

/**
 * 短信数据实体
 * 
 * @author Spook
 * @since 0.0.10
 * @version 0.2 2014/9/17 增加业务表示、模版ID与参数
 * @version 0.1 2014/9/4
 */
public class SMSBean extends MyDataBaseObjectSupport{

	/**
	 * 队列Id
	 */
	String puk;
	/**
	 * 业务标识
	 */
	String bizId;

	/**
	 * 手机号码
	 */
	String phoneNum;
	/**
	 * 信息内容
	 */
	String message;
	/**
	 * 网关状态 0移动1电信2联通
	 */
	String netType;
	/**
	 * 发送状态 0等待，1失败2失败3失败，8失败取消发送，9成功
	 */
	String sendType = "0";
	/**
	 * 模版ID
	 */
	String modelId;
	/**
	 * 模版参数
	 */
	String[] modelParam;
	/**
	 * 网关返回信息
	 */
	String result;

	public String getPuk() {
		return puk;
	}

	public void setPuk(String puk) {
		this.puk = puk;
	}

	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNetType() {
		return netType;
	}

	public void setNetType(String netType) {
		this.netType = netType;
	}

	public String getSendType() {
		return sendType;
	}

	public void setSendType(String sendType) {
		this.sendType = sendType;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String[] getModelParam() {
		return modelParam;
	}

	public void setModelParam(String[] modelParam) {
		this.modelParam = modelParam;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
