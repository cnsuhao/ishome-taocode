package com.hundsun.med.hdp.service.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.hundsun.med.access.hao.AccessQueryHAO;
import com.hundsun.med.framework.utils.BeanFactoryHelper;

/**
 * 对接通用查询接口
 * 
 * @author fucy
 * @version 2.2.2
 * @since 2.2.2 2015/5/13
 * 
 */
@Service("AccessQueryBusinessImpl")
public class AccessQueryBusinessImpl extends AHospitalProcessService {
	private static final Logger logger = LoggerFactory.getLogger(AccessQueryBusinessImpl.class);

	public AccessQueryBusinessImpl() {
		setBizName(ACCESSS_AccessQueryBusiness);
	}
	public static final String ACCESSS_BusinessImpl="BusinessImpl";//对接通用查询接口	(掌医，付长勇)	
	// 具体运行程序
	AHospitalProcessService bizBusinessImpl;

	AccessQueryHAO param;

	
	/**
	 * 先判断状态在发送数据
	 */
	@Override
	public boolean doInit() throws Exception {
		logger.debug(message.toString());
		param = JSON.parseObject(message.toString(), AccessQueryHAO.class);
		super.setPuk(param.getPuk());

		bizBusinessImpl = BeanFactoryHelper.getBean(param.getAccessIName()+ACCESSS_BusinessImpl);
		
		bizBusinessImpl.doInit(param.getJsonData());// 传递参数
		bizBusinessImpl.setPuk(this.getPuk());// 传递请求ID

		// 切入实际业务
		return bizBusinessImpl.doInit();
	}

	/**
	 * 完整性校验
	 */
	@Override
	public boolean doCheck() throws Exception {
		return bizBusinessImpl.doCheck();
	}

	/**
	 * 业务处理
	 */
	@Override
	public boolean doProcess() throws Exception {
		return bizBusinessImpl.doProcess();
	}

	@Override
	public boolean doSend() throws Exception {
		return bizBusinessImpl.doSend();
	}
}
