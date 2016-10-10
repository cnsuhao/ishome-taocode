package com.mcookies.qxy.common.STerm;

import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 学期设置表 */
public interface STermDao extends IDatabaseSupport {

	STermPVO findByTermId(STermPVO term);

}
