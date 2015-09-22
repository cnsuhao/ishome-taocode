package com.ttnn.business.cs.service;
import org.springframework.stereotype.Service;

import com.ttnn.business.cs.dao.AQSZ01Dao;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 数据字典管理*/
@Service
public class AQSZ01Service extends MyServiceSupportImpl {

    @Override
    public AQSZ01Dao getDao(){
        return mySqlSession.getMapper(AQSZ01Dao.class);
    }

	/**
	 * 系统公告查询
	 */
	public void doSelectPageGG(PageVO pageVO){
		pageVO.setPageData(getDao().doSelectPageGG(pageVO));
	}

	
}
