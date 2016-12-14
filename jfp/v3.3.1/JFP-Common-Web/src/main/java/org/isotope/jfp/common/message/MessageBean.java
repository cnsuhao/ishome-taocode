package org.isotope.jfp.common.message;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

/**
 * 信息对象
 * 
 * @author Administrator
 * 
 */
public class MessageBean extends MyDataBaseObjectSupport {
	/**
	 * 发件人ID
	 */
	private String k01_fjrid = "";

	/**
	 * 收件人ID
	 */
	private String k02_sjrid = "";

	/**
	 * 发件人昵称
	 */
	private String f01_fjrnc = "";

	/**
	 * 收件人昵称
	 */
	private String f02_sjrnc = "";

	/**
	 * 标题
	 */
	private String f03_bt = "";

	/**
	 * 紧急度
	 */
	private String f04_jjd = "";

	/**
	 * 重要度
	 */
	private String f05_zyd = "";

	/**
	 * 获取发件人ID
	 * 
	 * @return K01_FJRID 发件人ID
	 */
	public String getK01_fjrid() {
		return this.k01_fjrid;
	}

	/**
	 * 获取收件人ID
	 * 
	 * @return K02_SJRID 收件人ID
	 */
	public String getK02_sjrid() {
		return this.k02_sjrid;
	}

	/**
	 * 获取发件人昵称
	 * 
	 * @return F01_FJRNC 发件人昵称
	 */
	public String getF01_fjrnc() {
		return this.f01_fjrnc;
	}

	/**
	 * 获取收件人昵称
	 * 
	 * @return F02_SJRNC 收件人昵称
	 */
	public String getF02_sjrnc() {
		return this.f02_sjrnc;
	}

	/**
	 * 获取标题
	 * 
	 * @return F03_BT 标题
	 */
	public String getF03_bt() {
		return this.f03_bt;
	}

	/**
	 * 获取紧急度
	 * 
	 * @return F04_JJD 紧急度
	 */
	public String getF04_jjd() {
		return this.f04_jjd;
	}

	/**
	 * 获取重要度
	 * 
	 * @return F05_ZYD 重要度
	 */
	public String getF05_zyd() {
		return this.f05_zyd;
	}

	/**
	 * 设置发件人ID
	 * 
	 * @param K01_FJRID
	 *            发件人ID
	 */
	public void setK01_fjrid(String K01_FJRID) {
		this.k01_fjrid = K01_FJRID;
	}

	/**
	 * 设置收件人ID
	 * 
	 * @param K02_SJRID
	 *            收件人ID
	 */
	public void setK02_sjrid(String K02_SJRID) {
		this.k02_sjrid = K02_SJRID;
	}

	/**
	 * 设置发件人昵称
	 * 
	 * @param F01_FJRNC
	 *            发件人昵称
	 */
	public void setF01_fjrnc(String F01_FJRNC) {
		this.f01_fjrnc = F01_FJRNC;
	}

	/**
	 * 设置收件人昵称
	 * 
	 * @param F02_SJRNC
	 *            收件人昵称
	 */
	public void setF02_sjrnc(String F02_SJRNC) {
		this.f02_sjrnc = F02_SJRNC;
	}

	/**
	 * 设置标题
	 * 
	 * @param F03_BT
	 *            标题
	 */
	public void setF03_bt(String F03_BT) {
		this.f03_bt = F03_BT;
	}

	/**
	 * 设置紧急度
	 * 
	 * @param F04_JJD
	 *            紧急度
	 */
	public void setF04_jjd(String F04_JJD) {
		this.f04_jjd = F04_JJD;
	}

	/**
	 * 设置重要度
	 * 
	 * @param F05_ZYD
	 *            重要度
	 */
	public void setF05_zyd(String F05_ZYD) {
		this.f05_zyd = F05_ZYD;
	}

}
