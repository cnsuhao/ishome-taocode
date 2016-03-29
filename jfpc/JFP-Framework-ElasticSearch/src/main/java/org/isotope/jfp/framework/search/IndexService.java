package org.isotope.jfp.framework.search;

import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.IndicesExists;

@Service
public class IndexService {
	private Logger logger = LoggerFactory.getLogger(IndexService.class);
	@Resource
	ElasticsearchPool pool;

	/**
	 * 创建一个索引
	 * 
	 * @param index
	 *            索引名称，小写
	 * @throws Exception
	 */
	public void creatIndex(String index) throws Exception {
		JestResult result = pool.getClient().execute(new CreateIndex.Builder(index).build());
		logger.debug("creatIndex===" + result.getErrorMessage());
		logger.debug("creatIndex===" + result.getJsonString());
	}

	/**
	 * 删除一个索引
	 * @param index
	 * @throws IOException
	 */
	public void deleteIndex(String index) throws IOException {
		JestClient jestClient = pool.getClient();
		// 删除索引
		boolean indexExists = jestClient.execute(new IndicesExists.Builder(index).build()).isSucceeded();
		if (indexExists) {
			JestResult result = jestClient.execute(new DeleteIndex.Builder(index).build());
			logger.debug("deleteIndex===" + result.getErrorMessage());
			logger.debug("deleteIndex===" + result.getJsonString());
		}
	}
}
