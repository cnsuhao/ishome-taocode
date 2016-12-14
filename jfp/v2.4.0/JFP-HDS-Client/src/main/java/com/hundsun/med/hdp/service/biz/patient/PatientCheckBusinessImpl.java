package com.hundsun.med.hdp.service.biz.patient;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hundsun.med.access.beans.PatientInfoBean;
import com.hundsun.med.access.hao.patient.PatientCheckHAO;
import com.hundsun.med.framework.constants.ISJobConstants;
import com.hundsun.med.hdp.service.biz.AHospitalProcessService;
import com.hundsun.med.hdp.service.util.RequestXmlDataUtil;
import com.hundsun.med.hdp.service.util.WebServiceUtil;

/**
 * 校验病人信息
 * 
 * @author fucy
 * @version 2.0.5
 * @since 2.0.5 2015/3/5
 */
@Service("PatientCheckBusinessImpl")
public class PatientCheckBusinessImpl extends AHospitalProcessService {
	private static final Logger logger = LoggerFactory.getLogger(PatientCheckBusinessImpl.class);

	public PatientCheckBusinessImpl() {
		setBizName(ACCESSS_PatientCheckBusiness);
	}

	PatientCheckHAO param;
	
	/**
	 * 先判断状态在发送数据
	 */
	@Override
	public boolean doInit() throws Exception {
		logger.debug(message.toString());
		param = (PatientCheckHAO) message;
		//还原操作请求
		super.setPuk(param.getPuk());
		// 检测医院网络状态
		return true;
	}

	/**
	 * 完整性校验
	 */
	@Override
	public boolean doCheck() throws Exception {
		return true;
	}


	/**
	 * 业务处理
	 */
	@Override
	public boolean doProcess() throws Exception {
		PatientInfoBean patientBean = new PatientInfoBean();
		patientBean.setAccessPatId("1234567890");
		patientBean.setPatientName("000000");
		patientBean.setHosPatCardNo("987654321");
		patientBean.setHosPatCardType(1);
		patientBean.setPhoneNo("15057177411");
		patientBean.setSex("男");
		patientBean.setAge("26");
		setReturnObject(patientBean);
		// 保存需要发送的数据
		return true;
	}
}
