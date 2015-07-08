package org.ishome.jfp.mobile.biz;

import javax.annotation.Resource;

import org.ishome.jfp.common.Hospital.HospitalInfoService;
import org.ishome.jfp.common.Hospital.HospitalSyncConfigBean;
import org.ishome.jfp.framework.beands.RESTResultBean;
import org.ishome.jfp.framework.beands.UserTokenBean;
import org.ishome.jfp.framework.biz.ISProcess;
import org.ishome.jfp.framework.biz.custom.ISCheckCustomBusiness;
import org.ishome.jfp.framework.biz.custom.ISInitCustomBusiness;
import org.ishome.jfp.framework.biz.custom.ISProcessCustomBusiness;
import org.ishome.jfp.framework.biz.custom.ISSaveCustomBusiness;
import org.ishome.jfp.framework.job.HospitalMobileKeyService;
import org.ishome.jfp.framework.utils.EmptyHelper;
import org.ishome.jfp.framework.utils.token.UserTokenHelper;
import org.ishome.jfp.mobile.conf.MobileSecyrityConfig;
import org.ishome.jfp.mobile.constants.IMSRConstants;
import org.ishome.jfp.mobile.framework.HospitalSynchronizationSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * Mobile Service Controller <br>
 * 手机业务请求控制器，用于接受请求、转发处理请求和处理结果
 * 
 * @version 2.3.1 2015/6/23
 * @since 2.3.1 2015/6/23
 */
public class MobileDubboBusinessInitSupport extends HospitalSynchronizationSupport implements IMSRConstants, ISProcess {
	private static final Logger logger = LoggerFactory.getLogger(MobileDubboBusinessInitSupport.class);

	// 医院信息类
	@Resource
	protected HospitalInfoService hospitalInfoService;
	// 安全相关类
	@Resource
	protected MobileSecyrityConfig mobileSecyrityConfig;
	// 医院对接配置参数
	protected HospitalSyncConfigBean hospitalConfig;

	public MobileDubboBusinessInitSupport() {
		this.setVersion(HospitalMobileKeyService.getDefaultUseVersion());
	}

	private String[] values = null;// 临时变量

	public final boolean doInit(String msg) throws Exception {
		logger.debug(msg);
		try {
			if (EmptyHelper.isEmpty(msg))
				return false;
			// 进行数据转换
			values = new String[3];
			values[0] = (String) msg;// 原始数据
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

	/**
	 * 处理返回结果
	 * 
	 * @return
	 * @throws Exception
	 */
	public final String doFinished() throws Exception {
		RESTResultBean rs = new RESTResultBean();// 服务器返回结果
		rs.setCode(this.getReturnCode());
		rs.setMessage(this.getReturnMessage());
		rs.setResult(mobileSecyrityConfig.encryption(hospitalConfig, EMPTY + rs.getResult(), this.isEncryType()));// 加密处理
		return JSON.toJSONString(rs);
	}

	@Override
	public boolean doProcess() throws Exception {
		logger.debug(">>>>>.....ISInitCustomBusiness.....");
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

		logger.debug(">>>>>.....ISCheckCustomBusiness.....");
		if (this instanceof ISCheckCustomBusiness) {
			ISCheckCustomBusiness biz = (ISCheckCustomBusiness) this;
			if (biz.beforeCheck() == false) {
				setReturnCode("9920");
				setReturnMessage("业务校验前失败xxx");
				return false;
			}
			if (biz.doCheck() == false) {
				setReturnCode("9925");
				setReturnMessage("业务校验中失败xxxxxx");
				return false;
			}
			if (biz.afterCheck() == false) {
				setReturnCode("9929");
				setReturnMessage("业务校验后失败xxxxxxxxx");
				return false;
			}
		}
		logger.debug(">>>>>.....ISProcessCustomBusiness.....");
		if (this instanceof ISProcessCustomBusiness) {
			ISProcessCustomBusiness biz = (ISProcessCustomBusiness) this;
			if (biz.beforeProcess() == false) {
				setReturnCode("9930");
				setReturnMessage("业务处理前失败xxx");
				return false;
			}
			if (biz.doProcess() == false) {
				setReturnCode("9935");
				setReturnMessage("业务处理中失败xxxxxx");
				return false;
			}
			if (biz.afterProcess() == false) {
				setReturnCode("9939");
				setReturnMessage("业务处理后失败xxxxxxxxx");
				return false;
			}
		}
		logger.debug(">>>>>.....ISSaveCustomBusiness.....");
		if (this instanceof ISSaveCustomBusiness) {
			ISSaveCustomBusiness biz = (ISSaveCustomBusiness) this;
			if (biz.beforeSave() == false) {
				setReturnCode("9940");
				setReturnMessage("业务保存前失败xxx");
				return false;
			}
			if (biz.doSave() == false) {
				setReturnCode("9945");
				setReturnMessage("业务保存中失败xxxxxx");
				return false;
			}
			if (biz.afterSave() == false) {
				setReturnCode("9949");
				setReturnMessage("业务保存后失败xxxxxxxxx");
				return false;
			}
		}

		return false;
	}

}
