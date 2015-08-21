package com.hundsun.med.hdp.service.biz.price;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hundsun.med.access.beans.DiagnosisInfoBean;
import com.hundsun.med.access.hao.price.DiagnosisPriceListHAO;
import com.hundsun.med.hdp.service.biz.AHospitalProcessService;

/**
 * 诊疗项目查询
 * 
 * @author fucy
 * 
 */
@Service("DiagnosisPriceListBusinessImpl")
public class DiagnosisPriceListBusinessImpl extends AHospitalProcessService {
	private static final Logger logger = LoggerFactory.getLogger(DiagnosisPriceListBusinessImpl.class);

	public DiagnosisPriceListBusinessImpl() {
		setBizName(ACCESSS_DiagnosisPriceListBusiness);
	}

	DiagnosisPriceListHAO param;
	ArrayList<DiagnosisInfoBean> ret = new ArrayList<DiagnosisInfoBean>();

	/**
	 * 先判断状态在发送数据
	 */
	@Override
	public boolean doInit() throws Exception {
		logger.debug(message.toString());
		param = (DiagnosisPriceListHAO) message;
		super.setPuk(param.getPuk());
		// 检测医院网络状态
		return true;
	}

	/**
	 * 完整性校验
	 */
	@Override
	public boolean doCheck() throws Exception {
		// 这里做数据转换
		// 医院HIS系统数据状态
		// message
		return true;
	}

	/**
	 * 业务处理
	 */
	@Override
	public boolean doProcess() throws Exception {
		// 整理数据
		// 每一个业务请求具有唯一标识
		// TODO BaseHAO message

		// 获取HIS系统数据，封装出口

		// 保存需要发送的数据
		setReturnObject(ret);
		return true;
	}
}
