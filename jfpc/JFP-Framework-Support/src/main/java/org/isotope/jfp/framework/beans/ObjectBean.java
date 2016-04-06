package org.isotope.jfp.framework.beans;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.isotope.jfp.framework.utils.DateHelper;

/**
 * 参数配置信息
 * @author Spook
 * @version 0.1
 * @since 0.1.0 2014/2/8
 */
public class ObjectBean {
	/**
	 * 时间戳
	 */
	private String uuu = DateHelper.currentTimeMillisCN1();
	public void updateUuu() {
		 uuu = DateHelper.currentTimeMillisCN1();
	}
	public String getUuu() {
		return uuu;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
