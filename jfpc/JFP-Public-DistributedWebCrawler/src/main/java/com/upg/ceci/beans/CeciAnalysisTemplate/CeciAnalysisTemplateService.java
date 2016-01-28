package com.upg.ceci.beans.CeciAnalysisTemplate;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 网页解析模版*/
@Service("CeciAnalysisTemplateService")
public class CeciAnalysisTemplateService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CeciAnalysisTemplateService.class);

    public CeciAnalysisTemplateDao getDao(){
        return getMySqlSession().getMapper(CeciAnalysisTemplateDao.class);
    }

}
