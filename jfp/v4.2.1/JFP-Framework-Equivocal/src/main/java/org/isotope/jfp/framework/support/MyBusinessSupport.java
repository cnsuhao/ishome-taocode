package org.isotope.jfp.framework.support;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.isotope.jfp.framework.beans.common.RESTResultBean;
import org.isotope.jfp.framework.beans.user.UserBean;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.cache.session.SessionHelper;
import org.isotope.jfp.framework.common.ILoginer;
import org.isotope.jfp.framework.common.ISearchDatePrepare;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.constants.pub.ISModelConstants;
import org.isotope.jfp.framework.utils.DateHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据业务操作接口定义超类<br>
 * 事务性质业务逻辑<br>
 * 
 * @author Spook
 * @version 4.1.3 2017/04/15
 * @version 4.1.1 2016/12/12
 * @version 3.2.1 2016/08/28
 * @version 0.1.0 2013-8-21
 * @since 0.1.0 2013-8-21
 */
public class MyBusinessSupport<T> extends MyWorkSupport implements ISFrameworkConstants, ISModelConstants, ILoginer {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	// 缓存中心
	@Resource
	protected ICacheService myCacheService;

	protected RESTResultBean result;

	/**
	 * 设定返回值
	 * 
	 * @return
	 */
	public RESTResultBean getResult() {
		return result;
	}

	/////////////////////////////////////////////////////////////////////////////////////////
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

	////////////////////////////////////////////////////////////////////////////////////
	public void dateJudge(ISearchDatePrepare page) {
		// 计算当前时间前7天的日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -7);
		Date monday = c.getTime();
		String preMonday = sdf.format(monday);
		// 如果第一个时间为空 赋值为当前时间的前7天日期 第二个为空赋值为当前日期
		if (EmptyHelper.isEmpty(page.getStartDate()) && EmptyHelper.isEmpty(page.getEndDate())) {
			page.setStartDate(preMonday);
			page.setEndDate(DateHelper.currentTimeMillisCN3());
			return;
		}
		if (EmptyHelper.isEmpty(page.getStartDate())) {
			page.setStartDate(preMonday);
			return;
		}
		if (EmptyHelper.isEmpty(page.getEndDate())) {
			page.setEndDate(DateHelper.currentTimeMillisCN3());
			return;
		}

	}

}
