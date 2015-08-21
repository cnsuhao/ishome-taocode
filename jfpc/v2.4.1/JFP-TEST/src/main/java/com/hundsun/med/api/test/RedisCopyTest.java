package com.hundsun.med.api.test;

import redis.clients.jedis.Jedis;

import com.hundsun.med.framework.job.HospitalJobKeyService;
import com.hundsun.med.framework.utils.JedisUtil;

public class RedisCopyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("========>>>>>>>");
		
		System.out.println(HospitalJobKeyService.getBizDataKeyName("8a22b37c4c7a047a014cc6b6b23b0765", "OrderCheck"));
		
//		Jedis jd = JedisUtil.getJedis("121.43.186.82", 6379, "hundsun-med-redis");
		
//		String oldKey = "8a22b37c4d7b08a0014d8ecfb4830089:Department:data:911";
//		String newKey = "HSMED0089:Department:data:911";
//		JedisUtil.copy(jd,oldKey, newKey, true);
//		 oldKey = "8a22b37c4d7b08a0014d8ecfb4830089:Doctor:data:911";
//		 newKey = "HSMED0089:Doctor:data:911";
//		JedisUtil.copy(jd,oldKey, newKey, true);
//		 oldKey = "8a22b37c4d7b08a0014d8ecfb4830089:Scheduling:data:911";
//		 newKey = "HSMED0089:Scheduling:data:911";
//		JedisUtil.copy(jd,oldKey, newKey, true);
		
	
//		jd.del("8a22b37c4d7b08a0014d8ecfb4830089:Department:data:911");
//		jd.del("8a22b37c4d7b08a0014d8ecfb4830089:Doctor:data:911");
//		jd.del("8a22b37c4d7b08a0014d8ecfb4830089:Scheduling:data:911");
//		jd.del("8a22b37c4d7b08a0014d8ecfb4830089:JOB:sync:911");
//		
//		String oldKey = "HSMED0089:Department:data:911";
//		String newKey = "8a22b37c4d7b08a0014d8ecfb4830089:Department:data:911";
//		JedisUtil.copy(jd,oldKey, newKey, true);
//		
//		 oldKey = "HSMED0089:Doctor:data:911";
//		 newKey = "8a22b37c4d7b08a0014d8ecfb4830089:Doctor:data:911";
//		JedisUtil.copy(jd,oldKey, newKey, true);
//		
//		 oldKey = "HSMED0089:Scheduling:data:911";
//		 newKey = "8a22b37c4d7b08a0014d8ecfb4830089:Scheduling:data:911";
//		JedisUtil.copy(jd,oldKey, newKey, true);
		
//		String oldKey = "8a22b37c4c7a047a014cc6b6b23b0765:Scheduling:result:911";
//		String newKey = "8a22b37c4c7a047a014cc6b6b23b0765:Scheduling:data:911";
//		JedisUtil.copy(jd,oldKey, newKey, true);

//		Jedis oldJedis = JedisUtil.getJedis("121.43.186.82", 6379, "hundsun-med-redis");
//		Jedis newJedis = JedisUtil.getJedis("127.0.0.1", 6379, "hundsun-med-redis");
//		String oldKey = "8a22b37c4c7a047a014cc6b6b23b0765:Scheduling:result:911";
//		String newKey = "8a22b37c4c7a047a014cc6b6b23b0765:Scheduling:data:911";
//		JedisUtil.copy(oldJedis,oldKey,newJedis, newKey, true);
		
		System.out.println("<<<<<<<========");
	}

}
