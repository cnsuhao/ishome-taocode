package com.aek56.atm.credentials.MGACC_XDCPWSXKZ;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 厂家消毒产品卫生许可证*/
@Service
public class MGACC_XDCPWSXKZService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGACC_XDCPWSXKZService.class);

    public MGACC_XDCPWSXKZDao getDao(){
        return getMySqlSession().getMapper(MGACC_XDCPWSXKZDao.class);
    }

}
