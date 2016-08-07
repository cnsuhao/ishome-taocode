package org.isotope.jfp.common.token;

import org.isotope.jfp.framework.beans.common.BusinessTokenBean;
import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;

public class TokenBean extends FrameworkDataBean implements ISFrameworkConstants {

	/**
	 * 服务器认证授权码（登记授权）
	 */
	protected String token = EMPTY;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public BusinessTokenBean getToken(String token) {
		this.setToken(token);
		return BusinessTokenBean.build(token);
	}
}
