package org.jfpc.beans.platform.MS0M1;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 数据字典管理*/
@Service
public class MS0M1Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS0M1Service.class);

    public MS0M1Dao getDao(){
        return getMySqlSession().getMapper(MS0M1Dao.class);
    }

}
