package org.jfpc.beans.platform.MS1C2;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 企业组织机构定义*/
@Service
public class MS1C2Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS1C2Service.class);

    public MS1C2Dao getDao(){
        return getMySqlSession().getMapper(MS1C2Dao.class);
    }

}
