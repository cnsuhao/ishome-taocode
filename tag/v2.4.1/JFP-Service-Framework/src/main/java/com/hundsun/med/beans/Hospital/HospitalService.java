package com.hundsun.med.beans.Hospital;
import org.ishome.jfp.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 医院表 */
@Service
public class HospitalService extends MyServiceSupport {
    private Logger logger = LoggerFactory.getLogger(HospitalService.class);

    public HospitalDao getDao(){
        return getMySqlSession().getMapper(HospitalDao.class);
    }

}
