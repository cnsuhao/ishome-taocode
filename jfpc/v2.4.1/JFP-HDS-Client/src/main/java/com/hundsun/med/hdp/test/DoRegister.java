package com.hundsun.med.hdp.test;

import org.junit.Test;

import com.hundsun.med.access.hao.register.OrderRegisterHAO;
import com.hundsun.med.framework.mq.redis.MyRedis;

public class DoRegister {

	@Test
	public void test() {
		OrderRegisterHAO param =new OrderRegisterHAO();
		param.setHosPatCardNo("1061725750");
		param.setAccessSchId("2015-05-27;上午;眼科&amp;陈萍");
		param.setAccessSchTime("2015-05-27");
		param.setCost(Double.parseDouble("8"));
		param.setTime("08-09");
		System.out.println(MyRedis.getStringToRedis(param));
		}

}
