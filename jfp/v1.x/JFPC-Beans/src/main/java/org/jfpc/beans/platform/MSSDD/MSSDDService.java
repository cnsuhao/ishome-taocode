package org.jfpc.beans.platform.MSSDD;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 资源路径表*/
@Service
public class MSSDDService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MSSDDService.class);

    public MSSDDDao getDao(){
        return getMySqlSession().getMapper(MSSDDDao.class);
    }

}
