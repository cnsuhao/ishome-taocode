package com.mcookies.qxy.common.OaTags;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** OA标签表 */
@Service
public class OaTagsService extends MyServiceSupport {

	public OaTagsDao getDao() {
		return getMySqlSession().getMapper(OaTagsDao.class);
	}

}
