package com.aek56.qt.gys.MGAAC;

import java.util.List;
import java.util.Map;

import org.jfpc.framework.support.ISDatabaseSupport;
import org.jfpc.framework.support.MyDataBaseObjectSupport;

/**
 * 供应商注册证维护
 * 
 * @author zhengxw
 * 
 */
public interface MGAACDao extends ISDatabaseSupport {
    public List<Map<String, String>> doCountAllZs(MyDataBaseObjectSupport formParam);
}
