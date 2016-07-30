package com.mcookies.qxy.base.OaExamineResult;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** OA审批结果表*/
@Service
public class OaExamineResultService extends MyServiceSupport {

    public OaExamineResultDao getDao(){
        return getMySqlSession().getMapper(OaExamineResultDao.class);
    }

}
