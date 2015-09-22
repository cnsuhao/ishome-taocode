package com.aek56.atm.company.MGYS0_JBXX;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商基本信息*/
@Service
public class MGYS0_JBXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGYS0_JBXXService.class);

    public MGYS0_JBXXDao getDao(){
        return getMySqlSession().getMapper(MGYS0_JBXXDao.class);
    }

    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MGYS0_JBXXDBO md = (MGYS0_JBXXDBO)data;
   		if(EmptyHelper.isEmpty(md.getN02_jcpym())){
   			md.setN02_jcpym(StringHelper.getPinYinSample(md.getN01_qyjc()));
   		}
   		
		md.setGgg(EMPTY);

//   		String companyType = ((XXXXXDBO)data).getT01();
//   		// 分表处理
//   		if (ZERO.equals(companyType)) {
//   			data.setTablename("0");
//   		} else if (ONE.equals(companyType)) {
//   			data.setTablename("1");
//   		}
   	}
   	
////////////////////////审核拦截////////////////////////
//	public int doInsert(MyDataBaseObjectSupport formParamBean) {
//		CSM0_GYSXXDBO o = new CSM0_GYSXXDBO();
//		BeanUtils.copyProperties(formParamBean, o);
//		o.makePuk();
//		o.setP01_puk(formParamBean.getPuk());
//		o.setP02_sjlb(ONE);//新增
//		CSM0_GYSXXService.doInsert(o);
//		return super.doInsert(formParamBean);
//	}
//
//	@Resource
//	CSM0_GYSXXService CSM0_GYSXXService;
//	
//	public int doUpdate(MyDataBaseObjectSupport formParamBean) {
//		CSM0_GYSXXDBO o = new CSM0_GYSXXDBO();
//		BeanUtils.copyProperties(formParamBean, o);
//		o.makePuk();
//		o.setP01_puk(formParamBean.getPuk());
//		o.setP02_sjlb(TWO);//更新
//		CSM0_GYSXXService.doInsert(o);
//		return super.doUpdate(formParamBean);
//	}
}
