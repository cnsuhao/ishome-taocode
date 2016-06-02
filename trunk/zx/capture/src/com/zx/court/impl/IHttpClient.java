package com.zx.court.impl;

import com.zx.court.pojo.CookiePojo;
import com.zx.court.pojo.HttpClientParam;

public interface IHttpClient {
	public CookiePojo gethtmlByGet(String encode,String p_url, String p_referer,String host,String Proxy,int port,String strCookie,HttpClientParam param);
	public CookiePojo gethtmlByPost(String encode,String p_url, String p_referer, String host,String Proxy,int port,String strCookie,String type,String idx);
	public CookiePojo gethtmlByRedirect(String encode,String p_url, String p_referer,String host,String Proxy,int port,String strCookie,HttpClientParam param);
	
	
}
