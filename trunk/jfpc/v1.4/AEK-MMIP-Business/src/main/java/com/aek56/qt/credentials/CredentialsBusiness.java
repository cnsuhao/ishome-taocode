package com.aek56.qt.credentials;

import java.util.List;

import javax.annotation.Resource;

import org.jfpc.beans.common.MS3C2.MS3C2DBO;
import org.jfpc.common.message.MessageBean;
import org.jfpc.common.message.MessageModelUtils;
import org.jfpc.common.message.MessageService;
import org.jfpc.common.sms.SMSService;
import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.helper.DateHelper;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.page.PageVOSupport;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aek56.atm.company.MGYS0_JBXX.MGYS0_JBXXDBO;
import com.aek56.atm.company.MGYS0_JBXX.MGYS0_JBXXService;
import com.aek56.atm.company.MYY0_JBXX.MYY0_JBXXDBO;
import com.aek56.atm.company.MYY0_JBXX.MYY0_JBXXService;
import com.aek56.atm.company.MYY1_YYTJGYSXX.MYY1_YYTJGYSXXDBO;
import com.aek56.atm.credentials.MGA1_YYZZ.MGA1_YYZZDBO;
import com.aek56.constants.ISAek56SMSConstants;
import com.aek56.constants.ISSystemConstants;
import com.aek56.qt.credentials.dao.CredentialsDao;

/**
 * 证件管理
 * @author Administrator
 *
 */
@Service
public class CredentialsBusiness extends MyServiceSupport implements ISAek56SMSConstants {
	public CredentialsDao getDao() {
		return getMySqlSession().getMapper(CredentialsDao.class);
	}	
	
	/**
	 * 医院忽略证件提醒
	 * @param puk
	 * @param type
	 * @return
	 */
	public int doUpdateCredentials(String puk, char type) {
		String tablename ="";
		String ppp ="f06_shzt";
		String ggg ="f07";
		switch (type) {
		// 供应商营业执照
		case '1':
			tablename = "mgt1_yyzz";
			break;
		// 供应商经营许可证
		case '2':
			tablename = "mgt2_jyxkz";
			break;
		// 供应商工商税务登记证
		case '3':
			tablename = "mgt3_gsswdjz";
			break;
		// 供应商经销授权书
		case '4':
			tablename = "mgt4_jxsqs";
			ggg = "f10";
			break;
		//供应商销售人员委托书
		case '5':
			tablename = "mgt5_xsrywts";
			break;
		// 供应商售后服务承诺书
		case '6':
			tablename = "mgt6_shfwcns";
			break;
		// 供应商进口商检报告
		case '7':
			tablename = "mgt7_jksjbg";
			break;
		// 厂家营业执照
		case '8':
			tablename = "mgt8_cjyyzz";
			break;
		// 厂家生产许可证
		case '9':
			tablename = "mgt9_cjscxkz";
			break;
		//厂家医疗器械注册证
		case 'a':
			tablename = "mgta_cjylqxzcz";
			ppp = "f11_shzt";
			ggg = "f43";
			break;
		//厂家耗材商品3C认证
		case 'b':
			tablename = "mgtb_cjhcsprz";
			break;
		//厂家消毒产品卫生许可证
		case 'c':
			tablename = "mgtc_cjxdcpwsxkz";
			break;
		default:
			return 0;
		}
		MyDataBaseObjectSupport formParamBean = new MyDataBaseObjectSupport();
		formParamBean.setPuk(puk);
		formParamBean.setPpp(ppp);
		formParamBean.setTablename(tablename);
		formParamBean.setGgg(ggg);

		return getDao().doUpdateCredentials(formParamBean);
		
	}

