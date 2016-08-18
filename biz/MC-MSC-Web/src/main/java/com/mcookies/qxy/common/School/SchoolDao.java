package com.mcookies.qxy.common.School;

import java.util.List;

import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 学校表 */
public interface SchoolDao extends IDatabaseSupport {

	List<SchoolDBO> doSelectByIsUse(SchoolDBO school);

}
