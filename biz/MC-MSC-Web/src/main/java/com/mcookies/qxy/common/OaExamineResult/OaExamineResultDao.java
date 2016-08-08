package com.mcookies.qxy.common.OaExamineResult;

import java.util.List;

import org.isotope.jfp.framework.support.IDatabaseSupport;

import com.mcookies.qxy.common.OaExamineInformation.OaExamineInformationPVO;

/** OA审批结果表 */
public interface OaExamineResultDao extends IDatabaseSupport {
	List<OaExamineResultDBO> doSelectUnChecked(OaExamineResultDBO oaExamineResultDBO);
	void doUpdateSetStatus(OaExamineResultDBO result);
	List<OaExamineResultPVO> findByApprovalInformationId(OaExamineInformationPVO info);
}
