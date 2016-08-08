package com.mcookies.qxy.common.SDutyScheduling;

import javax.inject.Named;

@Named
/** 岗位人员排班表 */
public class SDutySchedulingPVO extends SDutySchedulingDBO {
	/**
	 * 关联教师id
	 */
	private Long tid;

	public Long getTid() {
		return tid;
	}

	public void setTid(Long tid) {
		this.tid = tid;
	}

}
