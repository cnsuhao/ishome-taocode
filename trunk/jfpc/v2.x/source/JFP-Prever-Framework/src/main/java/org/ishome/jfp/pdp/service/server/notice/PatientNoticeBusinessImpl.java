package org.ishome.jfp.pdp.service.server.notice;

import org.ishome.jfp.pdp.service.server.ACloundProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;


/**
 * 发送通知
 * 
 * @author Spook
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 */
@Controller
public class PatientNoticeBusinessImpl extends ACloundProcessService {
	protected static final Logger logger = LoggerFactory.getLogger(PatientNoticeBusinessImpl.class);
	public PatientNoticeBusinessImpl() {
		setBizName("PatientNotice");
	}
	
//	PatientNoticeHAO ret;
//	
//	public void setPatientNotice(PatientNoticeHAO notice){
//		ret = notice;
//	}
	
	/**
	 * 先判断状态在发送数据
	 */
	@Override
	public boolean doInit() throws Exception {
		//检测医院网络状态
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
		return true;
	}

	/**
	 * 业务处理
	 */
	@Override
	public boolean doProcess() throws Exception {
		// 整理数据
		//每一个业务请求具有唯一标识
		//TODO BaseHAO message
		
		//获取HIS系统数据，封装出口
		
		
		// 保存需要发送的数据
		//setPostData(ret);
		return true;
	}
	
}
