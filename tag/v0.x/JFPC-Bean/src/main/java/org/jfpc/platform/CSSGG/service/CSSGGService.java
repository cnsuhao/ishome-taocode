package org.jfpc.platform.CSSGG.service;
import org.jfpc.base.support.MyServiceSupport;
import org.jfpc.platform.CSSGG.dao.CSSGGDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 系统动态*/
@Service
public class CSSGGService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CSSGGService.class);

    public CSSGGDao getDao(){
        return getMySqlSession().getMapper(CSSGGDao.class);
    }

}
