package org.ishome.jfp.pdp.api;

import javax.annotation.Resource;

import org.ishome.jfp.framework.beands.RESTResultBean;
import org.ishome.jfp.pdp.service.service.notice.PatientNoticeBusinessImpl;
import org.ishome.jfp.pdp.utils.MyHospitalAPISupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 患者缴费通知
 * 
 * @author Spook
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
//		// 调用实际业务
//		PatientNoticeHAO param = new PatientNoticeHAO();
//		// 业务数据组装
//		PatientInfoBean patient = new PatientInfoBean();
//		patient.setPatientName(patientName);
//		patient.setHosPatCardNo(type);
//		patient.setHosPatCardType(Integer.parseInt(cardId));
//		patient.setCardNo(ID);
//		// 填充其他
//		param.setUserId("111");
//		// param.setBizName(bizName);
//		// param.setPushType(pushType);
//
//		param.setMessage(message);
//		logger.debug(param.toString());
//
//		// 设定消息
//		notice.setPatientNotice(param);
//		notice.run();
		return rs;
	}


}
