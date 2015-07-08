package org.jfpc.beans.platform.MS0AA;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 动态页面定义*/
@Service
public class MS0AAService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS0AAService.class);

    public MS0AADao getDao(){
        return getMySqlSession().getMapper(MS0AADao.class);
    }

}
