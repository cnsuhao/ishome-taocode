package org.isotope.jfp.common.weixin;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

/** 微信企业部门表 */
@Named
public class WeiXinCompanyDeptDBO extends MyDataBaseObjectSupport {
	/**
	 * 数据id
	 */
	private Long pid = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 用户部门id
	 */
	private Long did = null;

	/**
	 * 用户部门微信id
	 */
	private String deptWeixinId = null;

	/**
	 * 用户部门名称
	 */
	private String deptName = null;

	/**
	 * 父部门ID
	 */
	private String parentId = null;

	/**
	 * 关注学校企业号ID
	 */
	private String schoolAppId = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 获取数据id
	 *
	 * @return Pid 数据id
	 */
	public Long getPid() {
		return this.pid;
	}

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
	 * @return id 用户组id
	 */
	public Long getid() {
		return this.did;
	}

	/**
	 * 获取用户部门微信Id
	 *
	 * @return Dept_Weixin_Id 用户部门微信Id
	 */
	public String getDeptWeixinId() {
		return this.deptWeixinId;
	}

	/**
	 * 获取用户部门名称
	 *
	 * @return Dept_Name 用户部门名称
	 */
	public String geteptName() {
		return this.deptName;
	}

	/**
	 * 获取父部门ID
	 *
	 * @return arentId 父部门Id
	 */
	public String getParentId() {
		return this.parentId;
	}

	/**
	 * 获取关注学校企业号ID
	 *
	 * @return School_app_id 关注学校企业号ID
	 */
	public String getSchoolAppId() {
		return this.schoolAppId;
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
	 * 设置数据id
	 *
	 * @param Pid
	 *            数据id
	 */
	public void setPid(Long pid) {
		this.pid = pid;
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
	 * 设置用户部门id
	 *
	 * @param Did
	 *            用户部门id
	 */
	public void setDid(Long did) {
		this.did = did;
	}

	/**
	 * 设置用户部门微信ID
	 * 
	 * @param Dept_weixin_id
	 *            用户部门微信ID
	 */
	public void setDeptWeixinId(String deptweixinid, String deptWeixinId) {
		this.deptWeixinId = deptWeixinId;
	}

	/**
	 * 设置用户部门名称
	 *
	 * @param Dept_name
	 *            用户部门名称
	 */
	public void setGroupName(String deptname) {
		this.deptName = deptname;
	}

	/**
	 * 设置父部门Id
	 *
	 * @param Parent_id
	 *            父部门Id
	 */
	public void setParentId(String parentid) {
		this.parentId = parentid;
	}

	/**
	 * 设置关注学校企业号ID
	 *
	 * @param School_app_id
	 *            关注学校企业号ID
	 */
	public void setSchoolAppId(String schoolappid) {
		this.schoolAppId = schoolappid;
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
