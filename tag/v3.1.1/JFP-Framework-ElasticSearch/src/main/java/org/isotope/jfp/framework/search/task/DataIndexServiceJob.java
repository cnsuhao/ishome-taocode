package org.isotope.jfp.framework.search.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.isotope.jfp.framework.search.QuerySentence;
import org.isotope.jfp.framework.search.SQLService;
import org.isotope.jfp.framework.search.bean.QueryBean;
import org.isotope.jfp.framework.support.MyJobSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 索引同步
 * 
 * @author 001745
 *
 */
public class DataIndexServiceJob extends MyJobSupport {
	private Logger logger = LoggerFactory.getLogger(DataIndexServiceJob.class);
	// 缓存队列
	QuerySentence myQuerySentence;

	public QuerySentence getMyQuerySentence() {
		return myQuerySentence;
	}

	public void setMyQuerySentence(QuerySentence myQuerySentence) {
		this.myQuerySentence = myQuerySentence;
	}

	SQLService sqlService;

	public SQLService getSqlService() {
		return sqlService;
	}

	public void setSqlService(SQLService sqlService) {
		this.sqlService = sqlService;
	}

	/**
	 * Redis缓存空间索引
	 */
	int index = 0;

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public boolean doProcessRepeat() throws Exception {
		logger.info("全文检索索引同步更新业务  >>>>>===== 开始");
		// 数据整理,基于Redis进行缓存同步
		{
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.HOUR, -1);
			{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
				sqlService.setStarttime(format.format(calendar.getTime()));
			}
			{
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:59:59");
				sqlService.setEndtime(format.format(calendar.getTime()));
			}
			Map<String, QueryBean> updateMap = myQuerySentence.getUpdateMap();
			Iterator<Entry<String, QueryBean>> iter = updateMap.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, QueryBean> entry = iter.next();
				sqlService.updateIndexBySQL(entry.getValue(), EMPTY, EMPTY);
			}
		}
		logger.info("全文检索索引同步更新业务  <<<<<===== 结束");
		return true;
	}

}
