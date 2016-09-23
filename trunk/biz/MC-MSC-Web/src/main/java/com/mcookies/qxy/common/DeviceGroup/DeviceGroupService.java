import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 设备分组表*/
@Service
public class DeviceGroupService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(DeviceGroupService.class);

    public DeviceGroupDao getDao(){
        return getMySqlSession().getMapper(DeviceGroupDao.class);
    }

}
