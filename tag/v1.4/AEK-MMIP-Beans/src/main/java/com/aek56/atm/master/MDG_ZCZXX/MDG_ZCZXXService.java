package com.aek56.atm.master.MDG_ZCZXX;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aek56.utils.CredentialsHelper;

/** 注册证信息*/
@Service
public class MDG_ZCZXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MDG_ZCZXXService.class);

    public MDG_ZCZXXDao getDao(){
        return getMySqlSession().getMapper(MDG_ZCZXXDao.class);
    }

    /**
	 * 数据库分表
	 * @param data
	 */
	public void changeTable(MyDataBaseObjectSupport data) {
		MDG_ZCZXXDBO md = (MDG_ZCZXXDBO)data;
		
		CredentialsHelper.getCode(data);
		
//		String companyType = ((XXXXXDBO)data).getT01();
//		// 分表处理
//		if (ZERO.equals(companyType)) {
//			data.setTablename("0");
//		} else if (ONE.equals(companyType)) {
//			data.setTablename("1");
//		}
		
		//模糊查询索引
		StringBuffer index= new StringBuffer(1000);
		//注册号
		index.append(md.getF01_zczzwmc()).append(BACKSLASH2);
		index.append(md.getF02_zczywmc()).append(BACKSLASH2);
		//厂商
		index.append(md.getF32_scqyzwmc()).append(BACKSLASH2);
		index.append(md.getF36_scqyywmc()).append(BACKSLASH2);
		index.append(md.getF37_scqypym()).append(BACKSLASH2);
		md.setGgg(index.toString());
	}

}
