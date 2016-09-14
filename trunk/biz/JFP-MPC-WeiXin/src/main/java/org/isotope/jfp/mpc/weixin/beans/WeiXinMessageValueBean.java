package org.isotope.jfp.mpc.weixin.beans;

import java.io.File;

import org.isotope.jfp.common.weixin.constants.ISWeixinConstants;
import org.isotope.jfp.framework.beans.message.info.MessageValueBean;

/**
 * 推送内容
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 *
 */
public class WeiXinMessageValueBean extends MessageValueBean implements ISWeixinConstants {

	/**
	 * 信息内容
	 */
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 媒体类型
	 */
	private String mediaType = MEDIA_TEXT;
	
	/**
	 * 媒体文件
	 */
	private File mediaFile;
	
	/**
	 * 媒体文件说明摘要<br>
	 * (半角/符号)代表换行<br>
	 * 用户看到后是一个点击链接
	 */
	private String mediaDescription;

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public File getMediaFile() {
		return mediaFile;
	}

	public void setMediaFile(File mediaFile) {
		this.mediaFile = mediaFile;
	}

	public String getMediaDescription() {
		return mediaDescription;
	}

	public void setMediaDescription(String mediaDescription) {
		this.mediaDescription = mediaDescription;
	}

}
