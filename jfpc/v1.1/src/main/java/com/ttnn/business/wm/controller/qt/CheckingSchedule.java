package com.ttnn.business.wm.controller.qt;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ttnn.business.wm.biz.WMCheckOrderBussiness;
import com.ttnn.business.wm.service.WMBM02Service;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;

public class CheckingSchedule {

	@Resource
	WMBM02Service WMBM02Service_; //充值记录查询
    
	@Resource WMCheckOrderBussiness  WMCheckOrderBussiness_; //订单核对
	
	public Logger getLogger() {
		return LoggerFactory.getLogger(CheckingSchedule.class);
	}

	/**
	 * 定时与系统外部对账
	 */
	public void doc() {
		// 轮训时间创建开始后15分钟->1小时,自动
		CSPVOSupport bean = new CSPVOSupport();
		bean.setF11("0");
		List<FrameworkDataBean> databean = WMBM02Service_.doFindList(bean); // 查询所有未核对的
		for (int i = 0; databean != null && i < databean.size(); i++) {
			FrameworkDataBean db = databean.get(i); // 充值记录
			try {
				WMCheckOrderBussiness_.doCheckOrder(db);
			} catch (Exception e) {
				e.printStackTrace();
				getLogger().error(
						"订单核对失败:失败原因[" + e.getMessage() + "],订单号为:"
								+ db.getPuk());
			}
		}

	}

	/**
	 * 定时核对所有的通道余额与充值记录
	 */
	public void checkAll() {

	}

}
