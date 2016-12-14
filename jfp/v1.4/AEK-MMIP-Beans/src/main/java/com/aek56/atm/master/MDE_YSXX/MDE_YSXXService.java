package com.aek56.atm.master.MDE_YSXX;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 医生信息*/
@Service
public class MDE_YSXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MDE_YSXXService.class);

    public MDE_YSXXDao getDao(){
        return getMySqlSession().getMapper(MDE_YSXXDao.class);
    }

    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MDE_YSXXDBO md = (MDE_YSXXDBO)data;
   		if(EmptyHelper.isEmpty(md.getF02_yyxmpym())){
   			md.setF02_yyxmpym(StringHelper.getPinYinSample(md.getF01_yyxm()));
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
		index.append(md.getF01_yyxm()).append(BACKSLASH2);
		index.append(md.getF02_yyxmpym()).append(BACKSLASH2);
		md.setGgg(index.toString());
   	}
}
