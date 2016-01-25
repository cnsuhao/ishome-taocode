package org.isotope.jfp.framework.biz.custom;

import org.isotope.jfp.framework.biz.common.ISSave;

/**
 * 数据保存处理
 * 
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * 
 */
public interface ISSaveCustomBusiness extends ISSave {
	/**
	 * 数据保存前
	 */
	boolean beforeSave() throws Exception;
	/**
	 * 数据保存中
	 */
	boolean doSave() throws Exception;
	/**
	 * 数据保存后
	 */
	boolean afterSave() throws Exception;
}
