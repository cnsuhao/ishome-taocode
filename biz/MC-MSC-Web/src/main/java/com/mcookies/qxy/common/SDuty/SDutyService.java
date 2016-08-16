package com.mcookies.qxy.common.SDuty;

import java.util.List;

import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 岗位设置表 */
@Service
public class SDutyService extends MyServiceSupport {

	public SDutyDao getDao() {
		return getMySqlSession().getMapper(SDutyDao.class);
	}

	public List<SDutyPVO> findWithContentCountByIsUse(SDutyDBO duty) {
		return getDao().findWithContentCountByIsUse(duty);
	}

}
