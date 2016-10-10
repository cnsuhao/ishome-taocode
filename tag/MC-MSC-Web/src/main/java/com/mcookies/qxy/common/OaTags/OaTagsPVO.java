package com.mcookies.qxy.common.OaTags;

import javax.inject.Named;

@Named
/** OA标签表 */
public class OaTagsPVO extends OaTagsDBO {
	private Long oaruleCount;

	public Long getOaruleCount() {
		return oaruleCount;
	}

	public void setOaruleCount(Long oaruleCount) {
		this.oaruleCount = oaruleCount;
	}

}
