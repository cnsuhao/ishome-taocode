package com.aek56.yw.md.MDA;

import org.jfpc.framework.support.ISDatabaseSupport;
import org.jfpc.framework.support.MyDataBaseObjectSupport;

/**
 * 产品线维护
 * 
 * @author Administrator
 * 
 */
public interface MDA01Dao extends ISDatabaseSupport {
    /**
     * 逻辑删除产品线分类
     * 
     * @param paramBean
     */
    public int toDeleteCpxfl(MyDataBaseObjectSupport paramBean);
    
}
