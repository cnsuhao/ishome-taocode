import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

@Controller
/** 设备分组表*/
public class DeviceGroupController extends MyControllerSupport {
    
    @Resource
    protected DeviceGroupService DeviceGroupService_;

    public MyModelAndViewSupport getModelAndView(){
        return new MyModelAndViewSupport("device_group");
    }

}
