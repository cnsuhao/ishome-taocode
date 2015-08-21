package com.hundsun.med.hdp.test;

import org.junit.Test;

import com.hundsun.med.access.hao.register.CancelRegisterHAO;
import com.hundsun.med.framework.mq.redis.MyRedis;

public class DoCancelRegister {

	@Test
	public void test() {
		CancelRegisterHAO param=new CancelRegisterHAO();
		param.setRegId("1061725750");
		param.setExceptDate("2015-05-27");
		param.setAccessRegId("2015-05-27;上午;眼科&amp;陈萍");
		param.setExceptTime("08-09");
		System.out.println(MyRedis.getStringToRedis(param));
	}

}
