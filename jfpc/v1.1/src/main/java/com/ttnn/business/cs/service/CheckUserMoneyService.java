package com.ttnn.business.cs.service;

import org.springframework.stereotype.Service;

import com.ttnn.business.cs.dao.CheckUserMoneyDao;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;
/**
 * 对账
 * @author duan.p
 *
 */
@Service
public class CheckUserMoneyService extends MyServiceSupportImpl {


	
	public void findAllCheck(PageVO pageVO){
	  pageVO.setPageData(mySqlSession.getMapper(CheckUserMoneyDao.class).doSelectPageCheck(pageVO));
		
	}

}
