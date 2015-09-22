package com.aek56.yw.sh.CSM0;

import javax.annotation.Resource;

import org.jfpc.framework.helper.DateHelper;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.support.MyBusinessSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek56.atm.auditing.CSM0_GYSXX.CSM0_GYSXXDBO;
import com.aek56.atm.auditing.CSM0_GYSXX.CSM0_GYSXXService;
import com.aek56.atm.master.MD2_GYSXX.MD2_GYSXXDBO;
import com.aek56.atm.master.MD2_GYSXX.MD2_GYSXXService;
/**
 * 
* @Description: TODO(供应商审核) 
* @author tangmuming
 */
@Service
public class CSM0Business extends MyBusinessSupport{
	/**
	 * 供应商主数据
	 */
	@Resource
	private MD2_GYSXXService md2_GYSXXService;
	
	/**
	 * 供应商审核信息
	 */
	@Resource
	private CSM0_GYSXXService csm0_GYSXXService;
	
	@Transactional
	public void check(CSM0_GYSXXDBO param){
		//更新审核表记录
		param.setN01_shrid(this.getLoginerId());
		param.setN02_shrxm(this.getLoginerBean().getUserNameCN());
		param.setN09(DateHelper.currentTimeMillis2());
		csm0_GYSXXService.doUpdate(param);
		//通过
		if(ZERO.equals(param.getN03_shzt())){
			CSM0_GYSXXDBO csm0 = (CSM0_GYSXXDBO)csm0_GYSXXService.doRead(param);
			MD2_GYSXXDBO md2 = new MD2_GYSXXDBO();
			BeanUtils.copyProperties(csm0, md2);
			md2.setGgg(EMPTY);
			md2.setPpp(EMPTY);
			md2.setDdd(EMPTY);
			md2.setCc1(EMPTY);
			md2.setCc2(EMPTY);
			md2.setUu1(EMPTY);
			md2.setUu2(EMPTY);
			md2.setPuk(csm0.getP01_puk());
			md2_GYSXXService.doUpdate(md2);
		}
	}
}
