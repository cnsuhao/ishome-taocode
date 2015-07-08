package org.jfpc.beans.platform.MS0A4;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 页面定义*/
@Service
public class MS0A4Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS0A4Service.class);

    public MS0A4Dao getDao(){
        return getMySqlSession().getMapper(MS0A4Dao.class);
    }

}
