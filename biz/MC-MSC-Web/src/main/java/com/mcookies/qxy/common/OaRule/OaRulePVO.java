package com.mcookies.qxy.common.OaRule;

import javax.inject.Named;

@Named
/** OA规则表 */
public class OaRulePVO extends OaRuleDBO {

	private String oatagsName = null;

	public String getOatagsName() {
		return oatagsName;
	}

	public void setOatagsName(String oatagsName) {
		this.oatagsName = oatagsName;
	}

}
