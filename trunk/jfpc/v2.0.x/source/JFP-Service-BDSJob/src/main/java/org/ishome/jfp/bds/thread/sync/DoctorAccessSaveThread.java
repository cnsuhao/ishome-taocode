package org.ishome.jfp.bds.thread.sync;

import org.ishome.jfp.common.Hospital.HospitalInfoService;
import org.ishome.jfp.framework.job.HospitalJobKeyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;


/**
 * 医生信息
 * 
 * @author Spook
 * @version 2.0.1 2015/2/3
 * @version 2.0.0 2015/1/19
 * @see <DoctorAccessService>
 * @since 2.0.0 2015/1/19
 */
public class DoctorAccessSaveThread extends AHospitalDockingSynchronizationThread {
	public DoctorAccessSaveThread(String hosId, HospitalInfoService jobService) {
		super("Doctor", hosId, jobService);
	}

	private static final Logger logger = LoggerFactory.getLogger(DoctorAccessSaveThread.class);

//	@Resource
//	AccessFaultLogogService AccessFaultLogogService_;
//	@Resource
//	DoctorService DoctorService_;
//	@Resource
//	DepartmentService DepartmentService_;
//	@Resource
//	DepartmentDoctorService DepartmentDoctorService_;

	@Override
	public boolean doInit() throws Exception {
//		if (AccessFaultLogogService_ == null)
//			AccessFaultLogogService_ = BeanFactoryHelper.getBean("accessFaultLogogService");
//		if (DepartmentService_ == null)
//			DepartmentService_ = BeanFactoryHelper.getBean("departmentService");
//		if (DoctorService_ == null)
//			DoctorService_ = BeanFactoryHelper.getBean("doctorService");
//		if (DepartmentDoctorService_ == null)
//			DepartmentDoctorService_ = BeanFactoryHelper.getBean("departmentDoctorService");
//		if (HospitalInfoService_ == null)
//			HospitalInfoService_ = BeanFactoryHelper.getBean("hospitalInfoService");
		return true;
	}

	@Override
	public boolean doCheck() throws Exception {
//		if (AccessFaultLogogService_ == null)
//			return false;
//		if (DepartmentService_ == null)
//			return false;
//		if (DoctorService_ == null)
//			return false;
//		if (DepartmentDoctorService_ == null)
//			return false;
		return true;
	}

	/**
	 * 保存数据 AccessFaultLogServiceImpl DoctorAccessServiceImpl
	 */
	@Override
	@Transactional
	public boolean doSave() throws Exception {
		logger.debug(JOB_SAVE_START);
		
		logger.debug(JOB_SAVE_END);
		HospitalJobKeyService.setSyncJobType(hosId, "Doctor", JOB_FLAG_SUCCESS);
		return true;
	}

}
