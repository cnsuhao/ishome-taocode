package org.jfpc.beans.common.MS1C7;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 角色菜单权限*/
@Service
public class MS1C7Service extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MS1C7Service.class);

    public MS1C7Dao getDao(){
        return getMySqlSession().getMapper(MS1C7Dao.class);
    }

}
