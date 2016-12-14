package org.jfpc.beans.common.MMMSS;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 短信与邮件模版*/
@Service
public class MMMSSService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MMMSSService.class);

    public MMMSSDao getDao(){
        return getMySqlSession().getMapper(MMMSSDao.class);
    }

}
