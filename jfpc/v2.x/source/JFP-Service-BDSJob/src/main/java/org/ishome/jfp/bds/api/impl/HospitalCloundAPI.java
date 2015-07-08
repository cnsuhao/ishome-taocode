package org.ishome.jfp.bds.api.impl;

import javax.servlet.http.HttpServletRequest;

import org.ishome.jfp.bds.framework.MyCloundAPISupport;
import org.ishome.jfp.bds.service.biz.ACloundBusinessSupport;
import org.ishome.jfp.bds.service.sec.HeartDockingImpl;
import org.ishome.jfp.common.Hospital.HospitalInfoService;
import org.ishome.jfp.common.Hospital.HospitalSyncConfigBean;
import org.ishome.jfp.framework.beands.RESTResultBean;
import org.ishome.jfp.framework.utils.BeanFactoryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 医院系统请求处理（云端接收医院请求）
 * 
 * @author Spook
 * @version 2.1.0 2015/3/30
 * @version 2.0.1 2015/2/3
 * @version 2.0.0 2015/1/19
 * @since 2.0.0 2015/1/19
 */
@Controller
@Scope("prototype")
public class HospitalCloundAPI extends MyCloundAPISupport {
	private static final Logger logger = LoggerFactory.getLogger(HeartDockingImpl.class);

	//  http://127.0.0.1:8888/v210/hos/clound/123456/heart/D
	@RequestMapping(value = "/v210/hos/clound/{hosId}/{bizName}/{encryType}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean doHospitalCloundAPI(
			HttpServletRequest request, 
			@PathVariable String hosId,
			@PathVariable String bizName, 
			@PathVariable String encryType, 
			String jsonData) {
		RESTResultBean rs = new RESTResultBean();
		if(logger.isDebugEnabled()){
			logger.debug(HDS + "(hosId)" + hosId);
			logger.debug(HDS + "(bizName)" + bizName);
		}
		// 激活线程
		try {
			logger.debug("业务处理开始>>>>>>>>>>");

			ACloundBusinessSupport acbs = BeanFactoryHelper.getBean(bizName + Clound_Service_Name);
			if (acbs == null)
				throw new Exception("业务没有定义");
			
			// 初始化参数
			HospitalSyncConfigBean hospitalConfig = HospitalInfoService.getHosSyncConfig(hosId);
			//hospitalConfig =  new HospitalSyncConfigBean();
			if(hospitalConfig==null){
				rs.setCode(ONE);
				rs.setMessage("医院没有开通对接业务，请联系管理员");
				return rs;
			}
			
			if(logger.isDebugEnabled())
				logger.debug("hospitalConfig=====" + hospitalConfig);
			
			//数据解密处理
			boolean encryptionType = false;
			if (ENCRYPTION.equals(encryType))
				encryptionType = true;
			jsonData =  cloundSecyrityConfig.decryption(hospitalConfig, jsonData, encryptionType);
			
			//线程初始化
			acbs.doInit(mqService, hospitalConfig, hospitalInfoService, cloundSecyrityConfig, jsonData);
			acbs.setHosId(hosId);
			//进行业务处理
			if (acbs.doInit() && acbs.doCheck() && acbs.doProcess()) {
				logger.debug("业务处理结束<<<<<<<<<<<");
			}
			
			// 返回医院结果
			rs = acbs.getResult();
		} catch (Exception e) {
			rs.setCode(ONE);
			rs.setMessage("服务发生未知异常：" + e.getMessage());
			logger.error("服务发生未知异常", e);
		}

		return rs;
	}

}
