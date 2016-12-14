package com.ttnn.business.cs.dao;
import java.util.List;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.ISSQLDaoSupport;
/** 系统设置*/
public interface AQSZ01Dao extends ISSQLDaoSupport{
	
	/**
	 * 系统公告查询
	 */
	public List<FrameworkDataBean> doSelectPageGG(PageVO pageVO);

   /**
    * 黑名单查询
    */
}
