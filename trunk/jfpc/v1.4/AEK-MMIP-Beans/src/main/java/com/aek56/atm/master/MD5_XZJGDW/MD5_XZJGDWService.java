package com.aek56.atm.master.MD5_XZJGDW;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 行政机构单位*/
@Service
public class MD5_XZJGDWService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MD5_XZJGDWService.class);

    public MD5_XZJGDWDao getDao(){
        return getMySqlSession().getMapper(MD5_XZJGDWDao.class);
    }

    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MD5_XZJGDWDBO md = (MD5_XZJGDWDBO)data;
   		if(EmptyHelper.isEmpty(md.getF02_jgqcpym())){
   			md.setF02_jgqcpym(StringHelper.getPinYinSample(md.getF01_jgqc()));
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
		index.append(md.getF01_jgqc()).append(BACKSLASH2);
		index.append(md.getF02_jgqcpym()).append(BACKSLASH2);
		md.setGgg(index.toString());
   	}
}
