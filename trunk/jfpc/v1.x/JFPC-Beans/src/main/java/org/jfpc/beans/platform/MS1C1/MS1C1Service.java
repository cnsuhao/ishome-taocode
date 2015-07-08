package org.jfpc.beans.platform.MS1C1;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 登录用户临时授权*/
@Service
public class MS1C1Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS1C1Service.class);

    public MS1C1Dao getDao(){
        return getMySqlSession().getMapper(MS1C1Dao.class);
    }

}
