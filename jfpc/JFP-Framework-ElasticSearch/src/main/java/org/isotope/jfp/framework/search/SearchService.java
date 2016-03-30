package org.isotope.jfp.framework.search;

import java.util.List;

import javax.annotation.Resource;

import org.isotope.jfp.framework.search.bean.QueryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.upg.biz.mcws.beans.CeciCorpBase.CorpBase;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
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
	
	
	// whitespace：这个分析器基于空格字符来分离所提供的值。
		public static void main(String[] args) throws Exception {
			String key = "桂林市  米粉店";
			// System.out.println(HttpServiceHelper.doHttpGET("http://127.0.0.1:9200/qcc_company_1/_search?q="+key+"&pretty"));
//			JSONObject json = JSONObject.parseObject(HttpServiceHelper.doHttpGET("http://127.0.0.1:9200/qcc_company_1/_search?q=" + key + "&pretty"));
//			// System.out.println(json.get("hits"));
//			// System.out.println(((JSONObject)json.get("hits")).get("hits"));
//			JSONArray jhits = (JSONArray) ((JSONObject) json.get("hits")).get("hits");
//			for (Object hit : jhits) {
//				//System.out.println("===1111==" + ((JSONObject) hit).get("_source").toString());
//				CorpBase corp = JSON.parseObject(((JSONObject) hit).get("_source").toString(), CorpBase.class);
//				System.out.println("=====" + corp.getCorp_name());
//			}
						
			JestClientFactory factory = new JestClientFactory();
			factory.setHttpClientConfig(new HttpClientConfig.Builder("http://localhost:9200").multiThreaded(true).build());
			JestClient jestClient = factory.getObject();
			String index = "qcc_company_1";// 必须小写
			String query1 = "{\n"
	                + "    \"size\": 2,\n"
	                + "    \"query\": {\n"
	                + "        \"bool\" : {\n"
	                + "            \"must\" : {\n"
	                + "                \"match\" : {\n"
	           //    + "                    \"query\" : \"桂林市\",\n"
	          //     + "                    \"operator\" : \"and\",\n"
	                + "                    \"corp_name\" : \"桂林市刘伯娘米粉店\"\n"
	                + "                }\n"
	                + "            }"
	                + "        }\n"
	                + "    }\n"
	                + "}";
			System.out.println("query1=====" + query1.replace("{0}", "aaaa"));

			Search.Builder searchBuilder = new Search.Builder(query1).addIndex(index).addType("table");
			SearchResult result = jestClient.execute(searchBuilder.build());
			System.out.println("==result===" + result.getMaxScore());
			System.out.println("getErrorMessage=====" + result.getErrorMessage());
			List<String> hits = result.getSourceAsStringList();
			for (String hit : hits) {
				System.out.println("==hit===" + hit);
				CorpBase corp = JSON.parseObject(hit, CorpBase.class);
				System.out.println("==corp===" + corp.getCorp_name());
			}

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
