package org.jfpc.beans.platform.MS3H1;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 用户留言表*/
@Service
public class MS3H1Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS3H1Service.class);

    public MS3H1Dao getDao(){
        return getMySqlSession().getMapper(MS3H1Dao.class);
    }

}
