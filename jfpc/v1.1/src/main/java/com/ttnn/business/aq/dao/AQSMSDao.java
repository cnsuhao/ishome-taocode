package com.ttnn.business.aq.dao;
import java.util.List;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.ISSQLDaoSupport;
/** 实名认证*/
public interface AQSMSDao extends ISSQLDaoSupport{
	/**
	 * 查询用户id
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> doFindyh(FrameworkDataBean paramBean);

/**
 *更新
 * 
 * @param dbParamBean
 * @return
 */
public Integer doUpdate1(FrameworkDataBean paramBean);
   /**
    *添加
    * 
    * @param dbParamBean
    * @return
    */
public Integer doInsert1(FrameworkDataBean paramBean);

}
