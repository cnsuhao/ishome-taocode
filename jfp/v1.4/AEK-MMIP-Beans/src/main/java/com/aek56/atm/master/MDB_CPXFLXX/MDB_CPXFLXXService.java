package com.aek56.atm.master.MDB_CPXFLXX;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 产品线分类信息*/
@Service
public class MDB_CPXFLXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MDB_CPXFLXXService.class);

    public MDB_CPXFLXXDao getDao(){
        return getMySqlSession().getMapper(MDB_CPXFLXXDao.class);
    }
    
    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MDB_CPXFLXXDBO md = (MDB_CPXFLXXDBO)data;
   		if(EmptyHelper.isEmpty(md.getF02_flmcpym())){
   			md.setF02_flmcpym(StringHelper.getPinYinSample(md.getF01_flmc()));
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
		index.append(md.getF01_flmc()).append(BACKSLASH2);
		index.append(md.getF02_flmcpym()).append(BACKSLASH2);
		md.setGgg(index.toString());
   	}
}
