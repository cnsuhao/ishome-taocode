package com.hundsun.med.hdp.service.biz.good;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hundsun.med.access.beans.GoodOrderInfoBean;
import com.hundsun.med.access.hao.good.GoodOrderHAO;
import com.hundsun.med.hdp.service.biz.AHospitalProcessService;

/**
 * 订单创建
 * 
 * @author fucy
 * 
 */
@Service("GoodOrderBusinessImpl")
public class GoodOrderBusinessImpl extends AHospitalProcessService {
	private static final Logger logger = LoggerFactory.getLogger(GoodOrderBusinessImpl.class);

	public GoodOrderBusinessImpl() {
		setBizName(ACCESSS_GoodOrderBusiness);
	}

	GoodOrderHAO param;
	GoodOrderInfoBean ret = new GoodOrderInfoBean();

	/**
	 * 先判断状态在发送数据
	 */
	@Override
	public boolean doInit() throws Exception {
		logger.debug(message.toString());
		param = (GoodOrderHAO) message;
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
