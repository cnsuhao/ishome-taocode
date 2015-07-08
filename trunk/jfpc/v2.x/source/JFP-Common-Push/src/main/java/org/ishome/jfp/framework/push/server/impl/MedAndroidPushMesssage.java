package org.ishome.jfp.framework.push.server.impl;


/** 
 * MedAndroidPushMesssage.java
 *  TODO
 * @author ChenYa 
 * @version 创建时间：2014-7-1 下午1:58:03 
 */
public class MedAndroidPushMesssage extends MedPushMessage{

	private static final long serialVersionUID = -5272577401060929661L;

	public MedAndroidPushMesssage() {
	}

	public MedAndroidPushMesssage(String key, String title, String content,
			int termType) {
		super(key, title, content, termType);
	}

}
