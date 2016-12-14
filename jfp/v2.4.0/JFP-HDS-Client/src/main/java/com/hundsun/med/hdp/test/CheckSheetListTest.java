package com.hundsun.med.hdp.test;

import com.alibaba.fastjson.JSON;
import com.hundsun.med.access.hao.check.CheckSheetListHAO;
import com.hundsun.med.access.hao.check.CheckSheetViewHAO;
import com.hundsun.med.access.hao.examine.ExamineSheetListHAO;
import com.hundsun.med.access.hao.examine.ExamineSheetViewHAO;
import com.hundsun.med.access.hao.reportexam.ReportExamListHAO;
import com.hundsun.med.access.hao.reportexam.ReportExamViewHAO;
import com.hundsun.med.framework.mq.redis.MyRedis;

public class CheckSheetListTest {
	
	public static void main(String[] args) {
		CheckSheetListHAO checkSheetListHAO = new CheckSheetListHAO();
		checkSheetListHAO.setHosPatCardNo("A000968614");
//		checkSheetListHAO.setCardNo("A000968614");
		checkSheetListHAO.setStartTime("2014-04-26");
		checkSheetListHAO.setEndTime("2015-04-30");
//		System.out.println(MyRedis.getStringToRedis(checkSheetListHAO));
		
		CheckSheetViewHAO checkSheetViewHAO = new CheckSheetViewHAO();
		checkSheetViewHAO.setSheetId("1861599");
//		System.out.println(MyRedis.getStringToRedis(checkSheetViewHAO));
		
		ExamineSheetListHAO examineSheetListHAO = new ExamineSheetListHAO();
		examineSheetListHAO.setHosPatCardNo("1029834480");
		examineSheetListHAO.setStartTime("2015-03-12");
		examineSheetListHAO.setEndTime("2015-05-12");
//		System.out.println(MyRedis.getStringToRedis(examineSheetListHAO));
		
		ExamineSheetViewHAO examineSheetViewHAO = new ExamineSheetViewHAO();
		examineSheetViewHAO.setSheetId("91212190950");
//		System.out.println(MyRedis.getStringToRedis(examineSheetViewHAO));
		
		ReportExamViewHAO examViewHAO = new ReportExamViewHAO();
		examViewHAO.setExamId("099958043");
//		System.out.println(MyRedis.getStringToRedis(examViewHAO));
		
		ReportExamListHAO examListHAO = new ReportExamListHAO();
		examListHAO.setExamId("099958043");
		System.out.println(MyRedis.getStringToRedis(examListHAO));
	}

}
