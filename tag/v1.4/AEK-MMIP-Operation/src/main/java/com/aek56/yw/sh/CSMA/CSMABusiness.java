package com.aek56.yw.sh.CSMA;

import javax.annotation.Resource;

import org.jfpc.framework.helper.DateHelper;
import org.jfpc.framework.support.MyBusinessSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek56.atm.auditing.CSM1_YYXX.CSM1_YYXXDBO;
import com.aek56.atm.auditing.CSM1_YYXX.CSM1_YYXXService;
import com.aek56.atm.auditing.CSMA_PPXX.CSMA_PPXXDBO;
import com.aek56.atm.auditing.CSMA_PPXX.CSMA_PPXXService;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXDBO;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXService;
import com.aek56.atm.master.MDC_PPXX.MDC_PPXXDBO;
import com.aek56.atm.master.MDC_PPXX.MDC_PPXXService;
/**
 * 品牌审核
 * @ClassName: CSMABusiness 
 * @author: Gaoqin
 * @date: 2014-12-2 下午8:41:08
 */
@Service
public class CSMABusiness extends MyBusinessSupport{
	/**
	 * 品牌主数据
	 */
	@Resource
	private MDC_PPXXService mdc_PPXXService;
	
	/**
	 * 品牌审核信息
	 */
	@Resource
	private CSMA_PPXXService csma_PPXXService;
	
	@Transactional
	public void check(CSMA_PPXXDBO param){
		//更新审核表记录
		param.setN01_shrid(this.getLoginerId());
		param.setN02_shrxm(this.getLoginerBean().getUserNameCN());
		param.setN09(DateHelper.currentTimeMillis2());
		csma_PPXXService.doUpdate(param);
		//通过
		if(ZERO.equals(param.getN03_shzt())){
			CSMA_PPXXDBO csma = (CSMA_PPXXDBO)csma_PPXXService.doRead(param);
			MDC_PPXXDBO mdc = new MDC_PPXXDBO();
			BeanUtils.copyProperties(csma, mdc);
			mdc.setGgg(EMPTY);
			mdc.setPpp(EMPTY);
			mdc.setDdd(EMPTY);
			mdc.setCc1(EMPTY);
			mdc.setCc2(EMPTY);
			mdc.setUu1(EMPTY);
			mdc.setUu2(EMPTY);
			mdc.setPuk(csma.getP01_puk());
			mdc_PPXXService.doUpdate(mdc);
		}
	}
}
