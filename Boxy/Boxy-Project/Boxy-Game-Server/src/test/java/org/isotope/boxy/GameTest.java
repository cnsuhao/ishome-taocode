package org.isotope.boxy;

import java.util.HashMap;
import java.util.Map;

import org.isotope.jfp.framework.utils.HttpServiceHelper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class GameTest {
	
	public static void main(String[] args) throws Exception {
		String token = "117226335444553662771880299208217326335344453462171";
		String serviceURL = "http://127.0.0.1:8080/12345678901234567/12345678/99887766/1";//+token;
		Map<String, String> param = new HashMap<String, String>();
		JSONObject paramData = new JSONObject();
		{
			//数据读取测试
			paramData.put("type", "CHS");
		}
		{
			//角色创建用户第一次登录
			
		}
		
		
		

		param.put("paramValue", paramData.toJSONString());
		System.out.println(HttpServiceHelper.doHttpPOST(serviceURL, param));

	}

}
