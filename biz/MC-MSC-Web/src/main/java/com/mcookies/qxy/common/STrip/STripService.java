package com.mcookies.qxy.common.STrip;

import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 行程表 */
@Service
public class STripService extends MyServiceSupport {

	public STripDao getDao() {
		return getMySqlSession().getMapper(STripDao.class);
	}

	public PageVOSupport doSelectPageBytid(PageVOSupport formParamPageModel) {
		formParamPageModel.setPageListData(getDao().doSelectPageBytid(formParamPageModel));
		return formParamPageModel;
	}
}
