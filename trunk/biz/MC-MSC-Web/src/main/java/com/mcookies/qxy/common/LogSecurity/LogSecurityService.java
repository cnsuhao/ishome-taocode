package com.mcookies.qxy.common.LogSecurity;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 安全日志表*/
@Service
public class LogSecurityService extends MyServiceSupport {

    public LogSecurityDao getDao(){
        return getMySqlSession().getMapper(LogSecurityDao.class);
    }
	public PageVOSupport doSelectPageSecurityLog(PageVOSupport formParamPageModel) {
		formParamPageModel.setPageListData(getDao().doSelectPageSecurityLog(formParamPageModel));
		return formParamPageModel;
	}
}
