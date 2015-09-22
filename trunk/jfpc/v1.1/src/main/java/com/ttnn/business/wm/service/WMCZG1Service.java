package com.ttnn.business.wm.service;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.ttnn.business.wm.dao.UserDao;
import com.ttnn.business.wm.dao.WMCZG1Dao;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;


/** 支付通道*/
@Service
public class WMCZG1Service extends MyServiceSupportImpl {
	
	

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(WMCZG1Dao.class);
    }
    
    public PageVO doSelectPage(PageVO formParamPageModel) {
		//设定产品ID
		if(formParamPageModel.getPageData() instanceof CSPVOSupport)
		{
			CSPVOSupport formParamBean = (CSPVOSupport)formParamPageModel.getPageData();
			if(StringUtils.isNullOrEmpty(formParamBean.getEb5()))
				formParamBean.setEb5(super.getProductId());
		}
		
		 formParamPageModel.setPageData(mySqlSession.getMapper(WMCZG1Dao.class).doSelectPage(formParamPageModel));
//					formParamPageModel.getPageCurrent(), 
//					formParamPageModel.getPageLimit(), 
//					(FrameworkDataBean)formParamPageModel.getPageData()));
		return formParamPageModel;
	}
    
    public PageVO doSelectPageYJMX(PageVO formParamPageModel) {
		//设定产品ID
		if(formParamPageModel.getPageData() instanceof CSPVOSupport)
		{
			CSPVOSupport formParamBean = (CSPVOSupport)formParamPageModel.getPageData();
			if(StringUtils.isNullOrEmpty(formParamBean.getEb5()))
				formParamBean.setEb5(super.getProductId());
		}
		
		System.out.println(mySqlSession.getMapper(WMCZG1Dao.class).doSelectPageYJMX(formParamPageModel));
		
		 formParamPageModel.setPageData(mySqlSession.getMapper(WMCZG1Dao.class).doSelectPageYJMX(formParamPageModel));
//					formParamPageModel.getPageCurrent(), 
//					formParamPageModel.getPageLimit(), 
//					(FrameworkDataBean)formParamPageModel.getPageData()));
		return formParamPageModel;
	}
  

}
