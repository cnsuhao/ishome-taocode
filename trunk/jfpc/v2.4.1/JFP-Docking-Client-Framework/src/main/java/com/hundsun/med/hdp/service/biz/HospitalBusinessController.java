package com.hundsun.med.hdp.service.biz;

import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hundsun.med.framework.biz.ISTask;
import com.hundsun.med.framework.constants.ISFrameworkConstants;
import com.hundsun.med.framework.mq.IMedMqService;
import com.hundsun.med.framework.utils.HttpServiceHelper;
import com.hundsun.med.hdp.conf.CloundApiConfig;
import com.hundsun.med.hdp.conf.HospitalSecyrityConfig;
import com.hundsun.med.hdp.conf.MyJsonData;
import com.hundsun.med.hdp.constants.IHDPConstants;

/**
 * 处理来自云端的业务请求
 * 
 * @author fucy
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
			hbis.setBizClassName(entry.getValue());
			// 初始化参数
			hbis.doInit(HospitalSecyrityConfig_, HttpService_, CloundApiConfig_, IMedMqService_);
			// 激活线程
			(new Thread(hbis)).start();
		}
		return false;
	}
}
