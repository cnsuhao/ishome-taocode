package com.upg.zx.parser;

import java.util.Map;

import org.jsoup.nodes.Document;

public interface URLParser {
	
	public Map<String,Map<String,String>> parserForURL(String jspContent,String jsContent);
	
	public String parseJSContent(String host,String jsURL);
	
	public String parseJSURL(Document doc,String jspFileName);

}
