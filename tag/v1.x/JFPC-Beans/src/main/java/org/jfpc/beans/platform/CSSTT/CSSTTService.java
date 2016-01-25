package org.jfpc.beans.platform.CSSTT;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** TOKEN存放表*/
@Service
public class CSSTTService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CSSTTService.class);

    public CSSTTDao getDao(){
        return getMySqlSession().getMapper(CSSTTDao.class);
    }

}
