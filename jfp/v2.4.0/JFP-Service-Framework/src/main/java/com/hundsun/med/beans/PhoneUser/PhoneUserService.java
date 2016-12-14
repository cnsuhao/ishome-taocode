package com.hundsun.med.beans.PhoneUser;
import org.ishome.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 手机注册信息用户表*/
@Service
public class PhoneUserService extends MyServiceSupport {
    private Logger logger = LoggerFactory.getLogger(PhoneUserService.class);

    public PhoneUserDao getDao(){
        return getMySqlSession().getMapper(PhoneUserDao.class);
    }

}
