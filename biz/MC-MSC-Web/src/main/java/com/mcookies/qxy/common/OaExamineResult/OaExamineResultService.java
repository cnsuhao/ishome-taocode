package com.mcookies.qxy.common.OaExamineResult;

import java.util.List;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

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

}
