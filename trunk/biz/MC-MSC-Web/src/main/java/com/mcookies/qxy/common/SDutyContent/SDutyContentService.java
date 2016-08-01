package com.mcookies.qxy.common.SDutyContent;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 岗位内容设置表 */
@Service
public class SDutyContentService extends MyServiceSupport {

	public SDutyContentDao getDao() {
		return getMySqlSession().getMapper(SDutyContentDao.class);
	}

}
