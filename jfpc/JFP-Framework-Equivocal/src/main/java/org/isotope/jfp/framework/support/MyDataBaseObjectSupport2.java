package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.constants.ISDBConstants;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;

/**
 * 数据持久层超类
 * 
 * @author Spook
 * @since 0.1.0
 * @version 0.1.0 2014/2/8
 */
public class MyDataBaseObjectSupport2 extends MyDataBaseObjectSupport implements ISDBConstants {
	/**
	 * 拦截创建信息
	 */
	public void prepareCreator(String creator) {
		// Timestamp d = new Timestamp(System.currentTimeMillis());
		String t = DateHelper.currentTimeMillis2();
		if (EmptyHelper.isEmpty(getCc1()))
			setCc1(t);
		if (EmptyHelper.isEmpty(getCc2()))
			setCc2(creator);
	}

	/**
	 * 拦截更新信息
	 */
	public void prepareUpdator(String updator) {
		String t = DateHelper.currentTimeMillis2();
		if (EmptyHelper.isEmpty(getUu1()))
			setUu1(t);
		if (EmptyHelper.isEmpty(getUu2()))
			setUu2(updator);
	}

	/**
	 * 拦截有效标记信息
	 */
	public void prepareDeleteFlag(boolean del) {
		// 有效标记、创建者、创建时间
		if (del && EmptyHelper.isEmpty(getDdd()))
			setDdd("0");
	}

	/**
	 * 拦截组织信息
	 */
	public void prepareGroup(boolean sys) {
		// 数据所属系统
		if (sys && EmptyHelper.isEmpty(getGgg()))
			setGgg("SYSTEM");

		// 企业ID
		if (sys && EmptyHelper.isEmpty(getTtt()))
			setTtt(getGgg());
	}

	/**
	 * 备注说明
	 */
	private String bbb = null;

	/**
	 * 备用1
	 */
	private String fb1 = null;

	/**
	 * 备用2
	 */
	private String fb2 = null;

	/**
	 * 备用3
	 */
	private String fb3 = null;

	/**
	 * 备用4
	 */
	private String fb4 = null;

	/**
	 * 备用5
	 */
	private String fb5 = null;

	/**
	 * 扩展1
	 */
	private String eb1 = null;

	/**
	 * 扩展2
	 */
	private String eb2 = null;

	/**
	 * 扩展3
	 */
	private String eb3 = null;

	/**
	 * 扩展4
	 */
	private String eb4 = null;

	/**
	 * 扩展5
	 */
	private String eb5 = null;

	/**
	 * 所在组织
	 */
	private String ggg = null;

	/**
	 * 所在分类
	 */
	private String ttt = null;

	/**
	 * 有效标识
	 */
	private String ddd = null;

	/**
	 * 创建时间
	 */
	private String cc1 = null;

	/**
	 * 创建者
	 */
	private String cc2 = null;

	/**
	 * 最后更新时间
	 */
	private String uu1 = null;

	/**
	 * 最后更新者
	 */
	private String uu2 = null;

	/**
	 * 获取备注说明
	 *
	 * @return BBB 备注说明
	 */
	public String getBbb() {
		return this.bbb;
	}

	/**
	 * 获取备用1
	 *
	 * @return FB1 备用1
	 */
	public String getFb1() {
		return this.fb1;
	}

	/**
	 * 获取备用2
	 *
	 * @return FB2 备用2
	 */
	public String getFb2() {
		return this.fb2;
	}

	/**
	 * 获取备用3
	 *
	 * @return FB3 备用3
	 */
	public String getFb3() {
		return this.fb3;
	}

	/**
	 * 获取备用4
	 *
	 * @return FB4 备用4
	 */
	public String getFb4() {
		return this.fb4;
	}

	/**
	 * 获取备用5
	 *
	 * @return FB5 备用5
	 */
	public String getFb5() {
		return this.fb5;
	}

	/**
	 * 获取扩展1
	 *
	 * @return EB1 扩展1
	 */
	public String getEb1() {
		return this.eb1;
	}

	/**
	 * 获取扩展2
	 *
	 * @return EB2 扩展2
	 */
	public String getEb2() {
		return this.eb2;
	}

