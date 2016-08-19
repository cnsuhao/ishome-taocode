package com.upg.zx.capture.bean;

import java.util.HashMap;
import java.util.Map;

public class Contain {
	public static Map<String,String>shandDongMap = new HashMap<String,String>();
	
	//public static String ARTIFICIAL_SERVICE_HOST = "http://10.10.168.15:8080";
	//征信服务器地址
	public static String ZX_SERVICE_HOST = "https://www.wzyrz.cn";
	
	static{
		
		shandDongMap.put("1", "1");
		shandDongMap.put("2", "2");
		shandDongMap.put("3", "3");
		shandDongMap.put("4", "4");
		shandDongMap.put("5", "5");
		shandDongMap.put("6", "6");
		shandDongMap.put("7", "7");
		shandDongMap.put("8", "8");
		shandDongMap.put("9", "9");
		shandDongMap.put("0", "0");
		
		
		shandDongMap.put("一", "1");
		shandDongMap.put("二", "2");
		shandDongMap.put("三", "3");
		shandDongMap.put("四", "4");
		shandDongMap.put("五", "5");
		shandDongMap.put("六", "6");
		shandDongMap.put("七", "7");
		shandDongMap.put("八", "8");
		shandDongMap.put("九", "9");
		shandDongMap.put("零", "0");
		shandDongMap.put("o", "0");
		shandDongMap.put("O", "0");
		
		shandDongMap.put("壹", "1");
		shandDongMap.put("贰", "2");
		shandDongMap.put("叁", "3");
		shandDongMap.put("肆", "4");
		shandDongMap.put("伍", "5");
		shandDongMap.put("陆", "6");
		shandDongMap.put("柒", "7");
		shandDongMap.put("捌", "8");
		shandDongMap.put("玖", "9");
		
		shandDongMap.put("加", "+");
		shandDongMap.put("加上", "+");
		shandDongMap.put("+", "+");
		
		shandDongMap.put("减", "-");
		shandDongMap.put("减去", "-");
		shandDongMap.put("-", "-");
		
		shandDongMap.put("乘", "*");
		shandDongMap.put("乘以", "*");
		shandDongMap.put("*", "*");
		shandDongMap.put("x", "*");
		shandDongMap.put("X", "*");
		
		shandDongMap.put("除", "/");
		shandDongMap.put("除以", "/");
		shandDongMap.put("/", "/");
			
	}
	
	//需要人工打码的区域
	public static String[] NO_CAPTURE_AREA = new String[]{"150000","330000","440000","460000"};
	
	//需要人工获取列表以及详细信息区域
	public static String[] NO_CAPTURE_INFOAREA = new String[]{"110000","220000","520000"};
	
	//对应每个省份验证码自动识别最多失败次数
	public static Map<String,Integer> MAX_RETRY_FORYZM = new HashMap<String,Integer>();
	

	
}
