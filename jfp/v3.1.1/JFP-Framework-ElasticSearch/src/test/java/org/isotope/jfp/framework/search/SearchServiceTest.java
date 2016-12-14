package org.isotope.jfp.framework.search;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.isotope.jfp.framework.search.bean.QueryBean;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

public class SearchServiceTest {

	// whitespace：这个分析器基于空格字符来分离所提供的值。
	public static void main(String[] args) throws Exception {
		
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig.Builder("http://10.50.86.21:9200").multiThreaded(true).build());
		JestClient jestClient = factory.getObject();
		File f = new File(SearchServiceTest.class.getResource("").getPath()); 
		System.out.println(f);
		QuerySentence qs = new QuerySentence();
		qs.doLoadSentenceFiles(new FileInputStream(SearchServiceTest.class.getResource("").getPath()+"\\QueryTest.dsl"));
		QueryBean qb = qs.getSentence("test");
		
		String query = String.format(qb.getQuery(), "中新力合");
		System.out.println("query1=====" + query);

		Search.Builder searchBuilder = new Search.Builder(query).addIndex(qb.getIndex()).addType("data");
		SearchResult result = jestClient.execute(searchBuilder.build());
		System.out.println("==result===" + result.getTotal());
		System.out.println("getErrorMessage=====" + result.getErrorMessage());
		List<String> hits = result.getSourceAsStringList();
		for (String hit : hits) {
			System.out.println("==hit===" + hit);
			//CorpBaseBean corp = JSON.parseObject(hit, CorpBaseBean.class);
		//	System.out.println("==corp===" + corp.getCorp_name());
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
