package com.mcookies.qxy.common.OaExamineInformation;

import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

/** OA审批信息表 */
@Service
public class OaExamineInformationService extends MyServiceSupport {

	public OaExamineInformationDao getDao() {
		return getMySqlSession().getMapper(OaExamineInformationDao.class);
	}


	public PageVOSupport doSelectPageByCheckTid(PageVOSupport pageModel) {
		pageModel.setPageListData(getDao().doSelectPageByCheckTid(pageModel));
		return pageModel;
	}

}
