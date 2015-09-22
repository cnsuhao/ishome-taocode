package com.aek56.atm.company.MCS0_JBXX;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.helper.StringHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 厂商基本信息*/
@Service
public class MCS0_JBXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MCS0_JBXXService.class);

    public MCS0_JBXXDao getDao(){
        return getMySqlSession().getMapper(MCS0_JBXXDao.class);
    }

    /**
   	 * 数据库分表
   	 * @param data
   	 */
   	public void changeTable(MyDataBaseObjectSupport data) {
   		MCS0_JBXXDBO md = (MCS0_JBXXDBO)data;
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
//   	public int doInsert(MyDataBaseObjectSupport formParamBean) {
//		CSM2_CSXXDBO o = new CSM2_CSXXDBO();
//		BeanUtils.copyProperties(formParamBean, o);
//		o.makePuk();
//		o.setP01_puk(formParamBean.getPuk());
//		o.setP02_sjlb(ONE);//新增
//		CSM2_CSXXService.doInsert(o);
//		return super.doInsert(formParamBean);
//	}
//
//	@Resource
//	CSM2_CSXXService CSM2_CSXXService;
//	
//	public int doUpdate(MyDataBaseObjectSupport formParamBean) {
//		CSM2_CSXXDBO o = new CSM2_CSXXDBO();
//		BeanUtils.copyProperties(formParamBean, o);
//		o.makePuk();
//		o.setP01_puk(formParamBean.getPuk());
//		o.setP02_sjlb(TWO);//更新
//		CSM2_CSXXService.doInsert(o);
//		return super.doUpdate(formParamBean);
//	}
}
