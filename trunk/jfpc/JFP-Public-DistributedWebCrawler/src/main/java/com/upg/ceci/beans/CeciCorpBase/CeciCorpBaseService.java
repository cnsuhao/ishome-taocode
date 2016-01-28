package com.upg.ceci.beans.CeciCorpBase;
import org.isotope.jfp.framework.support.MyServiceSupport;
import org.isotope.jfp.framework.utils.DateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 企业基本信息(数据采集)*/
@Service("CeciCorpBaseService")
public class CeciCorpBaseService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CeciCorpBaseService.class);

    public CeciCorpBaseDao getDao(){
        return getMySqlSession().getMapper(CeciCorpBaseDao.class);
    }
    
    /**
	 * 整理上次运行作业数据
	 */
    @Transactional
	public void prepareLastJobData() {
    	try{
			CeciCorpBaseDao ccbd = getDao();
			CeciCorpBaseDBO ccb = new CeciCorpBaseDBO();
			ccb.setTableName(DateHelper.currentDate3());
			// 重新命名表
			ccbd.reNameTable(ccb);
			// 创建新的数据表
			ccbd.creatTable(ccb);
			// 数据整理
    	}catch(Exception e){
    		
    	}
	}

}
