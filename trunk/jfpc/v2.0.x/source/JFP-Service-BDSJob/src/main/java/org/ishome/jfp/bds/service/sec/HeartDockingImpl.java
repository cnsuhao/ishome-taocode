package org.ishome.jfp.bds.service.sec;

import java.util.HashMap;
import java.util.Map;

import org.ishome.jfp.bds.service.biz.ACloundBusinessSupport;
import org.ishome.jfp.beans.HospitalCloudAccessRule.HospitalCloudAccessRuleDBO;
import org.ishome.jfp.common.Hospital.HospitalInfoService;
import org.ishome.jfp.framework.beands.RESTResultBean;
import org.ishome.jfp.framework.utils.DateHelper;
import org.ishome.jfp.framework.utils.EmptyHelper;
import org.ishome.jfp.framework.utils.PKHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

/**
 * 对接心跳，监控与安全相关
 * 
 * @author Spook
 * @version 2.0.1 2015/2/3
 * @version 2.0.0 2015/1/19
 * @since 2.0.0 2015/1/19
 */
@Service("heartDockingImpl")
public class HeartDockingImpl extends ACloundBusinessSupport {
	private static final Logger logger = LoggerFactory.getLogger(HeartDockingImpl.class);
	//FeesHistoryListHAO param;
	
	@Override
	public boolean doProcess() throws Exception {
		if(hospitalConfig==null)
			return false;
		
		RESTResultBean rs = new RESTResultBean();
		
		HashMap<String, Object> bds = new HashMap<String, Object>();
		// 返回加密Key
		bds.put(MQ_KEY_SEC, hospitalConfig.getPrimaryKey());
		// 返回业务加密开启状态
		for (Map.Entry<String, HospitalCloudAccessRuleDBO> entry : hospitalConfig.getBizConfigs().entrySet()) {
			bds.put(entry.getKey(), entry.getValue().getFb3());
		}
		
		// 返回加密测试数据(加密医院ID)
		if(hospitalConfig.getSecyrityTestFlag()){
			//获得测试使用的密钥
			String key = (String) mqService.pollFirstObjectInList(hosId+":message");
			if(EmptyHelper.isNotBlank(key)){
				bds.put(MQ_KEY_HOS, cloundSecyrityConfig.encryption(hospitalConfig, hosId ,true));
				addHospitalMessage("医院加密测试开始>>>>>");
				addHospitalMessage("测试数据为(原始数据)："+hosId);
				addHospitalMessage("测试数据为(下发数据)："+bds.get(MQ_KEY_HOS));
				hospitalConfig.setSecyrityTestFlag(true);
				HospitalInfoService.setHosSyncConfig(hospitalConfig);
			}
		}
		
		String token = hospitalConfig.getPuk();
		if (EmptyHelper.isEmpty(token)) {
			token = PKHelper.creatPUKey();
			hospitalInfoService.setHospitalToken(hosId, token);
		}
		
		// 医院端产生
		rs.setToken(token);
		// 返回医院ID（使用简单加密）
		rs.setResult(cloundSecyrityConfig.encryption(hospitalConfig, null, JSON.toJSONString(bds)));
		
		//更新心跳同步时间
		hospitalConfig.setUpdateTime(DateHelper.currentTimeMillisCN1());
		hospitalConfig.setHosId(hosId);
		HospitalInfoService.setHosSyncConfig(hospitalConfig);
		
		this.setResult(rs);
		logger.debug(rs.toString());
		return true;
	}

	@Override
	public boolean doCheck() throws Exception {
		return true;
	}

	@Override
	public boolean doInit() throws Exception {
		//param = JSON.parseObject(message,FeesHistoryListHAO.class);
		return true;
	}

}
