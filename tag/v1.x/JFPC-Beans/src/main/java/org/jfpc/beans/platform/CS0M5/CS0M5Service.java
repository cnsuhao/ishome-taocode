package org.jfpc.beans.platform.CS0M5;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 文章评价*/
@Service
public class CS0M5Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CS0M5Service.class);

    public CS0M5Dao getDao(){
        return getMySqlSession().getMapper(CS0M5Dao.class);
    }

}
