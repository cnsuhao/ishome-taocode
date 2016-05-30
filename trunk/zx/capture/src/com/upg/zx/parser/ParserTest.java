package com.upg.zx.parser;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class ParserTest {
	
	public static void main(String[] args) throws IOException{
		 
		URLParserJSON parser = new URLParserJSON();
		File htmlFile = new File(parser.getClass().getResource("outer_fiei_queryCorpInfor_gsRelease.html").getPath());
		String jspContent = FileUtils.readFileToString(htmlFile, "utf-8");
		Document doc = Jsoup.parse(jspContent);
		String jsurl = parser.parseJSURL(doc,"pb_queryCorpInfor_gsRelease.jsp");
		System.out.println(jsurl);
		String jsContent = parser.parseJSContent("http://www.jsgsj.gov.cn:58888", jsurl);
		parser.parserForURL(FileUtils.readFileToString(htmlFile, "utf-8"),jsContent);

		
	}

}
