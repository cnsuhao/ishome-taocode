package com.aek56.yw.sh.CSM4;

import javax.annotation.Resource;

import org.jfpc.framework.helper.DateHelper;
import org.jfpc.framework.support.MyBusinessSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek56.atm.auditing.CSM4_CPXX.CSM4_CPXXDBO;
import com.aek56.atm.auditing.CSM4_CPXX.CSM4_CPXXService;
import com.aek56.atm.master.MD6_CPXX.MD6_CPXXDBO;
import com.aek56.atm.master.MD6_CPXX.MD6_CPXXService;
/**
 * 产品审核
 * @ClassName: CSM4Business 
 * @author: Gaoqin
 * @date: 2014-12-2 下午8:41:30
 */
@Service
public class CSM4Business extends MyBusinessSupport{
	/**
	 * 产品主数据
	 */
	@Resource
	private MD6_CPXXService md6_CPXXService;
	
	/**
	 * 产品审核信息
	 */
	@Resource
	private CSM4_CPXXService csm4_CPXXService;
	
	@Transactional
	public void check(CSM4_CPXXDBO param){
		//更新审核表记录
		param.setN01_shrid(this.getLoginerId());
		param.setN02_shrxm(this.getLoginerBean().getUserNameCN());
		param.setN09(DateHelper.currentTimeMillis2());
		csm4_CPXXService.doUpdate(param);
		//通过
		if(ZERO.equals(param.getN03_shzt())){
			CSM4_CPXXDBO csm4 = (CSM4_CPXXDBO)csm4_CPXXService.doRead(param);
			MD6_CPXXDBO md6 = new MD6_CPXXDBO();
			BeanUtils.copyProperties(csm4, md6);
			md6.setGgg(EMPTY);
			md6.setPpp(EMPTY);
			md6.setDdd(EMPTY);
			md6.setCc1(EMPTY);
			md6.setCc2(EMPTY);
			md6.setUu1(EMPTY);
			md6.setUu2(EMPTY);
			md6.setPuk(csm4.getP01_puk());
			md6_CPXXService.doUpdate(md6);
		}
	}
}
