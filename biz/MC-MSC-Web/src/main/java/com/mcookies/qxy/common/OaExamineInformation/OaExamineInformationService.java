package com.mcookies.qxy.common.OaExamineInformation;
import java.util.List;

import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

import com.mcookies.qxy.common.OaExamineResult.OaExamineResultDBO;

/** OA审批信息表 */
@Service
public class OaExamineInformationService extends MyServiceSupport {

	public OaExamineInformationDao getDao() {
		return getMySqlSession().getMapper(OaExamineInformationDao.class);
	}

	public List<OaExamineInformationPVO> findByCheckTid(OaExamineResultDBO res) {
		return getDao().findByCheckTid(res);
	}

}
