package org.jfpc.beans.common.MSSUU;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 用户基本信息*/
@Service
public class MSSUUService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MSSUUService.class);

    public MSSUUDao getDao(){
        return getMySqlSession().getMapper(MSSUUDao.class);
    }

}
