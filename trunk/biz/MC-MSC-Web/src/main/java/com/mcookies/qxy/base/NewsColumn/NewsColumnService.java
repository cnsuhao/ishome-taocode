package com.mcookies.qxy.base.NewsColumn;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 栏目表*/
@Service
public class NewsColumnService extends MyServiceSupport {

    public NewsColumnDao getDao(){
        return getMySqlSession().getMapper(NewsColumnDao.class);
    }

}
