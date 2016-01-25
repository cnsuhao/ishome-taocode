package com.ttnn.business.wm.biz;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.StringUtils;
import com.ttnn.business.cs.fvo.UserBean;
import com.ttnn.business.cs.service.CSSL11Service;
import com.ttnn.business.wm.dao.WMLoginDao;
import com.ttnn.common.util.DateUtil;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 用户登录 */
@Service
public class WMLoginBusiness extends MyServiceSupportImpl {
	@Resource
	private CSSL11Service CSSL11Service_;

	/**
	 * 获得系统日志输出对象
	 * 
	 * @return
	 */
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMLoginBusiness.class);
	}

	// 系统授权信息
	@Override
	public WMLoginDao getDao() {
		return mySqlSession.getMapper(WMLoginDao.class);
	}

	@Resource
	PageVO pageVO;

	// 证书管理
	// @Resource
	// protected CSSSS1Service CSSSS1Service_;

	public FrameworkDataBean doLogin(CSPVOSupport formParamBean, boolean encoder) {
		getLogger().debug("formParamBean" + formParamBean);
		// 根据授权域名获得对应产品信息
		CSPVOSupport dataParamBean = new CSPVOSupport();
		dataParamBean.setK02(formParamBean.getEb5());// 产品域名
		// 获得登录业务
		FrameworkDataBean Product = getDao().inMySystem(dataParamBean);
		if (Product != null && !StringUtils.isNullOrEmpty(Product.getPuk())) {
			String ProductId = Product.getPuk();
			dataParamBean = new CSPVOSupport();
			dataParamBean.setEb5(ProductId);// 产品ID
			dataParamBean.setF02(formParamBean.getF02());
			dataParamBean.setF03(formParamBean.getF03());
			if (encoder)
				dataParamBean.setF04(new Md5PasswordEncoder().encodePassword(formParamBean.getF04(), null));// 密码md5
			else
				dataParamBean.setF04(formParamBean.getF04());// 密码md5
			// 检查用户名和密码
			FrameworkDataBean user = getDao().getMySystem(dataParamBean);
			if (user == null || StringUtils.isNullOrEmpty(user.getPuk())) {
				formParamBean.setBbb("非法用户登录");
			} else {
				UserBean userbean = new UserBean();
				// 产品ID
				userbean.setProductId(ProductId);
				// 用户ID
				userbean.setUserId(user.getPuk());
				// 登录时间
				userbean.setLoginDateTime(DateUtil.currentTimestamp());
				// 客户端IP
				userbean.setLoginIP(formParamBean.getFb2());
				// 客户端MAC
				userbean.setLoginMAC("");
				// 记录登录日志
				{
					CSPVOSupport loginParamBean = new CSPVOSupport();
					// 登录用户名
					loginParamBean.setK03(formParamBean.getF03());
					// 登录域名
					loginParamBean.setK02(Product.getK02());
					// 系统授权编号
					loginParamBean.setK01(Product.getK01());
					// 客户端IP
					loginParamBean.setFb2(formParamBean.getFb2());
					// 判断是否为超级管理员
					if (Product.getK03().equals(formParamBean.getF03())) {
						user.setFb3("1");
						loginParamBean.setFb3("1");
						userbean.setFb3("1");
					} else {
						user.setFb3("0");
						loginParamBean.setFb3("0");
						userbean.setFb3("0");
					}
					// 客户端入口种类
					userbean.setLoginType(formParamBean.getF02());
					loginParamBean.setEb2(formParamBean.getF02());
					// 缓存保存
					getLogger().debug(">>>>>>>CONSTANT_USER_TOKEN>>>>>>>>" + userbean.getToken());

					setSessionAttribute(CONSTANT_USER_TOKEN, userbean.getToken());
					myMemCached.add(userbean.getToken(), userbean);

					user.setK01(userbean.getToken());// 下发Tonken

					CSSL11Service_.setLoginerBean(userbean);
					CSSL11Service_.doInsert(loginParamBean);

				}

			}
			return user;
		} else {
			formParamBean.setBbb("产品ID非法");
		}

		// 设定用户相关信息

		// 设定基本信息
		// 设定权限信息
		// 设定关注信息
		// CSSS01Service_

		// 检查证书
		// CSSSS1Service_

		// 设定画面提示信息

		// 登录成功后设定用户信息数据

		return null;
	}

	/**
	 * 根据用户ID获得功能快捷菜单（顶部）
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int getMyTopMenu(CSPVOSupport formParamBean, ModelAndView pageMAV) {
		getLogger().debug("formParamBean" + formParamBean);
		List<FrameworkDataBean> menus = getDao().getMyRool(formParamBean);
		pageMAV.addObject("mytopmenu", menus);
		return menus.size();
	}

	/**
	 * 根据用户ID获得全部业务菜单（左上侧）
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int getMyRool(CSPVOSupport formParamBean, ModelAndView pageMAV) {
		getLogger().debug("formParamBean" + formParamBean);

		List<FrameworkDataBean> menus = getDao().getMyRool(formParamBean);
		pageMAV.addObject("myrool", menus);
		return menus.size();
	}

	/**
	 * 超级管理员获得全部业务菜单（左上侧）
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int getAllMyRool(CSPVOSupport formParamBean, ModelAndView pageMAV) {
		getLogger().debug("formParamBean" + formParamBean);
		List<FrameworkDataBean> menus = getDao().getAllMyRool(formParamBean);
		pageMAV.addObject("myrool", menus);
		return menus.size();
	}

	/**
	 * 根据用户ID获得应用菜单（左下侧）
	 * 
	 * @param formParamBean
	 * @return
	 */
	public int getMyApp(CSPVOSupport formParamBean, ModelAndView pageMAV) {
		getLogger().debug("formParamBean" + formParamBean);
		List<FrameworkDataBean> menus = getDao().getMyApp(formParamBean);
		pageMAV.addObject("myapp", menus);
		return menus.size();
	}
}
