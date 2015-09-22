package org.jfpc.beans.common.MS2A1;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 系统角色定义*/
@Service
public class MS2A1Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS2A1Service.class);

    public MS2A1Dao getDao(){
        return getMySqlSession().getMapper(MS2A1Dao.class);
    }

}
