package com.aek56.atm.auditing.CSM1_YYXX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aek56.utils.MasterDataServiceSupport;

/** 医院信息(审核)*/
@Service
public class CSM1_YYXXService extends MasterDataServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CSM1_YYXXService.class);

    public CSM1_YYXXDao getDao(){
        return getMySqlSession().getMapper(CSM1_YYXXDao.class);
    }

}
