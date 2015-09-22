package org.jfpc.beans.common.MS3H2;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 留言回复表*/
@Service
public class MS3H2Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS3H2Service.class);

    public MS3H2Dao getDao(){
        return getMySqlSession().getMapper(MS3H2Dao.class);
    }

}
