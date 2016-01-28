package com.upg.ceci.beans.CeciLog;

import org.isotope.jfp.framework.support.ISDatabaseSupport;
import org.isotope.jfp.framework.support.MyDataBaseObjectSupport;

/** 抓取结果日志 */
public interface CeciLogDao extends ISDatabaseSupport {

	MyDataBaseObjectSupport doReadLast(MyDataBaseObjectSupport paramBean);
}
