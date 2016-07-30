package com.mcookies.qxy.base.AlarmRule;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 报警规则表*/
@Service
public class AlarmRuleService extends MyServiceSupport {

    public AlarmRuleDao getDao(){
        return getMySqlSession().getMapper(AlarmRuleDao.class);
    }

}
