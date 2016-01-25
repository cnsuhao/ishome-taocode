package com.aek56.atm.company.MGYS3_GYSTJYYXX;
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

/** 供应商添加医院信息*/
@Service
public class MGYS3_GYSTJYYXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGYS3_GYSTJYYXXService.class);

    public MGYS3_GYSTJYYXXDao getDao(){
        return getMySqlSession().getMapper(MGYS3_GYSTJYYXXDao.class);
    }
    
    @Resource
    CompanyService CompanyService_;
    
    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MGYS3_GYSTJYYXXDBO md = (MGYS3_GYSTJYYXXDBO)data;
   		if(EmptyHelper.isEmpty(md.getN02_jcpym())){
   			md.setN02_jcpym(StringHelper.getPinYinSample(md.getN01_qyjc()));
   		}
   		md.setGgg(EMPTY);
//   		String companyType = ((XXXXXDBO)data).getT01();
//   		// 分表处理
//   		if (ZERO.equals(companyType)) {
//   			data.setTablename("0");
//   		} else if (ONE.equals(companyType)) {
//   			data.setTablename("1");
//   		}
   	}
   	
   	/**
   	 * 医院添加供应商时候调用<br>
   	 * 判断自动审核状态
   	 * @param formParamBean
   	 * @param check
   	 * @return
   	 */
   	public int doInsert(MyDataBaseObjectSupport formParamBean) {
   		//处理自动审核
   		MGYS3_GYSTJYYXXDBO md = (MGYS3_GYSTJYYXXDBO)formParamBean;
   		MQC_PZZXDBO dbo = CompanyService_.myConfig(md.getPuk());
   		if(ZERO.equals(dbo.getF03()))
   			md.setF45(ZERO);
		return super.doInsert(formParamBean);
	}

}
