package org.isotopes.jfp.framework.support.impl;

import java.util.List;

import org.isotopes.jfp.HomeController;
import org.isotopes.jfp.framework.common.bean.UserBean;
import org.isotopes.jfp.framework.common.util.DateUtil;
import org.isotopes.jfp.framework.common.util.PKUtil;
import org.isotopes.jfp.framework.page.bean.PageVO;
import org.isotopes.jfp.framework.support.CSDBVOSupport;
import org.isotopes.jfp.framework.support.CSPVOSupport;
import org.isotopes.jfp.framework.support.ISSQLDaoSupport;
import org.isotopes.jfp.framework.support.ISServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.mysql.jdbc.StringUtils;

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
		UserBean spUser = super.getLoginerBean();
		if(spUser==null){
			return user;			
		}
		return spUser;
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
	@Override
	public int doUpdateAll(CSPVOSupport fVO) {
		CSDBVOSupport dVO = new CSDBVOSupport();
		BeanUtils.copyProperties(fVO, dVO);
		if(StringUtils.isNullOrEmpty(dVO.getEb5()))
			dVO.setEb5(super.getProductId());
		//更新者、更新时间		
		dVO.setUu2(getLoginerId());
		return getDao().doUpdate(dVO);
	}
	
	public List<CSDBVOSupport> doFindList(CSPVOSupport fVO) {
		CSDBVOSupport dVO = new CSDBVOSupport();
		BeanUtils.copyProperties(fVO, dVO);
		if(StringUtils.isNullOrEmpty(dVO.getEb5()))
			dVO.setEb5(super.getProductId());

		return getDao().doFindList(dVO);
	}

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
		 return formParamPageModel;
	}

	///////////////////////////////////基本操作///////////////////////////////////////////////
	/**
	 * 根据主键，逻辑删除一条数据，
	 */
	@Override
	public int toDelete(CSPVOSupport fVO) {
		CSDBVOSupport dVO = new CSDBVOSupport();
		BeanUtils.copyProperties(fVO, dVO);
		return getDao().toDelete(dVO);
	}
	
	@Override
	public int doInsert(CSPVOSupport fVO) {
		CSDBVOSupport dVO = new CSDBVOSupport();
		BeanUtils.copyProperties(fVO, dVO);
		
		//主键
		if(StringUtils.isNullOrEmpty(dVO.getPuk()))
			dVO.setPuk(PKUtil.getPUKey());
		//产品ID
		if(StringUtils.isNullOrEmpty(dVO.getEb5()))
			dVO.setEb5(getProductId());
		
		//有效标记、创建者、创建时间
		if(StringUtils.isNullOrEmpty(dVO.getDdd()))
			dVO.setDdd("0");
		dVO.setCc1(DateUtil.currentTimeMillis3());
		dVO.setCc2(getLoginerId());
		dVO.setUu1(DateUtil.currentTimeMillis3());
		dVO.setUu2(getLoginerId());
		return getDao().doInsert(dVO);
	}
	
	@Override
	public int doUpdate(CSPVOSupport fVO) {
		CSDBVOSupport dVO = new CSDBVOSupport();
		BeanUtils.copyProperties(fVO, dVO);
		//更新者、更新时间		
		dVO.setUu2(getLoginerId());
		return getDao().doUpdate(dVO);
	}

	@Override
	public CSDBVOSupport doRead(CSPVOSupport fVO) {
		CSDBVOSupport dVO = new CSDBVOSupport();
		BeanUtils.copyProperties(fVO, dVO);
		return getDao().doRead(dVO);
	}

	@Override
	public int doDelete(CSPVOSupport fVO) {
		CSDBVOSupport dVO = new CSDBVOSupport();
		BeanUtils.copyProperties(fVO, dVO);
		return getDao().doDelete(dVO);
	}

}
