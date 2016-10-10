package com.mcookies.qxy.common.OaRule;

import java.util.List;

import org.isotope.jfp.framework.support.IDatabaseSupport;

/** OA规则表 */
public interface OaRuleDao extends IDatabaseSupport {
	List<OaRuleDBO> doSelectByOaTagsIds(List<Long> oaTagsIds);
	OaRuleDBO doSelectNextRule(OaRuleDBO last);
}
