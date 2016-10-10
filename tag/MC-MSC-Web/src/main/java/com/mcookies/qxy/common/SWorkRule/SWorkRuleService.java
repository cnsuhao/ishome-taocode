package com.mcookies.qxy.common.SWorkRule;

import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 作息时间规则表 */
@Service
public class SWorkRuleService extends MyServiceSupport {

	public SWorkRuleDao getDao() {
		return getMySqlSession().getMapper(SWorkRuleDao.class);
	}

}
