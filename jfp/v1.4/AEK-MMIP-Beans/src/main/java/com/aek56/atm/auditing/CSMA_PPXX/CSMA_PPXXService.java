package com.aek56.atm.auditing.CSMA_PPXX;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.aek56.utils.MasterDataServiceSupport;

/** 品牌信息(审核)*/
@Service
public class CSMA_PPXXService extends MasterDataServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CSMA_PPXXService.class);

    public CSMA_PPXXDao getDao(){
        return getMySqlSession().getMapper(CSMA_PPXXDao.class);
    }

}
