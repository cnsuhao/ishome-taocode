package org.jfpc.beans.common.MS0M7;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 问卷调查*/
@Service
public class MS0M7Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS0M7Service.class);

    public MS0M7Dao getDao(){
        return getMySqlSession().getMapper(MS0M7Dao.class);
    }

}
