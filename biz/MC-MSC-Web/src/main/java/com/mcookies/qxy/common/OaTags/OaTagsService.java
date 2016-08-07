package com.mcookies.qxy.common.OaTags;

import java.util.List;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** OA标签表 */
@Service
public class OaTagsService extends MyServiceSupport {

	public OaTagsDao getDao() {
		return getMySqlSession().getMapper(OaTagsDao.class);
	}
	
	public List<OaTagsDBO> doSelectUsedByOaTagsIds(List<Long> oaTagsIds) {
		return getDao().doSelectUsedByOaTagsIds(oaTagsIds);
	}
	
	public List<OaTagsDBO> doSelectUsedByOaRuleIds(List<Long> oaRuleIds) {
		return getDao().doSelectUsedByOaRuleIds(oaRuleIds);
	}

}
