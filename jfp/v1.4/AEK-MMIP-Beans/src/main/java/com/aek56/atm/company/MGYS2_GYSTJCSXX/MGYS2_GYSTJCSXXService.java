package com.aek56.atm.company.MGYS2_GYSTJCSXX;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商添加厂商信息*/
@Service
public class MGYS2_GYSTJCSXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGYS2_GYSTJCSXXService.class);

    public MGYS2_GYSTJCSXXDao getDao(){
        return getMySqlSession().getMapper(MGYS2_GYSTJCSXXDao.class);
    }

    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MGYS2_GYSTJCSXXDBO md = (MGYS2_GYSTJCSXXDBO)data;
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
}
