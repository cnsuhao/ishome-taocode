package com.hundsun.med.hdp.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.hundsun.med.access.hao.fees.HosFeesListHAO;
import com.hundsun.med.access.mso.PatCardEntity;
import com.hundsun.med.framework.mq.redis.MyRedis;

public class HosFeesTest {

	@Test
	public void test() {
		HosFeesListHAO hosHao=new HosFeesListHAO();
		List<PatCardEntity> card=new ArrayList<PatCardEntity>();
		PatCardEntity patCard=new PatCardEntity();
		patCard.setHosPatCardNo("A000009709");
		patCard.setHosPatCardType(0);
		card.add(patCard);
		hosHao.setCards(card);
		hosHao.setPatName("郭德伶");
		System.out.println(MyRedis.getStringToRedis(hosHao));
	}

}
