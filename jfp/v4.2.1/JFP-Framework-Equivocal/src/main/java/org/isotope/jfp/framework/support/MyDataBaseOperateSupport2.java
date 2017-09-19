package org.isotope.jfp.framework.support;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;
import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.cache.session.SessionHelper;
import org.isotope.jfp.framework.common.ILoginer;

/**
 * 数据持久层超类
 * 
 * @author Spook
 * @version 4.1.3 2017/04/15
 * @version 4.1.1 2016/12/12
 * @version 3.2.1 2016/08/28
 * @since 3.2.1 2016/08/28
 */
public class MyDataBaseOperateSupport2<T> extends MyDataBaseOperateSupport<T> implements ILoginer {

	/**
	 * 当前登录用户
	 */
	public UserBean currentUser() {
		UserBean loginer = SessionHelper.currentUser();
		if (loginer == null) {
			throw new RuntimeException("当前用户没有登录");
		}
		return loginer;
	}

	/**
	 * 数据库分表
	 * 
	 * @param data
	 */
	public void changeTable(FrameworkDataBean formParamBean, int dbType) {
//		if (formParamBean instanceof MyDataBaseObjectSupport) {
//			MyDataBaseObjectSupport data = (MyDataBaseObjectSupport) formParamBean;
//			 String companyType = ((XXXXXDBO)data).getT01();
//			 //分表处理
//			 if (ZERO.equals(companyType)) {
//			 data.setTableName("0");
//			 } else if (ONE.equals(companyType)) {
//			 data.setTableName("1");
//			 }
//			 if (dbType != DB_SELECT) {
//			
//			 }
//		}
	}
}
