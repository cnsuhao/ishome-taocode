package org.ishome.jfp.bds.service.sec;

import org.ishome.jfp.bds.service.biz.ACloundBusinessSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


/**
 * 前置机版本更新（监控程序使用）
 * 
 * @author Spook
 * @version 2.1.0 2015/3/30
 */
@Service("preverDockingImpl")
public class PreverDockingImpl extends ACloundBusinessSupport {
	private static final Logger logger = LoggerFactory.getLogger(PreverDockingImpl.class);
	String curVersion;

	@Override
	public boolean doProcess() throws Exception {
		logger.debug(HDS + "(hosId)" + hospitalConfig.getHosId());
		logger.debug(HDS + "(curVersion)" + curVersion);
///////////////////////////////////////////////////////////////
//HospitalSyncConfigBean hospitalConfig = new HospitalSyncConfigBean();
//hospitalConfig.setAppVersion("v2.1.0");
//hospitalConfig.setPkgUrl("http://127.0.0.1:8888/resources/HS-MED-Hospital-SendData-2.1.0-SNAPSHOT.war");
///////////////////////////////////////////////////////////////
		if (hospitalConfig != null && !hospitalConfig.getAppVersion().equals(curVersion)) {
			//SyncParamVersionHAO ret = new SyncParamVersionHAO();
			//ret.setAppVersion(hospitalConfig.getAppVersion());
			//ret.setPkgUrl(hospitalConfig.getPkgUrl());
			// 返回升级信息
			//result.setResult(ret);
		} else {
			//result.setCode(ONE);
			//result.setResult(new SyncParamVersionHAO());
			//result.setMessage("已经是最新版本，无需更新");
		}
		return true;
	}

	@Override
	public boolean doCheck() throws Exception {
		return true;
	}

	@Override
	public boolean doInit() throws Exception {
		curVersion = message;
		return true;
	}

}
