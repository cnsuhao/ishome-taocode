package com.aek56.yw.sh.CSM3;

import javax.annotation.Resource;

import org.jfpc.framework.helper.DateHelper;
import org.jfpc.framework.support.MyBusinessSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek56.atm.auditing.CSM3_XZJGDW.CSM3_XZJGDWDBO;
import com.aek56.atm.auditing.CSM3_XZJGDW.CSM3_XZJGDWService;
import com.aek56.atm.master.MD5_XZJGDW.MD5_XZJGDWDBO;
import com.aek56.atm.master.MD5_XZJGDW.MD5_XZJGDWService;
/**
 * 
* @Description: TODO(行政机构单位审核) 
* @author tangmuming
 */
@Service
public class CSM3Business extends MyBusinessSupport{
	/**
	 * 行政机构单位主数据
	 */
	@Resource
	private MD5_XZJGDWService md5_XZJGDWService;
	
	/**
	 */
	@Resource
	private CSM3_XZJGDWService csm3_XZJGDWService;
	
	@Transactional
	public void check(CSM3_XZJGDWDBO param){
		//更新审核表记录
		param.setN01_shrid(this.getLoginerId());
		param.setN02_shrxm(this.getLoginerBean().getUserNameCN());
		param.setN09(DateHelper.currentTimeMillis2());
		csm3_XZJGDWService.doUpdate(param);
		//通过
		if(ZERO.equals(param.getN03_shzt())){
			CSM3_XZJGDWDBO csm2 = (CSM3_XZJGDWDBO)csm3_XZJGDWService.doRead(param);
			MD5_XZJGDWDBO md4 = new MD5_XZJGDWDBO();
			BeanUtils.copyProperties(csm2, md4);
			md4.setGgg(EMPTY);
			md4.setPpp(EMPTY);
			md4.setDdd(EMPTY);
			md4.setCc1(EMPTY);
			md4.setCc2(EMPTY);
			md4.setUu1(EMPTY);
			md4.setUu2(EMPTY);
			md4.setPuk(csm2.getP01_puk());
			md5_XZJGDWService.doInsert(md4);
		}
	}
}
