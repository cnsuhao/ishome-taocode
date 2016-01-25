package com.ttnn.business.wm.biz;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ttnn.business.cs.service.CSSPR1Service;
import com.ttnn.business.cs.service.CSSR01Service;
import com.ttnn.business.wm.dao.WMQTTJXXDao;
import com.ttnn.business.wm.service.WMBM01Service;
import com.ttnn.business.wm.service.WMBS01Service;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.common.util.PKUtil;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

/**
 * 前台下线管理业务
 * @author Administrator
 *
 */
@Service
public class WMQTTJXXBusiness extends MyServiceSupportImpl {
	@Resource
	protected WMUI01Service WMUI01Service_;
	@Resource
	protected CSSR01Service CSSR01Service_;
	@Resource
	protected CSSPR1Service CSSPR1Service_;
	@Resource
	protected WMBM01Service WMBM01Service_;
	@Resource
	protected WMBS01Service WMBS01Service_;

	@Override
	public WMQTTJXXDao getDao() {
		return mySqlSession.getMapper(WMQTTJXXDao.class);
	}

	/**
	 * 用户类型
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> doFind(FrameworkDataBean dbParamBean) {
		return getDao().doFind(dbParamBean);
	}

	/**
	 * 分配支付通道
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> doFindMyzfTD(FrameworkDataBean dbParamBean) {
		return getDao().doFindMyzfTD(dbParamBean);
	}

	/**
	 * 分配支付通道
	 * 
	 * @param dbParamBean
	 * @return
	 */
	public List<FrameworkDataBean> doFind2(FrameworkDataBean dbParamBean) {
		return getDao().doFind2(dbParamBean);
	}
   
	
	/**
	 * 查询未分配的通道
	 */
	public List<FrameworkDataBean> doSelectUnDis(FrameworkDataBean dbParamBean){
		return getDao().doSelectUnDis(dbParamBean);
	}
	/**
	 * 添加下级会员(会员基本信息，登陆名)
	 * @param paramBean
	 */
	// 开启事务控制,处理业务
	@Transactional
	public String doMyInsert(CSPVOSupport paramBean) {

		// 允许用户登录表
		CSPVOSupport c2 = new CSPVOSupport();//
		c2.setPuk(PKUtil.getPUKey());
		c2.setF01(PKUtil.getPUKey());
		c2.setF02("Y");
		c2.setF03(paramBean.getF01());
		c2.setF04(new Md5PasswordEncoder().encodePassword(paramBean.getF02(),null));
		c2.setF05("WM.QT");
		c2.setF06("0");
		c2.setDdd("1");

		CSSR01Service_.doInsert(c2);

		// 用户所属用户组
		CSPVOSupport c3 = new CSPVOSupport();//
		c3.setPuk(c2.getPuk());
		c3.setK01(paramBean.getK01());
		CSSPR1Service_.doInsert(c3);

		// 用户信息
		CSPVOSupport c4 = new CSPVOSupport();
		c4.setPuk(c2.getPuk());
		c4.setF04(paramBean.getF04());
		c4.setK01(super.getLoginerId());
		c4.setK02(paramBean.getK02());
		c4.setK03("0");
		c4.setF01("0");
		c4.setF02("0");
		c4.setF07("0");
		c4.setF08("0");
		c4.setF09("0");
		c4.setF10("0");
		c4.setFb4("0");
		WMUI01Service_.doInsert(c4);

		return c2.getPuk();
	}

	/**
	 * 添加下级会员(通道银行卡)
	 * @param paramBean
	 */
	@Transactional
	public void doAddCustom(CSPVOSupport paramBean) {
		 // 父通道
		CSPVOSupport prb = new CSPVOSupport();
		prb.setPuk(paramBean.getK01());
		FrameworkDataBean databean = WMBM01Service_.doRead(prb);

		//创建会员通道
		CSPVOSupport c2 = new CSPVOSupport();//
		c2.setK01(paramBean.getPuk());
		c2.setK02(super.getLoginerId());
		c2.setK03(databean.getK03());
		c2.setF05("%");
		c2.setF06("");
		c2.setFb1(databean.getFb1());
		c2.setFb2("0");
		c2.setFb3("0");
		c2.setFb4("0");
		c2.setF15(paramBean.getF15());
		c2.setF16(paramBean.getF16());
		c2.setF17(paramBean.getF17());
		c2.setF19(paramBean.getF19());
		//c2.setF18(paramBean.getF18());
         c2.setEb3(paramBean.getEb3());
         c2.setEb4(paramBean.getEb4());
		WMBM01Service_.doInsert(c2);

		CSPVOSupport c3 = new CSPVOSupport();//
		c3.setK01(paramBean.getPuk());
		c3.setK02(paramBean.getK02());
		c3.setK03(c2.getPuk());
//		开户名
		c3.setF01(paramBean.getF01());
//		银行名称
		c3.setF02(paramBean.getF02());
//		开户行名称
		c3.setF03(paramBean.getF03());
//		开户银行所在省
		c3.setF04(paramBean.getF04());
//		开户银行所在市
		c3.setF05(paramBean.getF05());
//		开户银行详细地址
//		银行种类
		c3.setF07(paramBean.getEb1());
//		分行名称
        c3.setFb3(paramBean.getFb3());
		c3.setF09("0");
		//c3.setFb1("1");
		WMBS01Service_.doInsert(c3);

	}

}