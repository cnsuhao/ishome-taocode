package com.ttnn.business.wm.service;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.ttnn.business.wm.dao.UserDao;
import com.ttnn.business.wm.dao.WMSZT2Dao;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;


/** 支付通道*/
@Service
public class WMSZT2Service extends MyServiceSupportImpl {
	
	

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(WMSZT2Dao.class);
    }
    
    public PageVO doSelectPage(PageVO formParamPageModel) {
		//设定产品ID
		if(formParamPageModel.getPageData() instanceof CSPVOSupport)
		{
			CSPVOSupport formParamBean = (CSPVOSupport)formParamPageModel.getPageData();
			if(StringUtils.isNullOrEmpty(formParamBean.getEb5()))
				formParamBean.setEb5(super.getProductId());
		}
		
		 formParamPageModel.setPageData(mySqlSession.getMapper(WMSZT2Dao.class).doSelectPage(formParamPageModel));
//					formParamPageModel.getPageCurrent(), 
//					formParamPageModel.getPageLimit(), 
//					(FrameworkDataBean)formParamPageModel.getPageData()));
		return formParamPageModel;
	}
    
    
    public PageVO doSelectPageZhtj(PageVO formParamPageModel) {
		//设定产品ID
		if(formParamPageModel.getPageData() instanceof CSPVOSupport)
		{
			CSPVOSupport formParamBean = (CSPVOSupport)formParamPageModel.getPageData();
			if(StringUtils.isNullOrEmpty(formParamBean.getEb5()))
				formParamBean.setEb5(super.getProductId());
		}
		
		 formParamPageModel.setPageData(mySqlSession.getMapper(WMSZT2Dao.class).doSelectPageZhtj(formParamPageModel));
//					formParamPageModel.getPageCurrent(), 
//					formParamPageModel.getPageLimit(), 
//					(FrameworkDataBean)formParamPageModel.getPageData()));
		return formParamPageModel;
	}
    
    public PageVO doSelectPageYutj(PageVO formParamPageModel) {
		//设定产品ID
		if(formParamPageModel.getPageData() instanceof CSPVOSupport)
		{
			CSPVOSupport formParamBean = (CSPVOSupport)formParamPageModel.getPageData();
			if(StringUtils.isNullOrEmpty(formParamBean.getEb5()))
				formParamBean.setEb5(super.getProductId());
		}
		
		 formParamPageModel.setPageData(mySqlSession.getMapper(WMSZT2Dao.class).doSelectPageYutj(formParamPageModel));
//					formParamPageModel.getPageCurrent(), 
//					formParamPageModel.getPageLimit(), 
//					(FrameworkDataBean)formParamPageModel.getPageData()));
		return formParamPageModel;
	}
    
    public PageVO doSelectPageRitj(PageVO formParamPageModel) {
		//设定产品ID
		if(formParamPageModel.getPageData() instanceof CSPVOSupport)
		{
			CSPVOSupport formParamBean = (CSPVOSupport)formParamPageModel.getPageData();
			if(StringUtils.isNullOrEmpty(formParamBean.getEb5()))
				formParamBean.setEb5(super.getProductId());
		}
		
		 formParamPageModel.setPageData(mySqlSession.getMapper(WMSZT2Dao.class).doSelectPageRitj(formParamPageModel));
//					formParamPageModel.getPageCurrent(), 
//					formParamPageModel.getPageLimit(), 
//					(FrameworkDataBean)formParamPageModel.getPageData()));
		return formParamPageModel;
	}
    
    public PageVO doSelectPageTX(PageVO formParamPageModel) {
		//设定产品ID
		if(formParamPageModel.getPageData() instanceof CSPVOSupport)
		{
			CSPVOSupport formParamBean = (CSPVOSupport)formParamPageModel.getPageData();
			if(StringUtils.isNullOrEmpty(formParamBean.getEb5()))
				formParamBean.setEb5(super.getProductId());
		}
		
		 formParamPageModel.setPageData(mySqlSession.getMapper(WMSZT2Dao.class).doSelectPageTX(formParamPageModel));
//					formParamPageModel.getPageCurrent(), 
//					formParamPageModel.getPageLimit(), 
//					(FrameworkDataBean)formParamPageModel.getPageData()));
		return formParamPageModel;
	}
    
    public PageVO doSelectPageYJ(PageVO formParamPageModel) {
		//设定产品ID
		if(formParamPageModel.getPageData() instanceof CSPVOSupport)
		{
			CSPVOSupport formParamBean = (CSPVOSupport)formParamPageModel.getPageData();
			if(StringUtils.isNullOrEmpty(formParamBean.getEb5()))
				formParamBean.setEb5(super.getProductId());
		}
		
		 formParamPageModel.setPageData(mySqlSession.getMapper(WMSZT2Dao.class).doSelectPageYJ(formParamPageModel));
//					formParamPageModel.getPageCurrent(), 
//					formParamPageModel.getPageLimit(), 
//					(FrameworkDataBean)formParamPageModel.getPageData()));
		return formParamPageModel;
	}
    
    
    
    public void doWMSZT2Update(CSPVOSupport param){
    	 mySqlSession.getMapper(WMSZT2Dao.class).doWMSZT2Update(param);
    }
   
    
    public void doUpdateTX(CSPVOSupport param){
   	 mySqlSession.getMapper(WMSZT2Dao.class).doUpdateTX(param);
   }
  
    
    public void doUpdateYJ(CSPVOSupport param){
      	 mySqlSession.getMapper(WMSZT2Dao.class).doUpdateYJ(param);
      }
    
   

}
