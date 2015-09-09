package com.hundsun.med.hdp.service.biz.register;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hundsun.med.access.beans.RegisterInfoBean;
import com.hundsun.med.access.hao.register.OrderRegisterHAO;
import com.hundsun.med.framework.constants.ISJobConstants;
import com.hundsun.med.hdp.service.biz.AHospitalProcessService;
import com.hundsun.med.hdp.service.util.RequestXmlDataUtil;
import com.hundsun.med.hdp.service.util.WebServiceUtil;
import com.mysql.jdbc.StringUtils;

/**
 * 预约挂号
 * 
 * @author fucy
 * @version 2.0.5
 * @since 2.0.5 2015/3/5
 */
@Service("OrderRegisterBusinessImpl")
public class OrderRegisterBusinessImpl extends AHospitalProcessService {
	private static final Logger logger = LoggerFactory.getLogger(OrderRegisterBusinessImpl.class);
	public OrderRegisterBusinessImpl() {
		setBizName(ACCESSS_OrderRegisterBusiness);
	}
	
	OrderRegisterHAO param;
	RegisterInfoBean ret = new RegisterInfoBean();//患者挂号信息
	
	/**
	 * 先判断状态在发送数据
	 */
	@Override
	public boolean doInit() throws Exception {
		logger.debug(message.toString());
		param = (OrderRegisterHAO) message;
		super.setPuk(param.getPuk());
		// 检测医院网络状态
		return true;
	}
	
	/**
	 * 完整性校验
	 */
	@Override
	public boolean doCheck() throws Exception {
		//就诊卡号、排班id、预约日期、费用、时间段
		if (!StringUtils.isNullOrEmpty(param.getHosPatCardNo())
				&& !StringUtils.isNullOrEmpty(param.getAccessSchId())
				&& !StringUtils.isNullOrEmpty(param.getAccessSchTime()) && 
				!StringUtils.isNullOrEmpty(param.getCost().toString())
				&& !StringUtils.isNullOrEmpty(param.getTime())) {
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
		logger.info("预约开始");
		String str=RequestXmlDataUtil.DOREGISTER_XML_REQUEST;
		//必传参数：对接排班id，排班日期，病人就诊卡号，时间段和挂号费用
		String st=str.replaceAll("PATIENT_STRING", param.getHosPatCardNo())
				.replaceAll("REASID_STRING", param.getAccessSchId().replaceAll("_", "&amp;").replaceAll("&","&amp;"))
				.replaceAll("BDATE_STRING", param.getAccessSchTime())
				.replaceAll("FEE_STRING", param.getCost().toString())
				.replaceAll("HTIME_STRINHG", param.getTime());
		//苗先杰已经在服务端做过处理
		/*if(st.contains("&")){
			st.replaceAll("&", "&amp;");
		}*/
		System.out.println(str);
		String result =WebServiceUtil.getResponseXml(st,RequestXmlDataUtil.WEBSERVICE_URL);
		
		Document doc=null;
		doc=DocumentHelper.parseText(result);
		Element rootElt=doc.getRootElement();
		Element body=rootElt.element("Body");
		Element getArrangementResponse = body.element("DoRegisterResponse");
		Element response=getArrangementResponse.element("Response");
		Element resultCode=response.element("ResultCode");
        Element errorMsg=response.element("ErrorMsg");
        System.out.println("返回码："+resultCode.getStringValue().trim()+"，返回信息："+errorMsg.getStringValue().trim());
        
        if(resultCode.getStringValue().trim().equals("0")){
        	RegisterInfoBean registerBean=new RegisterInfoBean();
        	registerBean.setAccessRegId(param.getHosPatCardNo());
        	registerBean.setAccessSchId(param.getAccessSchId());
        	registerBean.setCost(param.getCost());
        	setReturnObject(registerBean);
        }else{
        	setReturnCode(ISJobConstants.ACCESSS_RTN_CODE_ERROR_NODATA);
			setReturnMessage(ISJobConstants.ACCESSS_RTN_MESSAGE_ERROR_NODATA);
        }
		return true;
	}
}
