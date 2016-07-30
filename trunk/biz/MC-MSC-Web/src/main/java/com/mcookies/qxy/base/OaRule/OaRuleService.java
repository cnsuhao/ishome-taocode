package com.mcookies.qxy.base.OaRule;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** OA规则表*/
@Service
public class OaRuleService extends MyServiceSupport {

    public OaRuleDao getDao(){
        return getMySqlSession().getMapper(OaRuleDao.class);
    }

}
