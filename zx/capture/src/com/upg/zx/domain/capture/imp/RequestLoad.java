package com.upg.zx.domain.capture.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.upg.zx.capture.util.HttpClientUtil;
import com.upg.zx.domain.capture.bean.RamCache;
import com.upg.zx.domain.entity.AnalysisTemplate;
import com.upg.zx.domain.entity.RequestHead;
import com.upg.zx.domain.entity.RequestInfo;
 

public class RequestLoad {
	 
	
	private String request_url;
	
	private String analysisTemplateUrl;

	public void setRequest_url(String request_url) {
		this.request_url = request_url;
	}

	

	public void setAnalysisTemplateUrl(String analysisTemplateUrl) {
		this.analysisTemplateUrl = analysisTemplateUrl;
	}


	public void init(){
		try {
			System.out.println("加载工商网址信息......");
			requestLoad();
			System.out.println("加载解析模版信息......");
			analysisTemplateLoad();
			System.out.println("完成初始化...");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}		
	}
	
	
	public void requestLoad() throws IOException{
		String str = HttpClientUtil.getRequest(request_url);
		System.out.println("requestLoad=====>>>>>"+str);
		JsonConfig jsonConfig = new JsonConfig();
	    Map<String, Class> classMap = new HashMap<String, Class>();
	    classMap.put("headList", List.class); // 指定JsonRpcRequest的request字段的内部类型
	    jsonConfig.setClassMap(classMap);

		if(str != null && !"".equals(str)){
			JSONObject jsonObject = JSONObject.fromObject(str);
			JSONArray dataList = jsonObject.getJSONArray("data");
			for(int i = 0 ; i < dataList.size(); i++ ){
				JSONObject jsonObj = dataList.getJSONObject(i);
				RequestInfo requestInfo = (RequestInfo) JSONObject.toBean(jsonObj, RequestInfo.class);
				JSONArray headList = jsonObj.getJSONArray("headList");
				List<RequestHead> headls = new ArrayList<RequestHead>();
				for(int j = 0 ; j < headList.size() ; j++){
					JSONObject headObj = headList.getJSONObject(j);
					RequestHead requestHead = (RequestHead)JSONObject.toBean(headObj, RequestHead.class);
					headls.add(requestHead);
				}
				requestInfo.setHeadList(headls);
				
				if(requestInfo.getSubId() != null && !"".equals(requestInfo.getSubId())){
					RamCache.requestMap.put(requestInfo.getAreaCode()+"_"+requestInfo.getSubId()+"_"+requestInfo.getModeType(), requestInfo);
				}else{
					RamCache.requestMap.put(requestInfo.getAreaCode()+"_"+requestInfo.getModeType(), requestInfo);
				}
				
				String key = requestInfo.getAreaCode();
				if(requestInfo.getSubId() != null && !"".equals(requestInfo.getSubId())){
					key +="_"+requestInfo.getSubId();
				}
				
				if(RamCache.requestlistMap.get(key) != null){
					RamCache.requestlistMap.get(key).add(requestInfo);
				}else{
					List<RequestInfo> ls = new ArrayList<RequestInfo>();
					ls.add(requestInfo);
					RamCache.requestlistMap.put(key, ls);
				}
			}
		}
	}
	
	public void analysisTemplateLoad() throws IOException{
		String str = HttpClientUtil.getRequest(analysisTemplateUrl);
		System.out.println("analysisTemplateLoad=====>>>>>"+str);
		JSONObject jsonObject1 = JSONObject.fromObject(str);
		JSONObject jsonObject = jsonObject1.getJSONObject("data");
		Iterator it = jsonObject.keys();
		while(it.hasNext()){
			String key = (String)it.next();
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			List<AnalysisTemplate> list = (List<AnalysisTemplate>)JSONArray.toCollection(jsonArray,AnalysisTemplate.class);
			RamCache.analysisTemplateMap.put(key, list);
		}
		
	}
}
