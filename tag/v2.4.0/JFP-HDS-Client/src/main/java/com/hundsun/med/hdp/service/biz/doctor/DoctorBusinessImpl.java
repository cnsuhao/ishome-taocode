package com.hundsun.med.hdp.service.biz.doctor;

import org.springframework.stereotype.Service;

import com.hundsun.med.access.hao.sync.DoctorHAO;
import com.hundsun.med.framework.utils.BeanFactoryHelper;
import com.hundsun.med.hdp.service.biz.AHospitalProcessService;
import com.hundsun.med.hdp.service.sync.AHospitalDataSyncService;

/**
 * 医生信息 （手动同步检查）
 * 
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 * @see <DoctorSendService>
 */
@Service("DoctorBusinessImpl")
public class DoctorBusinessImpl extends AHospitalProcessService {

	public DoctorBusinessImpl() {
		setBizName(ACCESSS_DoctorBusiness);
	}

	DoctorHAO param;

	/**
	 * 先判断状态在发送数据
	 */
	@Override
	public boolean doInit() throws Exception {
		param = (DoctorHAO) message;

		// 服务器通知开始对接
		if (JOB_FLAG_WAITING.equals(param.getType()))
			return true;
		// 服务器对接处理失败
		else if (JOB_FLAG_ERROR.equals(param.getType()))
			return true;

		return false;
	}

	/**
	 * 业务处理
	 */
	@Override
	public boolean doProcess() throws Exception {
		// 服务获取
		AHospitalDataSyncService service = BeanFactoryHelper.getBean(SERVICE_TDOC);
		// 运行接收
		if (service != null)
			if (service.doCheck() && service.doProcess() && service.doSend()) {
				return true;
			}
		return false;
	}

	@Override
	public boolean doCheck() throws Exception {
		return true;
	}
}
