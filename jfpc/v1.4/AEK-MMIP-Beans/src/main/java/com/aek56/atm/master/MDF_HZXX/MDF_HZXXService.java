package com.aek56.atm.master.MDF_HZXX;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 患者信息*/
@Service
public class MDF_HZXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MDF_HZXXService.class);

    public MDF_HZXXDao getDao(){
        return getMySqlSession().getMapper(MDF_HZXXDao.class);
    }
    
    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MDF_HZXXDBO md = (MDF_HZXXDBO)data;
   		if(EmptyHelper.isEmpty(md.getF02_hzxmpym())){
   			md.setF02_hzxmpym(StringHelper.getPinYinSample(md.getF01_hzxm()));
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
		index.append(md.getF01_hzxm()).append(BACKSLASH2);
		index.append(md.getF02_hzxmpym()).append(BACKSLASH2);
		md.setGgg(index.toString());
   	}
}
