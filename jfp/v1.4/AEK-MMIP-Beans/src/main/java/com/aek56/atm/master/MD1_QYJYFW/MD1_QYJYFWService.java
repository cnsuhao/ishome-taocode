package com.aek56.atm.master.MD1_QYJYFW;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 企业经营范围*/
@Service
public class MD1_QYJYFWService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MD1_QYJYFWService.class);

    public MD1_QYJYFWDao getDao(){
        return getMySqlSession().getMapper(MD1_QYJYFWDao.class);
    }

    /**
	 * 数据库分表
	 * @param data
	 */
	public void changeTable(MyDataBaseObjectSupport data) {
//		MD2_GYSXXDBO md = (MD2_GYSXXDBO)data;
//		if(EmptyHelper.isEmpty(md.getF02_qcpym())){
//			md.setF02_qcpym(StringHelper.getPinYinSample(md.getF01_qyqc()));
//		}
//		
////		String companyType = ((XXXXXDBO)data).getT01();
////		// 分表处理
////		if (ZERO.equals(companyType)) {
////			data.setTablename("0");
////		} else if (ONE.equals(companyType)) {
////			data.setTablename("1");
////		}
//		
//		//模糊查询索引
//		md.setGgg(GGG);
	}
}
