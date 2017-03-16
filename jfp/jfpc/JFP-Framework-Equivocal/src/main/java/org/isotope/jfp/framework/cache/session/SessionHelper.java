package org.isotope.jfp.framework.cache.session;

import org.isotope.jfp.framework.beans.token.TokenBusinessBean;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;

import com.alibaba.fastjson.JSON;

/**
 * 登录用户Session（LoginerBean）本地ThreadLocal
 * 
 * @author fucy
 * @version 4.1.1 2016/12/12
 * @version 3.2.1 2016/8/6
 * @version 2.0.0 2015/1/19
 * @since 2.0.0 2015/1/19
 */
public class SessionHelper {
	private static final ThreadLocal<TokenBusinessBean> holder = new ThreadLocal<TokenBusinessBean>();

	public static void setSessionAttribute(TokenBusinessBean value) {
		holder.set(value);
	}

	/**
	 * 获得当前缓存的信息
	 * @see <MyDataBaseObjectSupport2>
	 * @return
	 */
	public static TokenBusinessBean getSessionAttribute() {
		return holder.get();
	}

	/**
	 * 加载测试用户登录数据
	 */
	public static void loadTestSession() {
		TokenBusinessBean tb = new TokenBusinessBean("12345678", "22233344", "1", "12345678901234567", "76543210987654321");
		setSessionAttribute(tb);
		ICacheService myCacheService = BeanFactoryHelper.getBean("myCache");
		myCacheService.putObject("12345678901234567", JSON.toJSONString(tb), 3600, false);
	}
}
