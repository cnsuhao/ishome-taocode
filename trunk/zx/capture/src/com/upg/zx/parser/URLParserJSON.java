package com.upg.zx.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.zxlh.util.StringUtils;

public class URLParserJSON implements URLParser {
	
	public Map<String,Map<String,String>> parserForURL(String jspContent,String jsContent){
		Map<String,Map<String,String>>  map = new HashMap<String,Map<String,String>>();
		Document doc = Jsoup.parse(jspContent);
		Map<String,String> commonParam = this.parseCommonParam(doc);
		Map<String,String> m1 = this.parseURLAndParam(jsContent, "basicInfo", commonParam, false);
		Map<String,String> m2 = this.parseURLAndParam(jsContent, "investmentInfor", commonParam,true);
		if(m2 == null){
			m2 = this.parseURLAndParamForOther(jsContent, "promoterInformation", "zhuguanbumenRp.load", commonParam, true);
		}
		Map<String,String> m3 = this.parseURLAndParam(jsContent, "changeInformation", commonParam,true);
		Map<String,String> m4 = this.parseURLAndParam(jsContent, "personnelInformation", commonParam,true);
		Map<String,String> m5 = this.parseURLAndParam(jsContent, "branchOfficeInfor", commonParam,true);
		if(m1!=null)
			map.put("基本信息", m1);
		if(m2!=null)
			map.put("出资信息", m2);
		if(m3!=null)
			map.put("变更信息", m3);
		if(m4!=null)
			map.put("主要人员信息", m4);
		if(m5!=null)
			map.put("分公司信息", m5);
		return map;
	}
	
	/**
	 * 
	 * @param jsContent
	 * @param functionName
	 * @param paramFlag 如： zhuguanbumenRp.load
	 * @param commonParam
	 * @param pageSupport
	 * @return
	 */
	private Map<String,String> parseURLAndParamForOther(String jsContent, String functionName, String paramFlag, Map<String,String> commonParam, boolean pageSupport){
		Map<String,String> map = new HashMap<String,String>();
		String[] lines = this.parseJSFunction(jsContent, functionName);
		if(lines == null)
			return null;
		for(String line:lines){
			if(line.contains("var") && line.contains("url")){
				map.put("json_url", line.substring(line.indexOf("'")+1,line.lastIndexOf("'")));
			}
			if(line.contains(paramFlag) && line.contains("{")&&line.contains("}")){
				String[] params = line.substring(line.indexOf("{")+1, line.lastIndexOf("}")).split(",");
				for(String p:params){
					if(!p.contains(":")) continue;
					String[] temArray = p.split(":");
					String varName = temArray[0].trim();
					String varValue = temArray[1].trim();
					if(StringUtils.isNumeric(varValue)){
						map.put(varName, varValue);
					}else if(varValue.contains("\"")){//字符串类型
						map.put(varName, varValue.replaceAll("\"", "").trim());
					}else if(varValue.contains("'")){//字符串类型
						map.put(varName, varValue.replaceAll("'", "").trim());
					}else if(commonParam.containsKey(varValue)){//变量，如：公共参数org id seq_id
						map.put(varName,commonParam.get(varValue).trim() );
					}
				}
				
				
			}
		}
		if(pageSupport){
			map.put("pageNo", "1");
			map.put("pageSize", "100");
		}
		return map;
	}
	
	private Map<String,String> parseURLAndParam(String jsContent, String functionName,  Map<String,String> commonParam, boolean pageSupport){
		Map<String,String> map = new HashMap<String,String>();
		String[] lines = this.parseJSFunction(jsContent, functionName);
		if(lines == null)
			return null;
		for(String line:lines){
			if(line.contains("var") && line.contains("url")){
				map.put("json_url", line.substring(line.indexOf("'")+1,line.lastIndexOf("'")));
			}
			if(line.contains("paramObj[\"") && line.contains("=")){
				String[] temArray = line.split("=");
				String varName = temArray[0];
				varName = varName.substring(varName.indexOf("\"")+1,varName.lastIndexOf("\"")).trim();
				String varValue = temArray[1].trim();
				if(varValue.contains("\"")){//字符串类型
					map.put(varName, varValue.replaceAll("\"", "").trim());
				}else if(varValue.contains("'")){//字符串类型
					map.put(varName, varValue.replaceAll("'", "").trim());
				}else if(commonParam.containsKey(varValue)){//变量，如：公共参数org id seq_id
					map.put(varName,commonParam.get(varValue).trim() );
				}
			}
		}
		if(pageSupport){
			map.put("pageNo", "1");
			map.put("pageSize", "100");
		}
		return map;
	}
	
	/**
	 * 请求JS内容
	 * @param host
	 * @param jsURL
	 * @return
	 */
	public String parseJSContent(String host,String jsURL){
		HttpClient client = new DefaultHttpClient(); 
		HttpGet get = new HttpGet(host+jsURL); 
		try{
			HttpResponse response =client.execute(get);
			response.toString();
	        HttpEntity entity = response.getEntity();  
	        String backContent = null;
	        if (entity != null) {      
	            InputStream is = entity.getContent();  
	            BufferedReader in = new BufferedReader(new InputStreamReader(is));   
	            StringBuffer buffer = new StringBuffer();   
	            String line = "";  
	            while ((line = in.readLine()) != null) {  
	                buffer.append(line+"\n");  
	            }  
	            backContent = buffer.toString();  
	            return backContent;
	        }  
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 解析指定函数，返回分号为分割的行列表
	 * @param jsContent
	 * @param functionName
	 * @param lineNumber 以分号为分隔符表示一行
	 * @return
	 */
	private String[] parseJSFunction(String jsContent, String functionName){
		 
			Pattern pattern2 = Pattern.compile("/\\*((.|[\\n\\t\\r\\s])(?!\\*/))+([\\n\\t\\r\\s]|.)\\*/|//[^\\r\\n]+");
			Matcher matcher2 = pattern2.matcher(jsContent);
		    String jsHTML = matcher2.replaceAll("");
		    String reg="function\\s+"+ functionName +"\\(\\)\\s*\\{([^\\{\\}]*?(\\{[^\\{\\}]*\\})*)*\\}";
			Pattern pattern = Pattern.compile(reg);
			Matcher matcher = pattern.matcher(jsHTML);
			
			boolean b= matcher.matches();
			if(matcher.find()){
				  String s = matcher.group();
				  String[] array = s.split(";\\s+");
				  return array;
			}
			
			return null;
	}
	/**
	 * 解析关键JavaScript地址
	 * @param doc
	 * @param jspFileName
	 * @return
	 */
	public String parseJSURL(Document doc,String jspFileName){
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
	 * 解析页面hidden值
	 * @param doc
	 * @return
	 */
	private Map<String,String> parseCommonParam(Document doc){
		Map<String,String> param = new HashMap<String,String>();
		Elements inputs = doc.select("#formx input[type=hidden]");
		int i=0;
		for (Element input : inputs) { 
			if(i>3) break;
			param.put(input.attr("name"),input.attr("value"));
			i++;
		}
		return param;
	}
	
}
