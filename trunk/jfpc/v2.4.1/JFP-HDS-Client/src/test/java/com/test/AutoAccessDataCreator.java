package com.test;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.hundsun.med.access.hao.user.CreatPhoneUserHAO;

public class AutoAccessDataCreator {

	@Test
	public void test() {
		System.out.println("============================================读取手机患者====================================================");
		CreatPhoneUserHAO caHao = new CreatPhoneUserHAO();
		caHao.setMobilePhone("15158811746");
		caHao.setAddress("asda");
		
		System.out.println(CreatPhoneUserHAO.class+"//"+JSON.toJSON(caHao));
	}

}
