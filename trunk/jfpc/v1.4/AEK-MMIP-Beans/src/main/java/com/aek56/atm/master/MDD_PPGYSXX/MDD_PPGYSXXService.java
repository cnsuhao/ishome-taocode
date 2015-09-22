package com.aek56.atm.master.MDD_PPGYSXX;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 品牌关联供应商信息*/
@Service
public class MDD_PPGYSXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MDD_PPGYSXXService.class);

    public MDD_PPGYSXXDao getDao(){
        return getMySqlSession().getMapper(MDD_PPGYSXXDao.class);
    }

}
