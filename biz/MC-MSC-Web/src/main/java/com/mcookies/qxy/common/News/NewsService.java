package com.mcookies.qxy.common.News;
import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 新闻表 */
@Service
public class NewsService extends MyServiceSupport {
	protected static final Logger logger = LoggerFactory.getLogger(NewsService.class);

	public NewsDao getDao() {
		return getMySqlSession().getMapper(NewsDao.class);
	}

	public NewsPVO findDetailByNewsId(NewsPVO news) {
		return getDao().findDetailByNewsId(news);
	}

	@SuppressWarnings("unused")
	public PageVOSupport doSelectPageByColumnIdAndType(PageVOSupport pageModel) {
		FrameworkDataBean formParamBean = pageModel.getFormParamBean();
		pageModel.setPageListData(getDao().doSelectPageByColumnIdAndType(pageModel));
		return pageModel;
	}
	
	@SuppressWarnings("unused")
	public PageVOSupport doSelectPageByColumnIdAndTid(PageVOSupport pageModel) {
		FrameworkDataBean formParamBean = pageModel.getFormParamBean();
		pageModel.setPageListData(getDao().doSelectPageByColumnIdAndTid(pageModel));
		return pageModel;
	}
	
	@SuppressWarnings("unused")
	public PageVOSupport doSelectPageByColumnIdAndParentId(PageVOSupport pageModel) {
		FrameworkDataBean formParamBean = pageModel.getFormParamBean();
		pageModel.setPageListData(getDao().doSelectPageByColumnIdAndParentId(pageModel));
		return pageModel;
	}

	@SuppressWarnings("unused")
	public PageVOSupport doSelectPageByColumnId(PageVOSupport pageModel) {
		FrameworkDataBean formParamBean = pageModel.getFormParamBean();
		pageModel.setPageListData(getDao().doSelectPageByColumnId(pageModel));
		return pageModel;
	}

}
