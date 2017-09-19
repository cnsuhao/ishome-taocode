package org.isotope.jfp.framework.biz.common;

/**
 * 保存数据到数据中心
 * 
 * @author Spook
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public interface ISSave {

	/**
	 * 数据保存
	 */
	boolean doSave() throws Exception;
}
