package org.isotope.jfp.framework.search.task;

import java.util.Map;
import java.util.Set;

import org.isotope.jfp.framework.search.QuerySentence;
import org.isotope.jfp.framework.search.bean.QueryBean;
import org.isotope.jfp.framework.support.MyJobSupport;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 全文检索参数缓存同步
 * 
 * @author 001745
 *
 */
public class QuerySentenceServiceJob extends MyJobSupport {
	private Logger logger = LoggerFactory.getLogger(QuerySentenceServiceJob.class);
	// 缓存队列
	QuerySentence myQuerySentence;

	public QuerySentence getMyQuerySentence() {
		return myQuerySentence;
	}

	public void setMyQuerySentence(QuerySentence myQuerySentence) {
		this.myQuerySentence = myQuerySentence;
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
		logger.info("全文检索参数缓存同步业务  >>>>>===== 开始");
		// 数据整理,基于Redis进行缓存同步
		Set<String> keys;
		String value;
		{
			Map<String, QueryBean> sentenceMap = myQuerySentence.getSentenceMap();
			keys = sentenceMap.keySet();
			for (String key : keys) {
				myCacheService.selectDB(index);
				value = (String) myCacheService.getObject(QuerySentence.SENTENCE_SCH + key, false);
				if (EmptyHelper.isNotEmpty(value))
					sentenceMap.put(key, JSON.parseObject(value, QueryBean.class));
			}
			myQuerySentence.setSentenceMap(sentenceMap);
		}
		{
			Map<String, QueryBean> updateMap = myQuerySentence.getUpdateMap();
			keys = updateMap.keySet();
			for (String key : keys) {
				myCacheService.selectDB(index);
				value = (String) myCacheService.getObject(QuerySentence.SENTENCE_UPD + key, false);
				if (EmptyHelper.isNotEmpty(value))
					updateMap.put(key, JSON.parseObject(value, QueryBean.class));
			}
			myQuerySentence.setUpdateMap(updateMap);
		}
		{
			Map<String, QueryBean> creatMap = myQuerySentence.getCreatMap();
			keys = creatMap.keySet();
			for (String key : keys) {
				myCacheService.selectDB(index);
				value = (String) myCacheService.getObject(QuerySentence.SENTENCE_CRT + key, false);
				if (EmptyHelper.isNotEmpty(value))
					creatMap.put(key, JSON.parseObject(value, QueryBean.class));
			}
			myQuerySentence.setCreatMap(creatMap);
		}
		myCacheService.init();
		logger.info("全文检索参数缓存同步业务  <<<<<===== 结束");
		return true;
	}

}
