package org.jfpc.beans.platform.MSSCC;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 系统配置信息*/
@Service
public class MSSCCService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MSSCCService.class);

    public MSSCCDao getDao(){
        return getMySqlSession().getMapper(MSSCCDao.class);
    }

}
