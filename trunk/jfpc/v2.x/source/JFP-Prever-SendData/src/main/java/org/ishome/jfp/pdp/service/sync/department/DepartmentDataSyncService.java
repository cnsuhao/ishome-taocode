package org.ishome.jfp.pdp.service.sync.department;

import java.util.ArrayList;
import java.util.List;

import org.ishome.jfp.framework.beands.RESTResultBean;
import org.ishome.jfp.framework.utils.HttpServiceHelper;
import org.ishome.jfp.pdp.service.sync.AHospitalDataSyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * 科室信息
 * 
 * @author Spook
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * @see <DepartmentSendThread>
 */
public class DepartmentDataSyncService extends AHospitalDataSyncService {
	private static final Logger logger = LoggerFactory.getLogger(DepartmentDataSyncService.class);

	/**
	 * 完整性校验
	 */
	@Override
	public boolean doCheck() throws Exception {
		// 检查本地配置是否开启
		return true;
	}

	public boolean doProcessOnce(String hosId) throws Exception {
		logger.debug(JOB_SEND_START);
		// 科室同步
		if (doInit() && doCheck() && doProcess() && doSend()) {
			logger.debug("门诊科室数据同步成功.....");
		} else {
			logger.debug("门诊科室数据同步失败xxxxx");
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
		// 开始科室同步
		logger.debug("科室数据同步开始...");

		List<Object> list = new ArrayList<Object>();
		setReturnObject(list);
		doSend();
		return true;
	}

}
