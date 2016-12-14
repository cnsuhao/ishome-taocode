package com.aek56.qt.gys.MGA4;

import java.util.List;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.support.ISDatabaseSupport;
import org.jfpc.framework.support.MyDataBaseObjectSupport;

/**
 * 供应商营业执照维护
 * 
 * @author zhengxw
 * 
 */
public interface MGA4Dao extends ISDatabaseSupport {
    public List<FrameworkDataBean> getAlreadyAddZcz(MyDataBaseObjectSupport paramBean);
}
