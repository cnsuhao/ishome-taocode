package com.aek56.atm.credentials.MGAAC_YLQXZCZ;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.support.ISDatabaseSupport;
import org.jfpc.framework.support.MyDataBaseObjectSupport;

/** 厂家医疗器械注册证*/
public interface MGAAC_YLQXZCZDao extends ISDatabaseSupport{

	/**
	 * 获得最新的证件
	 * @param formParamBean
	 * @return
	 */
	FrameworkDataBean loadNewCredentials(MyDataBaseObjectSupport formParamBean);

}
