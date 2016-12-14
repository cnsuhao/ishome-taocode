package com.aek56.atm.credentials.MGTA_CJYLQXZCZ;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aek56.utils.CredentialsHelper;

/** 供应商提供医院厂家医疗器械注册证*/
@Service
public class MGTA_CJYLQXZCZService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGTA_CJYLQXZCZService.class);

    public MGTA_CJYLQXZCZDao getDao(){
        return getMySqlSession().getMapper(MGTA_CJYLQXZCZDao.class);
    }
    
    /**
	 * 数据库分表
	 * @param data
	 */
	public void changeTable(MyDataBaseObjectSupport data) {
		CredentialsHelper.getCode(data);
		
//		String companyType = ((XXXXXDBO)data).getT01();
//		// 分表处理
//		if (ZERO.equals(companyType)) {
//			data.setTablename("0");
//		} else if (ONE.equals(companyType)) {
//			data.setTablename("1");
//		}
	}
}
