package com.aek56.atm.auditing.CSM0_GYSXX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aek56.utils.MasterDataServiceSupport;

/** 供应商信息(审核)*/
@Service
public class CSM0_GYSXXService extends MasterDataServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CSM0_GYSXXService.class);

    public CSM0_GYSXXDao getDao(){
        return getMySqlSession().getMapper(CSM0_GYSXXDao.class);
    }

}
