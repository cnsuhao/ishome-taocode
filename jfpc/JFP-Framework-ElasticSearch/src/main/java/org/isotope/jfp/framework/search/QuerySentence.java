package org.isotope.jfp.framework.search;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;

import org.isotope.jfp.framework.search.bean.QueryBean;
import org.springframework.core.io.Resource;

/**
 * 查询语句
 * 
 * @author 001745
 *
 */
public class QuerySentence {
	public static void main(String[] args) throws Exception {
		QuerySentence q = new QuerySentence();
		q.doInit(new File("E:/workspace/JFP-Public-DistributedWebCrawler/src/main/resources/config/es/spring-es-query-sentence.xml"));
		System.out.println();
	}

	/**
	 * 初始化
	 * 
	 * @throws IOException
	 */
	public void init() throws Exception {
		if (sentenceFiles == null || sentenceFiles.length == 0) {
			return;
		}
		for (Resource r : sentenceFiles) {
			doInit(r.getFile());
		}
	}

	public void doInit(File xmlFile) throws Exception {
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try {
			// 创建基于迭代器的事件读取器对象
			XMLStreamReader reader = factory.createXMLStreamReader(new FileReader(xmlFile));
			QueryBean qb = null;
			// 遍历XML文档
			while (reader.hasNext()) {
				int event = reader.next();
				// 如果事件对象是元素的开始
				if (event == XMLStreamReader.START_ELEMENT) {
					if ("query".equals(reader.getLocalName())) {
						qb = new QueryBean();
					} else if ("id".equals(reader.getLocalName()))
						qb.setId(reader.getElementText());
					else if ("index".equals(reader.getLocalName()))
						qb.setIndex(reader.getElementText());
					else if ("dsl".equals(reader.getLocalName())) {
						qb.setDsl(reader.getElementText());
						sentenceMap.put(qb.getId(), qb);
					}
				}
			}
			// System.out.println(sentenceMap);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询语句文件名称
	 */
	Resource[] sentenceFiles;

	public void setSentenceFiles(Resource[] sentenceFiles) {
		this.sentenceFiles = sentenceFiles;
	}

	/**
	 * 查询语句Map
	 */
	Map<String, QueryBean> sentenceMap = new HashMap<String, QueryBean>();

	public QueryBean getSentence(String queryID) {
		return sentenceMap.get(queryID);
	}

}
