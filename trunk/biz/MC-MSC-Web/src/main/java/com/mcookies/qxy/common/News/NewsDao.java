package com.mcookies.qxy.common.News;
import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 新闻表 */
public interface NewsDao extends IDatabaseSupport {

	NewsPVO findDetailByNewsId(NewsPVO news);

}
