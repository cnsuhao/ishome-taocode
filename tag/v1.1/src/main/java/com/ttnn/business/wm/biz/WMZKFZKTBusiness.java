package com.ttnn.business.wm.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ttnn.business.wm.dao.WMMainTotalDao;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 贷付宝后台数据统计服务 */
@Service
public class WMZKFZKTBusiness extends MyServiceSupportImpl {

	/**
	 * 获得系统日志输出对象
	 * 
	 * @return
	 */
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMZKFZKTBusiness.class);
	}

	// 系统授权信息
	@Override
	public WMMainTotalDao getDao() {
		return mySqlSession.getMapper(WMMainTotalDao.class);
	}

}
