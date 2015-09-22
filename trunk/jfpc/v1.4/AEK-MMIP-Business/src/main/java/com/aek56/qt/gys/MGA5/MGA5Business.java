package com.aek56.qt.gys.MGA5;

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
public class MGA5Business extends MyServiceSupport {
    
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
    
    public MGA5Dao getDao(){
        return getMySqlSession().getMapper(MGA5Dao.class);
    }
}