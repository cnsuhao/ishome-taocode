package org.isotope.jfp.framework.search;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.search.bean.QueryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

import com.alibaba.fastjson.JSON;

/**
 * 查询语句
 * 
 * @author 001745
 *
 */
public class QuerySentence implements ISSentenceConstants {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private ICacheService myCacheService;

	/**
	 * 初始化
	 * 
	 * @throws IOException
	 */
	public void init() throws Exception {
		logger.debug("全文检索初始化=====>>>>>开始");
		for (Resource file : sentenceFiles) {
			logger.debug("加载全文检索查询文件......" + file);
			doLoadSentenceFiles(file.getInputStream());
		}
		for (Resource file : creatFiles) {
			logger.debug("加载全文检索创建文件......" + file);
			doLoadCreatFiles(file.getInputStream());
		}
		for (Resource file : updateFiles) {
			logger.debug("加载全文检索更新文件......" + file);
			doLoadIndexFiles(file.getInputStream());
		}
		for (Resource file : urlFiles) {
			logger.debug("加载全文检索更新文件......" + file);
			doLoadAreaUrlFiles(file.getInputStream());
		}
		logger.debug("全文检索初始化<<<<<=====结束");
	}

	private void doLoadAreaUrlFiles(InputStream inputStream) {
		loadXML(inputStream, "area", "url", INDEX_URL,new HashMap<String, QueryBean>());
	}

	public void doLoadSentenceFiles(InputStream inputStream) throws Exception {
		loadXML(inputStream, "query", "dsl", SENTENCE_SCH,sentenceMap);
	}

	public void doLoadIndexFiles(InputStream inputStream) throws Exception {
		loadXML(inputStream, "update", "sql", SENTENCE_UPD,updateMap);
	}

	public void doLoadCreatFiles(InputStream inputStream) throws Exception {
		loadXML(inputStream, "creat", "sql", SENTENCE_CRT,creatMap);
	}

	private void loadXML(InputStream inputStream, String root, String type, String redisKey, Map<String, QueryBean> datas) {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try {
			// 创建基于迭代器的事件读取器对象
			XMLStreamReader reader = factory.createXMLStreamReader(inputStream, "UTF-8");
			QueryBean qb = null;
			String index;
			// 遍历XML文档
			while (reader.hasNext()) {
				int event = reader.next();
				// 如果事件对象是元素的开始
				if (event == XMLStreamReader.START_ELEMENT) {
					if (root.equals(reader.getLocalName())) {
						qb = new QueryBean();
					} else if ("id".equals(reader.getLocalName())) {
						qb.setId(reader.getElementText().toLowerCase());
					} else if ("index".equals(reader.getLocalName())) {
						index = reader.getElementText().toLowerCase();
						qb.setIndex(index);
						if(indexs.contains(index)==false)
							indexs.add(index);
					} else if (type.equals(reader.getLocalName())) {
						qb.setValue(reader.getElementText());
						logger.debug("保存全文检索配置.........." + qb.getId());
						datas.put(qb.getId(), qb);
						if (myCacheService != null)
							myCacheService.putObject(redisKey + qb.getId(), JSON.toJSONString(qb), 0, false);
					} else if ("mapping".equals(reader.getLocalName())) {
						qb.setMapping(reader.getElementText().toLowerCase());
					}
				}
			}
			//System.out.println(sentenceMap);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	ArrayList<String> indexs =  new ArrayList<String> ();
	public boolean containsIndex(String queryID) {
		return indexs.contains(queryID);
	}
	
	/////////////////// 使用动态更新//////////////////////
	/**
	 * 查询语句文件名称
	 */
	Resource[] sentenceFiles;

	public void setSentenceFiles(Resource[] sentenceFiles) {
		this.sentenceFiles = sentenceFiles;
	}

	/**
	 * 索引语句文件名称
	 */
	Resource[] updateFiles;

	public void setUpdateFiles(Resource[] updateFiles) {
		this.updateFiles = updateFiles;
	}

	/**
	 * 索引语句文件名称
	 */
	Resource[] creatFiles;

	public void setCreatFiles(Resource[] creatFiles) {
		this.creatFiles = creatFiles;
	}
	
	Resource[] urlFiles;

	public void setUrlFiles(Resource[] urlFiles) {
		this.urlFiles = urlFiles;
	}
	
	//----------------------------------------------------------------------------------//
	/**
	 * 查询语句Map
	 */
	Map<String, QueryBean> sentenceMap = new HashMap<String, QueryBean>();

	public QueryBean getSentence(String queryID) {
		return sentenceMap.get(queryID);
	}
	public Map<String, QueryBean> getSentenceMap() {
		return sentenceMap;
	}

	public void setSentenceMap(Map<String, QueryBean> sentenceMap) {
		this.sentenceMap = sentenceMap;
	}

	/**
	 * 更新SQL语句Map
	 */
	Map<String, QueryBean> updateMap = new HashMap<String, QueryBean>();

	public QueryBean getUpdate(String updateID) {
		return updateMap.get(updateID);
	}

	public Map<String, QueryBean> getUpdateMap() {
		return updateMap;
	}

	public void setUpdateMap(Map<String, QueryBean> updateMap) {
		this.updateMap = updateMap;
	}

	/**
	 * 创建SQL语句Map
	 */
	Map<String, QueryBean> creatMap = new HashMap<String, QueryBean>();

	public QueryBean getCreat(String creatID) {
		return creatMap.get(creatID);
	}

	public Map<String, QueryBean> getCreatMap() {
		return creatMap;
	}

	public void setCreatMap(Map<String, QueryBean> creatMap) {
		this.creatMap = creatMap;
	}
}
