package com.aek56.atm.credentials.MGAD_SQSGLZCZXX;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 供应商授权书关联注册证信息*/
@Service
public class MGAD_SQSGLZCZXXService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(MGAD_SQSGLZCZXXService.class);

    public MGAD_SQSGLZCZXXDao getDao(){
        return getMySqlSession().getMapper(MGAD_SQSGLZCZXXDao.class);
    }

}
