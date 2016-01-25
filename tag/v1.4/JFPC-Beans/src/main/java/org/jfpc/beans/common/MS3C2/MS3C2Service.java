package org.jfpc.beans.common.MS3C2;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 发送短信信息*/
@Service
public class MS3C2Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS3C2Service.class);

    public MS3C2Dao getDao(){
        return getMySqlSession().getMapper(MS3C2Dao.class);
    }

}
