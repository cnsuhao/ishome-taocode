package com.aek56.atm.auditing.CSM2_CSXX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aek56.utils.MasterDataServiceSupport;

/** 厂商信息(审核)*/
@Service
public class CSM2_CSXXService extends MasterDataServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CSM2_CSXXService.class);

    public CSM2_CSXXDao getDao(){
        return getMySqlSession().getMapper(CSM2_CSXXDao.class);
    }

}
