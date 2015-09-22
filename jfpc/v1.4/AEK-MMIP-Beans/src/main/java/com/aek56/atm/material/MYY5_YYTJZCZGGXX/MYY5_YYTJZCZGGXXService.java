package com.aek56.atm.material.MYY5_YYTJZCZGGXX;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 医院添加商品规格信息*/
@Service
public class MYY5_YYTJZCZGGXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MYY5_YYTJZCZGGXXService.class);

    public MYY5_YYTJZCZGGXXDao getDao(){
        return getMySqlSession().getMapper(MYY5_YYTJZCZGGXXDao.class);
    }
    
    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MYY5_YYTJZCZGGXXDBO md = (MYY5_YYTJZCZGGXXDBO)data;
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
