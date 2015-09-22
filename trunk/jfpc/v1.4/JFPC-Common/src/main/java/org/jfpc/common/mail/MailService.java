package org.jfpc.common.mail;

import javax.annotation.Resource;

import org.jfpc.common.message.MessageModelUtils;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 邮件发送者
 * 
 * @since 0.0.10
 * @version 0.1.0 2014/9/4
 */
public class MailService extends JavaMailSenderImpl {
	@Resource
	MessageModelUtils model_;

	/**
	 * @方法名: sendMail
	 * @参数名：@param subject 邮件主题
	 * @参数名：@param text 邮件内容
	 * @参数名：@param to 收件人Email地址
	 * @描述语: 发送邮件
	 */
	public void sendMail(String bizId, String mailTo, String modelId, String[] titleParam, String[] textParam) {
		//TODO 邮件发送记录
		
		MailBean mail = model_.buildMailMessage(modelId, titleParam, textParam);
		mail.setTo(mailTo); // 设定收件人
		send(mail); // 发送邮件
	}

	// Spring 依赖注入
	public void sendMail(MailBean mail) {
		send(mail); // 发送邮件
	}
}
