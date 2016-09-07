package com.mcookies.qxy.common.NewsColumn;

import java.util.List;

import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 栏目表 */
@Service
public class NewsColumnService extends MyServiceSupport {

	public NewsColumnDao getDao() {
		return getMySqlSession().getMapper(NewsColumnDao.class);
	}
	
	public List<NewsColumnDBO> doSelectWithParent(NewsColumnDBO newsCol) {
		return getDao().doSelectWithParent(newsCol);
	}

}
