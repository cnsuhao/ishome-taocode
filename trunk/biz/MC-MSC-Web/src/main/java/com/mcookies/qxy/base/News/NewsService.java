package com.mcookies.qxy.base.News;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;

/** 新闻表*/
@Service
public class NewsService extends MyServiceSupport {

    public NewsDao getDao(){
        return getMySqlSession().getMapper(NewsDao.class);
    }

}
