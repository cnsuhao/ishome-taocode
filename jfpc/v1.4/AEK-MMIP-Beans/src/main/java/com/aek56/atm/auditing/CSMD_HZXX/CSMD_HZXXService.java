package com.aek56.atm.auditing.CSMD_HZXX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aek56.utils.MasterDataServiceSupport;

/** 患者信息(审核)*/
@Service
public class CSMD_HZXXService extends MasterDataServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CSMD_HZXXService.class);

    public CSMD_HZXXDao getDao(){
        return getMySqlSession().getMapper(CSMD_HZXXDao.class);
    }

}
