package com.aek56.atm.auditing.CSM3_XZJGDW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aek56.utils.MasterDataServiceSupport;

/** 行政机构单位(审核)*/
@Service
public class CSM3_XZJGDWService extends MasterDataServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CSM3_XZJGDWService.class);

    public CSM3_XZJGDWDao getDao(){
        return getMySqlSession().getMapper(CSM3_XZJGDWDao.class);
    }

}
