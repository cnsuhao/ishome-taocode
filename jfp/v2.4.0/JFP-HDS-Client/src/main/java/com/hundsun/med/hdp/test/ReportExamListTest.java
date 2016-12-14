package com.hundsun.med.hdp.test;

import com.hundsun.med.access.hao.check.CheckSheetListHAO;
import com.hundsun.med.access.hao.check.CheckSheetViewHAO;
import com.hundsun.med.access.hao.examine.ExamineSheetListHAO;
import com.hundsun.med.access.hao.examine.ExamineSheetViewHAO;
import com.hundsun.med.access.hao.reportexam.ReportExamListHAO;
import com.hundsun.med.framework.mq.redis.MyRedis;

public class ReportExamListTest {
	
	public static void main(String[] args) {
		ReportExamListHAO examListHao = new ReportExamListHAO();
		examListHao.setExamId("099958043");
		examListHao.setStartTime("2015-04-20");
		examListHao.setEndTime("2015-04-25");
		System.out.println(MyRedis.getStringToRedis(examListHao));
	}

}
