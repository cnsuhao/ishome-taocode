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

	public static final String KEY_CECI = "CECI";
	// 缓存队列
	protected QuerySentence myQuerySentence;

	public QuerySentence getMyQuerySentence() {
		return myQuerySentence;
	}

	public void setMyQuerySentence(QuerySentence myQuerySentence) {
		this.myQuerySentence = myQuerySentence;
	}

	public QuerySentenceServiceJob() {
	}

	public void doProcess() throws Exception {

		logger.info("全文检索参数缓存同步业务  >>>>>===== 开始");
		setJobKey(KEY_CECI + ":TotalData");
		super.startLock();

		// 数据整理,基于Redis进行缓存同步
		{
			Map<String, QueryBean> sentenceMap = myQuerySentence.getSentenceMap();
			Set<String> keys = sentenceMap.keySet();
			String value;
			for (String key : keys) {
				value = (String) myCacheService.getObject(QuerySentence.SENTENCE_DSL + key, false);
				if (EmptyHelper.isNotEmpty(value))
					sentenceMap.put(key, JSON.parseObject(value, QueryBean.class));
			}

			Map<String, String> indexMap = myQuerySentence.getIndexMap();
			keys = indexMap.keySet();
			for (String key : keys) {
				value = (String) myCacheService.getObject(QuerySentence.SENTENCE_SQL + key, false);
				if (EmptyHelper.isNotEmpty(value))
					sentenceMap.put(key, JSON.parseObject(value, QueryBean.class));
			}
		}

		super.endLock();
		// 清空数据缓存
		logger.info("全文检索参数缓存同步业务  <<<<<===== 结束");
	}

}
