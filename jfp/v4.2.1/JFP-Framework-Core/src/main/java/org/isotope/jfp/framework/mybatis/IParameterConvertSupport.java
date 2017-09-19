package org.isotope.jfp.framework.mybatis;

/**
 * 参数内容统一拦截
 * 
 * @author Spook
 * @since 3.2.1
 * @version 3.2.1 2016/8/5
 * @see <MyFrameworkQueryPlugin><MyFrameworkUpdatePlugin>
 */
public interface IParameterConvertSupport {

	//TODO
	public void convert(Object object) throws Exception;
}
