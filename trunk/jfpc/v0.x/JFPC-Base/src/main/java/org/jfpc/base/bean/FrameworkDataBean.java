package org.jfpc.base.bean;

import javax.inject.Named;

/**
 * 基底共通
 * 
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/2/8
 */
@Named
public class FrameworkDataBean extends ObjectBean {
	/**
	 * 系统数据唯一识别ID（固定主键）
	 */
	private String puk = "";

	/**
	 * 所在组织
	 */
	private String ggg = "";

	/**
	 * 有效标识
	 */
	private String ddd = "";

	/**
	 * 创建时间
	 */
	private String cc1 = "";

	/**
	 * 创建者
	 */
	private String cc2 = "";

	/**
	 * 更新时间
	 */
	private String uu1 = "";

	/**
	 * 更新者
	 */
	private String uu2 = "";

	/**
	 * 获取系统数据唯一识别ID（固定主键）
	 * 
	 * @return PUK 系统数据唯一识别ID（固定主键）
	 */
	public String getPuk() {
		return this.puk;
	}

	/**
	 * 获取所在组织
	 * 
	 * @return GGG 所在组织
	 */
	public String getGgg() {
		return this.ggg;
	}

	/**
	 * 获取有效标识
	 * 
	 * @return DDD 有效标识
	 */
	public String getDdd() {
		return this.ddd;
	}

	/**
	 * 获取创建时间
	 * 
	 * @return CC1 创建时间
	 */
	public String getCc1() {
		return this.cc1;
	}

	/**
	 * 获取创建者
	 * 
	 * @return CC2 创建者
	 */
	public String getCc2() {
		return this.cc2;
	}

	/**
	 * 获取更新时间
	 * 
	 * @return UU1 更新时间
	 */
	public String getUu1() {
		return this.uu1;
	}

	/**
	 * 获取更新者
	 * 
	 * @return UU2 更新者
	 */
	public String getUu2() {
		return this.uu2;
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
	
	/**
	 * 设置所在组织
	 * 
	 * @param GGG
	 *            所在组织
	 */
	public void setGgg(String GGG) {
		this.ggg = GGG;
	}

	/**
	 * 设置有效标识
	 * 
	 * @param DDD
	 *            有效标识
	 */
	public void setDdd(String DDD) {
		this.ddd = DDD;
	}

	/**
	 * 设置创建时间
	 * 
	 * @param CC1
	 *            创建时间
	 */
	public void setCc1(String CC1) {
		this.cc1 = CC1;
	}

	/**
	 * 设置创建者
	 * 
	 * @param CC2
	 *            创建者
	 */
	public void setCc2(String CC2) {
		this.cc2 = CC2;
	}

	/**
	 * 设置更新时间
	 * 
	 * @param UU1
	 *            更新时间
	 */
	public void setUu1(String UU1) {
		this.uu1 = UU1;
	}

	/**
	 * 设置更新者
	 * 
	 * @param UU2
	 *            更新者
	 */
	public void setUu2(String UU2) {
		this.uu2 = UU2;
	}
}
