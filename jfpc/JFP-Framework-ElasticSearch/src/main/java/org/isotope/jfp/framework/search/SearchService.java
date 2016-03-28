package org.isotope.jfp.framework.search;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.upg.biz.mcws.beans.CeciCorpBase.CeciCorpBaseDBO;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Bulk.Builder;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.SearchResult.Hit;
import io.searchbox.core.search.sort.Sort;
import io.searchbox.core.search.sort.Sort.Sorting;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.IndicesExists;
@Service
public class SearchService {

	@Resource
	ElasticsearchPool pool;
	// whitespace：这个分析器基于空格字符来分离所提供的值。

	public static void main(String[] args) throws Exception {

		String query = "{\"query\" : { \"term\" : { \"corpname\" : \"三明\" } }}";

		// System.out.println(new String(query.getBytes("UTF-8")));

		JestClient jestClient = getClient();
		String index = "cecicorpbase111";// 必须小写
		// 删除索引
		deleteIndex(jestClient, index);
		// 创建索引
		createIndex(jestClient, index);

		long start = System.currentTimeMillis();
		// 添加数据
		Builder bulkIndexBuilder = new Bulk.Builder();
		File files = new File("E:/data/springdata/20160317");

		int num = 1;
		for (String f : files.list()) {
			File file = new File("E:/data/springdata/20160317/"+f);
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), "UTF-8");// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				System.out.println(lineTxt);
				CeciCorpBaseDBO corpBase = new CeciCorpBaseDBO();
				String[] s = lineTxt.split("_");
				if (s.length == 1)
					continue;
				corpBase.setCorpId(s[0]);
				corpBase.setCorpName(s[1]);
				num = num + 1;
				//bulkIndexBuilder.addAction(new Index.Builder(corpBase).index(index).type("name").build());
				bulkIndexBuilder.addAction(new Index.Builder(corpBase).index(index).type("comyanp").build());
				if (num % 3000 == 1) {
					System.out.println("num===" + num+"...."+jestClient.execute(bulkIndexBuilder.build()).getJsonString());
					Thread.sleep(1000);
					bulkIndexBuilder = new Bulk.Builder();
					jestClient = getClient();
				}

			}
			read.close();
		}
		long end = System.currentTimeMillis();
		System.out.println("创建索引时间:数据量是  " + num + "记录,共用时间 -->> " + (end - start) + " 毫秒");

		// 检索

		// //定义排序
		// Sort sortByPopulationAsc = new Sort("corpName", Sorting.DESC);
		// Search.Builder searchBuilder = new
		// Search.Builder(query).addIndex(index).addType("corpName");
		// SearchResult result = jestClient.execute(searchBuilder.build());
		// System.out.println(result.getErrorMessage());
		// System.out.println(result.getJsonString());
		// List<Hit<CeciCorpBaseDBO, Void>> hits =
		// result.getHits(CeciCorpBaseDBO.class);
		// for (Hit<CeciCorpBaseDBO, Void> hit : hits) {
		// CeciCorpBaseDBO corp = hit.source;
		// System.out.println(corp.getCorpId() + "=" +corp.getCorpName());
		// }

	}

	/**
	 * 获得连接
	 * 
	 * @return
	 * @throws IOException
	 */
	public static JestClient getClient() throws IOException {
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig.Builder("http://localhost:9200").multiThreaded(true).build());
		return factory.getObject();
	}

	/**
	 * 创建索引
	 * 
	 * @param jestClient
	 * @param index
	 * @throws IOException
	 */
	public static void createIndex(JestClient jestClient, String index) throws IOException {
		// GetSettings.Builder settingsBuilder = Settings.settingsBuilder();
		// settingsBuilder.put("number_of_shards",5);
		// settingsBuilder.put("number_of_replicas",1);
		// jestClient.execute(new
		// CreateIndex.Builder(index).settings(settingsBuilder.build().getAsMap()).build());
		JestResult result = jestClient.execute(new CreateIndex.Builder(index).build());
		System.out.println("createIndex===" + result.getErrorMessage());
		System.out.println("createIndex===" + result.getJsonString());
	}

	/**
	 * 删除索引
	 * 
	 * @param jestClient
	 * @throws IOException
	 */
	public static void deleteIndex(JestClient jestClient, String index) throws IOException {
		// 删除索引
		boolean indexExists = jestClient.execute(new IndicesExists.Builder(index).build()).isSucceeded();
		if (indexExists) {
			JestResult result = jestClient.execute(new DeleteIndex.Builder(index).build());
			System.out.println("deleteIndex===" + result.getErrorMessage());
			System.out.println("deleteIndex===" + result.getJsonString());
		}
	}

	/**
	 * 创建一个索引
	 */
	public void creatIndex(String indexName) {
	}

	/**
	 * 向一个已知索引里面添加数据
	 */
	public void addDataInIndex() {
	}
}
