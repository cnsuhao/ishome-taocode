package org.isotope.jfp.common.push.server.impl;

import java.io.Serializable;

/**
 * PushMessage.java
 * 
 *  TODO
 * @author ChenYa
 * @version 创建时间：2014-6-27 上午11:13:43
 */

public class PushMessage implements Serializable{
	private static final long serialVersionUID = 1639056446657345498L;
	
	protected String key;//hosId_usId
	protected String title;
	protected String content;
	protected int termType;// 终端类型(enum:TerminalType)

	public PushMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PushMessage(String key, String title, String content, int termType) {
		this.key = key;
		this.title = title;
		this.content = content;
		this.termType = termType;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getTermType() {
		return termType;
	}

	public void setTermType(int termType) {
		this.termType = termType;
	}

}
