package org.jfpc.beans.platform.MS0A5;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 页面功能按钮定义*/
@Service
public class MS0A5Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS0A5Service.class);

    public MS0A5Dao getDao(){
        return getMySqlSession().getMapper(MS0A5Dao.class);
    }

}
