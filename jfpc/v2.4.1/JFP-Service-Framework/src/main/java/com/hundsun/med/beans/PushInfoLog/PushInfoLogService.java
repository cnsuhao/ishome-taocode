package com.hundsun.med.beans.PushInfoLog;
import org.ishome.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 推送信息日志表*/
@Service
public class PushInfoLogService extends MyServiceSupport {
    private Logger logger = LoggerFactory.getLogger(PushInfoLogService.class);

    public PushInfoLogDao getDao(){
        return getMySqlSession().getMapper(PushInfoLogDao.class);
    }

}
