package com.mcookies.qxy.common.OaTags;

import java.util.List;

import org.isotope.jfp.framework.support.IDatabaseSupport;

/** OA标签表 */
public interface OaTagsDao extends IDatabaseSupport {
	List<OaTagsDBO> doSelectUsedByOaTagsIds(List<Long> oaTagsIds);
	List<OaTagsDBO> doSelectUsedByOaRuleIds(List<Long> oaRuleIds);
	List<OaTagsPVO> findBySid(OaTagsPVO oaTag);
}
