package com.aek56.atm.master.MD6_CPXX;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 产品信息*/
@Service
public class MD6_CPXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MD6_CPXXService.class);

    public MD6_CPXXDao getDao(){
        return getMySqlSession().getMapper(MD6_CPXXDao.class);
    }

    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MD6_CPXXDBO md = (MD6_CPXXDBO)data;
   		if(EmptyHelper.isEmpty(md.getF02_cpqcpym())){
   			md.setF02_cpqcpym(StringHelper.getPinYinSample(md.getF01_cpqc()));
   		}
   		
//   		String companyType = ((XXXXXDBO)data).getT01();
//   		// 分表处理
//   		if (ZERO.equals(companyType)) {
//   			data.setTablename("0");
//   		} else if (ONE.equals(companyType)) {
//   			data.setTablename("1");
//   		}
   		//模糊查询索引
		StringBuffer index= new StringBuffer(1000);
		index.append(md.getF01_cpqc()).append(BACKSLASH2);
		index.append(md.getF02_cpqcpym()).append(BACKSLASH2);
		md.setGgg(index.toString());
   	}
}
