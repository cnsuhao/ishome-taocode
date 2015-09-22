package com.aek56.atm.master.MDC_PPXX;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 品牌信息*/
@Service
public class MDC_PPXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MDC_PPXXService.class);

    public MDC_PPXXDao getDao(){
        return getMySqlSession().getMapper(MDC_PPXXDao.class);
    }
    
    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MDC_PPXXDBO md = (MDC_PPXXDBO)data;
   		if(EmptyHelper.isEmpty(md.getF02_ppqcpym())){
   			md.setF02_ppqcpym(StringHelper.getPinYinSample(md.getF01_ppqc()));
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
		index.append(md.getF01_ppqc()).append(BACKSLASH2);
		index.append(md.getF02_ppqcpym()).append(BACKSLASH2);
		md.setGgg(index.toString());
   	}
}
