package com.upg.zx.web.utils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
 

 




 






import com.upg.zx.capture.bean.CorpBase;
import com.upg.zx.domain.response.CorpBaseRes;

/**
 * jsoup工具类
 * 
 * @author lujf
 * 
 */
public class JsoupUtil {
	/**
	 * 根据id 获取输入框值
	 * 
	 * @param id
	 * @param html
	 * @return
	 */
	public static String getValById(String id, String html) {
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById(id);
		if (element != null) {
			return element.val();
		}
		return "";
	}
	
	/**
	 * 根据id获取Text
	 * @param id
	 * @param html
	 * @return
	 */
	public static String getTextById(String id, String html) {
		Document doc = Jsoup.parse(html);
		Element element = doc.getElementById(id);
		if (element != null) {
			return element.text().trim();
		}
		return "";
	}

	/**
	 * 根据名称获取第一个节点的值
	 * 
	 * @param name
	 * @param html
	 * @return
	 */
	public static String getValByNameOne(String name, String html) {
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select("[name=" + name + "]");
		if (elements != null && elements.size() > 0) {
			return elements.get(0).val();
		}
		return "";
	}

	/**
	 * 解析列表中a标签中的id以及显示的名称
	 * 
	 * @param idCode
	 * @param regex
	 * @param html
	 * @return
	 */
	public static List<CorpBase> parseHtmlA(String idCode, String regex,
			String html) {
		List<CorpBase> list = new ArrayList<CorpBase>();
		Document doc = Jsoup.parse(html);
		Pattern pattern = Pattern.compile(regex);
		Elements aElements = doc.getElementsByAttributeValueMatching("href",
				pattern);
		if (aElements != null) {
			for (int i = 0; i < aElements.size(); i++) {
				CorpBase corpBase = new CorpBase();
				String id = "";
				Element elementa = aElements.get(i);
				String href = elementa.attr("href");
				String text = elementa.text();
				if (href.indexOf(idCode) != -1) {
					id = href.substring(href.indexOf(idCode) + idCode.length()
							+ 1, href.length());
					if (id.indexOf("&") != -1) {
						id = id.substring(0, id.indexOf("&"));
					}
				}
				corpBase.setCorp_name(elementa.text());
				corpBase.setCompany_ID(id);
				list.add(corpBase);
			}
		}
		return list;

	}

	/**
	 * 判断是否存在指定样式的节点
	 * 
	 * @param css
	 * @return
	 */
	public static boolean isExistCss(String css, String html) {
		if (html == null || "".equals(html)) {
			return false;
		}
		Document doc = Jsoup.parse(html);
		Elements elements = doc.getElementsByClass(css);
		if (elements != null && elements.size() > 0) {
			return true;
		}
		return false;
	}

	/**
	 * 将table中的内容解析成map对象(key-value格式)
	 * 
	 * @param html_table
	 * @param keyReg key的正则
	 * @param valReg val的正则
	 * @param data_row 数据行
	 * @return
	 */
	public static Map<String, String> paseTableToMap(String html_table,
			String keyReg, String valReg, int data_row) {
		Map<String, String> map = new HashMap<String, String>();
		if (html_table == null || "".equals(html_table)) {
			return map;
		}
		Document doc = Jsoup.parse(html_table);
		Elements tabElements = doc.getElementsByTag("table");
		if (tabElements != null && tabElements.size() > 0) {
			Element table = tabElements.get(0);
			Elements trElements = table.getElementsByTag("tr");
			for (int i = 0; i < trElements.size(); i++) {
				if (i < data_row) {
					continue;
				}
				Element tr = trElements.get(i);
				Elements tdElements = tr.children();
				for (int j = 0; j < tdElements.size(); j++) {
					Element td = tdElements.get(j);
					// 判断是否是key
	  
					if (StringUtil.match(td.outerHtml(), keyReg)) {
						if (j < tdElements.size() - 1) {
							Element tdVal = tdElements.get(j + 1);
							if(StringUtil.match(tdVal.outerHtml(), valReg)){
								map.put(td.text().trim(), tdVal.text().trim());
							}
						}
					}
				}

			}
		}
		return map;
	}
	
