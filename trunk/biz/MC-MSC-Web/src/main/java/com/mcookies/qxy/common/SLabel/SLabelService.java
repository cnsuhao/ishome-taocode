package com.mcookies.qxy.common.SLabel;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 教工标签表 */
@Service
public class SLabelService extends MyServiceSupport {

	public SLabelDao getDao() {
		return getMySqlSession().getMapper(SLabelDao.class);
	}

}
