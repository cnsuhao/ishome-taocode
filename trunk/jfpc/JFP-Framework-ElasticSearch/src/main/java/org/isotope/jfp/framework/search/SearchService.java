package org.isotope.jfp.framework.search;

import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.framework.search.bean.QueryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

	public List<String> searchDataInIndex(String queryID, Object... param) throws Exception {
		List<String> hits = null;
		JestClient jestClient = null;
		try {
			QueryBean qb = sentence.getSentence(queryID);
			Search.Builder searchBuilder = new Search.Builder(String.format(qb.getValue(), param)).addIndex(qb.getIndex()).addType(ElasticsearchPool.TYPE);
			
			jestClient = pool.getClient();
			
			SearchResult result = jestClient.execute(searchBuilder.build());

			hits = result.getSourceAsStringList();
			logger.debug("getErrorMessage=====" + result.getErrorMessage());
			logger.debug("getTotal=====" + result.getTotal());
		}finally{
			if(jestClient!=null)
				jestClient.shutdownClient();
		}
		return hits;
	}

}
