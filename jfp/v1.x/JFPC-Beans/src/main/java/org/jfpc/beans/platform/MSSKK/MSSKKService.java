package org.jfpc.beans.platform.MSSKK;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 主键关联表*/
@Service
public class MSSKKService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MSSKKService.class);

    public MSSKKDao getDao(){
        return getMySqlSession().getMapper(MSSKKDao.class);
    }

}
