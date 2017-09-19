package org.isotope.jfp.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * 基于XML配置文件，映射内容到Bean
 * 
 * @author Spook
 * @since 0.2.1
 * @version 0.1.0 2014/11/28
 * 
 */
public class XMLHelper {
	public static void main(String[] args) throws Exception {
		System.out.println(loadXML("D:/a.txt"));
	}

	/**
	 * 
	 * @param fileIn
	 * @param keyXml
	 *            xml处理业务关键字
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String, String> loadXML(String fileName) throws Exception {
		return loadXML(new FileInputStream(new File(fileName)));
	}
	public static HashMap<String, String> loadXML(InputStream fileName) throws Exception {
		HashMap<String, String> maps = new HashMap<String, String>();

		XMLInputFactory factory = XMLInputFactory.newInstance();

		Reader fileReader = new InputStreamReader(fileName, "UTF-8");
		XMLEventReader reader = factory.createXMLEventReader(fileReader);
		String key = null;
		String value = null;
		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();
			if (event.isStartElement()) {
				// 转换成开始元素事件对象
				StartElement start = event.asStartElement();
				// start.getName().getLocalPart();
				key = start.getName().getLocalPart();
			} else if (event.isCharacters()) {
				Characters character = event.asCharacters();
				value = character.getData();
			} else if (event.isEndElement()) {
				maps.put(key, value);
			}
		}
		return maps;
	}
}
