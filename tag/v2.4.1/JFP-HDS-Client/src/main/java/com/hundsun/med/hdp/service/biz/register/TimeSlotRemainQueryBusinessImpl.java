package com.hundsun.med.hdp.service.biz.register;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hundsun.med.access.beans.SchedulingNumberSourceInfoBean;
import com.hundsun.med.access.hao.register.TimeSlotRemainQueryHAO;
import com.hundsun.med.framework.constants.ISJobConstants;
import com.hundsun.med.hdp.service.biz.AHospitalProcessService;
import com.hundsun.med.hdp.service.util.RequestXmlDataUtil;
import com.hundsun.med.hdp.service.util.WebServiceUtil;
import com.mysql.jdbc.StringUtils;

/**
 * 号源信息
 * 
 * @author fucy
 * @version 2.0.5
 * @since 2.0.5 2015/3/5
 */
@Service("TimeSlotRemainQueryBusinessImpl")
public class TimeSlotRemainQueryBusinessImpl extends AHospitalProcessService {
	private static final Logger logger = LoggerFactory.getLogger(TimeSlotRemainQueryBusinessImpl.class);
	public TimeSlotRemainQueryBusinessImpl() {
		setBizName(ACCESSS_TimeSlotRemainQueryBusiness);
	}
	
	TimeSlotRemainQueryHAO param;
	ArrayList<SchedulingNumberSourceInfoBean> ret = new ArrayList<SchedulingNumberSourceInfoBean>();
	
	/**
	 * 先判断状态在发送数据
	 */
	@Override
	public boolean doInit() throws Exception {
		logger.debug(message.toString());
		param = (TimeSlotRemainQueryHAO) message;
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
		if (!StringUtils.isNullOrEmpty(param.getAccessSchId())) {
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
		logger.info("开始取排班下面的时间段以及时间段内的余号和限号");
		String str = RequestXmlDataUtil.GET_ARRANGEMENT_HBTIME_REQUEST;
		String st = str.replaceAll("RBASID_STRING", param.getAccessSchId().replaceAll("_", "&amp;").replaceAll("&", "&amp;"));
		System.out.println(st);
		String result = WebServiceUtil.getResponseXml(st, RequestXmlDataUtil.WEBSERVICE_URL);
		System.out.println(result);

		Document doc = null;
		doc = DocumentHelper.parseText(result);
		Element rootElt = doc.getRootElement();
		Element body = rootElt.element("Body");
		Element getHbTimeResponse = body.element("GetHbTimeResponse");
		Element response = getHbTimeResponse.element("Response");
		Element resultCode = response.element("ResultCode");
		Element errorMsg = response.element("ErrorMsg");
		System.out.println("返回码：" + resultCode.getStringValue().trim() + "，返回信息：" + errorMsg.getStringValue().trim());

		List<SchedulingNumberSourceInfoBean> schInfoBeanList = new ArrayList<SchedulingNumberSourceInfoBean>();
		if (resultCode.getStringValue().trim().equals(ISJobConstants.ACCESSS_RTN_CODE_SUCCESS)) {
			Element element = response.element("HbTimes");

			Iterator it = element.elementIterator();
			while (it.hasNext()) {
				Element ele = (Element) it.next();
				SchedulingNumberSourceInfoBean schInfoBnean = new SchedulingNumberSourceInfoBean();
				schInfoBnean.setAccessSchId(ele.elementTextTrim("RBASId"));
				schInfoBnean.setAccessSchTime(ele.elementTextTrim("RBASId").split(";")[0]);
				// 时间段内的余号
				schInfoBnean.setTimeRemainNo(ele.elementTextTrim("OverPlusNo"));
				// 时间段内的限号
				// schInfoBnean.setTimeResNo(ele.elementTextTrim("ResNo"));
				schInfoBnean.setTime(ele.elementTextTrim("HbTime"));
				schInfoBeanList.add(schInfoBnean);
				System.out.println(ele.elementTextTrim("RBASId")+"--"+ele.elementTextTrim("RBASId").split(";")[0]+"--"+ele.elementTextTrim("OverPlusNo")+"--"+ele.elementTextTrim("OverPlusNo")+"--"+ele.elementTextTrim("HbTime"));
			}
			setReturnObject(schInfoBeanList);
		} else {
			setReturnCode(ISJobConstants.ACCESSS_RTN_CODE_ERROR_NODATA);
			setReturnMessage(ISJobConstants.ACCESSS_RTN_MESSAGE_ERROR_NODATA);
		}
		logger.info("取排班下面的时间段以及时间段内的余号和限号结束");
		return true;
	}
}
