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
     * 设备分组id
     */
    private Long dgroupId = null;

    /**
	 * 获取设备分组id
	 *
	 * 
	 */
	public Long getDgroupId() {
		return dgroupId;
	}

	public void setDgroupId(Long dgroupId) {
		this.dgroupId = dgroupId;
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

}
