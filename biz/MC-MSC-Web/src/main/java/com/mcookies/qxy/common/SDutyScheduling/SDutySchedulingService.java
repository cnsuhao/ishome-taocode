package com.mcookies.qxy.common.SDutyScheduling;

import java.util.List;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 岗位人员排班表 */
@Service
public class SDutySchedulingService extends MyServiceSupport {

	public SDutySchedulingDao getDao() {
		return getMySqlSession().getMapper(SDutySchedulingDao.class);
	}

	public List<SDutySchedulingDBO> findByTermIdAndTidAndIsUsed(SDutySchedulingPVO duty) {
		return getDao().findByTermIdAndTidAndIsUsed(duty);
	}

	public List<SDutySchedulingPVO> findByTermIdAndWeekAndIsUsed(SDutySchedulingDBO dutyScheduling) {
		return getDao().findByTermIdAndWeekAndIsUsed(dutyScheduling);
	}
	
	public List<SDutySchedulingPVO> findByTermIdAndDateAndIsUsed(SDutySchedulingDBO dutyScheduling) {
		return getDao().findByTermIdAndDateAndIsUsed(dutyScheduling);
	}

}
