package org.isotope.jfp.framework.search;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

/**
 * 检索服务器连接池
 * 
 * @author 001745
 *
 */
public class ElasticsearchPool {
	public final static String TYPE = "data";
	
	public static void main(String[] args) throws Exception {
		// "http://localhost:9200"
	}
	public ElasticsearchPool() {
		this.serverList.add("http://localhost:9200");
	}
	protected Set<String> serverList = new LinkedHashSet<String>();

	public ElasticsearchPool(Collection<String> serverUris) {
		this.serverList.addAll(serverUris);
	}

	public ElasticsearchPool(String serverUri) {
		this.serverList.add(serverUri);
	}

	/**
	 * 获得连接
	 * 
	 * @return
	 * @throws IOException
	 */
	public JestClient getClient() throws IOException {
		JestClientFactory factory = new JestClientFactory();
		factory.setHttpClientConfig(new HttpClientConfig.Builder(serverList).multiThreaded(true).build());
		return factory.getObject();
	}

}
