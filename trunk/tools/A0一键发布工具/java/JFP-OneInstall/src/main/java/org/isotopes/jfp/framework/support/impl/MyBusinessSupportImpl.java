package org.isotopes.jfp.framework.support.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.isotopes.jfp.framework.common.bean.UserBean;
import org.isotopes.jfp.framework.common.dao.DictionaryDao;
import org.isotopes.jfp.framework.support.CSDBConnectionSupport;
import org.isotopes.jfp.framework.support.ISBusinessSupport;
import org.isotopes.jfp.framework.support.bean.FrameworkDataBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;


/**
 * 业务规则超类(系统超类)
 * 
 * @since 0.1 2012-7-13
 * @version 0.1
 */
public class MyBusinessSupportImpl extends CSDBConnectionSupport implements ISBusinessSupport {

	/**
	 * 任意表，根据分类与产品ID查询
	 * dbParamBean.setTablename() 表名
	 * dbParamBean.setK01()
	 * dbParamBean.setK02()
	 * dbParamBean.setK03()
	 */
	public List<FrameworkDataBean> getDictionaryOnTable(FrameworkDataBean dbParamBean) {		
		return ((DictionaryDao)mySqlSession.getMapper(DictionaryDao.class)).getDictionaryOnTable(dbParamBean);
	}
	
	/**
	 * 标准字典查询
	 */
	public List<FrameworkDataBean> getDictionaryDefault(FrameworkDataBean dbParamBean) {		
		return ((DictionaryDao)mySqlSession.getMapper(DictionaryDao.class)).getDictionaryDefault(dbParamBean);
	}	
	/**
	 * 获得字典名称
	 */
	public String getDictionaryName(String dbPuk) {		
		return ((DictionaryDao)mySqlSession.getMapper(DictionaryDao.class)).getDictionaryName(dbPuk);
	}	
	
	/**
	 * 任意表，根据ID获得名称
	 * @param dbParamBean.setTablename() 表名
	 * @param dbParamBean.setK01() 显示用名称
	 * @param dbParamBean.setPuk() 主键 
	 */
	public String getNameOnTable(FrameworkDataBean dbParamBean) {		
		return ((DictionaryDao)mySqlSession.getMapper(DictionaryDao.class)).getNameOnTable(dbParamBean);
	}
	
//	/**
//	 * 导出数据
//	 * @return
//	 */
//	public ReportDataBean doOutputReport(ReportDataBean report){		
//		report.doRead();
//		return report;
//	}
//	
//	/**
//	 * 导入数据
//	 * @return
//	 */
//	public ReportDataBean doInputReport(ReportDataBean report){
//		
//		report.doWright();
//		return report;
//	}
	
	// Spring使用了接口名称扫描注册，当一个类实现多个接口的时候，这个类会知道注册到相应的接口下面去，
	// 出现多个的时候，就需要详细指定实现类的声明bean名称，参考Qualifier
	// @Qualifier("transactionManager")
	// @Qualifier 只能和 @Autowired 结合使用，是对 @Autowired 有益的补充。
	// 默认使用@Resource

	// 开启事务控制,处理业务
	// @Transactional
	// protected void doProcess(){
	//
	// }

	/**
	 * 获得系统日志输出对象
	 * 
	 * @return
	 */
	public Logger getLogger() {
		return LoggerFactory.getLogger(MyBusinessSupportImpl.class);
	}

	public String getLoginerId() {
		return getLoginerBean().getUserId();
	}

	public String getProductId() {
		return getLoginerBean().getProductId();
	}
	
	public String getLoginType(){
		return getLoginerBean().getLoginType();
	}
	
	/**
	 * 系统核心，不能修改
	 * @return
	 */
	public UserBean getLoginerBean() {
		getLogger().debug(">>>>>>>CONSTANT_USER_TOKEN>>>>>>>>" + getSessionAttribute(CONSTANT_USER_TOKEN));
		getLogger().debug("myMemCached>>>>>>=====" + myMemCached);

		UserBean bean = (UserBean) myMemCached.get("" + getSessionAttribute(CONSTANT_USER_TOKEN)); 
		
		return bean;
	}

	public void setSessionAttribute(String key, Object value) {
		getLogger().debug("setSessionAttribute>>>>>>=====" + key);
		RequestContextHolder.getRequestAttributes().setAttribute(key, value, 1);
	}

	public Object getSessionAttribute(String key) {
		getLogger().debug("getSessionAttribute>>>>>>=====" + key);
		return RequestContextHolder.getRequestAttributes().getAttribute(key, 1);
	}

	/**
	 * 登录拦截
	 * 
	 * @param response
	 */
	@ModelAttribute
	public void doCheckLogin(HttpServletRequest request, HttpServletResponse response) {
		getLogger().error("LoginUrl/////LoginUrl/////LoginUrl////>>>>>>====="+request.getParameter("LoginUrl"));
		if (doCheckLogin() == false) {
			getLogger().error("doCheckLogin>>>>>>=====" + request.getRequestURI());
			if (request.getRequestURI().length() > 6 && request.getRequestURI().indexOf("HOME") < 0)
				try {
//					if(request.getParameter("LoginUrl")!=null)
//						response.sendRedirect(request.getParameter("LoginUrl"));
//					else
						response.sendRedirect("/psad.html");
				} catch (IOException e) {
				}
		}
	}

	@Override
	public boolean doCheckLogin() {
		if (getSessionAttribute(CONSTANT_USER_TOKEN) == null)
			return false;
		return true;
	}

	@Override
	public boolean doCheckToken() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doCheckComputer() {
		// TODO Auto-generated method stub
		return false;
	}

}
