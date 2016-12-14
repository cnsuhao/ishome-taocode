package com.hundsun.his.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hundsun.his.constants.IHISConstants;
import com.hundsun.his.service.AHospitalBusinessService;
import com.hundsun.his.utils.MyHospitalAPISupport;
import com.hundsun.med.framework.beands.RESTResultBean;
import com.hundsun.med.framework.mq.IMedMqService;
import com.hundsun.med.framework.utils.BeanFactoryHelper;
import com.hundsun.med.framework.utils.EmptyHelper;
import com.hundsun.med.framework.utils.HttpServiceHelper;

/**
 * 医院开放接口业务
 * 
 * @author fucy
 * @version 2.3.1
 * @since 2.3.1 2015/6/12
 * 
 */
@Controller
public class HospitalBusinessAPI extends MyHospitalAPISupport implements IHISConstants {
	private static final Logger logger = LoggerFactory.getLogger(HospitalBusinessAPI.class);
	protected HttpServiceHelper httpService;
	protected IMedMqService mqService;	
	
	/**
	 * 获得医院对接系统版本信息（前置机监控程序调用）
	 * 
	 * @param request
	 * @param hosId
	 * @param bizName
	 * @return
	 */
	@RequestMapping(value = "/{bizName}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean doHosProcess(String bizName, String jsonData) {
		logger.debug(JOB_START);
		logger.debug(">>>>>" + bizName + "<<<<<");
		RESTResultBean rs = new RESTResultBean();
		try {
			AHospitalBusinessService hb = BeanFactoryHelper.getBean(bizName + HOSPITAL_BusinessImpl);
			Class<?> clazz = BeanFactoryHelper.getBean(bizName + "HAO").getClass();
			hb.doInit(httpService, mqService, JSON.parseObject(jsonData, clazz));
			// 数据处理
			if (hb.doInit() // 初始化业务处理
					&& hb.doCheck() // 进行业务校验
					&& hb.doProcess() // 处理业务内容
			) {
				rs = hb.getRESTResultBean();
				// 处理成功
				logger.debug(">>>>>" + rs + "<<<<<");
				logger.debug(JOB_END);
			} else {
				logger.debug(JOB_CANCEL);
				if (EmptyHelper.isEmpty(hb.getReturnCode()))
					rs.setCode(SYSTEM_ERROR_CODE);
				if (EmptyHelper.isEmpty(hb.getReturnMessage()))
					rs.setMessage("医院接口服务响应失败");
			}
		} catch (Exception e) {
			logger.error(JOB_CANCEL, e);
			rs.setCode(SYSTEM_ERROR_CODE);
			rs.setMessage(e.getMessage());
		}
		return rs;
	}
}