	/**
	 * 解析表格中的内容放到list中
	 * @param html 表格的html
	 * @param titleRow 标题行
	 * @param DataRow 数据行
	 * @param data_ColCount  标题个数
	 * @return
	 */
	public static List<Map<String,String>>paseTableToList(String html,int titleRow,int DataRow,int data_ColCount){
		 List<Map<String,String>> list = new  ArrayList<Map<String,String>>();
		 Document doc = Jsoup.parse(html);
		 Elements tabElements = doc.getElementsByTag("table");
		 String[] titles = new String[data_ColCount];
		 
		 if (tabElements != null && tabElements.size() > 0) {
			 Element table = tabElements.get(0);
			 Elements trElements = table.getElementsByTag("tr");
			 for (int i = 0; i < trElements.size(); i++) {
				 Element tr = trElements.get(i);
				 Elements tdElements = tr.children();
				 //标题处理
				 if(i == titleRow-1){					
					 for (int j = 0; j < tdElements.size(); j++) {
						 if(j > data_ColCount-1){
							 break;
						 }
						 Element td = tdElements.get(j);						 
						 titles[j] = td.text().trim(); 
					 }
				 }else if(i >= DataRow-1){
					//数据处理
					 if(tdElements.size() < data_ColCount){
						 continue;
					 }
					 
					 
					 int data_page_count = tdElements.size()/data_ColCount;
					 
					 int cyclic_count = 0;
					 
					 for(int d_count = 0 ; d_count < data_page_count; d_count++){
						 Map<String,String> map = new HashMap<String,String>();
						 for(String title : titles){
							 Element td = tdElements.get(cyclic_count);
							 //删除a标签中的内容
							 Elements aElements = td.getElementsByTag("a");
							 if(aElements != null && aElements.size() > 0){
								 for(int alength = 0 ; alength < aElements.size() ; alength++){
									 aElements.get(alength).remove();
								 }
							 }
							 map.put(title,td.text().trim());
							 cyclic_count++;
						 }
						 list.add(map);
					 }	 
				 }
			 }
		 }	 
		 return list;
	}
	
	
	
	
	
	
	/**
	 * 根据id或指定的标签以及选择器获取符合要求的内容
	 * @param id  id
	 * @param tag 标签
	 * @param selector 选择器字符串
	 * @param html
	 * @return
	 */
	public static String getSpecifiedHtml(String id,String tag,String selector,String html){
		if(html == null || "".equals(html)){
			return "";
		}
		Document doc = Jsoup.parse(html);
		Element tableElement;
		//如果有id直接通过id获取
		if(id != null && !"".equals(id)){
			tableElement = doc.getElementById(id);
			if(tableElement != null){
				return tableElement.outerHtml();
			}
			return "";
		}
		//获取指定标签的节点集合
		Elements elements = doc.select(tag);
		if(elements != null){
			for(int i = 0 ; i < elements.size() ; i++){
				Element element = elements.get(i);
				Elements textElements = element.select(selector);
				//判断是否有满足选择字符串的节点
				if(textElements != null && textElements.size() > 0){
					//返回满足条件的tag节点的html值
					return element.outerHtml();
				}
			}
		}
		return "";
	}
	

	/**
	 * 解析关键JavaScript地址
	 * @param doc
	 * @param jspFileName
	 * @return
	 */
	public static  String parseJSURL(String jspFileName,String html){
		Document doc = Jsoup.parse(html);
		Elements links = doc.getElementsByTag("script"); 
		 for (Element link : links) { 
		    String src = link.attr("src"); 
		    if(!src.contains("/")) continue;
		    String tem = src.substring(src.lastIndexOf("/")+1,src.lastIndexOf("."));
		    if(jspFileName.contains(tem)){
		    	return src;
		    }
		 }
		 return null;
	}
	
	/**
	 * 根据选择器返回input中的val的值
	 * @param query
	 * @param html
	 * @return
	 */
	public static String getValByQuery(String query,String html){
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select(query);
		if(elements != null && elements.size() > 0){
			return elements.get(0).val();
		}
		return "";
	}

