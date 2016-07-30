package com.mcookies.qxy.base.UParent;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 家长表*/
@Service
public class UParentService extends MyServiceSupport {
    
    public UParentDao getDao(){
        return getMySqlSession().getMapper(UParentDao.class);
    }

}
