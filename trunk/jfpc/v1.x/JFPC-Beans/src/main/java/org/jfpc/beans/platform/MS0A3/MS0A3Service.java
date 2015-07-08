package org.jfpc.beans.platform.MS0A3;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 业务功能定义*/
@Service
public class MS0A3Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS0A3Service.class);

    public MS0A3Dao getDao(){
        return getMySqlSession().getMapper(MS0A3Dao.class);
    }

}
