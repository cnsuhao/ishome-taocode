package org.isotope.jfp.framework.search;

import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.framework.search.bean.QueryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

/**
 * 查询数据
 * @author 001745
 *
 */
@Service
public class SearchService {
	private Logger logger = LoggerFactory.getLogger(TableService.class);

	@Resource
	ElasticsearchPool pool;
	@Resource
	QuerySentence sentence;

	public List<String> searchDataInIndex(String queryID, Object... param) throws Exception {
		List<String> hits = null;
		try {
			QueryBean qb = sentence.getSentence(queryID);
			Search.Builder searchBuilder = new Search.Builder(String.format(qb.getDsl(), param)).addIndex(qb.getIndex()).addType(ElasticsearchPool.TYPE);
			SearchResult result = pool.getClient().execute(searchBuilder.build());

			hits = result.getSourceAsStringList();
			logger.debug("getErrorMessage=====" + result.getErrorMessage());
			logger.debug("getTotal=====" + result.getTotal());

		} catch (Exception e) {
		}
		return hits;
	}

	// public void query1(){
	// String key = "";
	// JSONObject clientParam = new JSONObject();//查询客户端
	// JSONObject queryJson = new JSONObject();//查询条件
	//
	// clientParam.put("from", 50);//起始记录号
	// clientParam.put("size", 20);//分页
	//
	// JSONObject filtered = new JSONObject();//查询过滤器
	//
	// JSONObject type = new
	// JSONObject();//条件类别：term,match,match_all,must,must_not,range,bool,group_by_state
	//
	// JSONArray fields = new JSONArray();//返回字段过滤器
	// fields.add("corp_id");
	// fields.add("corp_name");
	// clientParam.put("_source", fields);
	//
	// JSONObject queryString = new JSONObject();
	// JSONObject param = new JSONObject();
	// param.put("query", key);//
	//
	//
	// JSONObject order = new JSONObject();
	// JSONObject sort = new JSONObject();
	// order.put("order", "desc");
	// sort.put("corp_id", order);
	// //queryJson.put("sort", sort);
	//
	//
	// queryString.put("query_string", param);
	// filtered.put("query", queryString);
	// queryJson.put("filtered", filtered);
	//
	//
	// clientParam.put("query", queryJson);
	//
	// }
}
