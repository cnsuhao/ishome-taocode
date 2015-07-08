package org.ishome.jfp.bds.api.impl;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.ishome.jfp.bds.framework.MyCloundAPISupport;
import org.ishome.jfp.beans.HospitalCloudAccessRule.HospitalCloudAccessRuleDBO;
import org.ishome.jfp.common.Hospital.HospitalInfoService;
import org.ishome.jfp.common.Hospital.HospitalSyncConfigBean;
import org.ishome.jfp.framework.beands.RESTResultBean;
import org.ishome.jfp.framework.job.HospitalCloundKeyService;
import org.ishome.jfp.framework.mq.redis.MyRedis;
import org.ishome.jfp.framework.utils.EmptyHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

/**
 * 手机用户请求处理（云端下发到医院）
 * 
 * @author Spook
 * @version 2.1.1 2015/4/9
 * @version 2.0.0 2015/1/19
 * @since 2.0.0 2015/1/19
 */
@Controller
@Scope("prototype")
public class HospitalBusinessForwardAPI extends MyCloundAPISupport {
	private static final Logger logger = LoggerFactory.getLogger(HospitalBusinessForwardAPI.class);

	/**
	 * 传递等待处理的业务数据
	 */
	@RequestMapping(value = "/v205/hos/biz/{hosId}/{bizName}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean doBiz(
			HttpServletRequest request, 
			@PathVariable String bizName, 
			@PathVariable String hosId,
			String jsonData) {
		RESTResultBean rs = new RESTResultBean();
		logger.debug(HDP + "(hosId)" + hosId);
		logger.debug(HDP + "(bizName)" + bizName);
		logger.debug(HDP + "(dataNum)" + jsonData);
		if (hospitalInfoService.checkCogradient(getIpAddr(request, true), hosId, bizName) == false) {
			rs.setCode(ACCESSS_RTN_CODE_ERROR_HOSPITAL);
			rs.setMessage(ACCESSS_RTN_MESSAGE_ERROR_HOSPITAL);
			return rs;
		}
		// 获得当前医院业务队列
		try
		{
			HospitalSyncConfigBean config = HospitalInfoService.getHosSyncConfig(hosId); 
			ArrayList<Object> jobs = new ArrayList<Object>();
			String redisKey = HospitalCloundKeyService.getHospitalSynchronizationKeyName(hosId, bizName);
			//并发性能
			int dataNum = 10;
			if(EmptyHelper.isEmpty(jsonData)||ZERO.equals(jsonData)){
				try
				{
					HospitalCloudAccessRuleDBO hca = cloundSecyrityConfig.getBizSyncConfigBean(config,bizName);
					if(EmptyHelper.isNotEmpty(hca)&&EmptyHelper.isNotEmpty(hca.getConcurrency()))
						dataNum = Integer.parseInt(hca.getConcurrency()) ;
				}catch(Exception e){
				}
			}else{
				try
				{
					dataNum = Integer.parseInt(jsonData) ;
				}catch(Exception e){
				}
			}
			//8a22b37c4c7a047a014cc6b6b23b0765:TimeSlotRemainQuery:biz:911
			//8a22b37c4c7a047a014cc6b6b23b0765:TimeSlotRemainQuery:biz:911
			long s = mqService.sizeOfList(redisKey);
			if (dataNum > s)
				dataNum = (int) s;			
			//10s超时处理
			while(jobs.size()<=dataNum){				
				//获取出来的业务数据JSON
				Object req = mqService.pollFirstObjectInList(redisKey,false);
				{
					if(EmptyHelper.isEmpty(req))
						break;
				}
				jobs.add(req);				
			}
			if(jobs.size()>0){
				rs.setResult(cloundSecyrityConfig.encryption(config, bizName, JSONArray.toJSONString(jobs)));
				rs.setMessage("已经获得请求数据");
			}
		}catch(Exception e){
			logger.error("对接处理异常...",e);
			rs.setCode(ACCESSS_RTN_CODE_ERROR_HOSPITAL);
			rs.setMessage(e.getMessage());
		}
		return rs;
	}
	
	public static void main(String[] args) {
		ArrayList<Object> jobs = new ArrayList<Object>();
		jobs.add("com.hundsun.med.access.hao.register.TimeSlotRemainQueryHAO//{\"hosId\":\"8a22b37c4c7a047a014cc6b6b23b0765\",\"puk\":\"9c43e3fbfc354411acf38f4f72c8be4b\",\"uuu\":\"2015-07-01 15:23:57\",\"accessSchId\":\"2015-07-02;下午;骨科&李士春\"}");		
		jobs.add("com.hundsun.med.access.hao.register.TimeSlotRemainQueryHAO//{\"hosId\":\"8a22b37c4c7a047a014cc6b6b23b0765\",\"puk\":\"9c43e3fbfc354411acf38f4f72c8be4b\",\"uuu\":\"2015-07-01 15:23:57\",\"accessSchId\":\"2015-07-02;下午;骨科&李士春\"}");		
		jobs.add("com.hundsun.med.access.hao.register.TimeSlotRemainQueryHAO//{\"hosId\":\"8a22b37c4c7a047a014cc6b6b23b0765\",\"puk\":\"9c43e3fbfc354411acf38f4f72c8be4b\",\"uuu\":\"2015-07-01 15:23:57\",\"accessSchId\":\"2015-07-02;下午;骨科&李士春\"}");		
		jobs.add("com.hundsun.med.access.hao.register.TimeSlotRemainQueryHAO//{\"hosId\":\"8a22b37c4c7a047a014cc6b6b23b0765\",\"puk\":\"9c43e3fbfc354411acf38f4f72c8be4b\",\"uuu\":\"2015-07-01 15:23:57\",\"accessSchId\":\"2015-07-02;下午;骨科&李士春\"}");		
		jobs.add("com.hundsun.med.access.hao.register.TimeSlotRemainQueryHAO//{\"hosId\":\"8a22b37c4c7a047a014cc6b6b23b0765\",\"puk\":\"9c43e3fbfc354411acf38f4f72c8be4b\",\"uuu\":\"2015-07-01 15:23:57\",\"accessSchId\":\"2015-07-02;下午;骨科&李士春\"}");		
		String s = JSONArray.toJSONString(jobs);
		
		JSONArray messages = (JSONArray) JSONArray.parseArray(s);
		for(int i =0; i < messages.size();i++)
		System.out.println(MyRedis.getClassFromRedis((String)messages.get(i)));
	}
	
	
	/**
	 * 接收医院处理的结果状态
	 */
	@RequestMapping(value = "/v205/hos/bizresult/{hosId}/{bizName}/{operationId}/{encryType}", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean doBizresult(HttpServletRequest request,
			@PathVariable String bizName, 
			@PathVariable String hosId,
			@PathVariable String operationId, 
			@PathVariable String encryType, 
			String jsonData) {//RESTResultBean
		RESTResultBean rs = new RESTResultBean();
		logger.debug(HDP + "(operationId)" + operationId);

		boolean encryptionType = false;
		if (ENCRYPTION.equals(encryType))
			encryptionType = true;
		// 数据回传解密处理
		//根据请求ID保存， 缓存用户处理结果（HASH）到数据中心
		mqService.putObject(operationId, cloundSecyrityConfig.decryption(HospitalInfoService.getHosSyncConfig(hosId), jsonData, encryptionType),15,false);

		return rs;
	}

}
