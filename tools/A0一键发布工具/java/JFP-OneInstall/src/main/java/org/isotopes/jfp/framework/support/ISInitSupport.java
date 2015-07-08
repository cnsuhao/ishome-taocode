package org.isotopes.jfp.framework.support;
/**
 * 初始化<br>
 * 用于配置文件<spring-bean.xml>进行初始化配置
 * @see <spring-bean.xml>
 * @since 0.2
 * @version 0.1 2012/9/14
 *
 */
public interface ISInitSupport {
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
    public boolean init() throws Exception ;
}
