package org.ishome.jfp.bds.api.impl;

import javax.servlet.http.HttpServletRequest;

import org.ishome.jfp.bds.framework.MyCloundAPISupport;
import org.ishome.jfp.bds.service.sync.AHospitalDataAccessService;
import org.ishome.jfp.common.Hospital.HospitalInfoService;
import org.ishome.jfp.framework.beands.RESTResultBean;
import org.ishome.jfp.framework.job.HospitalJobKeyService;
import org.ishome.jfp.framework.utils.BeanFactoryHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

/**
 * 医院数据对接同步
 * 
 * @author Spook
 * @version 2.0.1 2015/2/3
 * @version 2.0.0 2015/1/19
 * @since 2.0.0 2015/1/19
 */
@Controller
@Scope("prototype")
public class HospitalDataSynchronizationAPI extends MyCloundAPISupport {
	private static final Logger logger = LoggerFactory.getLogger(HospitalDataSynchronizationAPI.class);

	/**
	 * 接收医院同步数据
	 */
	// /v200/hos/sync/123456/department/D
	@RequestMapping(value = "/v200/hos/sync/{hosId}/{bizName}/{encryType}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean doReceive(HttpServletRequest request, @PathVariable String hosId, @PathVariable String bizName, @PathVariable String encryType, String jsonData, String doNow) {
		RESTResultBean rs = new RESTResultBean();
		if(logger.isDebugEnabled()){
			logger.debug(HDS + "(hosId)" + hosId);
			logger.debug(HDS + "(bizName)" + bizName);
			logger.debug(HDS + jsonData);
		}
		// 检查医院接口对接合法性
		if (hospitalInfoService.checkCogradient(getIpAddr(request, true), hosId, bizName) == false) {
			rs.setCode("1");
			rs.setMessage("不存在当前客户信息");
			logger.debug("数据同步失败...");
			return rs;
		}
		// 检查医院数据
		if (StringUtils.isEmpty(jsonData)) {
			rs.setCode("2");
			rs.setMessage("同步数据为空");
			logger.debug("数据同步失败...");
			return rs;
		}
		// 解密设定
		boolean encryptionType = false;
		if (ENCRYPTION.equals(encryType))
			encryptionType = true;
		// 直接缓存到数据中心
		rs = JSON.parseObject(jsonData, RESTResultBean.class);
		if (ONE.equals(rs.getCode())) {
			rs.setCode("3");
			rs.setMessage("前置机发生未知异常");
			logger.debug("数据同步失败...");
		} else {
			mqService.offerObjectInList(HospitalJobKeyService.getSyncJobDataKeyName(hosId, bizName + Synchronization_Result), cloundSecyrityConfig.decryption(HospitalInfoService.getHosSyncConfig(hosId), (String) rs.getResult(), encryptionType));
		}

		// 启动执行现成处理
		if (ONE.equals(doNow)) {
			try {
				AHospitalDataAccessService ahds = BeanFactoryHelper.getBean(bizName + "AccessService");
				ahds.setHosId(hosId);
				(new Thread(ahds)).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rs;
	}

	/**
	 * 返回对接结果状态
	 */
	// http://127.0.0.1:8888/v200/hos/synccheck/123456/department
	@RequestMapping(value = "/v200/hos/synccheck/{hosId}/{bizName}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean doReceiveCheck(HttpServletRequest request, @PathVariable String hosId, @PathVariable String bizName, String jsonData) {
		RESTResultBean rs = new RESTResultBean();
		if(logger.isDebugEnabled()){
			logger.debug(HDS + "(hosId)" + hosId);
			logger.debug(HDS + "(bizName)" + bizName);
		}
		// 同步处理结果
		rs.setResult(HospitalJobKeyService.getSyncJobType(hosId, bizName));
		return rs;
	}

}
