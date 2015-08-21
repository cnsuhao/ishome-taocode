package com.hundsun.med.hdp.test;

import com.hundsun.med.access.hao.reportexam.ReportExamViewHAO;
import com.hundsun.med.framework.mq.redis.MyRedis;

public class ReportExamViewTest {
	
	public static void main(String[] args) {
		ReportExamViewHAO bean = new ReportExamViewHAO();
		bean.setExamId("099958043");
		System.out.println(MyRedis.getStringToRedis(bean));
	}

}
