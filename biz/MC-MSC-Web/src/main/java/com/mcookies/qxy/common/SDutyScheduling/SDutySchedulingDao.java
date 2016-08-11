package com.mcookies.qxy.common.SDutyScheduling;

import java.util.List;

import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 岗位人员排班表 */
public interface SDutySchedulingDao extends IDatabaseSupport {

	List<SDutySchedulingDBO> findByTermIdAndTidAndIsUsed(SDutySchedulingPVO duty);

	List<SDutySchedulingPVO> findByTermIdAndWeekAndIsUsed(SDutySchedulingDBO dutyScheduling);

	List<SDutySchedulingPVO> findByTermIdAndDateAndIsUsed(SDutySchedulingDBO dutyScheduling);

	List<SDutySchedulingPVO> findByTermIdAndTidAndWeekAndIsUsed(SDutySchedulingPVO duty);

	List<SDutySchedulingPVO> findByTermIdAndTidAndDateAndIsUsed(SDutySchedulingPVO duty);

}
