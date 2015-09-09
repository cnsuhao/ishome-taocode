package com.hundsun.med.hdp.service.biz.register;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hundsun.med.access.hao.register.CancelRegisterHAO;
import com.hundsun.med.framework.constants.ISJobConstants;
import com.hundsun.med.hdp.service.biz.AHospitalProcessService;
import com.hundsun.med.hdp.service.util.RequestXmlDataUtil;
import com.hundsun.med.hdp.service.util.WebServiceUtil;
import com.mysql.jdbc.StringUtils;

/**
 * 取消挂号
 * 
 * @author fucy
 * @version 2.0.5
 * @since 2.0.5 2015/3/5
 */
@Service("CancelRegisterBusinessImpl")
public class CancelRegisterBusinessImpl extends AHospitalProcessService {
	private static final Logger logger = LoggerFactory.getLogger(CancelRegisterBusinessImpl.class);
	public CancelRegisterBusinessImpl() {
		setBizName(ACCESSS_CancelRegisterBusiness);
	}
	
	CancelRegisterHAO param;
	
	/**
	 * 先判断状态在发送数据
	 */
	@Override
	public boolean doInit() throws Exception {
		logger.debug(message.toString());
		param = (CancelRegisterHAO) message;
		super.setPuk(param.getPuk());
		// 检测医院网络状态
		return true;
	}
	
	/**
	 * 完整性校验
	 */
	@Override
	public boolean doCheck() throws Exception {
		//这里做数据转换
		//医院HIS系统数据状态
		//message
		if (!StringUtils.isNullOrEmpty(param.getAccessRegId()) && !StringUtils.isNullOrEmpty(param.getExceptDate()) && !StringUtils.isNullOrEmpty(param.getAccessSchId()) && !StringUtils.isNullOrEmpty(param.getExceptTime())) {
			return true;
		} else {
			setReturnCode(ACCESSS_RTN_CODE_ERROR_PARAM);
			setReturnMessage("缺少必要的参数,请补全后再试");
			return false;
		}
	}

	/**
	 * 业务处理
	 */
	@Override
	public boolean doProcess() throws Exception {
		logger.info("取消预约挂号");
		String str=RequestXmlDataUtil.CANCEL_REGISTER_XML_REQUEST;
		//必传参数：对接排班id，排班日期，病人就诊卡号，时间段和挂号费用
		String st=str.replaceAll("PATIENTID_STRING", param.getAccessRegId())
				.replaceAll("BDATE_STRING", param.getExceptDate())
				.replaceAll("RBASID_STRING",param.getAccessSchId().replaceAll("_", "&amp;").replaceAll("&", "&amp;"))
				.replaceAll("HBTIME_STRING", param.getExceptTime());
		System.out.println(st);
		String result =WebServiceUtil.getResponseXml(st,RequestXmlDataUtil.WEBSERVICE_URL);
		System.out.println(result);
		
		Document doc=null;
		doc=DocumentHelper.parseText(result);
		Element rootElt=doc.getRootElement();
		Element body=rootElt.element("Body");
		Element doCancelRegisterResponse = body.element("DoCancelRegisterResponse");
		Element response=doCancelRegisterResponse.element("Response");
		Element resultCode=response.element("resultCode");
        Element errorMsg=response.element("errorMsg");
        System.out.println("返回码："+resultCode.getStringValue().trim()+"，返回信息："+errorMsg.getStringValue().trim());
		
		if(resultCode.getStringValue().trim().equals("0")){
			setReturnObject(ZERO);
		}else{
			setReturnCode(ISJobConstants.ACCESSS_RTN_CODE_ERROR_JDBC);
			setReturnMessage(ISJobConstants.ACCESSS_RTN_MESSAGE_ERROR_JDBC);
		}
		return true;
	}
}
