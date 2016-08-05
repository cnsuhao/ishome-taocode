package com.mcookies.qxy.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.mybatis.IParameterConvertSupport;

public class SchoolConvert implements IParameterConvertSupport {

	public String getSchoolId() {
		return "9876543210";
	}

	@Override
	public void convert(FrameworkDataBean paramBean) throws Exception {
		BeanUtils.setProperty(paramBean, "sid", getSchoolId());

	}

}
