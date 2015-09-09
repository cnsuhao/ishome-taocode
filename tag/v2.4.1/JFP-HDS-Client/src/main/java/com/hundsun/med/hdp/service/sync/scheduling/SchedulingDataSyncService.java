package com.hundsun.med.hdp.service.sync.scheduling;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hundsun.med.framework.beands.RESTResultBean;
import com.hundsun.med.framework.utils.HttpServiceHelper;
import com.hundsun.med.hdp.service.sync.AHospitalDataSyncService;

/**
 * 医生排班信息
 * 
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * @see <SchedulingSendThread>
 */
public class SchedulingDataSyncService extends AHospitalDataSyncService {
	private static final Logger logger = LoggerFactory.getLogger(SchedulingDataSyncService.class);
	
	/**
	 * 完整性校验
	 */
	@Override
	public boolean doCheck() throws Exception {
		//检查本地配置是否开启
		return true;
	}
	
	public boolean doProcessOnce(String hosId) throws Exception {
		logger.debug(JOB_SEND_START);
		if (doInit() && doCheck() && doProcess() && doSend()) {
			logger.debug("排班数据同步结束.....");
		} else {
			logger.debug("排班数据同步失败xxxxx");
		}
		this.setReturnCode(ZERO);
		this.setReturnMessage("数据同步结束");
		logger.debug(JOB_SEND_END);
		return true;
	}
	
	/**
	 * 业务处理
	 */
	@Override
	public boolean doProcess() throws Exception {
		//开始同步
		logger.debug("排班数据同步开始...");
		
		String response = HttpServiceHelper.doHttpPOST(cloundApiConfig.getHospitalUrl() + getBizName(), "{}");
		rs = JSON.parseObject(response, RESTResultBean.class);
		if (ZERO.equals(rs.getCode())) {
			List<Object> messages = (List<Object>) JSONArray.parseArray((String) rs.getResult());
			List<Object> list = new ArrayList<Object>();
			for (Object fdb : messages) {
				list.add(fdb);
				if (list.size() >= 100) {
					// 保存需要发送的数据
					setReturnObject(list);
					doSend();
					list.clear();
				}
			}
			doSend();
		}
		
		//结束同步
		logger.debug("排班数据同步结束...");
		return true;
	}

}
