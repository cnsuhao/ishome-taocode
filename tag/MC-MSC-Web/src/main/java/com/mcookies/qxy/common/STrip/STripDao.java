package com.mcookies.qxy.common.STrip;

import java.util.List;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 行程表 */
public interface STripDao extends IDatabaseSupport {

	List<? extends FrameworkDataBean> doSelectPageBytid(PageVOSupport formParam);
}
