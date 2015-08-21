package com.hundsun.med.hdp.service.biz.queue;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hundsun.med.access.beans.PatientInfoBean;
import com.hundsun.med.access.beans.QueueInfoBean;
import com.hundsun.med.access.beans.QueueRecordInfoBean;
import com.hundsun.med.access.hao.queue.PatientQueueListHAO;
import com.hundsun.med.hdp.service.biz.AHospitalProcessService;

/**
 * 获取患者排队队列列表
 * 
 * @author fucy
 * @version 2.0.5
 * @since 2.0.5 2015/3/5
 */
@Service("PatientQueueListBusinessImpl")
public class PatientQueueListBusinessImpl extends AHospitalProcessService {
	private static final Logger logger = LoggerFactory.getLogger(PatientQueueListBusinessImpl.class);
	public PatientQueueListBusinessImpl() {
		setBizName(ACCESSS_PatientQueueListBusiness);
	}
	
	PatientQueueListHAO param;
	ArrayList<QueueInfoBean> ret;
	
	/**
	 * 先判断状态在发送数据
	 */
	@Override
	public boolean doInit() throws Exception {
		logger.debug(message.toString());
		param = (PatientQueueListHAO) message;
		super.setPuk(param.getPuk());
		// 检测医院网络状态
		return true;
	}
	
	/**
	 * 完整性校验
	 */
	@Override
	public boolean doCheck() throws Exception {
//		//这里做数据转换
//		//医院HIS系统数据状态
//		//message
//		if(  param.getPatient() != null ){
//			PatientInfoBean patientInfoBean = param.getPatient();
//			if( patientInfoBean.getAccessPatId()!=null ){
//				setReturnCode(ACCESSS_RTN_CODE_ERROR_PARAM);
//			    setReturnMessage("患者信息不全,无法获取排队队列状态");
//				return false;
//			}
//		}else{
//			setReturnCode(ACCESSS_RTN_CODE_ERROR_PARAM);
//		    setReturnMessage("患者信息不全,无法获取排队队列状态");
//			return false;
//	    }
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
		ret = new ArrayList<QueueInfoBean>();
		int count = (int) (Math.random()*10);
		QueueInfoBean queueInfoBean = null;
		if( (count)>7 ){
			queueInfoBean = new QueueInfoBean();
			queueInfoBean.setAccessQueueId(getPuk());
			queueInfoBean.setDeptName("内科门诊");
			queueInfoBean.setLocation("内科楼212室");
			QueueRecordInfoBean currentRecord = new QueueRecordInfoBean();
			currentRecord.setAccessQueueRecordId(getPuk());
			int current = (int) (Math.random()*10);
			currentRecord.setNum(current+"");
			currentRecord.setStatus("1"); // 叫号状态,0:未叫 1:已叫 (此处是否必须由实际决定)
			currentRecord.setStatusDesc("已叫");
			currentRecord.setQueueStatus("2"); 
			currentRecord.setQueueStatusDesc("准备"); //排队状态,0:暂停 1:等待 2:准备 3:受理 4:完成 5:放弃 6:退号 7:暂挂
			
			QueueRecordInfoBean patientRecord = new QueueRecordInfoBean();
			patientRecord.setNum((current+(int)(Math.random()*4))+"");
			patientRecord.setStatus("0"); // 叫号状态,0:未叫 1:已叫 (此处是否必须由实际决定)
			patientRecord.setStatusDesc("未叫");
			patientRecord.setQueueStatus("1"); 
			patientRecord.setQueueStatusDesc("等待"); //排队状态,0:暂停 1:等待 2:准备 3:受理 4:完成 5:放弃 6:退号 7:暂挂
			
//			QueueRecordInfoBean notifyRecord = new QueueRecordInfoBean();
//			notifyRecord.setStatus("0"); // 叫号状态,0:未叫 1:已叫 (此处是否必须由实际决定)
//			notifyRecord.setStatusDesc("未叫");
//			notifyRecord.setQueueStatus("1"); 
//			notifyRecord.setQueueStatusDesc("等待"); //排队状态,0:暂停 1:等待 2:准备 3:受理 4:完成 5:放弃 6:退号 7:暂挂
//			
			queueInfoBean.setCurrentRecord(currentRecord);
			queueInfoBean.setPatientRecord(patientRecord);
//			queueInfoBean.setNotifyRecord(notifyRecord);
		}else {
			queueInfoBean = new QueueInfoBean();
			queueInfoBean.setAccessQueueId(getPuk());
			queueInfoBean.setDocName("杨丁克");
			queueInfoBean.setLocation("外科楼318室");
			QueueRecordInfoBean currentRecord = new QueueRecordInfoBean();
			currentRecord.setAccessQueueRecordId(getPuk());
			int current = (int) (Math.random()*10);
			currentRecord.setNum(current+"");
			currentRecord.setStatus("1"); // 叫号状态,0:未叫 1:已叫 (此处是否必须由实际决定)
			currentRecord.setStatusDesc("已叫");
			currentRecord.setQueueStatus("2"); 
			currentRecord.setQueueStatusDesc("准备"); //排队状态,0:暂停 1:等待 2:准备 3:受理 4:完成 5:放弃 6:退号 7:暂挂
			current = (current+(int)(Math.random()*4)+1);
			QueueRecordInfoBean patientRecord = new QueueRecordInfoBean();
			patientRecord.setNum(current+"");
			patientRecord.setStatus("0"); // 叫号状态,0:未叫 1:已叫 (此处是否必须由实际决定)
			patientRecord.setStatusDesc("未叫");
			patientRecord.setQueueStatus("1"); 
			patientRecord.setQueueStatusDesc("等待"); //排队状态,0:暂停 1:等待 2:准备 3:受理 4:完成 5:放弃 6:退号 7:暂挂
			
			QueueRecordInfoBean notifyRecord = new QueueRecordInfoBean();
			notifyRecord.setNum((current-1)+"");
			notifyRecord.setStatus("0"); // 叫号状态,0:未叫 1:已叫 (此处是否必须由实际决定)
			notifyRecord.setStatusDesc("未叫");
			notifyRecord.setQueueStatus("1"); 
			notifyRecord.setQueueStatusDesc("等待"); //排队状态,0:暂停 1:等待 2:准备 3:受理 4:完成 5:放弃 6:退号 7:暂挂
			
			queueInfoBean.setCurrentRecord(currentRecord);
			queueInfoBean.setPatientRecord(patientRecord);
			queueInfoBean.setNotifyRecord(notifyRecord);
		}
		ret.add(queueInfoBean);
		setReturnObject(ret);
		return true;
	}
}
