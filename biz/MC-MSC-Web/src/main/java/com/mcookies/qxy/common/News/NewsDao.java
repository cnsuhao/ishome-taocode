package com.mcookies.qxy.common.News;
import java.util.List;

import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 新闻表 */
public interface NewsDao extends IDatabaseSupport {

	NewsPVO findDetailByNewsId(NewsPVO news);

	List<NewsPVO> doSelectPageByColumnIdAndType(PageVOSupport pageModel);
	
	List<NewsPVO> doSelectPageByColumnIdAndTid(PageVOSupport pageModel);
	
	List<NewsPVO> doSelectPageByColumnIdAndParentId(PageVOSupport pageModel);

}
