package org.jfpc.beans.platform.MSSRR;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 角色关联表*/
@Service
public class MSSRRService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MSSRRService.class);

    public MSSRRDao getDao(){
        return getMySqlSession().getMapper(MSSRRDao.class);
    }

}
