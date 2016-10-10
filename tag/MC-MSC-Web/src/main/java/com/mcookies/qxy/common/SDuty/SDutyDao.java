package com.mcookies.qxy.common.SDuty;

import java.util.List;

import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 岗位设置表 */
public interface SDutyDao extends IDatabaseSupport {

	List<SDutyPVO> findWithContentCountByIsUse(SDutyDBO duty);

}
