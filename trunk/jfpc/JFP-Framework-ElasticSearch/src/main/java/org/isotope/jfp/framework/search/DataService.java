package org.isotope.jfp.framework.search;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import io.searchbox.client.JestClient;
import io.searchbox.core.Bulk;
import io.searchbox.core.Bulk.Builder;
import io.searchbox.core.BulkResult;
import io.searchbox.core.Index;

/**
 * 
 * @author 001745
 * @deprecated
 */
@Service
public class DataService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public int size = 1000;

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Resource
	ElasticsearchPool pool;

	// 添加数据
	/**
	 * 向一个已知索引里面添加数据
	 * @throws Exception 
	 */
	public void addDataInIndex(String index, List<? extends Object> datas) throws Exception {
		BulkResult result;
		JestClient jestClient = pool.getClient();
		Builder bulkIndexBuilder = new Bulk.Builder();
		for (int i = 0; i < datas.size(); i++) {
			bulkIndexBuilder.addAction(new Index.Builder(datas.get(i)).index(index).type(ElasticsearchPool.TYPE).build());
			//分批提交数据
			if (i % size == 1) {
				result = jestClient.execute(bulkIndexBuilder.build());
				logger.debug( "num===" + i + "...." + result.getJsonString());
				Thread.sleep(500);
				bulkIndexBuilder = new Bulk.Builder();
				jestClient = pool.getClient();
			}
		}
		//提交剩余数据
		result = jestClient.execute(bulkIndexBuilder.build());
	}
	// 更新数据
	// 删除数据
	// 批量插入
	// 批量更新
	// 批量删除

}
