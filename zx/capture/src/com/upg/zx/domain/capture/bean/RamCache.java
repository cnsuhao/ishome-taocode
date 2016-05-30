package com.upg.zx.domain.capture.bean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.upg.zx.domain.entity.AnalysisTemplate;
import com.upg.zx.domain.entity.RequestInfo;
public class RamCache {
	//访问信息
	public static Map<String,RequestInfo> requestMap = new HashMap<String,RequestInfo>(); 
	
	public static Map<String,List<RequestInfo>> requestlistMap = new HashMap<String,List<RequestInfo>>();
	
	//访问模板信息
	public static Map<String,List<AnalysisTemplate>> analysisTemplateMap = new HashMap<String,List<AnalysisTemplate>>();
}
