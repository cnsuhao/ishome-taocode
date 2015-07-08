package org.isotopes.jfp.framework.support.impl;

import java.util.List;

import org.isotopes.jfp.framework.page.bean.PageVO;
import org.isotopes.jfp.framework.support.CSDBVOSupport;
import org.isotopes.jfp.framework.support.ISSQLDaoSupport;

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

	@Override
	public List<CSDBVOSupport> doFindList(CSDBVOSupport paramBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doUpdateAll(CSDBVOSupport paramBean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<CSDBVOSupport> doSelectPage(PageVO PageVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doInsert(CSDBVOSupport paramBean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doUpdate(CSDBVOSupport paramBean) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CSDBVOSupport doRead(CSDBVOSupport paramBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int doDelete(CSDBVOSupport paramBean) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int toDelete(CSDBVOSupport paramBean) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
