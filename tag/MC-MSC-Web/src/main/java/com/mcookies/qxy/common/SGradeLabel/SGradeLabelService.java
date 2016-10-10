package com.mcookies.qxy.common.SGradeLabel;

import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 年级标签表 */
@Service
public class SGradeLabelService extends MyServiceSupport {

	public SGradeLabelDao getDao() {
		return getMySqlSession().getMapper(SGradeLabelDao.class);
	}

}
