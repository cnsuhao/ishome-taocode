package com.ttnn.business.wm.service;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.ttnn.business.wm.dao.TotalDao;
import com.ttnn.business.wm.dao.UserDao;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;


/** 支付通道*/
@Service
public class TotalService extends MyServiceSupportImpl {
	
	

//   充值 
    public PageVO doSelectRecharge(PageVO formParamPageModel) {
		//设定产品ID
		if(formParamPageModel.getPageData() instanceof CSPVOSupport)
		{
			CSPVOSupport formParamBean = (CSPVOSupport)formParamPageModel.getPageData();
			if(StringUtils.isNullOrEmpty(formParamBean.getEb5()))
				formParamBean.setEb5(super.getProductId());
		}
		
		 formParamPageModel.setPageData(mySqlSession.getMapper(TotalDao.class).doSelectRecharge(formParamPageModel));
//					formParamPageModel.getPageCurrent(), 
//					formParamPageModel.getPageLimit(), 
//					(FrameworkDataBean)formParamPageModel.getPageData()));
		return formParamPageModel;
	}
//    提现
    public PageVO doSelectWithdraw(PageVO formParamPageModel) {
 		//设定产品ID
 		if(formParamPageModel.getPageData() instanceof CSPVOSupport)
 		{
 			CSPVOSupport formParamBean = (CSPVOSupport)formParamPageModel.getPageData();
 			if(StringUtils.isNullOrEmpty(formParamBean.getEb5()))
 				formParamBean.setEb5(super.getProductId());
 		}
 		
 		 formParamPageModel.setPageData(mySqlSession.getMapper(TotalDao.class).doSelectWithdraw(formParamPageModel));
// 					formParamPageModel.getPageCurrent(), 
// 					formParamPageModel.getPageLimit(), 
// 					(FrameworkDataBean)formParamPageModel.getPageData()));
 		return formParamPageModel;
 	}
    
// 佣金
  public PageVO doSelectCommission(PageVO formParamPageModel) {
		//设定产品ID
		if(formParamPageModel.getPageData() instanceof CSPVOSupport)
		{
			CSPVOSupport formParamBean = (CSPVOSupport)formParamPageModel.getPageData();
			if(StringUtils.isNullOrEmpty(formParamBean.getEb5()))
				formParamBean.setEb5(super.getProductId());
		}
		
		 formParamPageModel.setPageData(mySqlSession.getMapper(TotalDao.class).doSelectCommission(formParamPageModel));
//					formParamPageModel.getPageCurrent(), 
//					formParamPageModel.getPageLimit(), 
//					(FrameworkDataBean)formParamPageModel.getPageData()));
		return formParamPageModel;
	}
    
    
   

}
