package com.hundsun.med.hdp.api;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.hundsun.med.access.beans.PatientInfoBean;
import com.hundsun.med.access.hao.notice.PatientNoticeHAO;
import com.hundsun.med.framework.beands.RESTResultBean;
import com.hundsun.med.framework.utils.HttpServiceHelper;
import com.hundsun.med.hdp.service.server.notice.PatientNoticeBusinessImpl;
import com.hundsun.med.hdp.utils.MyHospitalAPISupport;

/**
 * 患者缴费通知
 * 
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 */
@Controller
public class PatientNoticeAPI extends MyHospitalAPISupport {
	private static final Logger logger = LoggerFactory.getLogger(PatientNoticeAPI.class);

	@Resource
	private PatientNoticeBusinessImpl notice;


	/**
	 * 患者缴费通知
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/Patient/PaymentNotice", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean doPaymentNotice(String patientName, String type, String cardId, String ID, String title, String message) throws Exception {

		RESTResultBean rs = new RESTResultBean();
		// 调用实际业务
		PatientNoticeHAO param = new PatientNoticeHAO();
		// 业务数据组装
		PatientInfoBean patient = new PatientInfoBean();
		patient.setPatientName(patientName);
		patient.setHosPatCardNo(type);
		patient.setHosPatCardType(Integer.parseInt(cardId));
		patient.setCardNo(ID);
		// 填充其他
		param.setUserId("111");
		// param.setBizName(bizName);
		// param.setPushType(pushType);

		param.setMessage(message);
		logger.debug(param.toString());

		// 设定消息
		notice.setPatientNotice(param);
		notice.run();
		return rs;
	}

	public static void main(String[] args) throws Exception {
		// 调用实际业务
		PatientNoticeHAO param = new PatientNoticeHAO();
		// 业务数据组装
		PatientInfoBean patient = new PatientInfoBean();
		patient.setPatientName("田阳");
		patient.setHosPatCardNo("1");
		patient.setHosPatCardType(Integer.parseInt("1"));
		patient.setPhoneNo("18767157572");
		param.setMobilePhone("18767157572");
		
		param.setPatient(patient);
		// 从前置机获取用户Id
		// 填充其他
		param.setUserId("8e1ab47cc2b8419b9ec3afbbbfdefe17");
		// param.setBizName(bizName);
		param.setPushType(1);
		param.setTitle("手机APP推送测试");
		param.setMessage("【恒生芸泰】验证码333333，协和掌医APP提醒您，有效时间为10分钟。如有疑问请联系10086。");

		String response = HttpServiceHelper.doHttpPOST("http://127.0.0.1:8888/v210/hos/clound/2c9082834cc77afb014ccad1c68b0015/PatientNotice/D", JSON.toJSONString(param));
System.out.print(response);
	}

//	public RESTResultBean doSendPatientNotice(String patientName, String type, String cardId, String ID, String title, String message) throws Exception {
//		// 调用实际业务
//		PatientNoticeHAO param = new PatientNoticeHAO();
//		// 业务数据组装
//		PatientInfoBean patient = new PatientInfoBean();
//		patient.setPatientName(patientName);
//		patient.setHosPatCardNo(type);
//		patient.setHosPatCardType(Integer.parseInt(cardId));
//		patient.setCardNo(ID);
//
//		// 从前置机获取用户Id
//		PhoneUserDBO phoneUserDBO = new PhoneUserDBO();
//		phoneUserDBO = phoneUserService.getPhoneUserId(phoneUserDBO);
//		
//		param.setPatient(patient);
//		// 填充其他
//		param.setUserId(phoneUserDBO.getUsId());
//		// param.setBizName(bizName);
//		// param.setPushType(pushType);
//
//		param.setMessage(message);
//		logger.debug(param.toString());
//
//		String response = HttpServiceHelper.doHttpPOST("http://123.56.3.239:8888/v210/hos/clound/2c9082834cc77afb014ccad1c68b0015/PatientNotice/D", JSON.toJSONString(param));
//		return JSON.parseObject(response, RESTResultBean.class);
//	}

}
