package com.ttnn.business.wm.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mysql.jdbc.StringUtils;
import com.ttnn.business.wm.dao.WMHTTotalDao;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 贷付宝后台数据统计服务 */
@Service
public class WMHTTotalBusiness extends MyServiceSupportImpl {

	/**
	 * 获得系统日志输出对象
	 * 
	 * @return
	 */
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMHTTotalBusiness.class);
	}

	// 系统授权信息
	@Override
	public WMHTTotalDao getDao() {
		return mySqlSession.getMapper(WMHTTotalDao.class);
	}

	/**
	 * 交易记录统计
	 * 
	 * @param paramPageVO 【F01登录会员id，F02会员名字，F03通道id，F04时间】
	 * @return
	 */
	public PageVO doSelectPageJY(PageVO formParamPageModel) { // 设定产品ID
		if (formParamPageModel.getPageData() instanceof CSPVOSupport) {
			CSPVOSupport formParamBean = (CSPVOSupport) formParamPageModel
					.getPageData();
			if (StringUtils.isNullOrEmpty(formParamBean.getEb5()))
				formParamBean.setEb5(super.getProductId());
		}

		formParamPageModel.setPageData(getDao()
				.doSelectPageJY(formParamPageModel));
		return formParamPageModel;

	}

	/**
	 * 充值记录统计
	 * 
	 * @param paramPageVO 【F01登录会员id，F02会员名字，F03通道id，F04时间】
	 * @return
	 */
	public PageVO doSelectPageCZ(PageVO formParamPageModel) {
		// 设定产品ID
		if (formParamPageModel.getPageData() instanceof CSPVOSupport) {
			CSPVOSupport formParamBean = (CSPVOSupport) formParamPageModel
					.getPageData();
			if (StringUtils.isNullOrEmpty(formParamBean.getEb5()))
				formParamBean.setEb5(super.getProductId());
		}

		formParamPageModel.setPageData(getDao()
				.doSelectPageCZ(formParamPageModel));
		return formParamPageModel;
	}

	/**
	 * 提现记录统计
	 * 
	 * @param paramPageVO 【F01登录会员id，F02会员名字，F03通道id，F04时间】
	 * @return
	 */
	public PageVO doSelectPageTX(PageVO formParamPageModel) {
		// 设定产品ID
		if (formParamPageModel.getPageData() instanceof CSPVOSupport) {
			CSPVOSupport formParamBean = (CSPVOSupport) formParamPageModel
					.getPageData();
			if (StringUtils.isNullOrEmpty(formParamBean.getEb5()))
				formParamBean.setEb5(super.getProductId());
		}

		formParamPageModel.setPageData(getDao().doSelectPageTX(formParamPageModel));
		return formParamPageModel;
	}

	/**
	 * 佣金记录统计
	 * 
	 * @param paramPageVO 【F01登录会员id，F02会员名字，F03通道id，F04时间】
	 * @return
	 */
	public PageVO doSelectPageYJ(PageVO formParamPageModel) {
		// 设定产品ID
		if (formParamPageModel.getPageData() instanceof CSPVOSupport) {
			CSPVOSupport formParamBean = (CSPVOSupport) formParamPageModel
					.getPageData();
			if (StringUtils.isNullOrEmpty(formParamBean.getEb5()))
				formParamBean.setEb5(super.getProductId());
		}

		formParamPageModel.setPageData(getDao()
				.doSelectPageTX(formParamPageModel));
		return formParamPageModel;
	}
}
