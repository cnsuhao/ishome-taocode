package com.mcookies.qxy.common.SDuty;

import javax.inject.Named;

@Named
/** 岗位设置表 */
public class SDutyPVO extends SDutyDBO {
	private Integer dutyContentCount;

	public Integer getDutyContentCount() {
		return dutyContentCount;
	}

	public void setDutyContentCount(Integer dutyContentCount) {
		this.dutyContentCount = dutyContentCount;
	}

}
