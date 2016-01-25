package com.aek56.atm.auditing.CSMC_YSXX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aek56.utils.MasterDataServiceSupport;

/** 医生信息(审核)*/
@Service
public class CSMC_YSXXService extends MasterDataServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CSMC_YSXXService.class);

    public CSMC_YSXXDao getDao(){
        return getMySqlSession().getMapper(CSMC_YSXXDao.class);
    }

}
