package org.jfpc.base.cache;

import org.jfpc.base.ISFrameworkConstants;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;


/**
 * 数据缓存
 * 
 * @author Spook
 * @since 0.1.0 
 * @version 0.1.0 2014/2/8
 */
public class SessionHelper implements ISFrameworkConstants{
	//////////////////////////////////////////////////////////////////////	
//	/**
//	 * 数据缓存
//	 */
//	@Resource
//	protected CatchUtils catchUtils;
	public static void setSessionAttribute(String key, Object value) {
		RequestContextHolder.getRequestAttributes().setAttribute(key, value, RequestAttributes.SCOPE_GLOBAL_SESSION);
	}

	public static Object getSessionAttribute(String key) {
		//基于缓存服务器
		return RequestContextHolder.getRequestAttributes().getAttribute(key, RequestAttributes.SCOPE_GLOBAL_SESSION);
	}
	
	public static void removeSessionAttribute(String key) {
		RequestContextHolder.getRequestAttributes().removeAttribute(key, RequestAttributes.SCOPE_GLOBAL_SESSION);
	}
}
