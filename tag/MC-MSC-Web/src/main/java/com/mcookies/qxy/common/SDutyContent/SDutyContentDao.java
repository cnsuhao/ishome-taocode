package com.mcookies.qxy.common.SDutyContent;

import java.util.List;

import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 岗位内容设置表 */
public interface SDutyContentDao extends IDatabaseSupport {
	List<SDutyContentDBO> doSelectByDutyIds(List<Long> dutyIds);
}
