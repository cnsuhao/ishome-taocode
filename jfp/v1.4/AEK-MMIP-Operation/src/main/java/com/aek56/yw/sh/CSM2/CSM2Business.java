package com.aek56.yw.sh.CSM2;

import javax.annotation.Resource;

import org.jfpc.framework.helper.DateHelper;
import org.jfpc.framework.support.MyBusinessSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek56.atm.auditing.CSM2_CSXX.CSM2_CSXXDBO;
import com.aek56.atm.auditing.CSM2_CSXX.CSM2_CSXXService;
import com.aek56.atm.master.MD4_CSXX.MD4_CSXXDBO;
import com.aek56.atm.master.MD4_CSXX.MD4_CSXXService;
/**
 * 
* @Description: TODO(厂商审核) 
* @author tangmuming
 */
@Service
public class CSM2Business extends MyBusinessSupport{
	/**
	 * 厂商主数据
	 */
	@Resource
	private MD4_CSXXService md4_CSXXService;
	
	/**
	 * 厂商审核信息
	 */
	@Resource
	private CSM2_CSXXService csm2_CSXXService;
	
	@Transactional
	public void check(CSM2_CSXXDBO param){
		//更新审核表记录
		param.setN01_shrid(this.getLoginerId());
		param.setN02_shrxm(this.getLoginerBean().getUserNameCN());
		param.setN09(DateHelper.currentTimeMillis2());
		csm2_CSXXService.doUpdate(param);
		//通过
		if(ZERO.equals(param.getN03_shzt())){
			CSM2_CSXXDBO csm2 = (CSM2_CSXXDBO)csm2_CSXXService.doRead(param);
			MD4_CSXXDBO md4 = new MD4_CSXXDBO();
			BeanUtils.copyProperties(csm2, md4);
			md4.setGgg(EMPTY);
			md4.setPpp(EMPTY);
			md4.setDdd(EMPTY);
			md4.setCc1(EMPTY);
			md4.setCc2(EMPTY);
			md4.setUu1(EMPTY);
			md4.setUu2(EMPTY);
			md4.setPuk(csm2.getP01_puk());
			md4_CSXXService.doUpdate(md4);
		}
	}
}
