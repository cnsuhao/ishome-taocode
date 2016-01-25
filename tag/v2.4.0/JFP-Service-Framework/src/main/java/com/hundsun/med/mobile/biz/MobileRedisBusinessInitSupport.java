package com.hundsun.med.mobile.biz;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.hundsun.med.common.Hospital.HospitalInfoService;
import com.hundsun.med.common.Hospital.HospitalSyncConfigBean;
import com.hundsun.med.framework.beands.RESTResultBean;
import com.hundsun.med.framework.beands.UserTokenBean;
import com.hundsun.med.framework.biz.ISFinished;
import com.hundsun.med.framework.biz.ISProcess;
import com.hundsun.med.framework.biz.custom.ISCheckCustomBusiness;
import com.hundsun.med.framework.biz.custom.ISInitCustomBusiness;
import com.hundsun.med.framework.biz.custom.ISProcessCustomBusiness;
import com.hundsun.med.framework.biz.custom.ISSaveCustomBusiness;
import com.hundsun.med.framework.job.HospitalMobileKeyService;
import com.hundsun.med.framework.utils.EmptyHelper;
import com.hundsun.med.framework.utils.token.UserTokenHelper;
import com.hundsun.med.mobile.conf.MobileSecyrityConfig;
import com.hundsun.med.mobile.constants.IMSRConstants;
import com.hundsun.med.mobile.framework.HospitalSynchronizationSupport;

/**
 * Mobile Service Controller <br>
 * 手机业务请求控制器，用于接受请求、转发处理请求和处理结果
 * 
 * @version 2.3.1 2015/6/23
 * @since 2.3.1 2015/6/23
 */
public class MobileRedisBusinessInitSupport extends HospitalSynchronizationSupport implements Runnable, IMSRConstants, ISProcess, ISFinished {
	private static final Logger logger = LoggerFactory.getLogger(MobileRedisBusinessInitSupport.class);

	// 医院信息类
	@Resource
	protected HospitalInfoService hospitalInfoService;
	// 安全相关类
	@Resource
	protected MobileSecyrityConfig mobileSecyrityConfig;
	// 医院对接配置参数
	protected HospitalSyncConfigBean hospitalConfig;

	public MobileRedisBusinessInitSupport() {
		this.setVersion(HospitalMobileKeyService.getDefaultUseVersion());
	}

	@Override
	public void run() {
		// 正常处理
		try {
			logger.debug(MOBILE_SERVICE_START);
			// 数据处理
			if (doInit(true) && doProcess()) {
				logger.debug(MOBILE_SERVICE_END);
			} else {
				// 失败
				setReturnCode(this.getReturnCode());
				setReturnMessage(this.getReturnMessage());
			}
			doFinished();
			logger.debug(MOBILE_SERVICE_END);
		} catch (Exception e) {
			logger.error(MOBILE_SERVICE_CANCEL + getBizName(), e);
		}
	}

	@Override
	public final boolean doFinished() throws Exception {
		if (EmptyHelper.isEmpty(this.getOperationId()))
			return false;
		RESTResultBean rs = new RESTResultBean();// 服务器返回结果
		rs.setCode(this.getReturnCode());
		rs.setMessage(this.getReturnMessage());
		rs.setResult(mobileSecyrityConfig.encryption(hospitalConfig, (String) rs.getResult(), this.isEncryType()));// 加密处理
		// 数据返回
		mqService.putObject(this.getOperationId(), JSON.toJSONString(rs), 10, false);
		return true;
	}

	private String[] values = null;// 临时变量

	public final boolean doInit(boolean init) throws Exception {
		try {
			String bizKeyVersion = HospitalMobileKeyService.getMsrKeyName(this.getBizName(), this.getVersion());
			// 请求消息队列数据
			Object req = mqService.peekFirstObjectInList(bizKeyVersion, false);
			if (EmptyHelper.isEmpty(req))
				return false;
			// 进行数据转换
			values = new String[3];
			values[0] = (String) req;// 原始数据
			values[1] = values[0].substring(0, values[0].indexOf("//"));// TOKEN
			values[2] = values[0].substring(values[0].indexOf("//") + 2);// 业务数据
			UserTokenBean user = UserTokenHelper.getUserTokenBean(values[0]);
			// 参数传递
			this.setCompanyId(user.getCompanyId());

			String encryType_userId = user.getUserId();
			this.setUserId(encryType_userId.substring(1));

			if (ENCRYPTION.equals(encryType_userId.charAt(0)))
				this.setEncryType(true);

			this.setOperationId(user.getLoginDateTime());
			this.message = values[2];
			// 初始化配置文件
			hospitalConfig = HospitalInfoService.getHosSyncConfig(this.getCompanyId());
		} catch (Exception e) {
			throw e;
		} finally {
			values = null;// 清空缓存
		}
		return true;
	}

	@Override
	public boolean doProcess() throws Exception {
		if (this instanceof ISInitCustomBusiness) {
			ISInitCustomBusiness biz = (ISInitCustomBusiness) this;
			if (biz.beforeInit() == false) {
				setReturnCode("9910");
				setReturnMessage("业务初始化前失败xxx");
				return false;
			}
			if (biz.doInit() == false) {
				setReturnCode("9915");
				setReturnMessage("业务初始化中失败xxxxxx");
				return false;
			}
			if (biz.afterInit() == false) {
				setReturnCode("9919");
				setReturnMessage("业务初始化后失败xxxxxxxxx");
				return false;
			}
		}

		if (this instanceof ISCheckCustomBusiness) {
			ISCheckCustomBusiness biz = (ISCheckCustomBusiness) this;
			if (biz.beforeCheck() == false) {
				setReturnCode("9910");
				setReturnMessage("业务校验前失败xxx");
				return false;
			}
			if (biz.doCheck() == false) {
				setReturnCode("9915");
				setReturnMessage("业务校验中失败xxxxxx");
				return false;
			}
			if (biz.afterCheck() == false) {
				setReturnCode("9919");
				setReturnMessage("业务校验后失败xxxxxxxxx");
				return false;
			}
		}

		if (this instanceof ISProcessCustomBusiness) {
			ISProcessCustomBusiness biz = (ISProcessCustomBusiness) this;
			if (biz.beforeProcess() == false) {
				setReturnCode("9910");
				setReturnMessage("业务处理前失败xxx");
				return false;
			}
			if (biz.doProcess() == false) {
				setReturnCode("9915");
				setReturnMessage("业务处理中失败xxxxxx");
				return false;
			}
			if (biz.afterProcess() == false) {
				setReturnCode("9919");
				setReturnMessage("业务处理后失败xxxxxxxxx");
				return false;
			}
		}

		if (this instanceof ISSaveCustomBusiness) {
			ISSaveCustomBusiness biz = (ISSaveCustomBusiness) this;
			if (biz.beforeSave() == false) {
				setReturnCode("9910");
				setReturnMessage("业务初始化前失败xxx");
				return false;
			}
			if (biz.doSave() == false) {
				setReturnCode("9915");
				setReturnMessage("业务初始化中失败xxxxxx");
				return false;
			}
			if (biz.afterSave() == false) {
				setReturnCode("9919");
				setReturnMessage("业务初始化后失败xxxxxxxxx");
				return false;
			}
		}

		return false;
	}

}
