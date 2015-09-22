package com.aek56.atm.company.MYY1_YYTJGYSXX;

import javax.annotation.Resource;

import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aek56.atm.company.MQC_PZZX.MQC_PZZXDBO;
import com.aek56.atm.company.cmp.CompanyService;

/** 医院添加供应商信息 */
@Service
public class MYY1_YYTJGYSXXService extends MyServiceSupport {
	protected static final Logger logger = LoggerFactory.getLogger(MYY1_YYTJGYSXXService.class);

	public MYY1_YYTJGYSXXDao getDao() {
		return getMySqlSession().getMapper(MYY1_YYTJGYSXXDao.class);
	}

	@Resource
	CompanyService CompanyService_;

	/**
	 * 数据库分表
	 * 
	 * @param data
	 */
	public void changeTable(MyDataBaseObjectSupport data) {
		MYY1_YYTJGYSXXDBO md = (MYY1_YYTJGYSXXDBO) data;
		if (EmptyHelper.isEmpty(md.getN02_jcpym())) {
			md.setN02_jcpym(StringHelper.getPinYinSample(md.getN01_qyjc()));
		}
		md.setGgg(EMPTY);
		// String companyType = ((XXXXXDBO)data).getT01();
		// // 分表处理
		// if (ZERO.equals(companyType)) {
		// data.setTablename("0");
		// } else if (ONE.equals(companyType)) {
		// data.setTablename("1");
		// }
	}

	/**
	 * 医院添加供应商时候调用<br>
	 * 判断自动审核状态
	 * 
	 * @param formParamBean
	 * @param check
	 * @return
	 */
	public int doInsert(MyDataBaseObjectSupport formParamBean) {
   		//处理自动审核
   		MYY1_YYTJGYSXXDBO md = (MYY1_YYTJGYSXXDBO)formParamBean;
   		MQC_PZZXDBO dbo = CompanyService_.myConfig(md.getPuk());
   		if(ZERO.equals(dbo.getF03()))
   			md.setF40(ZERO);
		return super.doInsert(formParamBean);
	}
}
