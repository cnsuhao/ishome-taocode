package com.ttnn.business.wm.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.ttnn.business.wm.dao.UserDao;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;


/** 支付通道*/
@Service
public class UserService extends MyServiceSupportImpl {
	
	

    @Override
    public ISSQLDaoSupport getDao(){
        return mySqlSession.getMapper(UserDao.class);
    }
    
    
    public PageVO doSelectPageUserRealname(PageVO formParamPageModel) {
		//设定产品ID
		if(formParamPageModel.getPageData() instanceof CSPVOSupport)
		{
			CSPVOSupport formParamBean = (CSPVOSupport)formParamPageModel.getPageData();
			if(StringUtils.isNullOrEmpty(formParamBean.getEb5()))
				formParamBean.setEb5(super.getProductId());
		}
		
		 formParamPageModel.setPageData(mySqlSession.getMapper(UserDao.class).doSelectPageUserRealname(formParamPageModel));
//					formParamPageModel.getPageCurrent(), 
//					formParamPageModel.getPageLimit(), 
//					(FrameworkDataBean)formParamPageModel.getPageData()));
		return formParamPageModel;
	}
    
    
    
    
    public PageVO doSelectPageUser(PageVO formParamPageModel) {
		//设定产品ID
		if(formParamPageModel.getPageData() instanceof CSPVOSupport)
		{
			CSPVOSupport formParamBean = (CSPVOSupport)formParamPageModel.getPageData();
			if(StringUtils.isNullOrEmpty(formParamBean.getEb5()))
				formParamBean.setEb5(super.getProductId());
		}
		
		 formParamPageModel.setPageData(mySqlSession.getMapper(UserDao.class).doSelectPageUser(formParamPageModel));
//					formParamPageModel.getPageCurrent(), 
//					formParamPageModel.getPageLimit(), 
//					(FrameworkDataBean)formParamPageModel.getPageData()));
		return formParamPageModel;
	}
    
    
    public PageVO doSelectPageBank(PageVO formParamPageModel) {
		//设定产品ID
		if(formParamPageModel.getPageData() instanceof CSPVOSupport)
		{
			CSPVOSupport formParamBean = (CSPVOSupport)formParamPageModel.getPageData();
			if(StringUtils.isNullOrEmpty(formParamBean.getEb5()))
				formParamBean.setEb5(super.getProductId());
		}
		
		 formParamPageModel.setPageData(mySqlSession.getMapper(UserDao.class).doSelectPageBank(formParamPageModel));
//					formParamPageModel.getPageCurrent(), 
//					formParamPageModel.getPageLimit(), 
//					(FrameworkDataBean)formParamPageModel.getPageData()));
		return formParamPageModel;
	}
    
    public void checkUser(CSPVOSupport param){
    	mySqlSession.getMapper(UserDao.class).checkUser(param);
    }
    
    public void cancelUser(CSPVOSupport param){
    	mySqlSession.getMapper(UserDao.class).cancelUser(param);
    }
    
    public void checkReal(CSPVOSupport param){
    	mySqlSession.getMapper(UserDao.class).checkReal(param);
    }
    
    public void checCard(CSPVOSupport param){
    	mySqlSession.getMapper(UserDao.class).checkCard(param);
    }
    
    public FrameworkDataBean showDetail(CSPVOSupport param){
    	return mySqlSession.getMapper(UserDao.class).showDetail(param);
    }
    
    public int countUsername(String username){
    	return  mySqlSession.getMapper(UserDao.class).countUsername(username);
    }
    
    public List<FrameworkDataBean> doFindgrtd(CSPVOSupport param){
    	return mySqlSession.getMapper(UserDao.class).doFindgrtd(param);
    }
    
    public FrameworkDataBean showDetailRealname(CSPVOSupport param){
    	return mySqlSession.getMapper(UserDao.class).showDetailRealname(param);
    }
    
    public void doupdateUsergroup(CSPVOSupport param){
    	mySqlSession.getMapper(UserDao.class).doupdateUsergroup(param);
    }
   

}
