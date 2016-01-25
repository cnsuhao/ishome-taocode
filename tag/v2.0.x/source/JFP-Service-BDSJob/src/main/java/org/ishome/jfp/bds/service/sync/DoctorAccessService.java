package org.ishome.jfp.bds.service.sync;

import java.util.List;

import org.ishome.jfp.beans.HospitalCloudAccessRule.HospitalCloudAccessRuleDBO;
import org.ishome.jfp.framework.beands.FrameworkDataBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 医生信息
 * 
 * @author Spook
 * @version 2.0.0
 * @see <DoctorAccessSaveThread>
 * @since 2.0.0 2015/1/19
 */
public class DoctorAccessService extends AHospitalDataAccessService {
	private static final Logger logger = LoggerFactory.getLogger(DoctorAccessService.class);

	@Override
	public boolean doProcessRepeat() throws Exception {
		logger.debug(JOB_SAVE_START);
		List<FrameworkDataBean> hosIdList = HospitalInfoService_.loadJobByBizName("Doctor", ONE);

		if (hosIdList.isEmpty()) {
			logger.info("没有要对接的医院");
			return false;
		}

		// 开启线程组来处理所有医院数据
		for (FrameworkDataBean hos : hosIdList) {
			String hosId = ((HospitalCloudAccessRuleDBO) hos).getHosId();
			try {
				doProcessOnce(hosId);
			} catch (Exception e) {
				logger.error("医生对接失败..." + hosId,e);
			}
		}
		logger.debug(JOB_SAVE_END);

		return true;
	}

	@Override
	public boolean doProcessOnce(String hosId) throws Exception {
//		// 检查医院ID
//		if (StringUtils.isEmpty(hosId))
//			return false;
//		// 等待处理数据量过多处理）
//		String key = HospitalJobKeyService.getSyncJobDataKeyName(hosId, ACCESSS_DoctorBusiness + Synchronization_Result);
//		
//		// 清理对接错误信息
//		{
//			AccessFaultLogDBO logDBO = new AccessFaultLogDBO();
//			logDBO.setHosId(hosId);
//			logDBO.setState(BigDecimal.ONE);
//			logDBO.setType(BigDecimal.ZERO);
//			logDBO.setErrorMetaId(ACCESSS_DoctorBusiness);// 错误类型ID 
//			AccessFaultLogogService AccessFaultLogogService_ = BeanFactoryHelper.getBean("accessFaultLogogService");
//			AccessFaultLogogService_.toDelete(logDBO);
//		}
//		
//		//开始同步数据
//		SyncShareData syncShareData = new SyncShareData();
//		DoctorAccessSaveThread dast = new DoctorAccessSaveThread(hosId, HospitalInfoService_);
//		while(true){
//			Object hos_data = mq.pollFirstObjectInList(key);
//			if(EmptyHelper.isEmpty(hos_data))
//				break;
//			// 开启处理业务
//			dast.setSyncShareData(syncShareData);
//			dast.setData((String)hos_data);
//			dast.run();
//			while(dast.isAlive()){
//				Thread.sleep(100);
//			}
//		}
		return true;
	}
}
