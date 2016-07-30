package com.mcookies.qxy.base.OaExamineInformation;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** OA审批信息表*/
@Service
public class OaExamineInformationService extends MyServiceSupport {

    public OaExamineInformationDao getDao(){
        return getMySqlSession().getMapper(OaExamineInformationDao.class);
    }

}
