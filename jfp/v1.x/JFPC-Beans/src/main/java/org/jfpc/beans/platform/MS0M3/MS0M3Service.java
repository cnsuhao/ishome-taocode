package org.jfpc.beans.platform.MS0M3;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 帮助信息意见评价*/
@Service
public class MS0M3Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS0M3Service.class);

    public MS0M3Dao getDao(){
        return getMySqlSession().getMapper(MS0M3Dao.class);
    }

}
