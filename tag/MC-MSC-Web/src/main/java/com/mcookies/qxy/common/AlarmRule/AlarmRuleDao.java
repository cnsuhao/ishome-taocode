package com.mcookies.qxy.common.AlarmRule;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 报警规则表*/
public interface AlarmRuleDao extends IDatabaseSupport{
	List<? extends FrameworkDataBean> doSelectPageClassAlarm(FrameworkDataBean formParamPageModel);
	
	List<? extends FrameworkDataBean> doSelectPageCountAlarmRule(PageVOSupport formParamPageModel);
}
