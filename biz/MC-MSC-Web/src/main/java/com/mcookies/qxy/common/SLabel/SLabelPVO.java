package com.mcookies.qxy.common.SLabel;
import javax.inject.Named;
 
@Named
/** 教工标签表*/
public class SLabelPVO extends SLabelDBO
{
	/**
	 * 联系电话
	 */
	private String phone = null;

	/**
	 * 邮箱
	 */
	private String email = null;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	} 
}
