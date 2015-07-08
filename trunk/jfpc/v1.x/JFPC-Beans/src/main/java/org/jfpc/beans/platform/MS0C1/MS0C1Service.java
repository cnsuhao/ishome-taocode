package org.jfpc.beans.platform.MS0C1;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 系统公告*/
@Service
public class MS0C1Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS0C1Service.class);

    public MS0C1Dao getDao(){
        return getMySqlSession().getMapper(MS0C1Dao.class);
    }

}
