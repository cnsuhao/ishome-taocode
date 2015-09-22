package com.ttnn.framework.support.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.StringUtils;
import com.ttnn.HomeController;
import com.ttnn.business.cs.fvo.UserBean;
import com.ttnn.common.util.DateUtil;
import com.ttnn.common.util.PKUtil;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.ISServiceSupport;

/**
 * 数据业务操作接口定义超类<br>
 * 定义通用8个操作方法<br>
 * 缓存参考SSM配置
 * @author Spook
 * @since 0.1.0 2013-8-21
 * @version 0.1.0
 * @see ISServiceSupport
 */
public class MyServiceSupportImpl extends MyBusinessSupportImpl implements ISServiceSupport {
	/**
	 * 获得数据库操作对象
	 * @return
	 */
	public ISSQLDaoSupport getDao(){
		return mySqlSession.getMapper(ISSQLDaoSupport.class);		
	}
	
	/**
	 * 获得系统日志输出对象
	 * @return
	 */
	public Logger getLogger(){
        return LoggerFactory.getLogger(HomeController.class);
    }
		
	//////////////////////////内部业务跳过登录方法////////////////////////////////////
	/**
	 * UserBean user = new UserBean();
		user.setProductId("TTNN_PT");  
		user.setUserId("TTNN_SYSTEM"); 
		user.setLoginDateTime(DateUtil.currentTimestamp());
		todoService.setLoginerBean(user);
	 */
	@Override
	public UserBean getLoginerBean() {
		if(user!=null){
			return user;			
		}
		return super.getLoginerBean();
	}
	public void setLoginerBean(UserBean loginer) {
		user = loginer;
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
	protected UserBean user;
	//////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	///////////////////////////////////复杂操作///////////////////////////////////////////////
//	/**
//	 * @deprecated
//	 */
//	@Override
//	public int[] doBatchDelete(PageVO formParamPageModel) {
//		@SuppressWarnings("unchecked")
//		List<CSPVOSupport> formParamBean = (List<CSPVOSupport>)formParamPageModel.getPageData();
//		return getDao().doBatchDelete(formParamBean);
//	}
//	/**
//	 * @deprecated
//	 */
//	@Override
//	public int[] doBatchInsert(PageVO formParamPageModel) {
//		@SuppressWarnings("unchecked")
//		List<CSPVOSupport> formParamBean = (List<CSPVOSupport>)formParamPageModel.getPageData();
//		return getDao().doBatchInsert(formParamBean);
//	}
	

//	/**
//	 * @deprecated
//	 */
//	@Override
//	public int doUpdateAll(FrameworkDataBean formParamBean) {
//		return getDao().doUpdate(formParamBean);
//	}
	
//	/**
//	 * @deprecated
//	 */
//	public List<FrameworkDataBean> doFindList(PageVO formParamPageModel) {
//		//设定产品ID
//		if(formParamPageModel.getPageData() instanceof CSPVOSupport)
//		{
//			CSPVOSupport formParamBean = (CSPVOSupport)formParamPageModel.getPageData();
//			if(StringUtils.isNullOrEmpty(formParamBean.getEb5()))
//				formParamBean.setEb5(super.getProductId());
//		}
//		formParamPageModel.setPageLimit(Integer.MAX_VALUE);
//		List<FrameworkDataBean> lists = getDao().doSelectPage(formParamPageModel);
//		formParamPageModel.setPageData(lists);
////							formParamPageModel.getPageCurrent(), 
////							formParamPageModel.getPageLimit(), 
////							(FrameworkDataBean)formParamPageModel.getPageData()));
//		return lists;
//	}

	@Override
	public PageVO doSelectPage(PageVO formParamPageModel) {
		//设定产品ID
		if(formParamPageModel.getPageData() instanceof CSPVOSupport)
		{
			CSPVOSupport formParamBean = (CSPVOSupport)formParamPageModel.getPageData();
			if(StringUtils.isNullOrEmpty(formParamBean.getEb5()))
				formParamBean.setEb5(getProductId());
		}
		
		 formParamPageModel.setPageData(getDao().doSelectPage(formParamPageModel));
//					formParamPageModel.getPageCurrent(), 
//					formParamPageModel.getPageLimit(), 
//					(FrameworkDataBean)formParamPageModel.getPageData()));
		return formParamPageModel;
	}

	///////////////////////////////////基本操作///////////////////////////////////////////////
	/**
	 * 根据主键，逻辑删除一条数据，
	 */
	@Override
	public int toDelete(CSPVOSupport formParamBean) {
		return getDao().toDelete(formParamBean);
	}
	
	@Override
	public int doInsert(CSPVOSupport formParamBean) {
		//主键
		if(StringUtils.isNullOrEmpty(formParamBean.getPuk()))
			formParamBean.setPuk(PKUtil.getPUKey());
		//产品ID
		if(StringUtils.isNullOrEmpty(formParamBean.getEb5()))
			formParamBean.setEb5(getProductId());
		
		//有效标记、创建者、创建时间
		if(StringUtils.isNullOrEmpty(formParamBean.getDdd()))
			formParamBean.setDdd("0");
		formParamBean.setCc1(DateUtil.currentTimeMillis3());
		formParamBean.setCc2(getLoginerId());
		formParamBean.setUu1(DateUtil.currentTimeMillis3());
		formParamBean.setUu2(getLoginerId());
		
		return getDao().doInsert(formParamBean);
	}
	
	@Override
	public int doUpdate(CSPVOSupport formParamBean) {
		//更新者、更新时间		
//		formParamBean.setUu1(DateUtil.currentTimeMillis2());
		formParamBean.setUu2(getLoginerId());
		return getDao().doUpdate(formParamBean);
	}

	@Override
	public FrameworkDataBean doRead(CSPVOSupport formParamBean) {
		//有效标记、创建者、创建时间
		if(StringUtils.isNullOrEmpty(formParamBean.getDdd()))
			formParamBean.setDdd("0");
		return getDao().doRead(formParamBean);
	}

	@Override
	public int doDelete(CSPVOSupport formParamBean) {
		return getDao().doDelete(formParamBean);
	}

}
