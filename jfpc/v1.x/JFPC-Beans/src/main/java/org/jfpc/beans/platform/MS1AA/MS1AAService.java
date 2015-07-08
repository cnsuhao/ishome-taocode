package org.jfpc.beans.platform.MS1AA;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 企业扩展信息*/
@Service
public class MS1AAService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS1AAService.class);

    public MS1AADao getDao(){
        return getMySqlSession().getMapper(MS1AADao.class);
    }

}
