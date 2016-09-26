package org.isotope.jfp.mpc.weixin.beans.recever;

import org.isotope.jfp.framework.beans.message.info.UserReceverBean;

/**
 * 微信用户信息 <br>
 * 参考【微信企业号关注用户表】company_weixin_user
 * 
 * @author spookfcy
 * @since 3.3.1
 * @version 3.3.1.20160825
 *
 */
public class WeiXinUserReceverBean extends UserReceverBean {

	/**
	 * 企业ID
	 */
	private String companyId;

	/**
	 * 学校id
	 */
	private Long sid;

	/**
	 * 微信id
	 */
	private String wxId;

	/**
	 * 用户类别
	 */
	private Integer uertType;

	/**
	 * 用户昵称
	 */
	private String userName;

	/**
	 * openId
	 */
	private String openid;

	/**
	 * 关注学校企业号ID
	 */
	private String schoolAppId;

	/**
	 * 是否启用
	 */
	private Integer isUse;

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Long getSid() {
		return sid;
	}

	public void setSid(Long sid) {
		this.sid = sid;
	}

	public String getWxId() {
		return wxId;
	}

	public void setWxId(String wxId) {
		this.wxId = wxId;
	}

	public Integer getUertType() {
		return uertType;
	}

	public void setUertType(Integer uertType) {
		this.uertType = uertType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getSchoolAppId() {
		return schoolAppId;
	}

	public void setSchoolAppId(String schoolAppId) {
		this.schoolAppId = schoolAppId;
	}

	public Integer getIsUse() {
		return isUse;
	}

	public void setIsUse(Integer isUse) {
		this.isUse = isUse;
	}

}
