package org.isotope.jfp.framework.search.biz;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.framework.search.ElasticsearchPool;
import org.isotope.jfp.framework.search.IPrepareSearchType;
import org.isotope.jfp.framework.search.QuerySentence;
import org.isotope.jfp.framework.search.bean.QueryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * 查询数据
 * 
 * @author 001745
 *
 */
@Service
public class SearchService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	ElasticsearchPool pool;
	@Resource
	QuerySentence sentence;

	public List<Object> searchDataInIndex(Class<?> clazz, IPrepareSearchType prepare, String queryID, Object... param) throws Exception {
		if (sentence.containsKey(queryID) == false) {
			throw new RuntimeException("The queryID is not exit ! >>>>>[" + queryID + "]<<<<<");
		}
		List<Object> ret = new ArrayList<Object>();
		List<String> hits = null;
		JestClient jestClient = null;
		try {
			//获得查询语句
			QueryBean qb = sentence.getSentence(queryID);
			//设定参数
			String query = String.format(qb.getValue(), param);
			logger.debug("query===================================" + query);
			//封装查询语法
			Search.Builder searchBuilder = new Search.Builder(query).addIndex(qb.getIndex()).addType(ElasticsearchPool.TYPE);
			//获得连接
			jestClient = pool.getClient();
			//执行查询语句
			SearchResult result = jestClient.execute(searchBuilder.build());
			//获得返回结果
			hits = result.getSourceAsStringList();
			logger.debug("getErrorMessage=====" + result.getErrorMessage());
			logger.debug("getTotal=====" + result.getTotal());
			//整理数据结果并返回
			Object cb;
			if (hits != null && hits.size() > 0) {
				for (String s : hits) {
					cb = JSON.parseObject(s, clazz);
					if (prepare != null)
						prepare.prepareSearchType(JSON.parseObject(s), cb);
					ret.add(cb);
				}
			}
		} finally {
			if (jestClient != null)
				jestClient.shutdownClient();
		}
		return ret;
	}

}
