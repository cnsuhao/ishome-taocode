package com.mcookies.qxy.common.OaExamineResult;

import java.util.List;

import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.springframework.stereotype.Service;

import com.mcookies.qxy.common.OaExamineInformation.OaExamineInformationPVO;

/** OA审批结果表 */
@Service
public class OaExamineResultService extends MyServiceSupport {

	public OaExamineResultDao getDao() {
		return getMySqlSession().getMapper(OaExamineResultDao.class);
	}
	
	public List<OaExamineResultDBO> doSelectUnChecked(OaExamineResultDBO oaExamineResultDBO) {
		return getDao().doSelectUnChecked(oaExamineResultDBO);
	}
	
	public void doUpdateSetStatus(OaExamineResultDBO result) {
		getDao().doUpdateSetStatus(result);
	}

	public List<OaExamineResultPVO> findByApprovalInformationId(OaExamineInformationPVO info) {
		return getDao().findByApprovalInformationId(info);
	}
	
	public OaExamineResultPVO doSelectMyauditAndInfo(OaExamineResultDBO oaExamineResultDBO) {
		return getDao().doSelectMyauditAndInfo(oaExamineResultDBO);
	}

}
