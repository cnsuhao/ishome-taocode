package com.upg.biz.mcws;

import redis.clients.jedis.Jedis;

public class TestRemoteService {

	public static void main(String[] args) throws Exception {
		Jedis dis = new Jedis("172.16.2.200");
		dis.auth("123456");
		dis.select(3);
		System.out.println(dis.hget("aaa", "aaa"));

		// Map<String, String> param = new HashMap<String, String>();
		// param.put("encrypt_key", "encrypt_key");
		// param.put("UserID", "UserID");
		// param.put("maxNum", "5");
		// param.put("corp_name", "广茂物");
		// param.put("clientType", "");
		// String value =
		// HttpServiceHelper.doHttpPOST("http://127.0.0.1:8888/CB", param);
		// System.out.println(value);
	}

}
