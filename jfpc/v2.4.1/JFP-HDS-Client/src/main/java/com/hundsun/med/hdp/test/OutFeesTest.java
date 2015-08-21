package com.hundsun.med.hdp.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hundsun.med.access.hao.fees.OutFeesListHAO;
import com.hundsun.med.access.mso.PatCardEntity;
import com.hundsun.med.framework.mq.redis.MyRedis;

public class OutFeesTest {

	@Test
	public void test() {
		OutFeesListHAO outFees=new OutFeesListHAO();
		List<PatCardEntity> card=new ArrayList<PatCardEntity>();
		PatCardEntity patCard=new PatCardEntity();
		patCard.setHosPatCardNo("A000009709");
		patCard.setHosPatCardType(1);
		outFees.setPatName("郭德伶");
		card.add(patCard);
		outFees.setCards(card);
		System.out.println(MyRedis.getStringToRedis(outFees));
	}

}
