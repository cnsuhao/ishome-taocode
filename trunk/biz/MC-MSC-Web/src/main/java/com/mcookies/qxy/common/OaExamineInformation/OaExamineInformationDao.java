package com.mcookies.qxy.common.OaExamineInformation;
import java.util.List;

import org.isotope.jfp.framework.support.IDatabaseSupport;

import com.mcookies.qxy.common.OaExamineResult.OaExamineResultDBO;

/** OA审批信息表 */
public interface OaExamineInformationDao extends IDatabaseSupport {

	List<OaExamineInformationPVO> findByCheckTid(OaExamineResultDBO res);

}
