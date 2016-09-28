package com.mcookies.qxy.common.SchoolWeixinGroup;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 学校微信企业号用户组表 */
public class SchoolWeixinGroupDBO extends MyDataBaseObjectSupport {
	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 用户组id
	 */
	private Long gid = null;

	/**
	 * 上级用户组ID
	 */
	private Long parentid = null;

	/**
	 * 用户组编号
	 */
	private String groupNo = null;

	/**
	 * 用户组名称
	 */
	private String groupName = null;

	/**
	 * 用户组openId
	 */
	private String openid = null;

	/**
	 * 微信ID
	 */
	private String wxId = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 获取学校id
	 *
	 * @return Sid 学校id
	 */
	public Long getSid() {
		return this.sid;
	}

	/**
	 * 获取用户组id
	 *
	 * @return Gid 用户组id
	 */
	public Long getGid() {
		return this.gid;
	}

	/**
	 * 获取上级用户组ID
	 *
	 * @return ParentId 上级用户组ID
	 */
	public Long getParentid() {
		return this.parentid;
	}

	/**
	 * 获取用户组编号
	 *
	 * @return Group_no 用户组编号
	 */
	public String getGroupNo() {
		return this.groupNo;
	}

	/**
	 * 获取用户组名称
	 *
	 * @return Group_name 用户组名称
	 */
	public String getGroupName() {
		return this.groupName;
	}

	/**
	 * 获取用户组openId
	 *
	 * @return Openid 用户组openId
	 */
	public String getOpenid() {
		return this.openid;
	}

	/**
	 * 获取微信ID
	 *
	 * @return Wx_id 微信ID
	 */
	public String getWxId() {
		return this.wxId;
	}

	/**
	 * 获取是否启用
	 *
	 * @return Is_use 是否启用
	 */
	public Integer getIsUse() {
		return this.isUse;
	}

	/**
	 * 设置学校id
	 *
	 * @param Sid
	 *            学校id
	 */
	public void setSid(Long sid) {
		this.sid = sid;
	}

	/**
	 * 设置用户组id
	 *
	 * @param Gid
	 *            用户组id
	 */
	public void setGid(Long gid) {
		this.gid = gid;
	}

	/**
	 * 设置上级用户组ID
	 *
	 * @param ParentId
	 *            上级用户组ID
	 */
	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	/**
	 * 设置用户组编号
	 *
	 * @param Group_no
	 *            用户组编号
	 */
	public void setGroupNo(String groupno) {
		this.groupNo = groupno;
	}

	/**
	 * 设置用户组名称
	 *
	 * @param Group_name
	 *            用户组名称
	 */
	public void setGroupName(String groupname) {
		this.groupName = groupname;
	}

	/**
	 * 设置用户组openId
	 *
	 * @param Openid
	 *            用户组openId
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/**
	 * 设置微信ID
	 *
	 * @param Wx_id
	 *            微信ID
	 */
	public void setWxId(String wxid) {
		this.wxId = wxid;
	}

	/**
	 * 设置是否启用
	 *
	 * @param Is_use
	 *            是否启用
	 */
	public void setIsUse(Integer isuse) {
		this.isUse = isuse;
	}

}
