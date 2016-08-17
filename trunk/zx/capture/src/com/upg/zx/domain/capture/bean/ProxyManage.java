package com.upg.zx.domain.capture.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.utils.EmptyHelper;

import com.mchange.v1.lang.Synchronizer;
import com.upg.zx.domain.entity.Proxy;
import com.upg.zx.serice.ProxyService;

public class ProxyManage {
	
	private ICacheService cache;
	
	private ProxyService proxyService;
	
	private final String PROXY_KEY = "_proxy";
	
	private int maxFailSize;
	
	

	public void setMaxFailSize(int maxFailSize) {
		this.maxFailSize = maxFailSize;
	}

	public void setCache(ICacheService cache) {
		this.cache = cache;
	}
	
	
	public void setProxyService(ProxyService proxyService) {
		this.proxyService = proxyService;
	}

	/**
	 * 根据区域码随机获取代理
	 * @param area
	 * @return
	 */
	public synchronized Proxy getProxyByArea(String area){
		List<Proxy> list = null;
		String j_str1 = (String)cache.getObject(area+this.PROXY_KEY);
		if(EmptyHelper.isEmpty(j_str1)){
			list = proxyService.queryAll();
			if(list != null){
				JSONArray j_array = JSONArray.fromObject(list);
				cache.putObject(area+this.PROXY_KEY, j_array.toString(),0,false);
			}
		}else{
			String j_str = (String)cache.getObject(area+this.PROXY_KEY);
			JSONArray j_array = JSONArray.fromObject(j_str);			
			list = (List)JSONArray.toCollection( j_array,Proxy.class);
		}
		if(list != null && list.size() > 0){
			Random random = new Random();
			int i = random.nextInt(list.size());
			return list.get(i);
		}
		return null;
	}
	
	
	/**
	 * 设置失败的代理
	 * 如果代理次数超过指定次数则删除指定代理
	 * @param area
	 * @param proxy
	 */
	public synchronized void setFaildProxy(String area,Proxy proxy){
		String  j_str = (String)cache.getObject(area+this.PROXY_KEY);
		if(EmptyHelper.isNotEmpty(j_str)){
			JSONArray j_array = JSONArray.fromObject(j_str);
			int change_count = -1;
			for(int i = 0 ; i < j_array.size() ; i++){
				Proxy proxy_s = (Proxy)JSONObject.toBean(j_array.getJSONObject(i), Proxy.class);
				if(proxy_s.equals(proxy)){
					change_count = i;
				}
			}
			
			if(change_count > -1){
				int failcount = j_array.getJSONObject(change_count).getInt("failSize");
				if(failcount > this.maxFailSize){
					j_array.remove(change_count);
				}else{
					j_array.getJSONObject(change_count).put("failSize", failcount+1);
				}
				
				if(j_array.size() == 0){
					cache.deleteObject(area+this.PROXY_KEY);
					//cache.putObject(area+this.PROXY_KEY, j_array.toString(),0,false);
				}else{
					cache.putObject(area+this.PROXY_KEY, j_array.toString(),0,false);
				}
			}
		}
	}
}
