package org.jfpc.beans.platform.CS1B2;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 企业人员基本信息*/
@Service
public class CS1B2Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CS1B2Service.class);

    public CS1B2Dao getDao(){
        return getMySqlSession().getMapper(CS1B2Dao.class);
    }

}
