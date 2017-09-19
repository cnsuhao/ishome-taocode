package org.isotope.jfp.common.message;

import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.isotope.jfp.framework.beans.ObjectBean;
import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.pub.MailBean;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.isotope.jfp.framework.utils.XMLHelper;
import org.springframework.core.io.Resource;

/**
 * 消息模版
 * 
 * @author Administrator
 * 
 */
public class MessageModelUtils {
	private HashMap<String, ObjectBean> maps = new HashMap<String, ObjectBean>();

	/**
	 * 提示信息模版
	 */
	private Resource[] messageLocations;
	/**
	 * 邮件模版
	 */
	private Resource[] mailLocations;
	/**
	 * 短信息模版
	 */
	private Resource[] smsLocations;

	public Resource[] getMessageLocations() {
		return messageLocations;
	}

	public void setMessageLocations(Resource[] messageLocations) {
		this.messageLocations = messageLocations;
	}

	public Resource[] getMailLocations() {
		return mailLocations;
	}

	public void setMailLocations(Resource[] mailLocations) {
		this.mailLocations = mailLocations;
	}

	public Resource[] getSmsLocations() {
		return smsLocations;
	}

	public void setSmsLocations(Resource[] smsLocations) {
		this.smsLocations = smsLocations;
	}
//
//	/**
//	 * 模版加载
//	 * 
//	 * @throws ParserConfigurationException
//	 */
//	public void init() throws Exception {
//		// 加载提示信息
//		if (EmptyHelper.isEmpty(messageLocations) == false) {
//			for (Resource r : messageLocations) {
//				maps.putAll(XMLHelper.loadXML(r.getInputStream(), "message", new MMMSSDBO()));
//			}
//		}
//		// 加载邮件模版信息
//		if (EmptyHelper.isEmpty(mailLocations) == false) {
//			for (Resource r : messageLocations) {
//				maps.putAll(XMLHelper.loadXML(r.getInputStream(), "mail", new MMMSSDBO()));
//			}
//		}
//		// 加载短信模版信息
//		if (EmptyHelper.isEmpty(smsLocations) == false) {
//			for (Resource r : messageLocations) {
//				maps.putAll(XMLHelper.loadXML(r.getInputStream(), "sms", new MMMSSDBO()));
//			}
//		}
//	}
//
//	/**
//	 * 获得一个邮件模版
//	 * 
//	 * @param messageCode
//	 * @param param
//	 * @return 返回格式化后的信息
//	 */
//	public MailBean buildMailMessage(String modelId, String[] titleParam, String[] textParam) {
//		// if (maps.size() == 0)
//		// init();
//		MailBean mb = new MailBean();
//		if (maps.containsKey(modelId)) {
//			MMMSSDBO model = (MMMSSDBO) maps.get(modelId);
//			mb.setSubject(String.format(model.getFb1(), titleParam));// 设置邮件主题
//			mb.setText(String.format(model.getBbb(), textParam));// 设置邮件内容
//		}
//		return mb;
//	}
//
//	/**
//	 * 获得一个邮件模版
//	 * 
//	 * @param messageCode
//	 * @param param
//	 * @return 返回格式化后的信息
//	 */
//	public void buildMessage(MessageBean msg, String modelId, String[] titleParam, String[] textParam) {
//		// if (maps.size() == 0)
//		// init();
//		MMMSSDBO model = (MMMSSDBO) maps.get(modelId);
//		if (model == null)
//			return;
//		msg.setF03_bt(String.format(model.getFb1(), titleParam));// 设置邮件主题
//		msg.setBbb(String.format(model.getBbb(), textParam));// 设置邮件内容
//		
//	}
//	
//	/**
//	 * 获得一个短信模版
//	 * 
//	 * @param target
//	 * @param modelId
//	 * @param args
//	 * @return
//	 */
//	public boolean buildMessage(FrameworkDataBean target, String modelId, String[] params) {
//		// if (maps.size() == 0)
//		// init();
//		MMMSSDBO model = (MMMSSDBO) maps.get(modelId);
//		if (model == null)
//			return false;
//		if (maps != null) {
//			// 设定标题
//			if (target instanceof MessageBean) {
//				((MessageBean) target).setF03_bt(model.getFb1());
//				// 设定信息
//				target.setBbb(String.format(model.getBbb(), params));
//			}
//			else if (target instanceof CS0D1DBO) {
//				((CS0D1DBO) target).setF03_bt(model.getFb1());
//				// 设定信息
//				target.setBbb(String.format(model.getBbb(), params));
//			}
//			else if (target instanceof MS3C2DBO) {
//				((MS3C2DBO) target).setF03_dxnr(String.format(model.getBbb(), params));
//			}
//			return true;
//		}
//		return false;
//	}
//
//	/**
//	 * 提示信息模版编码
//	 * 
//	 * @param modelId
//	 *            模版ID
//	 * @return
//	 */
//	public String getLocalMessage(String modelId) {
//		MMMSSDBO bean = (MMMSSDBO) maps.get(modelId);
//		if (bean != null)
//			return bean.getBbb();
//
//		return modelId;
//	}
//	
//	/**
//	 * 提示信息模版编码
//	 * 
//	 * @param modelId
//	 *            模版ID
//	 * @return
//	 */
//	public String getLocalMessage(String modelId,String[] params) {
//		MMMSSDBO bean = (MMMSSDBO) maps.get(modelId);
//		if (bean != null)
//			return String.format(bean.getBbb(), params);
//
//		return modelId;
//	}
}
