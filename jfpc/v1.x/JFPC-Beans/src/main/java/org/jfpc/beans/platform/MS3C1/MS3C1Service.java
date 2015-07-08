package org.jfpc.beans.platform.MS3C1;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 短信网关信息*/
@Service
public class MS3C1Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS3C1Service.class);

    public MS3C1Dao getDao(){
        return getMySqlSession().getMapper(MS3C1Dao.class);
    }

}
