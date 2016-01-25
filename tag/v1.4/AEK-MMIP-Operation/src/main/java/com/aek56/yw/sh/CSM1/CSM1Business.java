package com.aek56.yw.sh.CSM1;

import javax.annotation.Resource;

import org.jfpc.framework.helper.DateHelper;
import org.jfpc.framework.support.MyBusinessSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek56.atm.auditing.CSM1_YYXX.CSM1_YYXXDBO;
import com.aek56.atm.auditing.CSM1_YYXX.CSM1_YYXXService;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXDBO;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXService;
/**
 * 
* @Description: TODO(医院审核) 
* @author tangmuming
 */
@Service
public class CSM1Business extends MyBusinessSupport{
	/**
	 * 医院主数据
	 */
	@Resource
	private MD3_YYXXService md3_YYXXService;
	
	/**
	 * 医院审核信息
	 */
	@Resource
	private CSM1_YYXXService csm1_YYXXService;
	
	@Transactional
	public void check(CSM1_YYXXDBO param){
		//更新审核表记录
		param.setN01_shrid(this.getLoginerId());
		param.setN02_shrxm(this.getLoginerBean().getUserNameCN());
		param.setN09(DateHelper.currentTimeMillis2());
		csm1_YYXXService.doUpdate(param);
		//通过
		if(ZERO.equals(param.getN03_shzt())){
			CSM1_YYXXDBO csm0 = (CSM1_YYXXDBO)csm1_YYXXService.doRead(param);
			MD3_YYXXDBO md3 = new MD3_YYXXDBO();
			BeanUtils.copyProperties(csm0, md3);
			md3.setGgg(EMPTY);
			md3.setPpp(EMPTY);
			md3.setDdd(EMPTY);
			md3.setCc1(EMPTY);
			md3.setCc2(EMPTY);
			md3.setUu1(EMPTY);
			md3.setUu2(EMPTY);
			md3.setPuk(csm0.getP01_puk());
			md3_YYXXService.doUpdate(md3);
		}
	}
}
