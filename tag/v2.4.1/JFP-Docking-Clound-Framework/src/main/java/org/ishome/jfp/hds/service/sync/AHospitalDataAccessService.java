package org.ishome.jfp.hds.service.sync;

import javax.annotation.Resource;

import org.ishome.jfp.hds.conf.CloundSecyrityConfig;
import org.ishome.jfp.hds.constants.IHDSConstants;
import org.springframework.util.StringUtils;

import com.hundsun.med.common.Hospital.HospitalInfoService;
import com.hundsun.med.framework.biz.ISTask;
import com.hundsun.med.framework.constants.ISFrameworkConstants;
import com.hundsun.med.framework.mq.IMedMqService;

/**
 * 基于业务处理，用于获取同步数据内容
 * 
 * @author fucy
 * @version 2.3.3 2015/8/2
 * @version 2.0.0
 * @see <HospitalDataSynchronizationJob><HospitalAccessSaveThread>
 * @since 2.0.0 2015/1/19
 */
public abstract class AHospitalDataAccessService implements IHDSConstants, ISFrameworkConstants, ISTask {
	// 缓存队列
	@Resource
	protected IMedMqService mq;
	// 医院作业
	@Resource
	protected HospitalInfoService HospitalInfoService_;
	// 安全相关类
	@Resource
	protected CloundSecyrityConfig CloundSecyrityConfig_;

	/**
	 * 使用运营数据
	 */
	protected String useOperateData = EMPTY;

	public String getUseOperateData() {
		return useOperateData;
	}

	public void setUseOperateData(String useOperateData) {
		this.useOperateData = useOperateData;
	}

	protected boolean useTempTable;

	public boolean isUseTempTable() {
		return useTempTable;
	}

	public void setUseTempTable(boolean useTempTable) {
		this.useTempTable = useTempTable;
	}

	protected String hosId;

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	/**
	 * 需要运行的业务名称
	 */
	String bizName;

	public String getBizName() {
		return bizName;
	}

	public void setBizName(String bizName) {
		this.bizName = bizName;
	}

	/**
	 * 业务处理(重复运行)
	 * 
	 * @see #doProcessOnce(String)
	 */
	public boolean doProcessRepeat() throws Exception {
		// 检查医院ID
		if (StringUtils.isEmpty(hosId))
			return false;
		return doProcessOnce(hosId);
	}

}
