package com.mcookies.qxy.common.DeviceTag;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 设备标签表 */
public class DeviceTagDBO extends MyDataBaseObjectSupport {
	/**
	 * 自增id
	 */
	private Long id = null;

	/**
	 * 学校id
	 */
	private Long sid = null;

	/**
	 * 地点名字
	 */
	private String deviceName = null;

	/**
	 * 学校设备id
	 */
	private Long deviceId = null;

	/**
	 * 设备账号
	 */
	private String username = null;

	/**
	 * 设备密码
	 */
	private String password = null;

	/**
	 * 最近一次登陆时间
	 */
	private String lastLoginTime = null;

	/**
	 * 是否启用
	 */
	private Integer isUse = null;

	/**
	 * 创建时间
	 */
	private String createTime = null;

	/**
	 * 创建者
	 */
	private Long creator = null;

	/**
	 * 更新时间
	 */
	private String updateTime = null;

	/**
	 * 最后更新者
	 */
	private Long updator = null;

	/**
	 * 获取自增id
	 *
	 * @return Id 自增id
	 */
	public Long getId() {
		return this.id;
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
	 * 获取地点名字
	 *
	 * @return Device_name 地点名字
	 */
	public String getDeviceName() {
		return this.deviceName;
	}

	/**
	 * 获取学校设备id
	 *
	 * @return Device_id 学校设备id
	 */
	public Long getDeviceId() {
		return this.deviceId;
	}

	/**
	 * 获取设备账号
	 *
	 * @return Username 设备账号
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * 获取设备密码
	 *
	 * @return Password 设备密码
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * 获取最近一次登陆时间
	 *
	 * @return Last_login_time 最近一次登陆时间
	 */
	public String getLastLoginTime() {
		return this.lastLoginTime;
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
	 * 获取创建时间
	 *
	 * @return Create_time 创建时间
	 */
	public String getCreateTime() {
		return this.createTime;
	}

	/**
	 * 获取创建者
	 *
	 * @return Creator 创建者
	 */
	public Long getCreator() {
		return this.creator;
	}

	/**
	 * 获取更新时间
	 *
	 * @return Update_time 更新时间
	 */
	public String getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 获取最后更新者
	 *
	 * @return Updator 最后更新者
	 */
	public Long getUpdator() {
		return this.updator;
	}

	/**
	 * 设置自增id
	 *
	 * @param Id
	 *            自增id
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * 设置地点名字
	 *
	 * @param Device_name
	 *            地点名字
	 */
	public void setDeviceName(String devicename) {
		this.deviceName = devicename;
	}

	/**
	 * 设置学校设备id
	 *
	 * @param Device_id
	 *            学校设备id
	 */
	public void setDeviceId(Long deviceid) {
		this.deviceId = deviceid;
	}

	/**
	 * 设置设备账号
	 *
	 * @param Username
	 *            设备账号
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 设置设备密码
	 *
	 * @param Password
	 *            设备密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 设置最近一次登陆时间
	 *
	 * @param Last_login_time
	 *            最近一次登陆时间
	 */
	public void setLastLoginTime(String lastlogintime) {
		this.lastLoginTime = lastlogintime;
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

	/**
	 * 设置创建时间
	 *
	 * @param Create_time
	 *            创建时间
	 */
	public void setCreateTime(String createtime) {
		this.createTime = createtime;
	}

	/**
	 * 设置创建者
	 *
	 * @param Creator
	 *            创建者
	 */
	public void setCreator(Long creator) {
		this.creator = creator;
	}

	/**
	 * 设置更新时间
	 *
	 * @param Update_time
	 *            更新时间
	 */
	public void setUpdateTime(String updatetime) {
		this.updateTime = updatetime;
	}

	/**
	 * 设置最后更新者
	 *
	 * @param Updator
	 *            最后更新者
	 */
	public void setUpdator(Long updator) {
		this.updator = updator;
	}

}
