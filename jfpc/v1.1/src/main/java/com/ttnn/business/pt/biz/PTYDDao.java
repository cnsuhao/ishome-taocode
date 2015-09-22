package com.ttnn.business.pt.biz;
import java.util.List;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
/** 总控监管数据*/
public interface PTYDDao extends ISSQLDaoSupport{
	/**
	 * 一览（分页）
	 * @param start 		每页起始数目
	 * @param size  		每页显示数目
	 * @param dbParamBean		查询条件
	 * @return
	 * 、、、、、int start,int size,
	 */
	public List<FrameworkDataBean> doSelect(PageVO paramPageVO);
	
	/**
	 * 产品信息查询
	 * @param paramPageVO
	 * @return
	 */
	
	public FrameworkDataBean doFindList1(FrameworkDataBean dbParamBean);
	
	/**
	 * 产品功能信息查询
	 * @param paramPageVO
	 * @return
	 */
	
	public FrameworkDataBean doFindList2(FrameworkDataBean dbParamBean);
	
	/**
	 * 产品销售套餐分类查询
	 * @param paramPageVO
	 * @return
	 */
	
	public FrameworkDataBean doFindList3(FrameworkDataBean dbParamBean);
	
	/**
	 * 系统公告查询
	 * @param paramPageVO
	 * @return
	 */
	
	public FrameworkDataBean doFindList4(FrameworkDataBean dbParamBean);
		
}
