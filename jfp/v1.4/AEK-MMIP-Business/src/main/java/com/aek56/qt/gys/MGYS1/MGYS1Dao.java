package com.aek56.qt.gys.MGYS1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfpc.framework.support.ISDatabaseSupport;
import org.jfpc.framework.support.MyDataBaseObjectSupport;

/**
 * 供应商经营范围维护
 * 
 * @author zhengxw
 * 
 */
public interface MGYS1Dao extends ISDatabaseSupport {
    /**
     * 经营范围68码树
     *
     * @param paramBean
     * @return
     * @Author:zhengxw
     * @Date:2014-12-5
     */
    public List<HashMap<String, String>> doSearch68Tree(MyDataBaseObjectSupport paramBean);
    
    /**
     * 经营范围分类树
     *
     * @param paramBean
     * @return
     * @Author:zhengxw
     * @Date:2014-12-5
     */
    public List<HashMap<String, String>> doSearchFlTree(MyDataBaseObjectSupport paramBean);

    /**
     * 获得主数据经营范围
     * @param md1_QYJYFWDBO
     * @param b
     * @return
     */
	public List<Map<String, String>> loadAllJYFW(MyDataBaseObjectSupport paramBean);
	public List<Map<String, String>> loadAllJYFW123(MyDataBaseObjectSupport paramBean);
    
}
