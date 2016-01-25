package com.aek56.atm.credentials.MGTC_CJXDCPWSXKZ;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商提供医院厂家消毒产品卫生许可证*/
@Service
public class MGTC_CJXDCPWSXKZService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGTC_CJXDCPWSXKZService.class);

    public MGTC_CJXDCPWSXKZDao getDao(){
        return getMySqlSession().getMapper(MGTC_CJXDCPWSXKZDao.class);
    }

}
