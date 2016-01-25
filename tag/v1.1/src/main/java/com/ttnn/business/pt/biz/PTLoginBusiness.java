package com.ttnn.business.pt.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.ttnn.business.cs.fvo.UserBean;
import com.ttnn.business.cs.service.CSSR01Service;
import com.ttnn.business.cs.service.CSSS01Service;
import com.ttnn.business.wm.dao.WMLoginDao;
import com.ttnn.common.util.DateUtil;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/** 用户登录 */
@Service
public class PTLoginBusiness extends MyServiceSupportImpl {
	// 允许登录用户
	@Resource
	protected CSSR01Service CSSR01Service_;
	// 系统授权信息
	@Resource
	protected CSSS01Service CSSS01Service_;

	@Resource
	PageVO pageVO;

	// 证书管理
	// @Resource
	// protected CSSSS1Service CSSSS1Service_;

	public UserBean doLogin(CSPVOSupport paramBean) {

		// 检查产品ID
		CSPVOSupport cspVOSupport1 = new CSPVOSupport();
		cspVOSupport1.setPuk(paramBean.getK01());
		FrameworkDataBean dataBean = CSSS01Service_.doRead(cspVOSupport1);
		UserBean userbean = new UserBean();
           
		//userbean.setUserInfo(user);
		userbean.setProductId("TTNN_PT");  //产品ID
		userbean.setUserId("TTNN_PT_ADMIN");  //用户ID
		userbean.setLoginDateTime(DateUtil.currentTimestamp());
		myMemCached.add(userbean.getToken(), userbean);
		
		
//		if (dataBean != null) {
//			// 检查用户名和密码
//			CSPVOSupport cspVOSupport2 = new CSPVOSupport();
//			cspVOSupport2.setF03(paramBean.getF03());
//			// 密码md5
//			String password = paramBean.getF04();//new Md5PasswordEncoder().encodePassword(paramBean.getF04(), null);
//			cspVOSupport2.setF04(password);
//			pageVO.setPageData(cspVOSupport2);
//			pageVO.setPageLimit(1);
//			pageVO = CSSR01Service_.doSelectPage(pageVO);
//			if (!(pageVO.getPageData() instanceof List && ((List) (pageVO.getPageData())).size() > 0)) {
//				paramBean.setBbb("账号或者密码不对");
//			}else{
//				//获得用户登录信息
//				FrameworkDataBean user = ((List<FrameworkDataBean>)pageVO.getPageData()).get(0);
//				userbean.setUserInfo(user);
//				userbean.setProductId(user.getEb5());  //产品ID
//				userbean.setUserId(user.getPuk());  //用户ID
//				userbean.setLoginDateTime(DateUtil.currentTimestamp());
//				myMemCached.add(userbean.getToken(), userbean);
//				
//				//设定用户相关信息
//				
//				
//				
//				
//				//设定基本信息
//				//设定权限信息
//				//设定关注信息
//			}
//
//		
//			
//			
//			
//		} else {
//			paramBean.setBbb("产品ID非法");
//		}

		// CSSS01Service_

		// 检查证书
		// CSSSS1Service_

		// 设定画面提示信息

		// 登录成功后设定用户信息数据

		return userbean;
	}

	/**
	 * 根据用户ID获得功能快捷菜单（顶部）
	 * 
	 * @param paramBean
	 * @return
	 */
	public void getMyTopMenu(CSPVOSupport paramBean, ModelAndView pageMAV) {
		WMLoginDao ld = mySqlSession.getMapper(WMLoginDao.class);
		pageMAV.addObject("mytopmenu", ld.getMyRool(paramBean));
	}

	/**
	 * 根据用户ID获得全部业务菜单（左上侧）
	 * 
	 * @param paramBean
	 * @return
	 */
	public void getMyRool(CSPVOSupport paramBean, ModelAndView pageMAV) {
		WMLoginDao ld = mySqlSession.getMapper(WMLoginDao.class);
		pageMAV.addObject("myrool", ld.getMyRool(paramBean));
	}

	/**
	 * 根据用户ID获得应用菜单（左下侧）
	 * 
	 * @param paramBean
	 * @return
	 */
	public void getMyApp(CSPVOSupport paramBean, ModelAndView pageMAV) {
		WMLoginDao ld = mySqlSession.getMapper(WMLoginDao.class);
		pageMAV.addObject("myapp", ld.getMyRool(paramBean));
	}
}
