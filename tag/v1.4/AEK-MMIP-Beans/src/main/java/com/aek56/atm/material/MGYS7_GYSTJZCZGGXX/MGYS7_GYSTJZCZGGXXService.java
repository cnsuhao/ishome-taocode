package com.aek56.atm.material.MGYS7_GYSTJZCZGGXX;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商添加商品证规格信息*/
@Service
public class MGYS7_GYSTJZCZGGXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGYS7_GYSTJZCZGGXXService.class);

    public MGYS7_GYSTJZCZGGXXDao getDao(){
        return getMySqlSession().getMapper(MGYS7_GYSTJZCZGGXXDao.class);
    }

    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MGYS7_GYSTJZCZGGXXDBO md = (MGYS7_GYSTJZCZGGXXDBO)data;
   		if(EmptyHelper.isEmpty(md.getN02_ggjp())){
   			md.setN02_ggjp(StringHelper.getPinYinSample(md.getN01_ggjc()));
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
