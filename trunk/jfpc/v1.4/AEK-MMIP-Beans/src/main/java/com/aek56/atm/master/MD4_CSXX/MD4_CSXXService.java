package com.aek56.atm.master.MD4_CSXX;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 厂商信息*/
@Service
public class MD4_CSXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MD4_CSXXService.class);

    public MD4_CSXXDao getDao(){
        return getMySqlSession().getMapper(MD4_CSXXDao.class);
    }
    
    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MD4_CSXXDBO md = (MD4_CSXXDBO)data;
   		if(EmptyHelper.isEmpty(md.getF02_qcpym())){
   			md.setF02_qcpym(StringHelper.getPinYinSample(md.getF01_qyqc()));
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
		index.append(md.getF01_qyqc()).append(BACKSLASH2);
		index.append(md.getF02_qcpym()).append(BACKSLASH2);
		md.setGgg(index.toString());
   	}
}
