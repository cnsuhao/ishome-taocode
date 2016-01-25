package com.aek56.atm.auditing.CSM4_CPXX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aek56.utils.MasterDataServiceSupport;

/** 产品信息(审核)*/
@Service
public class CSM4_CPXXService extends MasterDataServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CSM4_CPXXService.class);

    public CSM4_CPXXDao getDao(){
        return getMySqlSession().getMapper(CSM4_CPXXDao.class);
    }

}
