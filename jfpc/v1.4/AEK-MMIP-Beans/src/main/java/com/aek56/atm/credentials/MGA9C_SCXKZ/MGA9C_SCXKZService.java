package com.aek56.atm.credentials.MGA9C_SCXKZ;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 厂家生产许可证*/
@Service
public class MGA9C_SCXKZService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGA9C_SCXKZService.class);

    public MGA9C_SCXKZDao getDao(){
        return getMySqlSession().getMapper(MGA9C_SCXKZDao.class);
    }

}
