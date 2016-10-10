package com.mcookies.qxy.common.NewsColumn;
import java.util.List;

import org.isotope.jfp.framework.support.IDatabaseSupport;

/** 栏目表 */
public interface NewsColumnDao extends IDatabaseSupport {
	
	List<NewsColumnDBO> doSelectWithParent(NewsColumnDBO newsCol);

}