	/**
	 * 根据表达式判断是否存在
	 * @param query
	 * @param html
	 * @return
	 */
	public static boolean isExistNode(String query,String html){
		if(html == null || "".equals(html)){
			return false;
		}
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select(query);
		if(elements != null && elements.size() > 0){
			return true;
		}
		return false;
	}
	

	/**
	 * 合并头部table和内容table
	 * @param headHtml
	 * @param bodyTable
	 * @return
	 */
	public static String mergeHeadTableAndBodyTable(String headHtml,String bodyTable){
		Document titleDoc = Jsoup.parse(headHtml);
		Document bodyDoc = Jsoup.parse(bodyTable);
		
		Elements titleTrElements = titleDoc.select("table tr");
		if(titleTrElements == null || titleTrElements.size() ==0){
			return "";
		}
		Elements data_table = bodyDoc.getElementsByTag("table");
		for(int i = 0 ; i < data_table.size() ; i++){
			Element dataTableEmement = data_table.get(i);
			Elements dataTrElements = dataTableEmement.getElementsByTag("tr");
			for(int j = 0 ; j < dataTrElements.size() ; j++){
				titleTrElements.last().after(dataTrElements.get(j));
			}
		}
		return titleDoc.outerHtml();
	}
	
	/**
	 * 用于安徽信息分页
	 * 获取html中表格的分页页数
	 * @param html
	 * @param select
	 * @return
	 */
	public static String parseHtmlToGetPage(String html,String select){
		Document doc = Jsoup.parse(html);
		Elements elements = doc.getElementsByTag("table");
		Element element = null;
		for(int i = 0 ; i < elements.size() ; i++){
			Element tableElement = elements.get(i);
			Elements temp = tableElement.select(select);
			if(temp != null && temp.size() > 0){
				//头部table
				element = tableElement;
				break;
			}
		}
		if(element != null){
			Element pageElement = element.nextElementSibling().nextElementSibling();
			if(pageElement != null && "TABLE".equals(pageElement.nodeName().toUpperCase())){
				//获取a标签下的span
				Elements spans = pageElement.select("a span");
				if(spans != null && spans.size() > 0){
					return spans.last().text();
				}
			}
		}	
		return null;
	}
	
	/**
	 * 根据id获取指定的html内容
	 * @param html
	 * @param id
	 * @return
	 */
	public static String getHtmlById(String html,String id){
		Document doc = Jsoup.parse(html);
		Element element =  doc.getElementById(id);
		return element.html();
	}
	
	/**
	 * 将html中a标签里面符合regex数据以及a标签的值放到list中
	 * @param html
	 * @param regex 正则
	 * @return
	 */
	public static List<String>getHtmlA(String html,String regex){
		List<String> list = new ArrayList<String>();
		Document doc = Jsoup.parse(html);
		Elements elements = doc.getElementsByTag("a");
		if(elements != null && elements.size() > 0 ){
			for(int i = 0 ; i < elements.size() ; i++){
				Element element = elements.get(i);
				String val = StringUtil.getMatchStr(element.outerHtml(), regex);
				String rs = element.text().trim()+","+val;
				if(val != null && !"".equals(val)){
					list.add(rs);
				}
			}
		}
		return list;
	}
	
	/**
	 * 
	  * getHtmlByClass(根据class获取指定的html内容)
	  * @Title: getHtmlByClass
	  * @param  html
	  * @param  htmlClass
	 */
	public static String getHtmlByClass(String html,String htmlClass){
		Document doc = Jsoup.parse(html);
		Elements elements = doc.getElementsByClass(htmlClass);
		return elements.html();
	}
	
	/**
	 * 根据选择器获取内容
	 * @param html
	 * @param select
	 * @return
	 */
	public static String getTextBySelect(String html,String select){
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select(select);
		if(elements != null && elements.size() > 0){
			return elements.last().text();
		}
		return "";
	}
	
	/**
	 * 追加html指定的节点中
	 * @param select
	 * @param html
	 * @return
	 */
	public static String  appHtmlToSelect(String select,String html,String appHtml){
		Document doc = Jsoup.parse(html);
		Elements elements = doc.select(select);
		if(elements != null && elements.size() > 0){
			elements.get(0).children().last().after(appHtml);
		}
		return doc.outerHtml();
	}
	
}
