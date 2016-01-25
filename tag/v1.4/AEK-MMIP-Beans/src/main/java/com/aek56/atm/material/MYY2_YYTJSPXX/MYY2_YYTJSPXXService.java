package com.aek56.atm.material.MYY2_YYTJSPXX;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 医院添加商品信息*/
@Service
public class MYY2_YYTJSPXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MYY2_YYTJSPXXService.class);

    public MYY2_YYTJSPXXDao getDao(){
        return getMySqlSession().getMapper(MYY2_YYTJSPXXDao.class);
    }

    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MYY2_YYTJSPXXDBO md = (MYY2_YYTJSPXXDBO)data;
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
