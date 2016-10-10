package com.mcookies.qxy.common.LogSecurity;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 安全日志表*/
public interface LogSecurityDao extends IDatabaseSupport{
	List<? extends FrameworkDataBean> doSelectPageSecurityLog(PageVOSupport formParamPageModel);
}
