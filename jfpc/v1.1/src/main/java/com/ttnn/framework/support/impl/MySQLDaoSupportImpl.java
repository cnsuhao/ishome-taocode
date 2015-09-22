package com.ttnn.framework.support.impl;

import java.util.List;

import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.ISSQLDaoSupport;

/**
 * 通常情况下使用原生方法即可<p>
 * 特殊情况下可以继承该类实现更多

 * @author Spook
 * @since 0.1.0 2013-8-21
 * @version 0.1.0
 * @see ISSQLDaoSupport
 *
 */
public class MySQLDaoSupportImpl extends MyBusinessSupportImpl implements ISSQLDaoSupport {

//	@Override
//	public int[] doBatchDelete(List<? extends FrameworkDataBean> paramBean) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public int[] doBatchInsert(List<? extends FrameworkDataBean> paramBean) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<FrameworkDataBean> doFindList(FrameworkDataBean paramBean) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public int doUpdateAll(FrameworkDataBean paramBean) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

	@Override
	public List<FrameworkDataBean> doSelectPage(PageVO PageVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doInsert(FrameworkDataBean paramBean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doUpdate(FrameworkDataBean paramBean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FrameworkDataBean doRead(FrameworkDataBean paramBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doDelete(FrameworkDataBean paramBean) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int toDelete(FrameworkDataBean paramBean) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
