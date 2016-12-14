package com.hundsun.med.api.test;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.hundsun.med.access.hao.sync.DepartmentHAO;
import com.hundsun.med.access.hao.sync.SchedulingHAO;
import com.hundsun.med.framework.constants.ISJobConstants;
import com.hundsun.med.framework.utils.EmptyHelper;
import com.hundsun.med.framework.utils.JedisUtil;

public class Test implements ISJobConstants {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DepartmentHAO hao = new DepartmentHAO();
		hao.setType(JOB_FLAG_WAITING);
		System.out.print(getStringToRedis(hao));
		
		Jedis jd = JedisUtil.getJedis("127.0.0.1", 6379, "hundsun-med-redis");
		String newKey = "8a212078481b3c9801483506d2910208:Department:biz:911";
		jd.rpush(newKey, getStringToRedis(hao));
	}
	/**
	 * 实例化对象
	 * @param value
	 * @return
	 */
	public static String getStringToRedis(Object value){
		if(EmptyHelper.isEmpty(value))
			return null;
		if(value instanceof String)
			return (String)value;
		return value.getClass().getName() + "//" + JSON.toJSON(value);
	}
}
