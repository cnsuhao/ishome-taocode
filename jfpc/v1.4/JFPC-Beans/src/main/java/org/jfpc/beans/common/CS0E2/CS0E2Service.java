package org.jfpc.beans.common.CS0E2;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 警告异常日志*/
@Service
public class CS0E2Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CS0E2Service.class);

    public CS0E2Dao getDao(){
        return getMySqlSession().getMapper(CS0E2Dao.class);
    }

}
