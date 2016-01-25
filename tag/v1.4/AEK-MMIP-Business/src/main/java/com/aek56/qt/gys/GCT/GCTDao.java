package com.aek56.qt.gys.GCT;

import java.util.List;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.page.PageVOSupport;
import org.jfpc.framework.support.ISDatabaseSupport;
import org.jfpc.framework.support.MyDataBaseObjectSupport;

/**
 * 供应商推送注册证
 * 
 * @author zhengxw
 * 
 */
public interface GCTDao extends ISDatabaseSupport {
    /**
     * 查询未推送注册证
     *
     * @param formParam
     * @return
     * @Author:zhengxw
     * @Date:2014-12-9
     */
    public List<FrameworkDataBean> doSelectPageUnsentZcz(PageVOSupport formParam);
    
    public List<FrameworkDataBean> doSelectPageUnsentZcz(MyDataBaseObjectSupport formParam);
    
    /**
     * 获取业务员以及勾选状态
     *
     * @param paramBean
     * @return
     * @Author:zhengxw
     * @Date:2014-12-9
     */
    public List<FrameworkDataBean> getYwyAndState(MyDataBaseObjectSupport paramBean);
    
    /**
     * 获取所有证书
     *
     * @param paramBean
     * @return
     * @Author:zhengxw
     * @Date:2014-12-9
     */
    public List<FrameworkDataBean> getMyAllZs(MyDataBaseObjectSupport paramBean);
    
}