	/**
	 * 我的客户总数（医院）
	 * @param puk
	 * @param type
	 * @return fb1：医院
	 */
	public FrameworkDataBean countNumWithHospital(String puk) {
		return getDao().countNumWithHospital(puk);
	}
	
	
	/**
	 * 我的客户总数（供应商）
	 * @param puk
	 * @param type
	 * @return fb1：供应商
	 * 			fb2:厂商
	 */
	public FrameworkDataBean countNumWithSupplier(String puk) {
		return getDao().countNumWithSupplier(puk);
	}
	
	/**
	 * 证件统计（医院）
	 * @param puk
	 * @param type
	 * @return fb1：医院
	 */
	public FrameworkDataBean countCredentialsNumWithHospital(String puk) {
		return getDao().countCredentialsNumWithHospital(puk);
	}	
	
	/**
	 * 证件统计（供应商）
	 * @param puk
	 * @param type
	 * @return fb1：供应商
	 * 			fb2:厂商
	 */
	public FrameworkDataBean countCredentialsNumWithSupplier(String puk) {
		return getDao().countCredentialsNumWithSupplier(puk);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 定时作业手动统计（客户关系）
	 * 
	 * @param hisPuk 医院
	 * @param cmpPuk 供应商
	 * @throws Exception
	 */
	@Transactional
	public int totalCustomCredentials(String hisPuk, String cmpPuk) throws Exception {
		MYY1_YYTJGYSXXDBO formParamBean = new MYY1_YYTJGYSXXDBO();
		formParamBean.setPuk(cmpPuk);
		formParamBean.setP01_yyid(hisPuk);
		// 更新者、更新时间
		formParamBean.setUu2(super.getLoginerId());
		return getDao().totalCustomCredentials(formParamBean);

	}

	/**
	 * 供应商查看所有证件总数
	 * 
	 * @param puk
	 * @return
	 * 
	 * @see <MGYS0_JBXXDBO>
	 */
	public FrameworkDataBean countNumBySupplier(String puk) {
		return getDao().countNumBySupplier(puk);
	}

	/**
	 * 医院查看所有证件总数
	 * 
	 * @param puk
	 * @return
	 * 
	 * @see <MYY0_JBXXDBO>
	 */
	public FrameworkDataBean countNumByHospital(String puk) {
		return getDao().countNumByHospital(puk);
	}

	/**
	 * 全体查询（供应商查看证件一览）
	 * 
	 * @param formParam
	 * @return
	 */
	public PageVOSupport doSelectPageCompanyCredentialsList(PageVOSupport formParam) {
		FrameworkDataBean formParamBean = formParam.getFormParamBean();
		String userType = super.getLoginerBean().getUserType();
		// 设定企业ID
		if (!USER_OPERATION.equals(userType) && !USER_CUSTOM_SERVICE.equals(userType))
			if (EmptyHelper.isEmpty(formParamBean.getPpp()))
				formParamBean.setPpp(super.getCompanyId());
		// 比较日期
		if (EmptyHelper.isEmpty(formParamBean.getCc1()))
			formParamBean.setCc1(DateHelper.currentTimeMillisCN3());
		// 过期天数
		if (EmptyHelper.isEmpty(formParamBean.getDdd()))
			formParamBean.setDdd("90");
		// 排列顺序
		if (EmptyHelper.isEmpty(formParam.getOrderby()))
			formParam.setOrderby(" ORDER BY cc1 desc ");
		// 查询数据
		formParam.setPageListData(getDao().doSelectPageCompanyCredentialsList(formParam));
		return formParam;
	}

	/**
	 * 全体查询（供应商查看证件一览）
	 * 
	 * @param formParam
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageCompanyCredentialsList(MyDataBaseObjectSupport paramBean) {
		// 比较日期
		if (EmptyHelper.isEmpty(paramBean.getCc1()))
			paramBean.setCc1(DateHelper.currentTimeMillisCN3());
		// 过期天数
		if (EmptyHelper.isEmpty(paramBean.getDdd()))
			paramBean.setDdd("90");
		return getDao().doSelectPageCompanyCredentialsList(paramBean);
	}

	/**
	 * 全体查询（医院查看证件一览）
	 * 
	 * @param formParam
	 * @return
	 */
	public PageVOSupport doSelectPageHospitalCredentialsList(PageVOSupport formParam) {
		FrameworkDataBean formParamBean = formParam.getFormParamBean();
		String userType = super.getLoginerBean().getUserType();
		// 设定企业ID
		if (!USER_OPERATION.equals(userType) && !USER_CUSTOM_SERVICE.equals(userType))
			if (EmptyHelper.isEmpty(formParamBean.getPpp()))
				formParamBean.setPpp(super.getCompanyId());
		// 比较日期
		if (EmptyHelper.isEmpty(formParamBean.getCc1()))
			formParamBean.setCc1(DateHelper.currentTimeMillisCN3());
		// 过期天数
		if (EmptyHelper.isEmpty(formParamBean.getDdd()))
			formParamBean.setDdd("90");
		// 排列顺序
		if (EmptyHelper.isEmpty(formParam.getOrderby()))
			formParam.setOrderby(" ORDER BY cc1 desc ");
		// 查询数据
		formParam.setPageListData(getDao().doSelectPageHospitalCredentialsList(formParam));
		return formParam;
	}

	/**
	 * 全体查询（医院查看证件一览）
	 * 
	 * @param formParam
	 * @return
	 */
	public List<FrameworkDataBean> doSelectPageHospitalCredentialsList(MyDataBaseObjectSupport paramBean) {
		// 比较日期
		if (EmptyHelper.isEmpty(paramBean.getCc1()))
			paramBean.setCc1(DateHelper.currentTimeMillisCN3());
		// 过期天数
		if (EmptyHelper.isEmpty(paramBean.getDdd()))
			paramBean.setDdd("90");
		return getDao().doSelectPageHospitalCredentialsList(paramBean);
	}

	@Resource
	MGYS0_JBXXService MGYS0_JBXXService_;
	@Resource
	MYY0_JBXXService MYY0_JBXXService_;

	/**
	 * 医院手动提醒用户，证件过期 (供应商ID)(医院添加供应商PUK)
	 * 
	 * @throws Exception
	 * @see <32011005>
	 */
	@Transactional
	public void sendSmsByHispotalOnGYSCompany(String gysId) throws Exception {
		MYY0_JBXXDBO cmpYY = new MYY0_JBXXDBO();
		cmpYY.setPuk(super.getCompanyId());
		cmpYY = (MYY0_JBXXDBO) MYY0_JBXXService_.doRead(cmpYY);
		MGYS0_JBXXDBO cmpGYS = new MGYS0_JBXXDBO();
		String bizId = "32011005";
		cmpGYS.setPuk(gysId);
		cmpGYS = (MGYS0_JBXXDBO) MGYS0_JBXXService_.doRead(cmpGYS);
		// 发送消息
		MessageBean msg = new MessageBean();
		msg.setK01_fjrid(cmpYY.getPuk());// 发件人ID
		msg.setK02_sjrid(cmpGYS.getF37());// 收件人ID
		msg.setF01_fjrnc(cmpYY.getF01_qyqc());// 发件人昵称
		msg.setF02_sjrnc(cmpGYS.getF30_lxrxm());// 收件人昵称
		// msg.setF03_bt("[过期提醒]");// 标题
		msg.setF04_jjd(TWO);// 紧急度:1立刻处理2紧急3一般
		msg.setF05_zyd(TWO);// 重要度:1非常重要2重要3一般
		// 【您的注册证资料存在三个月内过期，请尽快更新】
		// String message =
		// messageModel.getLocalMessage(bizId);//"【您的证件资料存在过期行为，请尽快更新！】";
		// msg.setBbb(message);// 消息内容
		msg.setFb1(cmpYY.getF01_qyqc());// 发件人企业简称
		msg.setFb2(cmpYY.getF01_qyqc());// 发件人企业全称
		msg.setFb3(bizId);// 业务标识ID
		msg.setFb4(ONE);// 0已经阅读1未阅读
		msg.setEb5(gysId);// 收件企业ID
		msg.setGgg(cmpYY.getPuk());// 发送企业ID
		msg.setPpp(cmpYY.getPuk());

		messageModel.buildMessage(msg, MAIL+bizId, new String[] {});
		MessageService_.sendMessage(msg);

		// 发送短信
		MS3C2DBO sms = new MS3C2DBO();
		sms.setK01_ywbsid(bizId);
		sms.setK02_sjhm(cmpGYS.getF32_lxrdh());
		sms.setF02_fszt(ZERO);// 0等待发送1已发送2发送成功9发送失败
		sms.setF03_dxnr(msg.getBbb());
		sms.setFb1(cmpYY.getF01_qyqc());// 发件人企业简称
		sms.setFb2(cmpYY.getF01_qyqc());// 发件人企业全称
		sms.setFb3(bizId);// 业务标识ID
		sms.setFb4("S"+bizId);// 短信模版ID
		sms.setEb1(cmpYY.getF01_qyqc());// 发件人昵称
		sms.setEb2(cmpGYS.getF30_lxrxm());// 收件人昵称
		sms.setEb5(gysId);// 收件企业ID
		sms.setGgg(cmpYY.getPuk());// 发送企业ID
		SMSService_.sendMessage(sms);
	}

	// /**
	// * 医院手动提醒用户，证件过期(多选证件) (供应商ID)(医院添加供应商BBB)
	// *
	// * @throws Exception
	// * @see <32011015/1>
	// */
	// @Transactional
	// public void sendSmsByHispotalOnFull(PageVOSupport formParam) throws
	// Exception {
	// FrameworkDataBean formParamBean = formParam.getFormParamBean();
	// // 获得证件信息
	// // fpb.setPuk();//证件ID
	// // fpb.getK01_gysid();//企业ID
	//
	// // 创作日期
	// if (EmptyHelper.isEmpty(formParamBean.getCc1()))
	// formParamBean.setCc1(DateHelper.currentTimeMillisCN3());
	// // 过期天数
	// if (EmptyHelper.isEmpty(formParamBean.getDdd()))
	// formParamBean.setDdd("90");
	//
	// formParam.setFormParamBean(formParamBean);
	// formParam.setPageCurrent(1);
	// formParam.setPageLimit(100);
	// // 查询所有要过期和已经过期的证件
	// List<FrameworkDataBean> list =
	// getDao().doSelectPageCompanyCredentialsList(formParam);
	// // 发送消息
	// for (FrameworkDataBean item : list) {
	// sendMessage("32011015", (MGA1_YYZZDBO) item);
	// }
	// }

	/**
	 * 医院手动提醒用户，证件过期(单一证件)
	 * 
	 * @throws Exception
	 * @see <32011015><MGA1_YYZZDBO>
	 */
	@Transactional
	public void sendSmsByHispotalOnDetail(PageVOSupport formParam) throws Exception {
		FrameworkDataBean formParamBean = formParam.getFormParamBean();
		// 获得证件信息
		// fpb.setPuk();//证件ID
		// fpb.getK01_gysid();//企业ID

		// 创作日期
		if (EmptyHelper.isEmpty(formParamBean.getCc1()))
			formParamBean.setCc1(DateHelper.currentTimeMillisCN3());
		// 过期天数
		if (EmptyHelper.isEmpty(formParamBean.getDdd()))
			formParamBean.setDdd("90");

		formParam.setFormParamBean(formParamBean);
		formParam.setPageCurrent(1);
		formParam.setPageLimit(100);
		// 查询所有要过期和已经过期的证件
		List<FrameworkDataBean> list = getDao().doSelectPageCompanyCredentialsList(formParam);
		// 发送消息
		for (FrameworkDataBean item : list) {
			sendMessage((MGA1_YYZZDBO) item);
		}
	}

	// 信息模版
	@Resource
	MessageModelUtils messageModel;

	@Resource
	MessageService MessageService_;

	@Resource
	SMSService SMSService_;

	/**
	 * 发送信息(自动)
	 * 
	 * @param item
	 * @throws Exception
	 */
	public void sendMessage(MGA1_YYZZDBO item) throws Exception {
		String bizId = "32011015"; 
		MYY0_JBXXDBO cmpYY = new MYY0_JBXXDBO();
		cmpYY.setPuk(super.getCompanyId());
		cmpYY = (MYY0_JBXXDBO) MYY0_JBXXService_.doRead(cmpYY);
		MGYS0_JBXXDBO cmp = new MGYS0_JBXXDBO();
		cmp.setPuk(item.getK01_gysid());
		cmp = (MGYS0_JBXXDBO) MGYS0_JBXXService_.doRead(cmp);// 供应商信息
		// 发送消息
		MessageBean msg = new MessageBean();
		msg.setK01_fjrid(cmpYY.getPuk());// 发件人ID
		msg.setK02_sjrid(cmp.getF37());// 收件人ID
		msg.setF01_fjrnc(cmpYY.getF01_qyqc());// 发件人昵称
		msg.setF02_sjrnc(cmp.getF30_lxrxm());// 收件人昵称
		// msg.setF03_bt("[过期提醒]");// 标题
		msg.setF04_jjd(TWO);// 紧急度:1立刻处理2紧急3一般
		msg.setF05_zyd(TWO);// 重要度:1非常重要2重要3一般
		// 【您的注册证资料存在三个月内过期，请尽快更新】
		// String message =
		// messageModel.getLocalMessage(bizId);//"【您的证件资料存在过期行为，请尽快更新！】";
		// msg.setBbb(message);// 消息内容
		msg.setFb1(cmpYY.getF01_qyqc());// 发件人企业简称
		msg.setFb2(cmpYY.getF01_qyqc());// 发件人企业全称
		msg.setFb3(bizId);// 业务标识ID
		msg.setFb4(ONE);// 0已经阅读1未阅读
		msg.setEb5(item.getK01_gysid());// 收件企业ID
		msg.setGgg(cmpYY.getPuk());// 发送企业ID
		msg.setPpp(cmpYY.getPuk());

		messageModel.buildMessage(msg, MAIL+bizId, new String[] { item.getK03_zjlb(), item.getK02_zjbh() }, new String[] {});
		MessageService_.sendMessage(msg);

		// // 发送短信
		// MS3C2DBO sms = new MS3C2DBO();
		// sms.setK01_ywbsid(bizId);
		// sms.setK02_sjhm(cmp.getF32_lxrdh());
		// sms.setF02_fszt(ZERO);// 0等待发送1已发送2发送成功9发送失败
		// sms.setF03_dxnr(msg.getBbb());
		// sms.setFb1(ISSystemConstants.COMPANY);// 发件人企业简称
		// sms.setFb2(ISSystemConstants.COMPANY);// 发件人企业全称
		// sms.setFb3(bizId);// 业务标识ID
		// sms.setFb4(bizId);// 短信模版ID
		// sms.setEb1(ISSystemConstants.NAME);// 发件人昵称
		// sms.setEb2(cmp.getF30_lxrxm());// 收件人昵称
		// sms.setEb5(cmpId);// 收件企业ID
		// sms.setGgg(ISSystemConstants.SYSTEM);// 发送企业ID
		// SMSService_.sendMessage(sms);

	}

	/**
	 * 系统自动提醒(即将过期，过期)
	 * 
	 * @throws Exception
	 * @see <3000003>
	 */
	@Transactional
	public void sendSmsOnCredentialsBySystem() throws Exception {
		MyDataBaseObjectSupport formParamBean = new MyDataBaseObjectSupport();

		formParamBean.setCc1(DateHelper.currentTimeMillisCN3());
		formParamBean.setDdd("45");
		// 查询所有要过期和已经过期的证件
		List<FrameworkDataBean> list = getDao().sendSmsOnCredentialsBySystem(formParamBean);
		// 发送消息
		for (FrameworkDataBean item : list) {
			sendMessage(item);
		}
	}

	/**
	 * 发送信息(自动)
	 * 
	 * @param modelId
	 *            业务标识ID
	 * @param item
	 * @throws Exception
	 */
	public void sendMessage(FrameworkDataBean item) throws Exception {
		String bizId = "3000003";
		
		 // 【您的注册证资料存在三个月内过期，请尽快更新】
		 MessageBean msg = new MessageBean();
		 msg.setK01_fjrid(ISSystemConstants.SYSTEM);// 发件人ID
		 msg.setK02_sjrid(item.getPuk());// 收件人ID
		 msg.setF01_fjrnc(ISSystemConstants.NAME);// 发件人昵称
		 msg.setF02_sjrnc(item.getFb2());// 收件人昵称
		 //msg.setF03_bt(bt);// 标题
		 msg.setF04_jjd(TWO);// 紧急度:1立刻处理2紧急3一般
		 msg.setF05_zyd(TWO);// 重要度:1非常重要2重要3一般
		 //msg.setBbb(message);// 消息内容
		 msg.setFb1(ISSystemConstants.NAME);// 发件人企业简称
		 msg.setFb2(ISSystemConstants.NAME);// 发件人企业全称
		 msg.setFb3(bizId);// 业务标识ID
		 msg.setFb4(ONE);// 0已经阅读1未阅读
		 msg.setEb5(item.getPuk());// 收件企业ID
		 msg.setGgg(ISSystemConstants.SYSTEM);// 发送企业ID
		 // 发送消息
		 messageModel.buildMessage(msg, MAIL+bizId, new String[] {});
		 MessageService_.sendMessage(msg);
		 // 发送短信
		 MS3C2DBO sms = new MS3C2DBO();
		 sms.setK01_ywbsid(bizId);
		 sms.setK02_sjhm(item.getFb3());
		 sms.setF02_fszt(ZERO);// 0等待发送1已发送2发送成功9发送失败
		 sms.setF03_dxnr(msg.getBbb());
		 sms.setFb1(ISSystemConstants.NAME);// 发件人企业简称
		 sms.setFb2(ISSystemConstants.NAME);// 发件人企业全称
		 sms.setFb3(bizId);// 业务标识ID
		 sms.setFb4(SMS+bizId);// 短信模版ID
		 sms.setEb1(ISSystemConstants.NAME);// 发件人昵称
		 sms.setEb2(item.getFb2());// 收件人昵称
		 sms.setEb5(item.getPuk());// 收件企业ID
		 sms.setGgg(ISSystemConstants.SYSTEM);// 发送企业ID
		 SMSService_.sendMessage(sms);
	}
	 
	/** 医疗器械注册证 回溯
	 * 
	 * @param cid 注册证ID（业务主键）
	 * @return
	 */
	public List<FrameworkDataBean> loadCredentialsHistory(MyDataBaseObjectSupport paramBean) {
		return getDao().loadCredentialsHistory(paramBean);		
	}
	
	public List<FrameworkDataBean> getZczByIds(MyDataBaseObjectSupport formParamBean) {
        return this.getDao().getZczByIds(formParamBean);
    }
	
	public List<FrameworkDataBean> getSqsByIds(MyDataBaseObjectSupport formParamBean) {
        return getDao().getSqsByIds(formParamBean);
    }
	
	public List<FrameworkDataBean> getCjscxkzByIds(MyDataBaseObjectSupport formParamBean) {
        return this.getDao().getCjscxkzByIds(formParamBean);
    }
    
    public List<FrameworkDataBean> getCjyyzzByIds(MyDataBaseObjectSupport formParamBean) {
        return getDao().getCjyyzzByIds(formParamBean);
    }
}
