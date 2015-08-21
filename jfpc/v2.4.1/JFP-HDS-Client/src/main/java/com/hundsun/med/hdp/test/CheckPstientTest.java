package com.hundsun.med.hdp.test;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.hundsun.med.access.hao.patient.PatientCheckHAO;

public class CheckPstientTest {

	@Test
	public void test() {
		PatientCheckHAO patientHao= new PatientCheckHAO();
		patientHao.setPatName("王可");
		patientHao.setHosPatCardNo("A000009461");
		patientHao.setHosPatCardType(0);
		patientHao.setPhoneNo("13810113213");
		System.out.println(patientHao.getClass()+"//"+JSON.toJSON(patientHao));
	}

}
