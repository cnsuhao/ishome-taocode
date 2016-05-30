package com.upg.zx.domain.capture.service.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpException;

import com.upg.zx.capture.bean.CorpBase;
import com.upg.zx.domain.capture.bean.RamCache;
import com.upg.zx.domain.capture.bean.Token;
import com.upg.zx.domain.capture.exception.CaptureException;
import com.upg.zx.domain.entity.RequestInfo;
import com.upg.zx.web.utils.JsoupUtil;
import com.upg.zx.web.utils.StringUtil;

public class JianXiCaptureService extends CaptureServiceImp{
 
 
	
	@Override
	protected boolean judgeCode(String html) {
		return JsoupUtil.isExistCss("list", html);
	}

	@Override
	public List<CorpBase> paseHtmlToList(String html) {
		List<String> hrefList = null;
		List<CorpBase> corpls = new ArrayList<CorpBase>();
		String onclick_str = JsoupUtil.getHtmlById(html, "qyList");
		hrefList = JsoupUtil.getHtmlA(onclick_str, "(?<=showJbxx\\()[^()]+(?=\\))");
		if(hrefList != null && hrefList.size() > 0){
			for(int i = 0 ; i < hrefList.size(); i++){
				CorpBase corpBase = new CorpBase();
				String href_str = hrefList.get(i);
				//参数值数组
				String[] params_str = href_str.split(",");
				String companyId_str="";
				for(int j = 0 ; j < params_str.length ; j++){
					if(j==0){
						//第一个为企业名称
						corpBase.setCorp_name(params_str[j]);
						continue;
					}
					if("".equals(companyId_str)){
						companyId_str = j+"="+params_str[j].replace('\'',' ').trim();
					}else{
						companyId_str += ","+j+"="+params_str[j].replace('\'',' ').trim();
					}
				}
				corpBase.setCompany_ID(companyId_str);
				corpls.add(corpBase);
			}
		}
		return corpls;
	}
	
	
 
	@Override
	protected Map<String, String> getRequestParam(String corpId,RequestInfo requestinfo){
		String params = requestinfo.getParams();
		Map<String,String> corpIdMap = StringUtil.parseMap(corpId, ",", "=");
		Map<String,String>params_map = StringUtil.parseMap(params, ",", "=");
		
		if(BASE_INFO.equals(requestinfo.getModeType())){
			//基本信息如果qylxFlag=2即corpIdMap中key是5 value=2的情况 请求地址为http://gsxt.jxaic.gov.cn/ECPS/qyxxgsAction_initGtjbqk.action
			//其他请求地址为http://gsxt.jxaic.gov.cn/ECPS/qyxxgsAction_initQyjbqk.action
			String[] urls = requestinfo.getRurl().split(",");
			if("2".equals(corpIdMap.get("5"))){
				requestinfo.setRurl(urls[1]);
			}else{
				requestinfo.setRurl(urls[0]);
			}
 
		}
		if(params != null && !"".equals(params)){	
			for (Map.Entry<String, String> entry : params_map.entrySet()){
				String val = entry.getValue();
				String key = entry.getKey();
				if(val.indexOf("#") != -1){
					String corpKey = val.substring(val.indexOf("#")+1, val.length());
					if(corpIdMap.get(corpKey) != null){
						entry.setValue(corpIdMap.get(corpKey));
					}
				}
			} 
		}
		return params_map;
	}
}
