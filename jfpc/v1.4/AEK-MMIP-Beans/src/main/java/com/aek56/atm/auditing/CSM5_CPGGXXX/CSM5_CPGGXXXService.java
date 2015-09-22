package com.aek56.atm.auditing.CSM5_CPGGXXX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aek56.utils.MasterDataServiceSupport;

/** 产品规格信息(审核)*/
@Service
public class CSM5_CPGGXXXService extends MasterDataServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CSM5_CPGGXXXService.class);

    public CSM5_CPGGXXXDao getDao(){
        return getMySqlSession().getMapper(CSM5_CPGGXXXDao.class);
    }

}
