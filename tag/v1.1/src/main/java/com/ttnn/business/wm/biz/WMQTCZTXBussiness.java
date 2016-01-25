package com.ttnn.business.wm.biz;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;
import com.ttnn.business.cs.fvo.UserBean;
import com.ttnn.business.wm.dao.WMQTCZTXDao;
import com.ttnn.business.wm.service.WMBM01Service;
import com.ttnn.business.wm.service.WMBM02Service;
import com.ttnn.business.wm.service.WMBM03Service;
import com.ttnn.business.wm.service.WMBM04Service;
import com.ttnn.business.wm.service.WMBM05Service;
import com.ttnn.business.wm.service.WMBMA1Service;
import com.ttnn.business.wm.service.WMBS01Service;
import com.ttnn.business.wm.service.WMUI01Service;
import com.ttnn.common.util.DateUtil;
import com.ttnn.common.util.PKUtil;
import com.ttnn.framework.bean.FrameworkDataBean;
import com.ttnn.framework.page.bean.PageVO;
import com.ttnn.framework.support.CSModelAndViewSupport;
import com.ttnn.framework.support.CSPVOSupport;
import com.ttnn.framework.support.ISSQLDaoSupport;
import com.ttnn.framework.support.impl.MyServiceSupportImpl;

@Service
public class WMQTCZTXBussiness extends MyServiceSupportImpl {

	@Resource
	protected WMBM02Service WMBM02Service_;
	/**
	 * 佣金记录
	 */
	@Resource
	protected WMBM04Service WMBM04Service_;
	/**
	 * 佣金提现记录
	 */
	@Resource
	protected WMBM05Service WMBM05Service_;

	/**
	 * 个人支付通道
	 */
	@Resource
	protected WMBM01Service WMBM01Service_;
	/**
	 * 系统支付通道
	 */
	@Resource
	protected WMBMA1Service WMBMA1Service_;
	/**
	 * 银行卡
	 */
	@Resource
	protected WMBS01Service WMBS01Service_;
	/**
	 * 个人信息
	 */
	@Resource
	protected WMUI01Service WMUI01Service_;

	/**
	 * 获得系统日志输出对象
	 * 
	 * @return
	 */
	public Logger getLogger() {
		return LoggerFactory.getLogger(WMLoginBusiness.class);
	}

	/**
	 * 获得数据库操作对象
	 * 
	 * @return
	 */
	public WMQTCZTXDao getDao() {
		return mySqlSession.getMapper(WMQTCZTXDao.class);
	}
	
	//格式化，保留两位小数 
	private java.text.DecimalFormat dataFormat =new java.text.DecimalFormat("0.00"); 
	
