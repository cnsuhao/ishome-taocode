package org.jfpc.beans.platform.MS1A1;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 企业基本信息*/
@Service
public class MS1A1Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS1A1Service.class);

    public MS1A1Dao getDao(){
        return getMySqlSession().getMapper(MS1A1Dao.class);
    }

}
