package com.mcookies.qxy.common.OaRule;

import java.util.List;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** OA规则表 */
@Service
public class OaRuleService extends MyServiceSupport {

	public OaRuleDao getDao() {
		return getMySqlSession().getMapper(OaRuleDao.class);
	}

	public List<OaRuleDBO> doSelectByOaTagsIds(List<Long> oaTagsIds) {
		return getDao().doSelectByOaTagsIds(oaTagsIds);
	}
	
	public OaRuleDBO doSelectNextRule(OaRuleDBO last) {
		return getDao().doSelectNextRule(last);
	}
	
}
