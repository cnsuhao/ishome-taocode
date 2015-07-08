package org.ishome.jfp.bds.service.sec;

import org.ishome.jfp.bds.service.biz.ACloundBusinessSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 对接加密测试
 * 
 * @author Spook
 * @version 2.1.0 2015/3/30
 */
@Service("securityDockingImpl")
public class SecurityDockingImpl extends ACloundBusinessSupport {
	private static final Logger logger = LoggerFactory.getLogger(SecurityDockingImpl.class);
	//SecurityTestHAO param;

	@Override
	public boolean doProcess() throws Exception {
//		logger.debug(param.toString());
//		// 比对加密测试结果
//		if (hosId.equals(param.getTestData())) {
//			addHospitalMessage("加密测试成功...VVV");
//		} else {
//			addHospitalMessage("加密测试失败...XXX");
//		}
//		addHospitalMessage("医院加密测试结束<<<<<");
		return true;
	}

	@Override
	public boolean doCheck() throws Exception {
		return true;
	}

	@Override
	public boolean doInit() throws Exception {
	//	param = JSON.parseObject(message, SecurityTestHAO.class);
		// 更新对接测试结果
		// 更新最后同步时间
		// 保存到缓存
	//	addHospitalMessage("接受到医院处理回执数据，医院开始处理时间为：" + param.getUuu());
		return true;
	}

}
