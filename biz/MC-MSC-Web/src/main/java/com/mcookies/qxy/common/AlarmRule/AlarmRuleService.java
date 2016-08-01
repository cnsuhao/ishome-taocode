package com.mcookies.qxy.common.AlarmRule;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 报警规则表*/
@Service
public class AlarmRuleService extends MyServiceSupport {

    public AlarmRuleDao getDao(){
        return getMySqlSession().getMapper(AlarmRuleDao.class);
    }

}
