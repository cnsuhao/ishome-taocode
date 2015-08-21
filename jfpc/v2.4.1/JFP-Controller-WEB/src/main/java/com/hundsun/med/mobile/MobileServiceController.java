package com.hundsun.med.mobile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.hundsun.med.beans.HospitalCloudAccessRule.HospitalCloudAccessRuleDBO;
import com.hundsun.med.common.Hospital.HospitalInfoService;
import com.hundsun.med.common.Hospital.HospitalSyncConfigBean;
import com.hundsun.med.framework.beands.BusinessTokenBean;
import com.hundsun.med.framework.beands.RESTResultBean;
import com.hundsun.med.framework.job.HospitalMobileKeyService;
import com.hundsun.med.framework.mq.IMedMqService;
import com.hundsun.med.framework.utils.BeanFactoryHelper;
import com.hundsun.med.framework.utils.DateHelper;
import com.hundsun.med.framework.utils.EmptyHelper;
import com.hundsun.med.framework.utils.PKHelper;
import com.hundsun.med.framework.utils.token.BusinessTokenHelper;
import com.hundsun.med.mobile.biz.MobileDubboBusinessInitSupport;
import com.hundsun.med.mobile.conf.MobileSecyrityConfig;
import com.hundsun.med.mobile.framework.MyMobileServiceAPISupport;

/**
 * Mobile Service Controller <br>
 * 手机业务请求控制器，用于接受请求、转发处理请求和处理结果
 * @version 2.3.1 2015/6/23
 * @since 2.3.1 2015/6/23
 */
@Controller
@Scope("prototype")
public class MobileServiceController extends MyMobileServiceAPISupport {
	private static final Logger logger = LoggerFactory.getLogger(MobileServiceController.class);

