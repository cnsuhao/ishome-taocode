package com.hundsun.med.beans.PhoneUserTerminal;
import org.ishome.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 用户终端表*/
@Service
public class PhoneUserTerminalService extends MyServiceSupport {
    private Logger logger = LoggerFactory.getLogger(PhoneUserTerminalService.class);

    public PhoneUserTerminalDao getDao(){
        return getMySqlSession().getMapper(PhoneUserTerminalDao.class);
    }

}
