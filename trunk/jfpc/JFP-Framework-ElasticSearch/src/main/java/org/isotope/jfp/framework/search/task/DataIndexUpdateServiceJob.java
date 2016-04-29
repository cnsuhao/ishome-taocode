package org.isotope.jfp.framework.search.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.search.ISSentenceConstants;
import org.isotope.jfp.framework.search.QuerySentence;
import org.isotope.jfp.framework.search.SQLService;
import org.isotope.jfp.framework.search.bean.QueryBean;
import org.isotope.jfp.framework.support.MyJobSupport;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 索引同步
 * 
 * @author 001745
 *
 */
public class DataIndexUpdateServiceJob extends MyJobSupport {
	private Logger logger = LoggerFactory.getLogger(DataIndexUpdateServiceJob.class);
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
			Calendar nowCalendar = Calendar.getInstance();
			Calendar lastCalendar = Calendar.getInstance();
			Map<String, QueryBean> updateMap = myQuerySentence.getUpdateMap();
			Iterator<Entry<String, QueryBean>> iter = updateMap.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, QueryBean> entry = iter.next();
				QueryBean qb = entry.getValue();
				// 获得最后一次更新时间
				String lastTime = (String) myCacheService.getObject(ISSentenceConstants.SENTENCE_UTD + qb.getIndex(), false);
				if (EmptyHelper.isEmpty(lastTime)) {
					logger.info("全文检索索引同步更新业务  xxxxx===== 取消....." + entry.getKey());
					continue;
				} 
				boolean upLast = true;
				while (upLast) {
					lastCalendar.add(Calendar.HOUR, 1);
					if ((nowCalendar.getTimeInMillis() - lastCalendar.getTimeInMillis()) < 3600 * 60 * 2) {
						upLast = false;
					}

					{
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
						sqlService.setStarttime(format.format(lastCalendar.getTime()));
					}
					{
						SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:59:59");
						sqlService.setEndtime(format.format(lastCalendar.getTime()));
					}

					sqlService.updateIndexBySQL(entry.getValue(), EMPTY, EMPTY);
				}
				// 设定最后一次更新时间
				myCacheService.putObject(ISSentenceConstants.SENTENCE_UTD + qb.getIndex(), "" + lastCalendar.getTimeInMillis(), 0, false);
			}
		}
		logger.info("全文检索索引同步更新业务  <<<<<===== 结束");
		return true;
	}

	@Autowired
	private ICacheService myCacheService;
}
