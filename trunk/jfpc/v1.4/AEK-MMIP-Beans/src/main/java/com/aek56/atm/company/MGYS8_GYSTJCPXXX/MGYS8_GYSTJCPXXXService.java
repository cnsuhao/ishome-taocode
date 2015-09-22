package com.aek56.atm.company.MGYS8_GYSTJCPXXX;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商添加产品线信息*/
@Service
public class MGYS8_GYSTJCPXXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGYS8_GYSTJCPXXXService.class);

    public MGYS8_GYSTJCPXXXDao getDao(){
        return getMySqlSession().getMapper(MGYS8_GYSTJCPXXXDao.class);
    }

    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MGYS8_GYSTJCPXXXDBO md = (MGYS8_GYSTJCPXXXDBO)data;
   		if(EmptyHelper.isEmpty(md.getN02_cpxjp())){
   			md.setN02_cpxjp(StringHelper.getPinYinSample(md.getN01_cpxjc()));
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
