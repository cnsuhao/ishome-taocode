package org.isotope.jfp.framework.common;

import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.cache.session.SessionHelper;
import org.isotope.jfp.framework.constants.ISDBConstants;
import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

/**
 * 数据持久层超类
 * 
 * @author Spook
 * @version 4.1.3 2017/04/15
 * @version 4.1.1 2016/12/12
 * @version 3.2.1 2016/08/28
 * @since 3.2.1 2016/08/28
 */
public class MyDataBaseExtSupport extends MyDataBaseObjectSupport implements ISDBConstants, IExtendData, ILoginer {

	/**
	 * 当前登录用户
	 */
	public UserBean currentUser() {
		UserBean loginer = SessionHelper.currentUser();
		if (loginer == null) {
			throw new RuntimeException("当前用户没有登录");
		}
		return loginer;
	}

	/////////////////////////////////////////////////////////////////////////
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

	/////////////////////////////////////////////////////////////////
	/**
	 * 页面1
	 */
	private String pv1 = null;
	/**
	 * 页面2
	 */
	private String pv2 = null;
	/**
	 * 页面3
	 */
	private String pv3 = null;
	/**
	 * 页面4
	 */
	private String pv4 = null;
	/**
	 * 页面5
	 */
	private String pv5 = null;

	public String getPv1() {
		return pv1;
	}

	public void setPv1(String pv1) {
		this.pv1 = pv1;
	}

	public String getPv2() {
		return pv2;
	}

	public void setPv2(String pv2) {
		this.pv2 = pv2;
	}

	public String getPv3() {
		return pv3;
	}

	public void setPv3(String pv3) {
		this.pv3 = pv3;
	}

	public String getPv4() {
		return pv4;
	}

	public void setPv4(String pv4) {
		this.pv4 = pv4;
	}

	public String getPv5() {
		return pv5;
	}

	public void setPv5(String pv5) {
		this.pv5 = pv5;
	}

}
