package com.aek56.qt.gys.MGA6;

import org.jfpc.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 供应商维护注册证事务类
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Service
public class MGA6Business extends MyServiceSupport {
    
    /**
     * xxxx
     * 
     * @param gysid
     * @Author:zhengxw
     * @Date:2014-11-29
     */
    @Transactional
    public void deleteGYS(String gysid) {
        
    }
    
    public MGA6Dao getDao(){
        return getMySqlSession().getMapper(MGA6Dao.class);
    }
}