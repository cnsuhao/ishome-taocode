package org.ishome.jfp.beans.HospitalCloudAccessRule;
import org.ishome.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 医院云平台对接规则表*/
@Service
public class HospitalCloudAccessRuleService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(HospitalCloudAccessRuleService.class);

    public HospitalCloudAccessRuleDao getDao(){
        return getMySqlSession().getMapper(HospitalCloudAccessRuleDao.class);
    }

}
