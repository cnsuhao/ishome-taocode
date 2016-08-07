package com.mcookies.qxy.common.OaExamineResult;

import java.util.List;

import org.isotope.jfp.framework.support.IDatabaseSupport;

/** OA审批结果表 */
public interface OaExamineResultDao extends IDatabaseSupport {
	List<OaExamineResultDBO> doSelectUnChecked(OaExamineResultDBO oaExamineResultDBO);
	void doUpdateSetStatus(OaExamineResultDBO result);
}
