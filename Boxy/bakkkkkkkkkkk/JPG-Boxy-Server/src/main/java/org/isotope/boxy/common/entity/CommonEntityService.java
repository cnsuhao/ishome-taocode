package org.isotope.boxy.common.entity;
import org.isotope.jfp.framework.support.sync.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/** 数据存储*/
@Service
public class CommonEntityService extends MyServiceSupport {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public CommonEntityDao getDao(){
        return getMySqlSession().getMapper(CommonEntityDao.class);
    }

}
