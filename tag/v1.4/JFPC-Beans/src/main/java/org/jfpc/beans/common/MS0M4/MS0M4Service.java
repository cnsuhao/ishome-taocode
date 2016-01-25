package org.jfpc.beans.common.MS0M4;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 文章信息*/
@Service
public class MS0M4Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS0M4Service.class);

    public MS0M4Dao getDao(){
        return getMySqlSession().getMapper(MS0M4Dao.class);
    }

}
