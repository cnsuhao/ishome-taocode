package com.upg.zx.domain.capture.bean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
 
/**
 * 配置信息
 * @author lujf
 *
 */
public class Config {
	private Logger logger = Logger.getLogger(this.getClass());
	private  Document document;
	
	/**
	 * xml文件读取
	 */
	public void readResource(){
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); 
		 DocumentBuilder db = null;
		 try {
			db= dbf.newDocumentBuilder();  
			document = db.parse(Config.class.getClassLoader().getResourceAsStream("property_column.xml"));
		} catch (Exception  e) {
			logger.error("文件读取异常!"+e.getMessage());
		} 
	}
	
	/**
	 * 解析信息
	 */
	public void paseConfig(){
		if(document == null){
			this.readResource();
		}
		NodeList capturelist = document.getElementsByTagName("capture"); 
		if(capturelist == null){
			logger.debug("capture标签不存在!");
			return;
		}
		//解析capture节点
		for(int i= 0 ; i < capturelist.getLength() ; i++){
			 Element capture = (Element)capturelist.item(i);   
			 String areaCode = capture.getAttribute("id");
			 Mode mode = new Mode();
			 NodeList modeNodeList = capture.getElementsByTagName("mode");
			 if(modeNodeList == null){
				 continue;
			 }
			 //解析mode节点
			 for(int j = 0 ; j < modeNodeList.getLength() ; j++){
				 Element modeElement = (Element)modeNodeList.item(j);   
				 String modeCode = modeElement.getAttribute("id");
				 NodeList columnList = modeElement.getElementsByTagName("column");
				 if(columnList == null){
					 continue;
				 }
				 List<Column> columns = new ArrayList<Column>();
				 //解析column节点
				 for(int k = 0 ; k < columnList.getLength(); k++ ){
					 Element columnElement = (Element)columnList.item(k);
					 Column column = new Column();
					 column.setProperty(columnElement.getAttribute("property"));
					 column.setJavaType(columnElement.getAttribute("javaType"));
					 column.setPropertySource(columnElement.getAttribute("propertySource"));
					 column.setTypeSource(columnElement.getAttribute("typeSource"));
					 column.setValReg(columnElement.getAttribute("valReg"));
					 if("Date".equals(column.getJavaType())){
						 column.setDateForm(columnElement.getAttribute("dateForm"));
					 }
					 
					 columns.add(column);
				 }
				 mode.setColumnMap(areaCode, modeCode, columns);
			 }
			 Template.setModeMap(areaCode, mode);
		}
		
	}
}
