package org.jfpc.beans.common.CS0M8;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 用户问卷答案*/
@Service
public class CS0M8Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CS0M8Service.class);

    public CS0M8Dao getDao(){
        return getMySqlSession().getMapper(CS0M8Dao.class);
    }

}
