package com.mcookies.qxy.common.SDutyContent;

import java.util.List;

import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 岗位内容设置表 */
@Service
public class SDutyContentService extends MyServiceSupport {

	public SDutyContentDao getDao() {
		return getMySqlSession().getMapper(SDutyContentDao.class);
	}
	
	public List<SDutyContentDBO> doSelectByDutyIds(List<Long> dutyIds) {
		return getDao().doSelectByDutyIds(dutyIds);
	}

}
