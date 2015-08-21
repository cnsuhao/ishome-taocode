package com.hundsun.med.hdp.service.server.notice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.hundsun.med.access.hao.notice.PatientNoticeHAO;
import com.hundsun.med.hdp.service.server.ACloundProcessService;

/**
 * 发送通知
 * 
 * @author fucy
 * @version 2.0.0
 * @since 2.0.0 2015/1/19
 */
@Controller
public class PatientNoticeBusinessImpl extends ACloundProcessService {
	private static final Logger logger = LoggerFactory.getLogger(PatientNoticeBusinessImpl.class);
	public PatientNoticeBusinessImpl() {
		setBizName(Clound_PatientNoticeBusiness);
	}
	
	PatientNoticeHAO ret;
	
	public void setPatientNotice(PatientNoticeHAO notice){
		ret = notice;
	}
	
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
		setPostData(ret);
		return true;
	}
	
}
