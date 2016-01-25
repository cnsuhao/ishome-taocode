package com.aek56.yw.md.MDG;

import org.jfpc.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 后台维护主数据注册证事务类
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Service
public class MDG01Business extends MyServiceSupport {
    
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
    
    public MDG01Dao getDao(){
        return getMySqlSession().getMapper(MDG01Dao.class);
    }
}