	public String getVersion() {
		return "v2.3.1 at 2015/6/23";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView doHome(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("index");
		model.addObject("result", getVersion()+DateHelper.currentTimeMillisCN1());
		return model;
	}

	/**
	 * 接收处理的结果状态
	 */
	@RequestMapping(value = "/{bizToken}", method = RequestMethod.GET)
	public ModelAndView doHomeGET(HttpServletRequest request,
			@PathVariable String bizToken) {
		RESTResultBean rs = new RESTResultBean();
		rs.setCode("9000");
		rs.setMessage("请求方式不对，请使用正确的方式处理");
		
		ModelAndView model = new ModelAndView("index");
		model.addObject("result", JSON.toJSONString(rs));
		return model;
	}

	/**
	 * 接收手机用户请求（请求模式）
	 */
	@RequestMapping(value = "/{hosId}/{userId}/{bizName}/{encryType}/{clientTimestamp}", method = RequestMethod.GET)
	public ModelAndView doHomeGET2(HttpServletRequest request, 
			@PathVariable String hosId, 
			@PathVariable String userId, 
			@PathVariable String bizName, 
			@PathVariable String encryType, 
			@PathVariable String clientTimestamp, 
			String jsonData) {
		RESTResultBean rs = new RESTResultBean();
		rs.setCode("9000");
		rs.setMessage("请求方式不对，请使用正确的方式处理");		
		
		ModelAndView model = new ModelAndView("index");
		model.addObject("result", JSON.toJSONString(rs));
		return model;
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 接收处理的结果状态
	 */
	@RequestMapping(value = "/{templateId}/{operationId}", method = RequestMethod.GET)
	public ModelAndView doGetReportWithTemplate(HttpServletRequest request, 
			@PathVariable String templateId, 
			@PathVariable String operationId) {
		ModelAndView model = new ModelAndView("index");
		try {
			long t1 = System.currentTimeMillis();
			while (true) {
				Thread.sleep(900);
				// ////////////////////////////////////////////////
				// 处理结果返回
				Object result = mqService.getObject(operationId);
				if (EmptyHelper.isNotEmpty(result)) {
					mqService.deleteObject(operationId);
					model.addObject("result", result);
				}
				// ////////////////////////////////////////////////
				if (System.currentTimeMillis() - t1 > 15 * 1000) {// 3 second
					// 从缓存里面获得返回数据并展示到页面
					model.addObject("result", "您的报告还没有完成，请稍后再试");
					break;
				}
			}
		} catch (Exception e) {

		}
		
		return model;
	}
	// ///////////////////////////////////////////////////////////////////////////////////////////////////////
	
	// 缓存队列
	@Resource
	protected IMedMqService mqService;
	// 医院信息类
	@Resource
	protected HospitalInfoService hospitalInfoService;
	// 安全相关类
	@Resource
	protected MobileSecyrityConfig cloundSecyrityConfig;

	/**
	 * 接收手机用户请求（代理模式）
	 */
	@RequestMapping(value = "/{bizToken}", method = RequestMethod.POST)
	public ModelAndView doProcessPOSTWithURL(HttpServletRequest request, 
			@PathVariable String bizToken,
			String jsonData) {
		// 解析请求URI
		BusinessTokenBean b2b = BusinessTokenBean.buildBusinessTokenBean(bizToken);
		String result = doProcess(request, b2b.getCompanyId(), b2b.getUserId(), b2b.getBizName(), b2b.getEncryType(), b2b.getRequestDateTime(), jsonData);
		
		ModelAndView model = new ModelAndView("index");
		model.addObject("result", result);
		return model;
	}

	/**
	 * 接收手机用户请求（请求模式）
	 */
	@RequestMapping(value = "/{hosId}/{userId}/{bizName}/{encryType}/{clientTimestamp}", method = RequestMethod.POST)
	public ModelAndView doProcessPOSTWithParam(HttpServletRequest request, 
			@PathVariable String hosId, 
			@PathVariable String userId, 
			@PathVariable String bizName, 
			@PathVariable String encryType, 
			@PathVariable String clientTimestamp, 
			String jsonData) {		
		String result = doProcess(request, hosId, userId, bizName, encryType, clientTimestamp, jsonData);		
		
		ModelAndView model = new ModelAndView("index");
		model.addObject("result", result);
		return model;
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////////
	HospitalSyncConfigBean hospitalConfig;
	
	/**
	 * 同步模式：Dubbo框架业务驱动，增加业务时候需要重启服务<br>
	 * 异步模式：数据直接压入缓存，其他服务器监听业务
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param companyId
	 *            客户ID
	 * @param userId
	 *            用户ID
	 * @param bizName
	 *            业务名称
	 * @param encryType
	 *            加密模式
	 * @param clientTimestamp
	 *            请求时间（客户端）
	 * @param jsonData
	 *            请求参数
	 * @return 处理结果 JSON字符串
	 */
	public String doProcess(HttpServletRequest request, String companyId, String userId, String bizName, String encryType, String clientTimestamp, String jsonData) {
		// 判断用户权限
		if (hospitalInfoService.checkCogradient(getIpAddr(request, true), companyId, bizName) == false) {
			RESTResultBean rs = new RESTResultBean();
			rs.setCode("9999");
			rs.setMessage("您请求的医院不存在，请联系管理员");
			return JSON.toJSONString(rs);
		}

		RESTResultBean rs = new RESTResultBean();
		try {
			// 客户有效性检查
			hospitalConfig = HospitalInfoService.getHosSyncConfig(companyId);
			if (EmptyHelper.isEmpty(hospitalConfig) || EmptyHelper.isEmpty(hospitalConfig.getHosId())) {
				rs.setCode("9998");
				rs.setMessage("您请求的医院不存在，请联系管理员");
				return JSON.toJSONString(rs);
			}
			
			//初始化环境变量
			s = new StringBuffer(200);
			
			// 业务驱动检查
			HospitalCloudAccessRuleDBO rule = hospitalConfig.getBizConfigs().get(bizName);// 获得请求业务配置信息
			// 开始处理数据
			{
				// 数据解密
				// 数据解密处理
				boolean type = false;
				if (ENCRYPTION.equals(encryType))
					type = true;
				jsonData = cloundSecyrityConfig.decryption(hospitalConfig, jsonData, type);
				// 获得医院版本号---------------------------------------------------------------
				String ver = EMPTY;//HospitalMobileKeyService.getDefaultUseVersion();
				// TODO 没有确定该参数
				if (EmptyHelper.isNotEmpty(rule)&&EmptyHelper.isNotEmpty(rule.getEb5())) {
					ver = HospitalMobileKeyService.getUseVersion(rule.getEb5());
				}
				
				////////////////////////////////////////////////////////////////////////////
				//业务请求ID
				String operationId = PKHelper.creatPUKey();
				////////////////////////////////////////////////////////////////////////////
				
				// 判断信息是同步、异步---------------------------------------------------------------
				// TODO 没有确定该参数
				if (EmptyHelper.isNotEmpty(rule.getFb5())) {
					// 获得服务响应实体类
					MobileDubboBusinessInitSupport bizService = BeanFactoryHelper.getBean(bizName + ver + Mobile_Service_Name);// 获得业务实例
					if (bizService == null) {
						rs.setCode("9991");
						rs.setMessage("Dubbo框架业务驱动不存在，请联系管理员！！！");
						return JSON.toJSONString(rs);
					}
					return doProcessSynchronous(bizService, operationId, companyId, userId, bizName, encryType, clientTimestamp, jsonData, 15);
				} else {
					//keyVersion=
					//业务名称
					//-----:BIZ
					//-----------版本
					//operationId=
					//--------------请求ID//业务参数					
					String bizKeyVersion = HospitalMobileKeyService.getMsrKeyName(bizName, ver);					
					return doProcessasynchronous(bizKeyVersion, operationId, companyId, userId, bizName, encryType, clientTimestamp, jsonData, 15);
				}
			}
		} catch (Exception e) {
			logger.error(companyId + "..." + userId + "..." + clientTimestamp + "..." + jsonData, e);
			// 定义为标准错误返回
			rs.setCode("9990");
			rs.setMessage("系统升级中，请稍后再试");
		}

		return JSON.toJSONString(rs);
	}

	/**
	 * 同步调用
	 * 
	 * @throws Exception
	 */
	public String doProcessSynchronous(MobileDubboBusinessInitSupport bizService, String operationId, 
			String companyId, String userId, String bizName, String encryType, String clientTimestamp, 
			String jsonData, int timeout) {
		// 正常处理
		try {
			logger.debug(MOBILE_SERVICE_START);
			//整理业务数据
			s.append(encryType).append(BusinessTokenHelper.getBizTokenData(userId, companyId, operationId)).append(BACKSLASH2).append(jsonData);
			// 正常处理
			if (bizService.doInit(s.toString()) && bizService.doProcess()) {
				logger.debug(MOBILE_SERVICE_END);
				return bizService.doFinished();
			}
		} catch (Exception e) {
			logger.error(MOBILE_SERVICE_CANCEL + bizName, e);
		}		
		
		// 异常返回
		RESTResultBean rs = new RESTResultBean();
		rs.setCode(SYSTEM_ERROR_CODE);
		rs.setMessage(SYSTEM_ERROR_MESSAGE);
		return JSON.toJSONString(rs);
	}

	/**
	 * 异步调用
	 */
	StringBuffer s;
	public String doProcessasynchronous(String keyVersion, String operationId, 
			String companyId, String userId, String bizName, String encryType, String clientTimestamp, 
			String jsonData, int timeout) {
		// 获得业务实例
		RESTResultBean rs;
		try {
			//制作业务key
			s.append(encryType).append(BusinessTokenHelper.getBizTokenData(userId, companyId, operationId)).append(BACKSLASH2).append(jsonData);
			mqService.offerObjectInList(keyVersion, s.toString(), false);
			long t1 = System.currentTimeMillis();
			while (true) {
				Thread.sleep(300);
				// ////////////////////////////////////////////////
				// 处理结果返回
				Object result = mqService.getObject(operationId);
				if (EmptyHelper.isNotEmpty(result)) {
					mqService.deleteObject(operationId);
					return EMPTY + result;
				}
				// ////////////////////////////////////////////////
				if (System.currentTimeMillis() - t1 > timeout * 1000) {// 3 second
					rs = new RESTResultBean();
					rs.setCode(ONE);
					rs.setResult(MESSAGE_WAITING);
				}
			}
		} catch (Exception e) {
			logger.error(companyId + "..." + userId + "..." + clientTimestamp + "..." + jsonData, e);
			rs = new RESTResultBean();
			rs.setCode(SYSTEM_ERROR_CODE);
			rs.setMessage(SYSTEM_ERROR_MESSAGE);
		}
		return JSON.toJSONString(rs);
	}
}
