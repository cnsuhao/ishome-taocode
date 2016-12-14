package org.jfpc.beans.common.MS1C4;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 企业用户所属角色*/
@Service
public class MS1C4Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS1C4Service.class);

    public MS1C4Dao getDao(){
        return getMySqlSession().getMapper(MS1C4Dao.class);
    }

}
