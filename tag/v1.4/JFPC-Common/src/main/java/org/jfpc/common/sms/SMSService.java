package org.jfpc.common.sms;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.jfpc.beans.common.MS3C2.MS3C2DBO;
import org.jfpc.beans.common.MS3C2.MS3C2Service;
import org.jfpc.common.message.MessageModelUtils;
import org.jfpc.common.sms.client.DefaultSMSClient;
import org.jfpc.common.sms.server.SMSConfig;
import org.jfpc.common.sms.server.SMSGatewayFactory;
import org.jfpc.constants.ISFrameworkConstants;

/**
 * 短信客户端工厂
 * 
 * @author Spook
 * @since 0.0.10
 * @version 0.1.0 2014/9/4
 */
public class SMSService implements ISFrameworkConstants {

	@Resource
	SMSGatewayFactory sgf;
	@Resource
	MS3C2Service MS3C2Service_;
	@Resource
	MessageModelUtils model;

	/**
	 * 设定SQL连接<br>
	 * 多个Service调用的时候，建议使用手动设定
	 * 
	 * @param mySqlSession
	 */
	public void setMySqlSession(SqlSession mySqlSession) {
		MS3C2Service_.setMySqlSession(mySqlSession);
	}

	/**
	 * 获得一个客户端
	 * 
	 * @return
	 */
	public DefaultSMSClient buildClient() {
		// 获得短信网关
		SMSConfig sp = sgf.getSMSGateway();

		// 获得客户端连接
		DefaultSMSClient sc;
		try {
			sc = (DefaultSMSClient) Class.forName(sp.getClassName()).newInstance();
		} catch (Exception e) {
			sc = new DefaultSMSClient();
		}

		sc.setConfig(sp);
		return sc;
	}
	
	/**
	 * 直接发送短信
	 * 
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public SMSBean sendMessage(SMSBean sms) throws Exception {
		MS3C2DBO smsDb = new MS3C2DBO();
		DefaultSMSClient sc = buildClient();
		
		smsDb.setPuk(sms.getPuk());
		smsDb.setK01_ywbsid(sms.getBizId());
		smsDb.setK02_sjhm(sms.getPhoneNum());
		smsDb.setF03_dxnr(sms.getMessage());
		smsDb.setFb4(sms.getModelId());
		
		smsDb.setF01_wgzt(sc.getConfig().getSmsType());// 服务状态(0开启1关闭)
		MS3C2Service_.doInsert(smsDb);
		//发送短信
		return sc.sendMessage(sms);
	}

	/**
	 * 直接发送短信
	 * 
	 * @param msg
	 * @return
	 * @throws Exception
	 */
	public SMSBean sendMessage(MS3C2DBO smsDb) throws Exception {
		DefaultSMSClient sc = buildClient();
		smsDb.setF01_wgzt(sc.getConfig().getSmsType());// 服务状态(0开启1关闭)
		MS3C2Service_.doInsert(smsDb);
		//发送短信
		SMSBean sms = new SMSBean();
		sms.setPuk(smsDb.getPuk());
		sms.setBizId(smsDb.getK01_ywbsid());
		sms.setPhoneNum(smsDb.getK02_sjhm());
		sms.setMessage(smsDb.getF03_dxnr());
		sms.setSendType(ZERO);
		sms.setModelId(smsDb.getFb4());
		return sc.sendMessage(sms);
	}

	/**
	 * 直接发送短信
	 * 
	 * @param mobilNumber
	 * @param Message
	 * @return
	 * @throws Exception
	 */
	public SMSBean sendMessage(String bizId, String mobilNumber, String message) throws Exception {
		DefaultSMSClient sc = buildClient();
		MS3C2DBO msg = new MS3C2DBO();
		msg.setK01_ywbsid(bizId);
		msg.setK02_sjhm(mobilNumber);
		msg.setF01_wgzt(sc.getConfig().getSmsType());// 服务状态(0开启1关闭)
		msg.setF02_fszt("0");// 0等待发送1已发送2发送成功9发送失败
		msg.setF03_dxnr(message);
		MS3C2Service_.doInsert(msg);
		return sc.sendMessage(mobilNumber, message);
	}

	/**
	 * 根据模版发送短信
	 * 
	 * @param mobilNumber
	 *            接收者号码
	 * @param modelId
	 *            模版ID
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public SMSBean sendMessage(String bizId, String mobilNumber, String modelId, String... param) throws Exception {
		DefaultSMSClient sc = buildClient();
		MS3C2DBO msg = new MS3C2DBO();
		msg.setK01_ywbsid(bizId);
		msg.setK02_sjhm(mobilNumber);
		msg.setF01_wgzt(sc.getConfig().getSmsType());// 服务状态(0开启1关闭)
		msg.setF02_fszt("0");// 0等待发送1已发送2发送成功9发送失败
		if (model.buildMessage(msg, modelId, param) == false)
			return null;
		MS3C2Service_.doInsert(msg);
		return sc.sendMessage(mobilNumber, msg.getF03_dxnr());
	}

}
