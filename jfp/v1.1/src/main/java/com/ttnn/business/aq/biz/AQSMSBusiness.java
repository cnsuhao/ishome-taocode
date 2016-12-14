package com.ttnn.business.aq.biz;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ttnn.business.aq.dao.AQSMSDao;
import com.ttnn.business.wm.dao.WMQTJLCXDao;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 用户类型 */
@Service
public class AQSMSBusiness extends MyServiceSupportImpl {
	

	@Override
	public AQSMSDao getDao() {
		return mySqlSession.getMapper(AQSMSDao.class);
	}
	/**
	 * 查询用户id
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> doFindyh(FrameworkDataBean paramBean) {
		return getDao().doFindyh(paramBean);

	}	
	/**
	 * 查询用户id
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public Integer doUpdate1(FrameworkDataBean paramBean) {
		return getDao().doUpdate1(paramBean);

	}	
	/**
	 * 查询用户id
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public Integer doInsert1(FrameworkDataBean paramBean) {
		return getDao().doInsert1(paramBean);

	}	
}
	