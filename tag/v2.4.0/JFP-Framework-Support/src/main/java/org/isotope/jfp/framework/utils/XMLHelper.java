package org.isotope.jfp.framework.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.isotope.jfp.framework.beands.ObjectBean;

/**
 * 基于XML配置文件，映射内容到Bean
 * 
 * @author Spook
 * @since 0.2.1
 * @version 0.1.0 2014/11/28
 * 
 */
public class XMLHelper {

	/**
	 * 
	 * @param fileIn
	 * @param keyXml  xml处理业务关键字
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static HashMap<String, ObjectBean> loadXML(
			InputStream fileIn,
			String keyXml,
			ObjectBean bean) throws Exception {
		HashMap<String, ObjectBean> maps = new HashMap<String, ObjectBean>();
		
		XMLInputFactory factory = XMLInputFactory.newInstance();
	
		Reader fileReader = new InputStreamReader(fileIn,"GB2312");
		XMLEventReader reader = factory.createXMLEventReader(fileReader);
		
		while (reader.hasNext()) {
			XMLEvent event = reader.nextEvent();
			if (event.isStartElement()) {
				// 转换成开始元素事件对象  
                StartElement start = event.asStartElement(); 
                if(keyXml.equals(start.getName().getLocalPart())){
                	bean = bean.getClass().newInstance();
	                // 取得所有属性  
	                Iterator<?> attrs = start.getAttributes();  
	                while (attrs.hasNext()) {  
	                    // 打印所有属性信息  
	                    //Attribute attr = (Attribute) attrs.next();  
	                  //  BeanUtils.setProperty(bean, attr.getName().getLocalPart(), attr.getValue());  
	                } 
	                
	               // maps.put(BeanUtils.getProperty(bean, "k03_mbbh"), bean);
                }
			}
		}
		return maps;
	}
}
