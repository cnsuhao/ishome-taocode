package com.mcookies.qxy.common.OaExamineInformation;
import java.util.List;

import org.isotope.jfp.framework.beans.page.PageVOSupport;
import org.isotope.jfp.framework.support.IDatabaseSupport;


/** OA审批信息表 */
public interface OaExamineInformationDao extends IDatabaseSupport {
	
	List<OaExamineInformationPVO> doSelectPageByCheckTid(PageVOSupport pageModel);

}
