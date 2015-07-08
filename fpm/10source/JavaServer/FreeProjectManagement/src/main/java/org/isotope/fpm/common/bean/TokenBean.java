package org.isotope.fpm.common.bean;

import javax.inject.Named;

/**
 * 数据交换关键信息
 * @author Spook
 * @version 0.1
 * @since 0.1.0.0
 */
@Named
public class TokenBean {

	/**
	 * 用户ID
	 */
	private String userid = "";
	/**
	 * 合作用户ID
	 */
	private String customid = "";
	/**
	 * 登录时间
	 */
	private String logintime = "";

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCustomid() {
		return customid;
	}

	public void setCustomid(String customid) {
		this.customid = customid;
	}

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

	@Override
	public String toString() {
		return "TokenBean [userid=" + userid + ", customid=" + customid + ", logintime=" + logintime + "]";
	}

}