	/**
	 * 获取扩展3
	 *
	 * @return EB3 扩展3
	 */
	public String getEb3() {
		return this.eb3;
	}

	/**
	 * 获取扩展4
	 *
	 * @return EB4 扩展4
	 */
	public String getEb4() {
		return this.eb4;
	}

	/**
	 * 获取扩展5
	 *
	 * @return EB5 扩展5
	 */
	public String getEb5() {
		return this.eb5;
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
	 * 获取所在分类
	 *
	 * @return TTT 所在分类
	 */
	public String getTtt() {
		return this.ttt;
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
	 * 获取最后更新时间
	 *
	 * @return UU1 最后更新时间
	 */
	public String getUu1() {
		return this.uu1;
	}

	/**
	 * 获取最后更新者
	 *
	 * @return UU2 最后更新者
	 */
	public String getUu2() {
		return this.uu2;
	}

	/**
	 * 设置备注说明
	 *
	 * @param BBB
	 *            备注说明
	 */
	public void setBbb(String bbb) {
		this.bbb = bbb;
	}

	/**
	 * 设置备用1
	 *
	 * @param FB1
	 *            备用1
	 */
	public void setFb1(String fb1) {
		this.fb1 = fb1;
	}

	/**
	 * 设置备用2
	 *
	 * @param FB2
	 *            备用2
	 */
	public void setFb2(String fb2) {
		this.fb2 = fb2;
	}

	/**
	 * 设置备用3
	 *
	 * @param FB3
	 *            备用3
	 */
	public void setFb3(String fb3) {
		this.fb3 = fb3;
	}

	/**
	 * 设置备用4
	 *
	 * @param FB4
	 *            备用4
	 */
	public void setFb4(String fb4) {
		this.fb4 = fb4;
	}

	/**
	 * 设置备用5
	 *
	 * @param FB5
	 *            备用5
	 */
	public void setFb5(String fb5) {
		this.fb5 = fb5;
	}

	/**
	 * 设置扩展1
	 *
	 * @param EB1
	 *            扩展1
	 */
	public void setEb1(String eb1) {
		this.eb1 = eb1;
	}

	/**
	 * 设置扩展2
	 *
	 * @param EB2
	 *            扩展2
	 */
	public void setEb2(String eb2) {
		this.eb2 = eb2;
	}

	/**
	 * 设置扩展3
	 *
	 * @param EB3
	 *            扩展3
	 */
	public void setEb3(String eb3) {
		this.eb3 = eb3;
	}

	/**
	 * 设置扩展4
	 *
	 * @param EB4
	 *            扩展4
	 */
	public void setEb4(String eb4) {
		this.eb4 = eb4;
	}

	/**
	 * 设置扩展5
	 *
	 * @param EB5
	 *            扩展5
	 */
	public void setEb5(String eb5) {
		this.eb5 = eb5;
	}

	/**
	 * 设置所在组织
	 *
	 * @param GGG
	 *            所在组织
	 */
	public void setGgg(String ggg) {
		this.ggg = ggg;
	}

	/**
	 * 设置所在分类
	 *
	 * @param TTT
	 *            所在分类
	 */
	public void setTtt(String ttt) {
		this.ttt = ttt;
	}

	/**
	 * 设置有效标识
	 *
	 * @param DDD
	 *            有效标识
	 */
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}

	/**
	 * 设置创建时间
	 *
	 * @param CC1
	 *            创建时间
	 */
	public void setCc1(String cc1) {
		this.cc1 = cc1;
	}

	/**
	 * 设置创建者
	 *
	 * @param CC2
	 *            创建者
	 */
	public void setCc2(String cc2) {
		this.cc2 = cc2;
	}

	/**
	 * 设置最后更新时间
	 *
	 * @param UU1
	 *            最后更新时间
	 */
	public void setUu1(String uu1) {
		this.uu1 = uu1;
	}

	/**
	 * 设置最后更新者
	 *
	 * @param UU2
	 *            最后更新者
	 */
	public void setUu2(String uu2) {
		this.uu2 = uu2;
	}

}
