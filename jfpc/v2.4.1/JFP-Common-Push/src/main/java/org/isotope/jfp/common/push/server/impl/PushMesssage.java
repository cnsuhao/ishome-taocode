package org.isotope.jfp.common.push.server.impl;


/**
 * MedIosPushMesssage.java
 * 
 *  TODO
 * @author ChenYa
 * @version 创建时间：2014-7-1 下午1:58:49
 */
public class PushMesssage extends MedPushMessage {

	private static final long serialVersionUID = 2286761675184055921L;

	private String pushToken;
	private String certificatePath;// 证书地址

	public PushMesssage() {
	}
	
	public PushMesssage(String key, String title, String content,
			int termType, String pushToken, String certificatePath) {
		super(key, title, content, termType);
		this.pushToken = pushToken;
		this.certificatePath = certificatePath;
	}

	public String getPushToken() {
		return pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}

	public String getCertificatePath() {
		return certificatePath;
	}

	public void setCertificatePath(String certificatePath) {
		this.certificatePath = certificatePath;
	}

}
