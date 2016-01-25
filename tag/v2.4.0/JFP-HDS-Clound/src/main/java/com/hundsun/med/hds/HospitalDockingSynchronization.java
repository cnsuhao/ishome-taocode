package com.hundsun.med.hds;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.ishome.jfp.framework.beands.common.FrameworkDataBean;
import org.ishome.jfp.framework.beands.common.RESTResultBean;
import org.ishome.jfp.framework.cache.ICacheService;
import org.ishome.jfp.framework.cache.mq.MQHelper;
import org.ishome.jfp.framework.constants.ISFrameworkConstants;
import org.ishome.jfp.framework.constants.pub.ISJobConstants;
import org.ishome.jfp.framework.mq.redis.MyRedis;
import org.ishome.jfp.framework.utils.DateHelper;
import org.ishome.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;

/**
 * Heart docking synchronization 医院数据推送
 * 
 * @version 2.0.0
 * @see <HospitalDataAcquisitionJob>
 * @since 2.0.0 2015/1/22
 */
// com.hundsun.med.access.hao.patient.PatientCheckHAO//{"puk":"d3de1a6390a240238082a2435f311e9f","uuu":"2015-07-03 05:56:22"}
@Controller
public class HospitalDockingSynchronization implements ISFrameworkConstants, ISJobConstants {
	private Logger logger = LoggerFactory.getLogger(HospitalDockingSynchronization.class);

	// 缓存队列
	@Resource
	protected ICacheService mq;

	public String getVersion() {
		return "v2.1.0 at 2015/4/1";
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("index");
		model.addObject("ver", getVersion());
		model.addObject("now", DateHelper.currentTimeMillisCN1());
		model.addObject("jsonData", "请输入JSON格式的参数，基于转换后的字符串");
		return model;
	}

	@RequestMapping(value = "/APITest", method = RequestMethod.GET)
	public String doAPITestGET() {
		return "redirect:/";
	}

	/**
	 * 接收处理的结果状态
	 */
	@RequestMapping(value = "/APITest", method = RequestMethod.POST)
	public ModelAndView doAPITestPOST(HttpServletRequest request, String actionURL, String operationId, String bizName, String hosId, String jsonData) {
		logger.debug("hosId===>>>" + hosId);
		logger.debug("bizName===>>>" + bizName);
		logger.debug("jsonData===>>>" + jsonData);
		// ////////////////////////////////////
		ModelAndView model = new ModelAndView("index");
		model.addObject("ver", getVersion());
		model.addObject("now", DateHelper.currentTimeMillisCN1());
		try {
			model.addObject("actionURL", bizName);
			// 创建基本配置信息
			HospitalSyncConfigBean config = HospitalInfoService.getHosSyncConfig(hosId);
			config.setHosId(hosId);
			// 创建对接配置信息
			HospitalCloudAccessRuleDBO hcard = config.getBizConfigs().get(bizName);
			if (hcard == null) {
				hcard = new HospitalCloudAccessRuleDBO();
				hcard.setModuleType(bizName);
				hcard.setAccessFlag(ONE);
				hcard.setFb3(TWO);
			}
			config.setBizConfig(hcard);
			HospitalInfoService.setHosSyncConfig(config);
			// ////////////对接调用////////////////////////
			RESTResultBean res = null;
			if (bizName.indexOf("sync") > 0) {
				res = getHospitalReultSync(getOperationId(operationId, jsonData), hosId, bizName, jsonData, 10);
			} else {
				res = NewAccessMessageSender.getHospitalReult(getOperationId(operationId, jsonData), hosId, bizName, jsonData);
			}

			logger.debug("RESTResultBean===>>>" + res);
			model.addObject("jsonData", res.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}

	public String getOperationId(String operationId, String jsonData) {
		try {
			FrameworkDataBean fdb = (FrameworkDataBean) MyRedis.getClassFromRedis(jsonData);
			return fdb.getPuk();
		} catch (Exception e) {

		}
		return operationId;
	}

	public static RESTResultBean getHospitalReultSync(String operationId, String hosId, String bizName, String jsonData, int timeout) {
		// ////////////////////////////////////////////////
		RESTResultBean rs;
		IMedMqService mq = MQHelper.getMqService();
		// 发送数据
		mq.putObject(getKeyName(hosId, bizName), jsonData, 15, false);
		long t1 = System.currentTimeMillis();
		try {
			while (true) {
				Thread.sleep(300);
				// ////////////////////////////////////////////////
				// 处理结果返回
				String result = (String) mq.getObject(operationId, false);
				if (EmptyHelper.isNotEmpty(result)) {
					mq.deleteObject(operationId);
					return JSON.parseObject(result, RESTResultBean.class);
				}
				// ////////////////////////////////////////////////
				if (System.currentTimeMillis() - t1 > timeout * 1000) {//3 second
					rs = new RESTResultBean();
					rs.setCode(ONE);
					rs.setResult("网络访问超时");
					return rs;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new RESTResultBean();
	}

	// /////////////////////////////////////////////////////////////////////////////

	/**
	 * 获得对接程序使用版本(目前不支持到业务)
	 * 
	 * @param hosId
	 * @param bizName
	 * @return
	 */
	public static String getUseVersion(String hosId, String bizName) {
		// HospitalSyncConfigBean config = getHosSyncConfig(hosId);
		// TODO
		// return config.getAppVersion();
		return APP_SERVER_VERSION;
	}

	final static String APP_SERVER_VERSION = "911";

	/**
	 * 获得对接业务名称(客户数据)
	 * 
	 * @param hosId
	 *            医院ID
	 * @return
	 */
	public static String getKeyName(String hosId, String bizName) {
		// Key = 医院ID:业务名称(英字):对接程序所属版本号
		StringBuffer key = new StringBuffer();
		key.append(hosId);
		key.append(COLON);
		key.append(bizName);
		key.append(COLON);
		key.append(getUseVersion(hosId, bizName));
		return key.toString();
	}
}
