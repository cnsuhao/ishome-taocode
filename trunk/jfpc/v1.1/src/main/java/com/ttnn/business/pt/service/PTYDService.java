package com.ttnn.business.pt.service;

import org.springframework.stereotype.Service;

import com.ttnn.business.pt.biz.PTYDDao;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 总控监管数据*/
@Service
public class PTYDService extends MyServiceSupportImpl {

    @Override
    public PTYDDao getDao(){
        return mySqlSession.getMapper(PTYDDao.class);
    }
    
    /**
     * 产品信息查询
     * @param dbParamBean
     * @return
     */
    public FrameworkDataBean doFindList1(FrameworkDataBean dbParamBean){
    	return getDao().doFindList1(dbParamBean);
    }
    /**
     * 产品功能信息查询
     * @param dbParamBean
     * @return
     */
    public FrameworkDataBean doFindList2(FrameworkDataBean dbParamBean){
    	return getDao().doFindList2(dbParamBean);
    }
    /**
     * 产品销售套餐分类查询
     * @param dbParamBean
     * @return
     */
    public FrameworkDataBean doFindList3(FrameworkDataBean dbParamBean){
    	return getDao().doFindList3(dbParamBean);
    }
    
    /**
     * 系统公告查询
     * @param dbParamBean
     * @return
     */
    public FrameworkDataBean doFindList4(FrameworkDataBean dbParamBean){
    	return getDao().doFindList4(dbParamBean);
    }
}
