package com.aek56.atm.material.MGYS4_GYSTJSP;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商添加商品*/
@Service
public class MGYS4_GYSTJSPService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGYS4_GYSTJSPService.class);

    public MGYS4_GYSTJSPDao getDao(){
        return getMySqlSession().getMapper(MGYS4_GYSTJSPDao.class);
    }

    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MGYS4_GYSTJSPDBO md = (MGYS4_GYSTJSPDBO)data;
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
