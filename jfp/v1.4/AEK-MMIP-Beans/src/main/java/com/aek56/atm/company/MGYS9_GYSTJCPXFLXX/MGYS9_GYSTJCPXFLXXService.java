package com.aek56.atm.company.MGYS9_GYSTJCPXFLXX;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商添加产品线分类信息*/
@Service
public class MGYS9_GYSTJCPXFLXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGYS9_GYSTJCPXFLXXService.class);

    public MGYS9_GYSTJCPXFLXXDao getDao(){
        return getMySqlSession().getMapper(MGYS9_GYSTJCPXFLXXDao.class);
    }

    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MGYS9_GYSTJCPXFLXXDBO md = (MGYS9_GYSTJCPXFLXXDBO)data;
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
