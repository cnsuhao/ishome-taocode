package org.jfpc.beans.platform.MS0A2;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 机能模块定义*/
@Service
public class MS0A2Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS0A2Service.class);

    public MS0A2Dao getDao(){
        return getMySqlSession().getMapper(MS0A2Dao.class);
    }

}
