package com.ttnn.business.cs.service;
import java.util.List;

import org.springframework.stereotype.Service;
import com.ttnn.business.aq.dao.PTCPDao;
import com.ttnn.business.cs.dao.AQSZ01Dao;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 平台产品*/
@Service
public class PTCPService extends MyServiceSupportImpl {

    @Override
    public PTCPDao getDao(){
        return mySqlSession.getMapper(PTCPDao.class);
    }

    /**
	 * 查询各产品的销售套餐 
	 */
	public List<FrameworkDataBean> doSelectPT(FrameworkDataBean paramBean){
		return getDao().doSelectPT(paramBean);
	}
	
	/**
	 * 查询各产品的对应套餐的功能
	 */
	public List<FrameworkDataBean> doSelectGN(FrameworkDataBean paramBean){
		return getDao().doSelectGN(paramBean);
	}
	
	/**
	 * 功能查询
	 */
	public List<FrameworkDataBean> doSelectCPGN(FrameworkDataBean paramBean){
		return getDao().doSelectCPGN(paramBean);
	}
	
	/**
	 *查询套餐功能
	 */
	public List<FrameworkDataBean> do1SelectCP(FrameworkDataBean paramBean){
		return getDao().do1SelectCP(paramBean);
	}

	/**
	 *查询一条记录
	 */
	public FrameworkDataBean doRead1(FrameworkDataBean paramBean){
		return getDao().doRead1(paramBean);
	}
	
	/**
	 * 管理员查询
	 */	
	public List<FrameworkDataBean> doReadC(FrameworkDataBean paramBean){
		return getDao().doReadC(paramBean);
	}
	
	/**
	 * 平台系统查询
	 */
	public int do2InsertPt(FrameworkDataBean paramBean){
		return getDao().do2InsertPt(paramBean);
	}

	/**
	 * 添加管理员
	 * @param paramBean
	 */
	public int do3InsertPt(CSPVOSupport paramBean) {
		return getDao().do3InsertPt(paramBean);
	}
			
}
