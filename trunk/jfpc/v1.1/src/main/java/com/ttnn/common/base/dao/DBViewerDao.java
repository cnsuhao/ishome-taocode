package com.ttnn.common.base.dao;

import java.util.List;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;

/**
 * 基于视图的一览查询
 */
public interface DBViewerDao {
	/**
	 * 一览（分页）
	 * @param start 		每页起始数目
	 * @param size  		每页显示数目
	 * @param dbParamBean		查询条件
	 * @return
	 * 			int start,int size,
	 */
	public List<FrameworkDataBean> doSelectPage(PageVO paramPageVO);
	
	
}
