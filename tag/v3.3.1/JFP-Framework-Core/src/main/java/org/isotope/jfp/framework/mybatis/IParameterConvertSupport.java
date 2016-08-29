package org.isotope.jfp.framework.mybatis;

import org.isotope.jfp.framework.beans.common.FrameworkDataBean;

/**
 * 参数内容统一拦截
 * 
 * @author Spook
 * @since 3.2.1
 * @version 3.2.1 2016/8/5
 * @see <MyFrameworkQueryPlugin><MyFrameworkUpdatePlugin>
 */
public interface IParameterConvertSupport {

	public void convert(FrameworkDataBean paramBean) throws Exception;
}
