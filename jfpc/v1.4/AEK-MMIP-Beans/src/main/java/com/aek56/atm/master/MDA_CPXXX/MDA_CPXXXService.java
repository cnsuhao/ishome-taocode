package com.aek56.atm.master.MDA_CPXXX;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 产品线信息*/
@Service
public class MDA_CPXXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MDA_CPXXXService.class);

    public MDA_CPXXXDao getDao(){
        return getMySqlSession().getMapper(MDA_CPXXXDao.class);
    }
    
    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MDA_CPXXXDBO md = (MDA_CPXXXDBO)data;
   		if(EmptyHelper.isEmpty(md.getF03_zwmcpym())){
   			md.setF03_zwmcpym(StringHelper.getPinYinSample(md.getF01_zwmc()));
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
		index.append(md.getF01_zwmc()).append(BACKSLASH2);
		index.append(md.getF03_zwmcpym()).append(BACKSLASH2);
		md.setGgg(index.toString());
   	}
}
