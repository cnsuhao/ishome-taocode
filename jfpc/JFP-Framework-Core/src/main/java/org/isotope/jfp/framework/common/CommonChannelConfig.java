package org.isotope.jfp.framework.common;

import org.isotope.jfp.framework.biz.common.ISInit;
import org.isotope.jfp.framework.cache.ICacheService;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;

/**
 * 通用Redis通道队列设置
 * 
 * @author fucy
 * @version 2.4.1 2015/8/15
 * @since 2.4.1
 */
public class CommonChannelConfig implements ISFrameworkConstants, ISInit {

	/**
	 * 缓存定义
	 */
	protected ICacheService catchService;

	public void setCatchService(ICacheService catchService) {
		this.catchService = catchService;
	}

	public ICacheService getCatchService() {
		return catchService;
	}
	
	protected int defaultIndex = 0;
	

	public int getDefaultIndex() {
		return defaultIndex;
	}

	public void setDefaultIndex(int defaultIndex) {
		this.defaultIndex = defaultIndex;
	}

	/**
	 * Redis通道定义
	 */
	protected String channelKey;

	public String getChannelKey() {
		return channelKey;
	}

	public void setChannelKey(String channelKey) {
		this.channelKey = channelKey;
	}

	public boolean doInit() {
		catchService = BeanFactoryHelper.getBean(catchBeanName);
		return true;
	}

	protected String catchBeanName;

	public String getCatchBeanName() {
		return catchBeanName;
	}

	public void setCatchBeanName(String catchBeanName) {
		this.catchBeanName = catchBeanName;
	}
}
