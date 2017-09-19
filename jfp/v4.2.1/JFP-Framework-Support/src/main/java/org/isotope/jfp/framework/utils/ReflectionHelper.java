package org.isotope.jfp.framework.utils;

import java.lang.reflect.Method;

/**
 * 匿名反射运行方法<br>
 * 类中不存在重载
 * @author Spook
 * @version 2.4.1 2015/11/10
 * @since 0.1.0
 *
 */
public class ReflectionHelper {

	public static Object doInvokeMethod(String beanId, String method, Object... args) throws Exception {
		Object o = BeanFactoryHelper.getBean(beanId); // 注入service
		Method m = findMethod(o.getClass(), method);
		return doInvokeMethod(o, m, args);
	}

	public static Method findMethod(final Class<?> cls, final String methodName) throws Exception {
		// search through all methods
		Method bestMatch = null;
		final Method[] methods = cls.getMethods();
		for (final Method method : methods) {
			// compare name and parameters
			if (method.getName().equals(methodName)) {
				bestMatch = method;
			}
		}
		return bestMatch;
	}

	public static Object doInvokeMethod(Object target, Method method, Object... args) throws Exception {
		try {
			return method.invoke(target, args);
		} catch (Exception ex) {
			throw new Exception();
		}
	}
}
