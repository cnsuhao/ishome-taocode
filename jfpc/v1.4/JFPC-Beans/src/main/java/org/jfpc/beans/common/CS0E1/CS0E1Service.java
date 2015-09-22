package org.jfpc.beans.common.CS0E1;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 业务日志*/
@Service
public class CS0E1Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CS0E1Service.class);

    public CS0E1Dao getDao(){
        return getMySqlSession().getMapper(CS0E1Dao.class);
    }

}
