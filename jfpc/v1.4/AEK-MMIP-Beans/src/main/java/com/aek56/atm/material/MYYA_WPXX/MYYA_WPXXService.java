package com.aek56.atm.material.MYYA_WPXX;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 产品信息*/
@Service
public class MYYA_WPXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MYYA_WPXXService.class);

    public MYYA_WPXXDao getDao(){
        return getMySqlSession().getMapper(MYYA_WPXXDao.class);
    }

    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MYYA_WPXXDBO md = (MYYA_WPXXDBO)data;
   		if(EmptyHelper.isEmpty(md.getN02_spjp())){
   			md.setN02_spjp(StringHelper.getPinYinSample(md.getN01_spjc()));
   		}
   		
//   		String companyType = ((XXXXXDBO)data).getT01();
//   		// 分表处理
//   		if (ZERO.equals(companyType)) {
//   			data.setTablename("0");
//   		} else if (ONE.equals(companyType)) {
//   			data.setTablename("1");
//   		}
   	}
}
