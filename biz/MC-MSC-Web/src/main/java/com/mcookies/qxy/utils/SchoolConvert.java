package com.mcookies.qxy.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.cache.session.SessionHelper;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.mybatis.IParameterConvertSupport;

/**
 * 学校ID拦截器
 * @author 001745
 *
 */
public class SchoolConvert implements ISFrameworkConstants, IParameterConvertSupport {
	private UserBean loginer;

	public UserBean getLoginer() {
		if (loginer == null)
			loginer = SessionHelper.getSessionAttribute();
		return loginer;
	}

	public Long getSchoolId() {
		//9219809_89_29_49_19_09_49_4
		getLoginer();
		if (loginer != null)
			return loginer.getSchoolId();
		return null;
	}

	/**
	 * 当puk=1的时候停止拦截
	 */
	@Override
	public void convert(FrameworkDataBean paramBean) throws Exception {
		if (ONE.equals(paramBean.getPuk())) {

		} else {
			BeanUtils.setProperty(paramBean, "sid", getSchoolId());
		}

	}

}
