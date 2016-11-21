package org.isotope.jfp.framework.beans.common;

import javax.inject.Named;

import org.isotope.jfp.framework.beans.ObjectBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;

/**
 * 基底共通
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/2/8
 */

public class FrameworkDataBean extends ObjectBean implements ISFrameworkConstants {

	/**
	 * 系统数据唯一识别ID（固定主键）
	 */
	private String puk;

	/**
	 * 获取系统数据唯一识别ID（固定主键）
	 *
	 * @return PUK 系统数据唯一识别ID（固定主键）
	 */
	public String getPuk() {
		return this.puk;
	}

	/**
	 * 设置系统数据唯一识别ID（固定主键）
	 *
	 * @param PUK
	 *            系统数据唯一识别ID（固定主键）
	 */
	public void setPuk(String PUK) {
		this.puk = PUK;
	}

}
