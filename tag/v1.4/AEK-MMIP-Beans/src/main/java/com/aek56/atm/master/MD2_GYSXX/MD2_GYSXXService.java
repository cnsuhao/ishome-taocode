package com.aek56.atm.master.MD2_GYSXX;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商信息*/
@Service
public class MD2_GYSXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MD2_GYSXXService.class);

    public MD2_GYSXXDao getDao(){
        return getMySqlSession().getMapper(MD2_GYSXXDao.class);
    }
    
    /**
	 * 数据库分表
	 * @param data
	 */
	public void changeTable(MyDataBaseObjectSupport data) {
		MD2_GYSXXDBO md = (MD2_GYSXXDBO)data;
		if(EmptyHelper.isEmpty(md.getF02_qcpym())){
			md.setF02_qcpym(StringHelper.getPinYinSample(md.getF01_qyqc()));
		}
		
//		String companyType = ((XXXXXDBO)data).getT01();
//		// 分表处理
//		if (ZERO.equals(companyType)) {
//			data.setTablename("0");
//		} else if (ONE.equals(companyType)) {
//			data.setTablename("1");
//		}
		
		//模糊查询索引
		StringBuffer index= new StringBuffer(1000);
		index.append(md.getF01_qyqc()).append(BACKSLASH2);
		index.append(md.getF02_qcpym()).append(BACKSLASH2);
		md.setGgg(index.toString());
	}
}
