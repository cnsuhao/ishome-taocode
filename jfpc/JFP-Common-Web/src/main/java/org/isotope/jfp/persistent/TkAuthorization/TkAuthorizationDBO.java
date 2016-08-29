package org.isotope.jfp.persistent.TkAuthorization;

import java.sql.Date;

import javax.inject.Named;

import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

@Named
/** 第三方授权Token表 */
public class TkAuthorizationDBO extends MyDataBaseObjectSupport {
	/**
	 * TOKEN
	 */
	private String authorizerRefreshToken = null;

	/**
	 * 用户ID
	 */
	private Long uid = null;

	/**
	 * 学校ID
	 */
	private Long sid = null;

	/**
	 * 类别
	 */
	private Integer type = null;

	/**
	 * 第三方id
	 */
	private String tid = null;

	/**
	 * 授权时间
	 */
	private Date startTime = null;

	/**
	 * 授权终了时间
	 */
	private Date endTime = null;

	/**
	 * 获取TOKEN
	 *
	 * @return Authorizer_refresh_token TOKEN
	 */
	public String getAuthorizerRefreshToken() {
		return this.authorizerRefreshToken;
	}

	/**
	 * 获取用户ID
	 *
	 * @return Uid 用户ID
	 */
	public Long getUid() {
		return this.uid;
	}

	/**
	 * 获取学校ID
	 *
	 * @return Sid 学校ID
	 */
	public Long getSid() {
		return this.sid;
	}

	/**
	 * 获取类别
	 *
	 * @return Type 类别
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * 获取第三方id
	 *
	 * @return Tid 第三方id
	 */
	public String getTid() {
		return this.tid;
	}

	/**
	 * 获取授权时间
	 *
	 * @return Start_time 授权时间
	 */
	public Date getStartTime() {
		return this.startTime;
	}

	/**
	 * 获取授权终了时间
	 *
	 * @return End_time 授权终了时间
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * 设置TOKEN
	 *
	 * @param Authorizer_refresh_token
	 *            TOKEN
	 */
	public void setAuthorizerRefreshToken(String authorizerrefreshtoken) {
		this.authorizerRefreshToken = authorizerrefreshtoken;
	}

	/**
	 * 设置用户ID
	 *
	 * @param Uid
	 *            用户ID
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}

	/**
	 * 设置学校ID
	 *
	 * @param Sid
	 *            学校ID
	 */
	public void setSid(Long sid) {
		this.sid = sid;
	}

	/**
	 * 设置类别
	 *
	 * @param Type
	 *            类别
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * 设置第三方id
	 *
	 * @param Tid
	 *            第三方id
	 */
	public void setTid(String tid) {
		this.tid = tid;
	}

	/**
	 * 设置授权时间
	 *
	 * @param Start_time
	 *            授权时间
	 */
	public void setStartTime(Date starttime) {
		this.startTime = starttime;
	}

	/**
	 * 设置授权终了时间
	 *
	 * @param End_time
	 *            授权终了时间
	 */
	public void setEndTime(Date endtime) {
		this.endTime = endtime;
	}

}
