package org.jfpc.framework.support;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;


public class MyEmailSupport  {
//	// 发送邮件的服务器的IP和端口
//	private String mailServerHost = "smtp.qq.com";
//	private String mailServerPort = "25";
//	// 登陆邮件发送服务器的用户名和密码
//	private String userName = "744788292@qq.com";
//	private String password = "zaq12wsx";
//	// 是否需要身份验证
//	private boolean validate = false;
//
//	/**
//	 * 获得邮件会话属性
//	 */
//	public JavaMailSenderImpl getMailSender() {
//		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//		mailSender.setHost(mailServerHost);
//	    mailSender.setUsername(userName);
//	    mailSender.setPassword(password);
//		return mailSender;
//	}
//
//	
//	public boolean sendMailByMail(MailBean mailBean) {
//		
//		
//		SimpleMailMessage smm = new SimpleMailMessage();
//		// 设定邮件参数
//	    smm.setFrom(mailBean.getSendUser());
//	    smm.setTo(mailBean.getReceiveUser());
//	    smm.setSubject(mailBean.getTitle());
//	    smm.setText(mailBean.getBoday());
//	    // 发送邮件
//	    getMailSender().send(smm);
//		
//		return true;
//	
//	}
//
//	
//	public boolean sendMailByUserId(MailBean mailBean) {
//		// TODO Auto-generated method stub
//		return false;
//	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		MyEmailSupportImpl ms = new MyEmailSupportImpl();
//		
//		MailBean mailBean = new MailBean();
//		mailBean.setTitle("Test by fcy");
//		mailBean.setSendUser("744788292@qq.com");
//		mailBean.setReceiveUser("mdxk@163.com");
//		
//		mailBean.setBoday("AAAAAAAAAAAAAAA");
//		
//		ms.sendMailByMail(mailBean);
	}
	
	
	 private MailSender mailSender;
	    private SimpleMailMessage simpleMailMessage;
	    
	  /**
	     * @方法名: sendMail 
	     * @参数名：@param subject  邮件主题
	     * @参数名：@param content 邮件主题内容
	     * @参数名：@param to         收件人Email地址
	     * @描述语: 发送邮件
	     */
	    public void sendMail(String subject, String content, String to) {
	        
	        simpleMailMessage.setSubject(subject); //设置邮件主题
	        simpleMailMessage.setTo(to);             //设定收件人
	        simpleMailMessage.setText(content);  //设置邮件主题内容
	        mailSender.send(simpleMailMessage); //发送邮件
	    }
	      //Spring 依赖注入
	    public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
	        this.simpleMailMessage = simpleMailMessage;
	    }
	      //Spring 依赖注入 
	    public void setMailSender(MailSender mailSender) {
	        this.mailSender = mailSender;
	    }
	
	

}
