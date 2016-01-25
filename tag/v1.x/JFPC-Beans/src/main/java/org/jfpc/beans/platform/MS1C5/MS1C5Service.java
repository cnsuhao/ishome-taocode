package org.jfpc.beans.platform.MS1C5;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 企业组织机构角色定义*/
@Service
public class MS1C5Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS1C5Service.class);

    public MS1C5Dao getDao(){
        return getMySqlSession().getMapper(MS1C5Dao.class);
    }

}
