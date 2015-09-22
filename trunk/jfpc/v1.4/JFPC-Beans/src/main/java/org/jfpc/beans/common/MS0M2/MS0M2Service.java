package org.jfpc.beans.common.MS0M2;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 帮助信息表*/
@Service
public class MS0M2Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS0M2Service.class);

    public MS0M2Dao getDao(){
        return getMySqlSession().getMapper(MS0M2Dao.class);
    }

}
