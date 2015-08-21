package com.hundsun.med.hdp.test;

import org.junit.Test;

import com.hundsun.med.access.hao.register.TimeSlotRemainQueryHAO;
import com.hundsun.med.framework.mq.redis.MyRedis;

public class Remain {

	@Test
	public void test() {
		TimeSlotRemainQueryHAO param=new TimeSlotRemainQueryHAO();
		param.setAccessSchId("2015-05-27;上午;眼科&amp;陈萍");
		System.out.println(MyRedis.getStringToRedis(param));
		
	}

}
