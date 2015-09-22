package com.aek56.atm.master.MD7_CPGGXX;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 产品规格信息*/
@Service
public class MD7_CPGGXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MD7_CPGGXXService.class);

    public MD7_CPGGXXDao getDao(){
        return getMySqlSession().getMapper(MD7_CPGGXXDao.class);
    }
    
    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MD7_CPGGXXDBO md = (MD7_CPGGXXDBO)data;
   		if(EmptyHelper.isEmpty(md.getF02_ggqcpym())){
   			md.setF02_ggqcpym(StringHelper.getPinYinSample(md.getF01_ggqc()));
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
		index.append(md.getF01_ggqc()).append(BACKSLASH2);
		index.append(md.getF02_ggqcpym()).append(BACKSLASH2);
		md.setGgg(index.toString());
   	}
}
