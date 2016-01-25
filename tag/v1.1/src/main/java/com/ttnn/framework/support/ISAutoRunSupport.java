package com.ttnn.framework.support;


/**
 * 自动运行超类
 * @since 0.1 
 * @version 0.1 2012-9-19 标准化开发
 * @see <spring-bean.xml>
 */

public interface ISAutoRunSupport  extends ISBusinessSupport{

	public boolean initialization() throws Exception ;
}
