package org.ishome.jfp.pdp.service.biz;

import java.util.Map.Entry;

import javax.annotation.Resource;

import org.ishome.jfp.framework.biz.ISTask;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.mq.IMedMqService;
import org.ishome.jfp.framework.utils.HttpServiceHelper;
import org.ishome.jfp.pdp.conf.CloundApiConfig;
import org.ishome.jfp.pdp.conf.HospitalSecyrityConfig;
import org.ishome.jfp.pdp.conf.MyJsonData;
import org.ishome.jfp.pdp.constants.IHDPConstants;
import org.springframework.stereotype.Service;


/**
 * 处理来自云端的业务请求
 * 
 * @author Spook
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * @see <AHospitalDataProcessService><HospitalProcessService>
 */
@Service
public class HospitalBusinessController extends MyJsonData implements IHDPConstants, ISFrameworkConstants, ISTask {
	public static final String SERVICE_HEART_HOS = "myHospitalHeartController";//心跳服务
	HttpServiceHelper HttpService_ = new HttpServiceHelper();
	@Resource
	protected CloundApiConfig CloundApiConfig_;
	@Resource
	protected IMedMqService IMedMqService_;// 缓存队列
	@Resource
	protected HospitalSecyrityConfig HospitalSecyrityConfig_;	
	public HospitalBusinessController() {
		setBizName(MQ_KEY_BIZ);
	}
	
	@Override
	public boolean doProcessRepeat() throws Exception {
		return doProcessOnce(null);
	}

	@Override
	public boolean doProcessOnce(String hosId) throws Exception {
		// 心跳通信
		// 获得通信令牌
		// 获得加密状态
//		if (EmptyHelper.isEmpty(CloundApiConfig_.getToken())) {
//			// 获得对应线程
//			HospitalHeartController dp = BeanFactoryHelper.getBean(SERVICE_HEART_HOS);
//			//TODO 这里最后需要取消注释
//			// 初始化参数
//			dp.doInit(HttpService_, CloundApiConfig_,HospitalSecyrityConfig_, IMedMqService_);
//			// 激活线程
//			(new Thread(dp)).start();
//			return false;
//		}
		// 根据参数配置，激活业务处理线程(spring-task.xml)
		for (Entry<String, String> entry: CloundApiConfig_.getMonitorConfig().entrySet()) {
			// 获得对应线程
			HospitalBusinessInitSupport hbis = new HospitalBusinessInitSupport();
			hbis.setBizName(entry.getKey());
			hbis.setBizClassNmae(entry.getValue());
			// 初始化参数
			hbis.doInit(HospitalSecyrityConfig_, HttpService_, CloundApiConfig_, IMedMqService_);
			// 激活线程
			(new Thread(hbis)).start();
		}
		return false;
	}
}
