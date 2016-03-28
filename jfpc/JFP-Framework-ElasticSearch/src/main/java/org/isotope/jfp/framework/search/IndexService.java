package org.isotope.jfp.framework.search;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.IndicesExists;

@Service
public class IndexService {
	@Resource
	ElasticsearchPool pool;

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
	 * 
	 * @param index
	 *            索引名称，小写
	 * @throws Exception
	 */
	public void creatIndex(String index) throws Exception {
		JestResult result = pool.getClient().execute(new CreateIndex.Builder(index).build());
		System.out.println("creatIndex===" + result.getErrorMessage());
		System.out.println("creatIndex===" + result.getJsonString());
	}

	public void deleteIndex(String index) throws IOException {
		JestClient jestClient = pool.getClient();
		// 删除索引
		boolean indexExists = jestClient.execute(new IndicesExists.Builder(index).build()).isSucceeded();
		if (indexExists) {
			JestResult result = jestClient.execute(new DeleteIndex.Builder(index).build());
			System.out.println("deleteIndex===" + result.getErrorMessage());
			System.out.println("deleteIndex===" + result.getJsonString());
		}
	}
}
