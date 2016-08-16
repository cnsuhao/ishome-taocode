package com.mcookies.qxy.common.AlarmRule;
import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 报警规则表*/
@Service
public class AlarmRuleService extends MyServiceSupport {

    public AlarmRuleDao getDao(){
        return getMySqlSession().getMapper(AlarmRuleDao.class);
    }

	public List<? extends FrameworkDataBean> doSelectPageClassAlarm(FrameworkDataBean formParamPageModel) {
		return getDao().doSelectPageClassAlarm(formParamPageModel);
	}
	
	public PageVOSupport doSelectPageCountAlarmRule(PageVOSupport formParamPageModel) {
		formParamPageModel.setPageListData(getDao().doSelectPageCountAlarmRule(formParamPageModel));
		return formParamPageModel;
	}
}
