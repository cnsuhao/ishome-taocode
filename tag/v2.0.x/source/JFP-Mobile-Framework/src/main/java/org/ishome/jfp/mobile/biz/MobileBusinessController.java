package org.ishome.jfp.mobile.biz;

import java.util.Map.Entry;

import javax.annotation.Resource;

import org.ishome.jfp.framework.biz.ISTask;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.utils.BeanFactoryHelper;
import org.ishome.jfp.mobile.conf.MobileApiConfig;
import org.ishome.jfp.mobile.constants.IMSRConstants;


/**
 * 处理来自云端的业务请求
 * 
 * @author Spook
 * @version 2.3.1 2015/6/23
 * @since 2.3.1 2015/6/23
 * @see <MobileServiceBusinessInitSupport><MobileServiceController>
 */
public class MobileBusinessController implements IMSRConstants, ISFrameworkConstants, ISTask {
	//业务配置
	@Resource
	protected MobileApiConfig MobileApiConfig_;

	@Override
	public boolean doProcessRepeat() throws Exception {
		return doProcessOnce(null);
	}

	@Override
	public boolean doProcessOnce(String hosId) throws Exception {

		// 根据参数配置，激活业务处理线程(spring-task.xml)
		for (Entry<String, String> entry : MobileApiConfig_.getMonitorConfig().entrySet()) {
			// 获得服务响应实体类
			MobileRedisBusinessInitSupport bizService = BeanFactoryHelper.getBean(entry.getValue());// 获得业务实例
			bizService.setBizName(entry.getKey());
			// 激活线程
			(new Thread(bizService)).start();
		}
		return false;
	}
}
