package com.hundsun.med.hdp.service.biz.queue;

import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hundsun.med.access.beans.QueueRecordInfoBean;
import com.hundsun.med.access.hao.queue.PatientQueueRecordListHAO;
import com.hundsun.med.hdp.service.biz.AHospitalProcessService;

/**
 * 获取患者某个排队队列下的全部排队状态列表
 * 
 * @author fucy
 * @version 2.0.5
 * @since 2.0.5 2015/3/5
 */
@Service("PatientQueueRecordListBusinessImpl")
public class PatientQueueRecordListBusinessImpl extends AHospitalProcessService {
	private static final Logger logger = LoggerFactory.getLogger(PatientQueueRecordListBusinessImpl.class);
	public PatientQueueRecordListBusinessImpl() {
		setBizName(ACCESSS_PatientQueueListBusiness);
	}
	
	PatientQueueRecordListHAO param;
	ArrayList<QueueRecordInfoBean> ret;
	
	/**
	 * 先判断状态在发送数据
	 */
	@Override
	public boolean doInit() throws Exception {
		logger.debug(message.toString());
		param = (PatientQueueRecordListHAO) message;
		super.setPuk(param.getPuk());
		// 检测医院网络状态
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
		if( StringUtils.isNotBlank(param.getAccessQueueId()) ){
			
		}else {
			setReturnCode(ACCESSS_RTN_CODE_ERROR_PARAM);
		    setReturnMessage("队列信息不全,无法获取队列列表状态");
			return false;
		}
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
		ret = new ArrayList<QueueRecordInfoBean>();
		int current = (int) (Math.random()*10);
		for(int i=0;i<(int)(Math.random()*20);i++){
			QueueRecordInfoBean notifyRecord = new QueueRecordInfoBean();
			notifyRecord.setNum( current + "");
			if(i>10){
				notifyRecord.setStatus("1"); // 叫号状态,0:未叫 1:已叫 (此处是否必须由实际决定)
				notifyRecord.setStatusDesc("已叫");
			}else {
				notifyRecord.setStatus("0"); // 叫号状态,0:未叫 1:已叫 (此处是否必须由实际决定)
				notifyRecord.setStatusDesc("未叫");
			}
			int queueStatus = (int) (Math.random()*10); 
			notifyRecord.setQueueStatus(queueStatus+""); 
			switch (queueStatus) {
				case 0:notifyRecord.setQueueStatusDesc("暂停");break;
				case 1:notifyRecord.setQueueStatusDesc("等待");break;
				case 2:notifyRecord.setQueueStatusDesc("准备");break;	
				case 3:notifyRecord.setQueueStatusDesc("受理");break;	
				case 4:notifyRecord.setQueueStatusDesc("完成");break;
				case 5:notifyRecord.setQueueStatusDesc("放弃");break;	
				case 6:notifyRecord.setQueueStatusDesc("退号");break;
				case 7:notifyRecord.setQueueStatusDesc("暂挂");break;
				default:break;
			}
			 //排队状态,0:暂停 1:等待 2:准备 3:受理 4:完成 5:放弃 6:退号 7:暂挂
			ret.add(notifyRecord);
		}
		// 保存需要发送的数据
		setReturnObject(ret);
		return true;
	}
}