	/**
	 * 充值
	 * 
	 * @param paramBean
	 * @throws Exception
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void doCZInsert(CSPVOSupport paramBean) throws Exception {
		getLogger().debug("充值预备操作开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		getLogger().debug("paramBean" + paramBean);
		// 1、插入充值，申请中
		// 2、插入佣金，未审核
		// 支付通道PUK
		CSPVOSupport c2 = new CSPVOSupport();
		c2.setPuk(paramBean.getF01());
		// 根据时间动态获得费率
		FrameworkDataBean grzftdFrameworkDataBean = WMBM01Service_.doRead(c2);
		if (grzftdFrameworkDataBean == null) {
			throw new Exception("充值失败，【个人通道】不存在");
		}
		// 系统通道
		c2 = new CSPVOSupport();
		c2.setPuk(grzftdFrameworkDataBean.getK03());
		FrameworkDataBean xttdFrameworkDataBean = WMBMA1Service_.doRead(c2);
		if (xttdFrameworkDataBean == null) {
			throw new Exception("充值失败，【系统通道】不存在");
		}
		// 银行卡识别ID
		// c2 = new CSPVOSupport();
		// c2.setPuk(paramBean.getF03());
		// FrameworkDataBean yhkFrameworkDataBean = WMBS01Service_.doRead(c2);
		// 个人信息
		c2 = new CSPVOSupport();
		c2.setPuk(super.getLoginerId());
		FrameworkDataBean grxxFrameworkDataBean = WMUI01Service_.doRead(c2);
		if (grxxFrameworkDataBean == null) {
			throw new Exception("充值失败，【非法用户】");
		}
		// 查询出费率
		BigDecimal czsxfl = new BigDecimal("0.01");
		// if ("%".equals(zftdFrameworkDataBean.getF05()))
		if (grzftdFrameworkDataBean != null && !StringUtils.isNullOrEmpty(grzftdFrameworkDataBean.getF15()))
			czsxfl = czsxfl.multiply(new BigDecimal(grzftdFrameworkDataBean.getF15()));
		// 画面输入充值金额
		BigDecimal czje = new BigDecimal(paramBean.getF07());
		// 根据费率计算充值费用
		BigDecimal czsxfy = czje.multiply(czsxfl);
		// 计算出实得金额
		BigDecimal sdje = czje.subtract(czsxfy);

		// 存入DB
		CSPVOSupport jydbParamBean = new CSPVOSupport();

		// 操作流水号
		jydbParamBean.setPuk(paramBean.getPuk());
		// 用户ID
		jydbParamBean.setK01(super.getLoginerId());
		// 通道ID
		jydbParamBean.setK02(paramBean.getF01());
		// // 卡ID
		// jydbParamBean.setK03(paramBean.getF03());
		// // 银行账户
		// jydbParamBean.setF01(yhkFrameworkDataBean.getK02());
		// // 授权编号
		// jydbParamBean.setF02(yhkFrameworkDataBean.getFb1());
		// // 开户名
		// jydbParamBean.setF03(yhkFrameworkDataBean.getF01());
		 // 银行名称
		 jydbParamBean.setF04(paramBean.getF04());
		// // 卡片种类
		// jydbParamBean.setF05(yhkFrameworkDataBean.getF08());
		// 充值操作状态
		jydbParamBean.setF06("0");// 0申请中
		// 充值金额
		jydbParamBean.setF07(paramBean.getF07());
		// 充值流水号（银行返回）
		// 手机号码
		jydbParamBean.setF09(grxxFrameworkDataBean.getF05());
		// 验证码
		jydbParamBean.setF10(paramBean.getF10());
		// 交易场所（证书所在场所）
		// 对账校验结果(0未校验1校验失败2校验成功)
		jydbParamBean.setF11("0");
		// 操作IP
		// 交易费率
		jydbParamBean.setF14(dataFormat.format(czsxfl));
		// 交易说明
		jydbParamBean.setBbb(paramBean.getBbb());
		// 实得金额
		jydbParamBean.setF18(dataFormat.format(sdje));
		// 通道名称
		jydbParamBean.setFb1(grzftdFrameworkDataBean.getFb1());
		// 发生前账户通道余额
		jydbParamBean.setFb2(grzftdFrameworkDataBean.getFb4());

		// 上级用户ID
		String parentID = grxxFrameworkDataBean.getK01();

		// 审核状态
		jydbParamBean.setFb5("1");
		// 交易手续费
		jydbParamBean.setEb2(dataFormat.format(czsxfy));
		// 上级用户ID
		jydbParamBean.setEb4(parentID);

		// 佣金计算
		if (!StringUtils.isNullOrEmpty(parentID)) {
			// 上级用户信息
			c2 = new CSPVOSupport();
			c2.setPuk(parentID);
			grxxFrameworkDataBean = WMUI01Service_.doRead(c2);

			// 判断提成种类
			String tczl = "0";
			if (grxxFrameworkDataBean != null && !StringUtils.isNullOrEmpty(grxxFrameworkDataBean.getK02())) {
				tczl = grxxFrameworkDataBean.getK03();
				getLogger().debug("上线提成种类为==" + grxxFrameworkDataBean.getF04() + "===>>>>>" + tczl);
			}

			// 上级用户通道
			c2 = new CSPVOSupport();
			c2.setK01(parentID);
			c2.setK03(grzftdFrameworkDataBean.getK03());
			c2.setEb5(super.getProductId());
			c2.setTablename("wmbm01");
			List<FrameworkDataBean> grzftdParentsFrameworkDataBeans = getDictionaryOnTable(c2);
			FrameworkDataBean grzftdParentFrameworkDataBean;
			if (grzftdParentsFrameworkDataBeans.size() > 0)
				grzftdParentFrameworkDataBean = grzftdParentsFrameworkDataBeans.get(0);
			else
				grzftdParentFrameworkDataBean = new FrameworkDataBean();

			// 处理佣金
			CSPVOSupport yjdbParamBean = new CSPVOSupport();

			// 订单号
			yjdbParamBean.setPuk(PKUtil.getPUKey());
			// 交易种类
			yjdbParamBean.setK01("0");
			// 交易ID
			yjdbParamBean.setK02(jydbParamBean.getPuk());
			// 用户ID
			yjdbParamBean.setK03(parentID);
			// 交易操作金额
			yjdbParamBean.setF01(paramBean.getF07());
			// 结算操作状态
			yjdbParamBean.setF02("0");
			// 佣金提现记录ID
			// 实得金额
			yjdbParamBean.setF04(jydbParamBean.getF18());
			// 交易费率
			yjdbParamBean.setF05(dataFormat.format(czsxfl));
			// 结算费
			// 结算上限
			// 产生佣金金额
			BigDecimal czyj = BigDecimal.ZERO;
			{
				// 查询出费率
				czsxfl = new BigDecimal("0.01");
				// if ("%".equals(zftdFrameworkDataBean.getF05()))
				if (grzftdParentFrameworkDataBean != null && !StringUtils.isNullOrEmpty(grzftdParentFrameworkDataBean.getF15()))
					czsxfl = czsxfl.multiply(new BigDecimal(grzftdParentFrameworkDataBean.getF15()));
				// 画面输入充值金额
				// 根据费率计算充值费用
				czyj = czsxfy.subtract(czje.multiply(czsxfl));
			}
			yjdbParamBean.setF08(dataFormat.format(czyj));
			// 上级交易费率
			yjdbParamBean.setF09(grzftdParentFrameworkDataBean.getF15());
			// 上级结算费
			// 上级结算上限

			// 产生系统成本金额
			BigDecimal xtcb = BigDecimal.ZERO;
			{
				// 查询出费率
				czsxfl = new BigDecimal("0.01");
				// if ("%".equals(zftdFrameworkDataBean.getF05()))
				if (xttdFrameworkDataBean != null && !StringUtils.isNullOrEmpty(xttdFrameworkDataBean.getF15()))
					czsxfl = czsxfl.multiply(new BigDecimal(xttdFrameworkDataBean.getF15()));
				// 画面输入充值金额
				// 根据费率计算充值费用
				xtcb = czje.multiply(czsxfl);
				yjdbParamBean.setF12(dataFormat.format(xtcb));
			}

			// 计算交易利润(手续费-佣金-成本)
			BigDecimal jylr = BigDecimal.ZERO;
			{
				jylr = czsxfy.subtract(czyj).subtract(xtcb);
			}
			yjdbParamBean.setFb3(dataFormat.format(jylr));
			jydbParamBean.setFb3(dataFormat.format(jylr));

			// 系统交易费率
			yjdbParamBean.setF13(xttdFrameworkDataBean.getF15());
			// 系统结算费
			// 系统结算上限
			// 备注说明
			yjdbParamBean.setBbb(jydbParamBean.getBbb());
			// 审核状态
			yjdbParamBean.setFb1("0");
			// 通道ID
			yjdbParamBean.setEb2(grzftdParentFrameworkDataBean.getPuk());
			// 通道ID
			yjdbParamBean.setEb3(grzftdParentFrameworkDataBean.getK03());
			// 上线用户ID
			yjdbParamBean.setEb4(super.getLoginerId());
			yjdbParamBean.setDdd("1");

			// 插入佣金记录
			if (czyj.doubleValue() > 0 && ("1".equals(tczl) || "3".equals(tczl))) {
				WMBM04Service_.doInsert(yjdbParamBean);
				getLogger().debug("佣金记录保存成功");
				// 佣金记录ID
				jydbParamBean.setEb3(yjdbParamBean.getPuk());
			}
		}

		// 插入充值记录
		WMBM02Service_.doInsert(jydbParamBean);
		getLogger().debug("充值记录保存成功");
		getLogger().debug("充值预备操作结束<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}

	
	
	private void init(UserBean loginer) {
		WMBM02Service_.setLoginerBean(loginer);
		WMBM04Service_.setLoginerBean(loginer);
		WMBM05Service_.setLoginerBean(loginer);
		WMBM01Service_.setLoginerBean(loginer);
		WMBMA1Service_.setLoginerBean(loginer);
		WMBS01Service_.setLoginerBean(loginer);
		WMUI01Service_.setLoginerBean(loginer);
	}

	/**
	 * 充值成功
	 * 
	 * @param paramBean
	 * @throws Exception
	 * @see ISSQLDaoSupport#doInsert(CSPVOSupport)
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void doCZUpdate(FrameworkDataBean paramBean, CSModelAndViewSupport pageMAV) throws Exception {
		getLogger().debug("回调操作开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		getLogger().debug("paramBean" + paramBean);
		pageMAV.addObject("puk", paramBean.getPuk());
		int result = 0;
		// 充值佣金
		BigDecimal czyj = BigDecimal.ZERO;
		// 实得金额
		BigDecimal sdje = BigDecimal.ZERO;
		// 系统通道ID
		String xttdid = "xttdid";
		CSPVOSupport c2 = new CSPVOSupport();
		c2.setPuk(paramBean.getPuk());
		FrameworkDataBean czFrameworkDataBean = WMBM02Service_.doRead(c2);

		if (czFrameworkDataBean == null) {
			pageMAV.addObject("message", "充值失败【该笔充值不存在】");
			throw new Exception("充值失败【该笔充值不存在】");
		}

		if ("2".equals(czFrameworkDataBean.getF06())) {
			pageMAV.addObject("message", "充值成功【该笔充值已经完成】");
			return;
		}
		
		/////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////
		//初始化系统
		UserBean user = null;
		try{
			user = getLoginerBean();
		}catch(Exception e){}
		if(user==null){
			if("9".equals(paramBean.getDdd())){
				user = new UserBean();
				user.setProductId(czFrameworkDataBean.getEb5());  
				user.setUserId(czFrameworkDataBean.getCc2()); 
				user.setLoginDateTime(DateUtil.currentTimestamp());
				init(user);	
			}else if("P".equals(paramBean.getDdd())){
				user = new UserBean();
				user.setProductId(czFrameworkDataBean.getEb5());  
				user.setUserId("P"+czFrameworkDataBean.getCc2()); 
				user.setLoginDateTime(DateUtil.currentTimestamp());
				init(user);		
			}else{
				throw new Exception("充值失败【非法操作】");
			}
		}
		/////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////

		
		// 获得实得金额
		sdje = new BigDecimal(czFrameworkDataBean.getF18());
		// 修正通道总金额
		{
			// 支付通道PUK
			c2 = new CSPVOSupport();
			c2.setPuk(czFrameworkDataBean.getK02());
			FrameworkDataBean grzftdMyFrameworkDataBean = getDao().doRead(c2);
			xttdid = grzftdMyFrameworkDataBean.getK03();
			// 通道内充值总额
			BigDecimal czze = BigDecimal.ZERO;
			if (grzftdMyFrameworkDataBean != null && !StringUtils.isNullOrEmpty(grzftdMyFrameworkDataBean.getFb2()))
				czze = new BigDecimal(grzftdMyFrameworkDataBean.getFb2());

			// 画面输入充值金额
			BigDecimal czje = BigDecimal.ZERO;
			if (czFrameworkDataBean != null && !StringUtils.isNullOrEmpty(czFrameworkDataBean.getF07())) {
				czje = new BigDecimal(czFrameworkDataBean.getF07());
			}

			czze = czze.add(czje);
			c2.setFb2(dataFormat.format(czze));
			// 通道内提现总额
			// 通道内当前余额
			BigDecimal tdye = BigDecimal.ZERO;
			if (grzftdMyFrameworkDataBean != null && !StringUtils.isNullOrEmpty(grzftdMyFrameworkDataBean.getFb4()))
				tdye = new BigDecimal(grzftdMyFrameworkDataBean.getFb4());

			// 修正充值后余额
			tdye = tdye.add(sdje);
			c2.setFb4(dataFormat.format(tdye));
			if(c2.getFb4().indexOf("-")>=0)
			{
				pageMAV.addObject("message", "充值失败，通道异常！");
				throw new Exception("充值后回调更新失败【个人信息余额异常！】");
			}
			// 更新日期
			c2.setUu1(grzftdMyFrameworkDataBean.getUu1());
			result = WMBM01Service_.doUpdate(c2);
			if (result == 1) {
			} else {
				pageMAV.addObject("message", "充值失败");
				throw new Exception("充值后回调更新失败【个人通道】");
			}
		}
		getLogger().debug("个人通道更新成功");
		// 修正个人总金额
		{
			c2 = new CSPVOSupport();
			c2.setPuk(czFrameworkDataBean.getK01());
			FrameworkDataBean grFrameworkDataBean = WMUI01Service_.doRead(c2);
			// 当前用户总额
			BigDecimal yhye = BigDecimal.ZERO;
			if (grFrameworkDataBean != null && !StringUtils.isNullOrEmpty(grFrameworkDataBean.getF01()))
				yhye = new BigDecimal(grFrameworkDataBean.getF01());
			yhye = yhye.add(sdje);
			// 账户余额（总数）
			c2.setF01(dataFormat.format(yhye));
			if(c2.getF01().indexOf("-")>=0)
			{
				pageMAV.addObject("message", "充值失败");
				throw new Exception("充值后回调更新失败【账户余额异常！】");
			}
			// 更新日时
			c2.setUu1(grFrameworkDataBean.getUu1());
			result = WMUI01Service_.doUpdate(c2);
			if (result == 1) {
				// 保存佣金记录
			} else {
				pageMAV.addObject("message", "充值失败");
				throw new Exception("充值后回调更新失败【用户信息】");
			}
		}
		getLogger().debug("用户信息更新成功");
		// 修正充值记录
		{
			c2 = new CSPVOSupport();
			c2.setPuk(paramBean.getPuk());
			c2.setF06("2");
			c2.setF08(paramBean.getF08());// 第三方支付订单号
			c2.setF17(paramBean.getF17());// 回调说明
			c2.setUu1(czFrameworkDataBean.getUu1());
			result = WMBM02Service_.doUpdate(c2);
			if (result == 1) {
			} else {
				pageMAV.addObject("message", "充值失败");
				throw new Exception("充值后回调更新失败【充值记录】");
			}
		}
		getLogger().debug("充值记录新成功");
		// 佣金处理
		if (!StringUtils.isNullOrEmpty(czFrameworkDataBean.getEb3())) {
			// 佣金记录
			c2 = new CSPVOSupport();
			c2.setPuk(czFrameworkDataBean.getEb3());
			c2.setDdd("1");
			FrameworkDataBean grczYJFrameworkDataBean = WMBM04Service_.doRead(c2);
			c2.setUu1(grczYJFrameworkDataBean.getUu1());
			c2.setFb1("1");
			c2.setDdd("0");
			result = WMBM04Service_.doUpdate(c2);
			if (result == 1) {
			} else {
				pageMAV.addObject("message", "充值失败");
				throw new Exception("充值后回调更新失败【佣金记录】");
			}
			getLogger().debug("佣金记录更新成功");
			czyj = new BigDecimal(grczYJFrameworkDataBean.getF08());
			// 上级用户信息
			String parentID = czFrameworkDataBean.getEb4();
			c2 = new CSPVOSupport();
			c2.setPuk(parentID);
			FrameworkDataBean grxxParentFrameworkDataBean = WMUI01Service_.doRead(c2);
			// 上级用户通道
			c2 = new CSPVOSupport();
			c2.setK01(parentID);
			c2.setK03(xttdid);
			c2.setEb5(czFrameworkDataBean.getEb5());
			c2.setTablename("wmbm01");
			List<FrameworkDataBean> grzftdFrameworkDataBeans = getDictionaryOnTable(c2);
			FrameworkDataBean grzftdParentFrameworkDataBean;
			if (grzftdFrameworkDataBeans.size() > 0)
				grzftdParentFrameworkDataBean = grzftdFrameworkDataBeans.get(0);
			else
				grzftdParentFrameworkDataBean = new FrameworkDataBean();

			// 通道内佣金总额
			BigDecimal tdyjze = BigDecimal.ZERO;
			if (grzftdParentFrameworkDataBean != null && !StringUtils.isNullOrEmpty(grzftdParentFrameworkDataBean.getFb5()))
				tdyjze = new BigDecimal(grzftdParentFrameworkDataBean.getFb5());
			tdyjze = tdyjze.add(czyj);
			// 通道内佣金余额
			BigDecimal tdyjye = BigDecimal.ZERO;
			if (grzftdParentFrameworkDataBean != null && !StringUtils.isNullOrEmpty(grzftdParentFrameworkDataBean.getEb1()))
				tdyjye = new BigDecimal(grzftdParentFrameworkDataBean.getEb1());
			tdyjye = tdyjye.add(czyj);

			// 个人信息内佣金总额
			BigDecimal gryjze = BigDecimal.ZERO;
			if (grxxParentFrameworkDataBean != null && !StringUtils.isNullOrEmpty(grxxParentFrameworkDataBean.getF02()))
				gryjze = new BigDecimal(grxxParentFrameworkDataBean.getF02());
			gryjze = gryjze.add(czyj);

			c2 = new CSPVOSupport();
			c2.setPuk(grzftdParentFrameworkDataBean.getPuk());
			// 通道内佣金总额
			c2.setFb5(dataFormat.format(tdyjze));
			// 通道内佣金余额
			c2.setEb1(dataFormat.format(tdyjye));
			if(c2.getEb1().indexOf("-")>=0)
			{
				pageMAV.addObject("message", "充值失败，通道异常！");
				throw new Exception("充值后回调更新失败【通道内佣金余额异常！】");
			}
			c2.setUu1(grzftdParentFrameworkDataBean.getUu1());
			result = WMBM01Service_.doUpdate(c2);
			if (result == 1) {
			} else {
				pageMAV.addObject("message", "充值失败");
				throw new Exception("充值后回调更新失败【上线通道】");
			}
			getLogger().debug("上线通道更新成功");
			c2 = new CSPVOSupport();
			c2.setPuk(grxxParentFrameworkDataBean.getPuk());
			// 未结算佣金总额
			c2.setF02(dataFormat.format(gryjze));
			if(c2.getF02().indexOf("-")>=0)
			{
				pageMAV.addObject("message", "充值失败，通道异常！");
				throw new Exception("充值后回调更新失败【个人信息余额异常！】");
			}
			c2.setUu1(grxxParentFrameworkDataBean.getUu1());
			result = WMUI01Service_.doUpdate(c2);
			if (result == 1) {
			} else {
				pageMAV.addObject("message", "充值失败");
				throw new Exception("充值后回调更新失败【上线信息】");
			}
			getLogger().debug("上线信息更新成功");
		}
		getLogger().debug("充值成功");
		pageMAV.addObject("message", "充值成功");
		// 充值记录保存
		pageMAV.addObject("flag", "1");
		// 充值记录保存
		pageMAV.addObject("paramBean", czFrameworkDataBean);
		getLogger().debug("回调操作结束<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}

	// 根据时间动态获得费率
	public void changeFY(FrameworkDataBean grzftdFrameworkDataBean) {

		// 判断是不是假日
		FrameworkDataBean fd = new FrameworkDataBean();
		fd.setEb5(super.getProductId());
		fd.setK02("DATE_FORMAT(NOW(), '%Y/%m/%d')");
		fd.setTablename("wmbma2");
		List<FrameworkDataBean> days = getDictionaryOnTable(fd);
		if (days != null && days.size() > 1) {
			// 强制使用节假日
			grzftdFrameworkDataBean.setF16(grzftdFrameworkDataBean.getEb4());
			return;
		}
		// grzftdFrameworkDataBean.getF16()
		int week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		// 判断是不是工作时间
		if (week > 1 && week < 7) {
			int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			{
				if (hour > 8 && hour < 18)
					return;
			}
		}
		// 强制使用休息日
		grzftdFrameworkDataBean.setF16(grzftdFrameworkDataBean.getEb3());

	}

	@Resource
	protected WMBM03Service WMBM03Service_;

	/**
	 * 提现
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void doTXInsert(CSPVOSupport paramBean, CSModelAndViewSupport pageMAV) throws Exception {
		getLogger().debug("提现操作开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		getLogger().debug("paramBean" + paramBean);
		// 佣金
		BigDecimal txyj = BigDecimal.ZERO;
		// 支付通道PUK
		CSPVOSupport c2 = new CSPVOSupport();
		c2.setPuk(paramBean.getF01());
		FrameworkDataBean grzftdFrameworkDataBean = WMBM01Service_.doRead(c2);

		// 根据时间动态获得费率
		changeFY(grzftdFrameworkDataBean);

		if (grzftdFrameworkDataBean == null) {
			pageMAV.addObject("message", "提现失败【个人通道已经关闭】");
			throw new Exception("提现失败【个人通道已经关闭】");
		}

		c2 = new CSPVOSupport();
		c2.setPuk(grzftdFrameworkDataBean.getK03());
		FrameworkDataBean xttdFrameworkDataBean = WMBMA1Service_.doRead(c2);
		if (xttdFrameworkDataBean == null) {
			pageMAV.addObject("message", "提现失败【系统通道已经关闭】");
			throw new Exception("提现失败【系统通道已经关闭】");
		}
		// 银行卡识别ID
		c2 = new CSPVOSupport();
		c2.setPuk(paramBean.getF03());
		FrameworkDataBean yhkFrameworkDataBean = WMBS01Service_.doRead(c2);
		if (yhkFrameworkDataBean == null || StringUtils.isNullOrEmpty(yhkFrameworkDataBean.getK02())) {
			pageMAV.addObject("message", "提现失败【银行卡信息不全】");
			throw new Exception("提现失败【银行卡信息不全】");
		}

		// 个人信息
		c2 = new CSPVOSupport();
		c2.setPuk(super.getLoginerId());
		FrameworkDataBean grxxFrameworkDataBean = WMUI01Service_.doRead(c2);

		// 查询出手续费
		BigDecimal txsxf = BigDecimal.ZERO;
		if (grzftdFrameworkDataBean != null && !StringUtils.isNullOrEmpty(grzftdFrameworkDataBean.getF16()))
			txsxf = new BigDecimal(grzftdFrameworkDataBean.getF16());
		// ////////////////////////////////////////////////

		// 提现最大金额
		BigDecimal txzdje = BigDecimal.ZERO;
		if (grzftdFrameworkDataBean != null && !StringUtils.isNullOrEmpty(grzftdFrameworkDataBean.getF17()))
			txzdje = new BigDecimal(grzftdFrameworkDataBean.getF17());

		// 画面输入充值金额
		BigDecimal txje = BigDecimal.ZERO;
		if (!StringUtils.isNullOrEmpty(grzftdFrameworkDataBean.getFb4()))
			txje = new BigDecimal(grzftdFrameworkDataBean.getFb4());// paramBean.getF07());
		if (txje.longValue() == 0) {
			pageMAV.addObject("message", "提现失败【该通道账户余额为零】");
			throw new Exception("提现失败【该通道账户余额为零】");
		}
		// 计算实际手续费
		BigDecimal txsjsxf = BigDecimal.ZERO;
		long inttxje = txje.longValue();
		long inttxzdje = txzdje.longValue();
		if (inttxzdje == 0) {
			pageMAV.addObject("message", "提现失败【该通道最大提现金额为零】");
			throw new Exception("提现失败【该通道最大提现金额为零】");
		}

		txsjsxf = txsxf.multiply(new BigDecimal(inttxje / inttxzdje));
		if (inttxje % inttxzdje != 0) {
			txsjsxf = txsjsxf.add(txsxf);
		}
		txsxf = txsjsxf;

		// 计算出实得金额
		BigDecimal sdje = txje.subtract(txsxf);
		if (sdje.longValue() <= 0) {
			pageMAV.addObject("message", "提现失败【扣除手续费后，余额为零】");
			throw new Exception("提现失败【扣除手续费后，余额为零】");
		}
		// 存入DB
		CSPVOSupport jydbParamBean = new CSPVOSupport();

		// 操作流水号
		jydbParamBean.setPuk(paramBean.getPuk());
		// 用户ID
		jydbParamBean.setK01(super.getLoginerId());
		// 通道ID
		jydbParamBean.setK02(paramBean.getF01());
		// 卡ID
		jydbParamBean.setK03(paramBean.getF03());
		// 银行账户
		jydbParamBean.setF01(yhkFrameworkDataBean.getK02());
		// 授权编号
		jydbParamBean.setF02(yhkFrameworkDataBean.getFb1());
		// 开户名
		jydbParamBean.setF03(yhkFrameworkDataBean.getF01());
		// 银行名称
		jydbParamBean.setF04(yhkFrameworkDataBean.getF02());
		// 卡片种类
		jydbParamBean.setF05(yhkFrameworkDataBean.getF08());
		// 充值操作状态
		jydbParamBean.setF06("0");// 0申请中
		// 充值金额
		jydbParamBean.setF07(dataFormat.format(txje));
		// 充值流水号（银行返回）
		// 手机号码
		jydbParamBean.setF09(grxxFrameworkDataBean.getF05());
		// 验证码
		jydbParamBean.setF10(paramBean.getF10());
		// 交易场所（证书所在场所）
		// 交易证书ID
		// 操作IP
		// 交易费率
		// 结算费
		jydbParamBean.setF15(grzftdFrameworkDataBean.getF16());
		// 结算上限
		jydbParamBean.setF16(grzftdFrameworkDataBean.getF17());

		// 交易说明
		jydbParamBean.setBbb(paramBean.getBbb());
		// 实得金额
		jydbParamBean.setF18(dataFormat.format(sdje));
		// 通道名称
		jydbParamBean.setFb1(grzftdFrameworkDataBean.getFb1());
		// 通道内当前余额
		jydbParamBean.setFb2(grzftdFrameworkDataBean.getFb4());

		// 上级用户ID
		String parentID = grxxFrameworkDataBean.getK01();

		// 审核状态
		jydbParamBean.setFb5("1");
		// 交易手续费
		jydbParamBean.setEb2(dataFormat.format(txsxf));
		// 上级用户ID
		jydbParamBean.setEb4(parentID);
		jydbParamBean.setUu1(" ");

		// 佣金计算
		if (!StringUtils.isNullOrEmpty(parentID)) {
			// 上级用户信息
			c2 = new CSPVOSupport();
			c2.setPuk(parentID);
			grxxFrameworkDataBean = WMUI01Service_.doRead(c2);
			// 判断提成种类
			String tczl = "0";
			if (grxxFrameworkDataBean != null && !StringUtils.isNullOrEmpty(grxxFrameworkDataBean.getK03())) {
				tczl = grxxFrameworkDataBean.getK03();
				getLogger().debug("上线提成种类为==" + grxxFrameworkDataBean.getF04() + "===>>>>>" + tczl);
			}
			// 上级用户通道
			c2 = new CSPVOSupport();
			c2.setK01(parentID);
			c2.setK03(grzftdFrameworkDataBean.getK03());
			c2.setEb5(super.getProductId());
			c2.setTablename("wmbm01");
			List<FrameworkDataBean> grzftdParentsFrameworkDataBeans = getDictionaryOnTable(c2);
			FrameworkDataBean grzftdParentsFrameworkDataBean;

			if (grzftdParentsFrameworkDataBeans.size() > 0)
				grzftdParentsFrameworkDataBean = grzftdParentsFrameworkDataBeans.get(0);
			else
				grzftdParentsFrameworkDataBean = new FrameworkDataBean();

			// 处理佣金
			CSPVOSupport yjdbParamBean = new CSPVOSupport();

			// 订单号
			yjdbParamBean.setPuk(PKUtil.getPUKey());
			// 交易种类
			yjdbParamBean.setK01("1");
			// 交易ID
			yjdbParamBean.setK02(jydbParamBean.getPuk());
			// 用户ID
			yjdbParamBean.setK03(parentID);
			// 交易操作金额
			yjdbParamBean.setF01(dataFormat.format(txje));
			// 结算操作状态
			yjdbParamBean.setF02("0");
			// 佣金提现记录ID
			// 实得金额
			yjdbParamBean.setF04(jydbParamBean.getF18());
			// 交易费率
			yjdbParamBean.setF05(dataFormat.format(txsxf));
			// 结算费
			// 结算上限
			// 产生佣金金额
			{
				// 查询出费率
				// 画面输入充值金额
				// 根据费率计算充值费用
				if (grzftdParentsFrameworkDataBean != null && !StringUtils.isNullOrEmpty(grzftdParentsFrameworkDataBean.getF16()))
					txyj = txsxf.subtract(new BigDecimal(grzftdParentsFrameworkDataBean.getF16()));
				yjdbParamBean.setF08(dataFormat.format(txyj));
			}
			// 上级交易费率
			// 上级结算费
			yjdbParamBean.setF10(grzftdParentsFrameworkDataBean.getF16());
			// 上级结算上限
			// 产生系统成本金额
			BigDecimal xtcb = BigDecimal.ZERO;
			{
				// 查询出费率
				// 画面输入充值金额
				// 根据费率计算充值费用
				if (xttdFrameworkDataBean != null && !StringUtils.isNullOrEmpty(xttdFrameworkDataBean.getF16())) {
					xtcb = new BigDecimal(xttdFrameworkDataBean.getF16());
					yjdbParamBean.setF12(dataFormat.format(xtcb));
				} else
					yjdbParamBean.setF12("0");
			}

			// 计算交易利润(手续费-佣金-成本)
			BigDecimal jylr = BigDecimal.ZERO;
			{
				jylr = txsxf.subtract(txyj).subtract(xtcb);
			}
			yjdbParamBean.setFb3(dataFormat.format(jylr));
			jydbParamBean.setFb3(dataFormat.format(jylr));

			// 系统交易费率
			yjdbParamBean.setF13(xttdFrameworkDataBean.getF15());
			// 系统结算费
			// 系统结算上限
			// 备注说明
			yjdbParamBean.setBbb(jydbParamBean.getBbb());
			// 审核状态
			yjdbParamBean.setFb1("1");
			// 个人通道ID
			yjdbParamBean.setEb2(grzftdParentsFrameworkDataBean.getPuk());
			// 系统通道ID
			yjdbParamBean.setEb3(grzftdParentsFrameworkDataBean.getK03());
			// 上线用户ID
			yjdbParamBean.setEb4(super.getLoginerId());

			if (txyj.doubleValue() > 0 && ("2".equals(tczl) || "3".equals(tczl))) {
				// 提成记录ID
				jydbParamBean.setEb3(yjdbParamBean.getPuk());

				// 保存佣金记录
				WMBM04Service_.doInsert(yjdbParamBean);
				getLogger().debug("佣金记录保存成功");
				// 通道内佣金总额
				BigDecimal tdyjze = BigDecimal.ZERO;
				if (!StringUtils.isNullOrEmpty(grzftdParentsFrameworkDataBean.getFb5()))
					tdyjze = new BigDecimal(grzftdParentsFrameworkDataBean.getFb5());
				tdyjze = tdyjze.add(txyj);
				// 通道内佣金余额
				BigDecimal tdyjye = BigDecimal.ZERO;
				if (!StringUtils.isNullOrEmpty(grzftdParentsFrameworkDataBean.getEb1()))
					tdyjye = new BigDecimal(grzftdParentsFrameworkDataBean.getEb1());
				tdyjye = tdyjye.add(txyj);

				// 个人信息内佣金总额
				BigDecimal gryjze = BigDecimal.ZERO;
				if (!StringUtils.isNullOrEmpty(grxxFrameworkDataBean.getF02()))
					gryjze = new BigDecimal(grxxFrameworkDataBean.getF02());
				gryjze = gryjze.add(txyj);

				c2 = new CSPVOSupport();
				c2.setPuk(grzftdParentsFrameworkDataBean.getPuk());
				// 通道内佣金总额
				c2.setFb5(dataFormat.format(tdyjze));
				// 通道内佣金余额
				c2.setEb1(dataFormat.format(tdyjye));
				if(c2.getEb1().indexOf("-")>=0)
				{
					pageMAV.addObject("message", "充值失败，通道异常！");
					throw new Exception("提现更新失败【通道内佣金余额异常！】");
				}
				c2.setUu1(grzftdParentsFrameworkDataBean.getUu1());
				int result = WMBM01Service_.doUpdate(c2);
				if (result == 1) {
				} else {
					pageMAV.addObject("message", "提现失败");
					throw new Exception("提现更新失败【上线通道】");
				}
				getLogger().debug("上线通道更新成功");
				c2 = new CSPVOSupport();
				c2.setPuk(grxxFrameworkDataBean.getPuk());
				// 未结算佣金总额
				c2.setF02(dataFormat.format(gryjze));
				if(c2.getF02().indexOf("-")>=0)
				{
					pageMAV.addObject("message", "提现失败，通道异常！");
					throw new Exception("提现更新失败【上线信息余额异常！】");
				}
				c2.setUu1(grxxFrameworkDataBean.getUu1());
				result = WMUI01Service_.doUpdate(c2);
				if (result == 1) {
				} else {
					pageMAV.addObject("message", "提现失败");
					throw new Exception("提现更新失败【上线信息】");
				}
				getLogger().debug("上线信息更新成功");
			}
		}
		// 插入提现记录
		WMBM03Service_.doInsert(jydbParamBean);
		getLogger().debug("提现记录更新成功");
		// 更新所有充值记录关联
		c2 = new CSPVOSupport();
		c2.setK01(super.getLoginerId());
		c2.setK02(grzftdFrameworkDataBean.getPuk());
		c2.setEb1(jydbParamBean.getPuk());// 关联提现交易记录ID
		c2.setUu2(super.getLoginerId());
		// 插入提现记录
		getDao().doUpdateCZWithTX(c2);
		getLogger().debug("充值记录关联更新成功");
		// 修正通道总金额
		{
			c2 = new CSPVOSupport();
			c2.setPuk(grzftdFrameworkDataBean.getPuk());
			// 通道内充值总额
			// 通道内提现总额
			BigDecimal txze = BigDecimal.ZERO;
			if (grzftdFrameworkDataBean != null && !StringUtils.isNullOrEmpty(grzftdFrameworkDataBean.getFb2()))
				txze = new BigDecimal(grzftdFrameworkDataBean.getFb2());
			if (jydbParamBean != null && !StringUtils.isNullOrEmpty(jydbParamBean.getF07()))
				txze = txze.add(new BigDecimal(jydbParamBean.getF07()));
			c2.setFb3(dataFormat.format(txze));
			// 通道内当前余额
			BigDecimal tdye = BigDecimal.ZERO;
			if (grzftdFrameworkDataBean != null && !StringUtils.isNullOrEmpty(grzftdFrameworkDataBean.getFb4()))
				tdye = new BigDecimal(grzftdFrameworkDataBean.getFb4());
			if (jydbParamBean != null && !StringUtils.isNullOrEmpty(jydbParamBean.getF07()))
				tdye = tdye.subtract(new BigDecimal(jydbParamBean.getF07()));
			c2.setFb4(dataFormat.format(tdye));
			if(c2.getFb4().indexOf("-")>=0)
			{
				pageMAV.addObject("message", "充值失败，通道异常！");
				throw new Exception("提现更新失败【个人通道账户余额异常！】");
			}
			c2.setUu1(grzftdFrameworkDataBean.getUu1());
			int result = WMBM01Service_.doUpdate(c2);
			if (result == 1) {
			} else {
				pageMAV.addObject("message", "提现失败");
				throw new Exception("提现更新失败【个人通道】");
			}
			getLogger().debug("个人通道更新成功");
		}

		// 修正个人总金额
		c2 = new CSPVOSupport();
		c2.setPuk(super.getLoginerId());
		FrameworkDataBean grFrameworkDataBean = WMUI01Service_.doRead(c2);
		// 当前用户总额
		BigDecimal yhye = BigDecimal.ZERO;
		if (grFrameworkDataBean != null && !StringUtils.isNullOrEmpty(grFrameworkDataBean.getF01()))
			yhye = new BigDecimal(grFrameworkDataBean.getF01());
		if (jydbParamBean != null && !StringUtils.isNullOrEmpty(jydbParamBean.getF18()))
			yhye = yhye.subtract(txje);
		c2.setF01(dataFormat.format(yhye));
		if(c2.getF01().indexOf("-")>=0)
		{
			pageMAV.addObject("message", "充值失败，通道异常！");
			throw new Exception("提现更新失败【个人账户余额异常！】");
		}
		c2.setUu1(grFrameworkDataBean.getUu1());
		int result = WMUI01Service_.doUpdate(c2);
		if (result == 1) {
			pageMAV.addObject("message", "提现成功");
			// 保存佣金记录

		} else {
			pageMAV.addObject("message", "提现失败");
		}
		// 调用第三方页面
		// 回调页面
		pageMAV.setViewName("WM/QT/WMZHT3");

		pageMAV.addObject("flag", "1");

		// 提现记录
		pageMAV.addObject("paramBean", jydbParamBean);
		getLogger().debug("提现操作结束<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}

	/**
	 * 佣金提现
	 * 
	 * @param paramBean
	 * @throws Exception
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void doYJJSInsert(CSPVOSupport paramBean, PageVO pageVO, CSModelAndViewSupport pageMAV) throws Exception {
		getLogger().debug("佣金提现操作开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		getLogger().debug("paramBean" + paramBean);
		// 充值佣金
		BigDecimal jsyj = BigDecimal.ZERO;
		// 支付通道PUK
		CSPVOSupport c2 = new CSPVOSupport();
		c2.setPuk(paramBean.getEb2());
		// 根据时间动态获得费率
		FrameworkDataBean grzftdFrameworkDataBean = WMBM01Service_.doRead(c2);
		if (grzftdFrameworkDataBean == null)
			grzftdFrameworkDataBean = new FrameworkDataBean();
		c2 = new CSPVOSupport();
		c2.setPuk(grzftdFrameworkDataBean.getK03());
		FrameworkDataBean xttdFrameworkDataBean = WMBMA1Service_.doRead(c2);
		if (xttdFrameworkDataBean == null)
			xttdFrameworkDataBean = new FrameworkDataBean();

		if (!StringUtils.isNullOrEmpty(grzftdFrameworkDataBean.getEb1()))
			jsyj = new BigDecimal(grzftdFrameworkDataBean.getEb1());
		// 银行卡识别ID
		c2 = new CSPVOSupport();
		c2.setK01(super.getLoginerId());
		c2.setK03(paramBean.getK01());
		FrameworkDataBean yhkFrameworkDataBean = new FrameworkDataBean();
		pageVO.setPageData(c2);
		List<FrameworkDataBean> yhkFrameworkDataBeans = (List<FrameworkDataBean>) WMBS01Service_.doSelectPage(pageVO).getPageData();
		if (yhkFrameworkDataBeans.size() > 0)
			yhkFrameworkDataBean = yhkFrameworkDataBeans.get(0);
		else {
			pageMAV.addObject("message", "佣金提现失败，该通道下面没有绑定银行卡");
			throw new Exception("佣金提现失败，该通道下面没有绑定银行卡");
		}

		// 个人信息
		c2 = new CSPVOSupport();
		c2.setPuk(super.getLoginerId());
		FrameworkDataBean grxxFrameworkDataBean = WMUI01Service_.doRead(c2);
		// 创建佣金提现记录
		CSPVOSupport jydbParamBean = new CSPVOSupport();

		// 操作流水号
		jydbParamBean.setPuk(paramBean.getPuk());
		// 用户ID
		jydbParamBean.setK01(super.getLoginerId());
		// 通道ID
		jydbParamBean.setK02(grzftdFrameworkDataBean.getPuk());
		// 卡ID
		jydbParamBean.setK03(yhkFrameworkDataBean.getPuk());
		// 银行账户
		jydbParamBean.setF01(yhkFrameworkDataBean.getK02());
		// 授权编号
		jydbParamBean.setF02(yhkFrameworkDataBean.getFb1());
		// 开户名
		jydbParamBean.setF03(yhkFrameworkDataBean.getF01());
		// 银行名称
		jydbParamBean.setF04(yhkFrameworkDataBean.getF02());
		// 卡片种类
		jydbParamBean.setF05(yhkFrameworkDataBean.getF08());
		// 充值操作状态
		jydbParamBean.setF06("0");// 0申请中
		// 充值金额
		jydbParamBean.setF07(grzftdFrameworkDataBean.getEb1());
		// 充值流水号（银行返回）
		// 手机号码
		jydbParamBean.setF09(grxxFrameworkDataBean.getF05());
		// 验证码
		jydbParamBean.setF10(paramBean.getF10());
		// 交易场所（证书所在场所）
		// 交易证书ID
		// 操作IP
		// 交易费率
		// 结算费
		jydbParamBean.setF15(grzftdFrameworkDataBean.getF16());
		// 结算上限
		jydbParamBean.setF16(grzftdFrameworkDataBean.getF17());

		// 交易说明
		jydbParamBean.setBbb(paramBean.getBbb());

		// 查询出手续费
		BigDecimal txsxf = BigDecimal.ZERO;
		if (grzftdFrameworkDataBean != null && !StringUtils.isNullOrEmpty(grzftdFrameworkDataBean.getF16()))
			txsxf = new BigDecimal(grzftdFrameworkDataBean.getF16());
		// ////////////////////////////////////////////////

		// 提现最大金额
		BigDecimal txzdje = BigDecimal.ZERO;
		if (grzftdFrameworkDataBean != null && !StringUtils.isNullOrEmpty(grzftdFrameworkDataBean.getF17()))
			txzdje = new BigDecimal(grzftdFrameworkDataBean.getF17());
		// 计算实际手续费
		BigDecimal txsjsxf = BigDecimal.ZERO;
		long inttxje = jsyj.longValue();
		long inttxzdje = txzdje.longValue();
		txsjsxf = txsxf.multiply(new BigDecimal(inttxje / inttxzdje));
		if (inttxje % inttxzdje != 0) {
			txsjsxf = txsjsxf.add(txsxf);
		}
		txsxf = txsjsxf;

		// 计算出实得金额
		BigDecimal sdje = jsyj.subtract(txsxf);
		if (sdje.longValue() <= 0) {
			pageMAV.addObject("message", "佣金提现失败【扣除手续费后，余额为零】");
			throw new Exception("佣金提现失败【扣除手续费后，余额为零】");
		}

		// 计算交易利润(手续费-成本)
		BigDecimal jylr = BigDecimal.ZERO;
		{
			jylr = txsxf.subtract(new BigDecimal(xttdFrameworkDataBean.getF16()));
		}
		jydbParamBean.setFb3(dataFormat.format(jylr));

		// 实得金额
		jydbParamBean.setF18(dataFormat.format(sdje));
		pageMAV.addObject("f18", dataFormat.format(sdje));
		// 通道名称
		jydbParamBean.setFb1(grzftdFrameworkDataBean.getFb1());
		// 通道内当前余额
		jydbParamBean.setFb2(grzftdFrameworkDataBean.getEb1());

		// 审核状态
		jydbParamBean.setFb5("1");
		// 交易手续费
		jydbParamBean.setEb2(dataFormat.format(txsxf));

		// 插入佣金提现记录
		WMBM05Service_.doInsert(jydbParamBean);
		getLogger().debug("佣金提现记录保存成功");
		// 更新所有充值记录关联
		c2 = new CSPVOSupport();
		c2.setK03(super.getLoginerId());
		c2.setF03(jydbParamBean.getPuk());// 关联佣金交易记录ID
		c2.setEb2(jydbParamBean.getK02());// 个人通道ID
		c2.setUu2(super.getLoginerId());
		// 更新佣金记录
		getDao().doUpdateYJWithJS(c2);

		// 修正通道总金额
		{
			c2 = new CSPVOSupport();
			c2.setPuk(grzftdFrameworkDataBean.getPuk());
			c2.setEb1("0");
			c2.setUu1(grzftdFrameworkDataBean.getUu1());
			int result = WMBM01Service_.doUpdate(c2);
			if (result == 1) {
			} else {
				pageMAV.addObject("message", "佣金提现失败");
				throw new Exception("佣金提现更新失败【个人通道】");
			}
			getLogger().debug("个人通道更新成功");
		}

		// 修正个人总金额
		{
			c2 = new CSPVOSupport();
			c2.setPuk(super.getLoginerId());
			// 当前用户总额
			BigDecimal yhye = BigDecimal.ZERO;
			if (grxxFrameworkDataBean != null && !StringUtils.isNullOrEmpty(grxxFrameworkDataBean.getF02()))
				yhye = new BigDecimal(grxxFrameworkDataBean.getF02());
			yhye = yhye.subtract(jsyj);
			c2.setF02(dataFormat.format(yhye));
			if(c2.getF02().indexOf("-")>=0)
			{
				pageMAV.addObject("message", "充值失败，通道异常！");
				throw new Exception("佣金提现失败【个人信息余额异常！】");
			}
			c2.setUu1(grxxFrameworkDataBean.getUu1());
			int result = WMUI01Service_.doUpdate(c2);
			if (result == 1) {
				pageMAV.addObject("message", "佣金提现成功");
			} else {
				pageMAV.addObject("message", "佣金提现失败");
				throw new Exception("佣金提现更新失败【个人信息】");
			}
			getLogger().debug("个人信息更新成功");
		}
		// 充值记录
		pageMAV.addObject("paramBean", jydbParamBean);

		pageMAV.setViewName("WM/QT/WMZHT3");

		pageMAV.addObject("flag", "1");
		getLogger().debug("佣金提现操作结束<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
	}

}
