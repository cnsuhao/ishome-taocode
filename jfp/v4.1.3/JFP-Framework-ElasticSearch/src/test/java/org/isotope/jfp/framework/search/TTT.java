package org.isotope.jfp.framework.search;

import org.isotope.jfp.framework.utils.HttpServiceHelper;

public class TTT {

	final static String TASK_KEY = "COMPANY:CAP";
//	final static String TASK_KEY = "JOB_LIST11";
	public static void main(String[] args) throws Exception {
//		Jedis jedis = new Jedis("172.16.2.201",6379);
//		jedis.auth("123456");
//		jedis.select(3);
//		System.out.println(jedis.rpush(TASK_KEY, "87ee92b4-e1af-41ae-b485-4d4b880d2719;330000;中新力合股份有限公司"));
//		System.out.println(jedis.rpush(TASK_KEY, "87ee92b4-e1af-41ae-b485-4d4b880d2719;330000;中新力合股份有限公司"));
//		System.out.println(jedis.rpush(TASK_KEY, "87ee92b4-e1af-41ae-b485-4d4b880d2719;330000;中新力合股份有限公司"));
//		System.out.println(jedis.rpush(TASK_KEY, "87ee92b4-e1af-41ae-b485-4d4b880d2719;330000;中新力合股份有限公司"));
//		System.out.println(jedis.rpush(TASK_KEY, "87ee92b4-e1af-41ae-b485-4d4b880d2719;330000;中新力合股份有限公司"));
//		System.out.println(jedis.rpop(TASK_KEY));
//		 jedis = new Jedis("127.0.0.1",6379);
//		 
//		 jedis.select(3);
//			System.out.println(jedis.rpush(TASK_KEY, "aa222aaa"));
		
//		
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
//		//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:59:59");
//		Calendar calendar = Calendar.getInstance();
//		calendar.add(Calendar.HOUR, -1);
//		System.out.println(
//		
//				format.format(calendar.getTime())
//				);
//		
		System.out.println(
		HttpServiceHelper.doHttpGET("http://127.0.0.1:8080/Zheng/corp/queryCompanyNew?paramValue=瓶盖&encrypt_key=AAMKdxYTFFNcRFRSBhBSQQ==&life=&UserID=1&capital=&userLog=355653050424941&paramType=0000100&version=3.3.1")
		);
	}

}
