package com.aek56.atm.auditing.CSM6_ZCZXX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aek56.utils.MasterDataServiceSupport;

/** 注册证信息(审核)*/
@Service
public class CSM6_ZCZXXService extends MasterDataServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CSM6_ZCZXXService.class);

    public CSM6_ZCZXXDao getDao(){
        return getMySqlSession().getMapper(CSM6_ZCZXXDao.class);
    }

}
