package com.aek56.yw.sh.CSM6;

import javax.annotation.Resource;

import org.jfpc.framework.support.MyServiceSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek56.atm.auditing.CSM6_ZCZXX.CSM6_ZCZXXDBO;
import com.aek56.atm.auditing.CSM6_ZCZXX.CSM6_ZCZXXService;
import com.aek56.atm.master.MDG_ZCZXX.MDG_ZCZXXDBO;
import com.aek56.atm.master.MDG_ZCZXX.MDG_ZCZXXService;

/**
 * 后台维护主数据注册证事务类
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Service
public class CSM6Business extends MyServiceSupport {
	/**
	 * 注册证主数据表
	 */
	@Resource
	protected MDG_ZCZXXService MDG_ZCZXXService_;

	/**
	 * 注册证审核表
	 */
	@Resource
	protected CSM6_ZCZXXService CSM6_ZCZXXService_;

	/**
	 * 审核通过
	 * 
	 * @param pukid
	 */
	@Transactional
	public void throughAudit(CSM6_ZCZXXDBO param) {
		// 获取审核表中的记录
		CSM6_ZCZXXDBO csm6Param = new CSM6_ZCZXXDBO();
		csm6Param.setPuk(param.getPuk());
		CSM6_ZCZXXDBO csm6Rs = (CSM6_ZCZXXDBO) this.CSM6_ZCZXXService_
				.doRead(csm6Param);

		// 修改审核状态为已审核
		csm6Rs.setN01_shrid(this.getLoginerId());
		csm6Rs.setN02_shrxm(this.getLoginerBean().getUserNameCN());
		csm6Rs.setN03_shzt(ZERO);
		csm6Rs.setN04_shly(param.getN04_shly());
		this.CSM6_ZCZXXService_.doUpdate(csm6Rs);

		// 修改主数据
		MDG_ZCZXXDBO mdgRs = new MDG_ZCZXXDBO();
		BeanUtils.copyProperties(csm6Rs, mdgRs);
		mdgRs.setPuk(csm6Rs.getP01_puk());
		mdgRs.setUu1("");
		this.MDG_ZCZXXService_.doUpdate(mdgRs);
		
	}

	/**
	 * 审核失败
	 * 
	 * @param pukid
	 */
	@Transactional
	public void donotPassTheAudit(CSM6_ZCZXXDBO param) {
		// 获取审核表中的记录
		CSM6_ZCZXXDBO csm6Param = new CSM6_ZCZXXDBO();
		csm6Param.setPuk(param.getPuk());
		CSM6_ZCZXXDBO csm6Rs = (CSM6_ZCZXXDBO) this.CSM6_ZCZXXService_
				.doRead(csm6Param);

		// 修改审核状态为已审核
		csm6Rs.setN01_shrid(this.getLoginerId());
		csm6Rs.setN02_shrxm(this.getLoginerBean().getUserNameCN());
		csm6Rs.setN03_shzt(ONE);
		csm6Rs.setN04_shly(param.getN04_shly());
		this.CSM6_ZCZXXService_.doUpdate(csm6Rs);

	}
	
	public CSM6Dao getDao(){
        return getMySqlSession().getMapper(CSM6Dao.class);
    }
}