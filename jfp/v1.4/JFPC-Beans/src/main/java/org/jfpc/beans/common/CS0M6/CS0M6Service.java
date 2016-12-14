package org.jfpc.beans.common.CS0M6;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 文章评价人*/
@Service
public class CS0M6Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CS0M6Service.class);

    public CS0M6Dao getDao(){
        return getMySqlSession().getMapper(CS0M6Dao.class);
    }

}
