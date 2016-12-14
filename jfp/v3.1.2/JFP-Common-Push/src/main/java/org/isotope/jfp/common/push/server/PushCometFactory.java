package org.isotope.jfp.common.push.server;

import java.util.Map;

import org.isotope.jfp.framework.beands.common.RESTResultBean;
import org.isotope.jfp.framework.beands.pub.PushBean;
import org.isotope.jfp.framework.constants.ISFrameworkConstants;
import org.isotope.jfp.framework.support.ISPushCometSupport;
import org.isotope.jfp.framework.utils.BeanFactoryHelper;
import org.isotope.jfp.framework.utils.EmptyHelper;

/**
 * 推送处理工厂
 * 
 * @author fucy
 * @version 2.4.1 2015/9/9
 * @since 2.4.1
 */
public class PushCometFactory implements ISPushCometSupport, ISFrameworkConstants {
	/**
	 * 默认推送服务
	 */
	private ISPushCometSupport defaultPushComet;

	/**
	 * 推送服务分类定义
	 */
	private Map<String, String> pushCometConfig;

	public ISPushCometSupport getDefaultPushComet() {
		return defaultPushComet;
	}

	public void setDefaultPushComet(ISPushCometSupport defaultPushComet) {
		this.defaultPushComet = defaultPushComet;
	}

	public Map<String, String> getPushCometConfig() {
		return pushCometConfig;
	}

	public void setPushCometConfig(Map<String, String> pushCometConfig) {
		this.pushCometConfig = pushCometConfig;
	}

	/**
	 * 消息推送
	 */
	@Override
	public RESTResultBean doPush(PushBean message) {
		ISPushCometSupport curPushComet = null;
		// 获得手机类别对应的推送通道
		String type = pushCometConfig.get(message.getPhoneType());
		if (EmptyHelper.isNotEmpty(type)) {
			// 获得手机号段对应的网关
			curPushComet = BeanFactoryHelper.getBean(type);
		}
		// 如果不存在使用默认网关
		if (curPushComet == null) {
			curPushComet = defaultPushComet;
		}
		return curPushComet.doPush(message);
	}

}
