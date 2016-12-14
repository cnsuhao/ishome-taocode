package com.aek56.atm.company.cmp;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.bean.LoginerBean;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.helper.EmptyHelper;
import org.jfpc.framework.support.MyServiceSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.aek56.atm.company.MGYS0_JBXX.MGYS0_JBXXDBO;
import com.aek56.atm.company.MGYS0_JBXX.MGYS0_JBXXService;
import com.aek56.atm.company.MQC_PZZX.MQC_PZZXDBO;
import com.aek56.atm.company.MQC_PZZX.MQC_PZZXService;
import com.aek56.atm.company.MYY0_JBXX.MYY0_JBXXDBO;
import com.aek56.atm.company.MYY0_JBXX.MYY0_JBXXService;
import com.aek56.atm.credentials.CGTZ_TSJL.CGTZ_TSJLDBO;
import com.aek56.atm.credentials.CGTZ_TSJL.CGTZ_TSJLService;
import com.aek56.atm.credentials.MGA1_YYZZ.MGA1_YYZZDBO;
import com.aek56.atm.credentials.MGA1_YYZZ.MGA1_YYZZService;
import com.aek56.atm.credentials.MGA2_JYXKZ.MGA2_JYXKZDBO;
import com.aek56.atm.credentials.MGA2_JYXKZ.MGA2_JYXKZService;
import com.aek56.atm.credentials.MGA3_GSSWDJZ.MGA3_GSSWDJZDBO;
import com.aek56.atm.credentials.MGA3_GSSWDJZ.MGA3_GSSWDJZService;
import com.aek56.atm.credentials.MGA4_JXSQS.MGA4_JXSQSDBO;
import com.aek56.atm.credentials.MGA4_JXSQS.MGA4_JXSQSService;
import com.aek56.atm.credentials.MGA5_XSRYWTS.MGA5_XSRYWTSDBO;
import com.aek56.atm.credentials.MGA5_XSRYWTS.MGA5_XSRYWTSService;
import com.aek56.atm.credentials.MGA6_SHFWCNS.MGA6_SHFWCNSDBO;
import com.aek56.atm.credentials.MGA6_SHFWCNS.MGA6_SHFWCNSService;
import com.aek56.atm.credentials.MGA7_JKSJBG.MGA7_JKSJBGDBO;
import com.aek56.atm.credentials.MGA7_JKSJBG.MGA7_JKSJBGService;
import com.aek56.atm.credentials.MGA8C_YYZZ.MGA8C_YYZZDBO;
import com.aek56.atm.credentials.MGA8C_YYZZ.MGA8C_YYZZService;
import com.aek56.atm.credentials.MGA9C_SCXKZ.MGA9C_SCXKZDBO;
import com.aek56.atm.credentials.MGA9C_SCXKZ.MGA9C_SCXKZService;
import com.aek56.atm.credentials.MGAAC_YLQXZCZ.MGAAC_YLQXZCZDBO;
import com.aek56.atm.credentials.MGAAC_YLQXZCZ.MGAAC_YLQXZCZService;
import com.aek56.atm.credentials.MGABC_HCSPRZ.MGABC_HCSPRZDBO;
import com.aek56.atm.credentials.MGABC_HCSPRZ.MGABC_HCSPRZService;
import com.aek56.atm.credentials.MGACC_XDCPWSXKZ.MGACC_XDCPWSXKZDBO;
import com.aek56.atm.credentials.MGACC_XDCPWSXKZ.MGACC_XDCPWSXKZService;
import com.aek56.atm.credentials.MGT1_YYZZ.MGT1_YYZZDBO;
import com.aek56.atm.credentials.MGT1_YYZZ.MGT1_YYZZService;
import com.aek56.atm.credentials.MGT2_JYXKZ.MGT2_JYXKZDBO;
import com.aek56.atm.credentials.MGT2_JYXKZ.MGT2_JYXKZService;
import com.aek56.atm.credentials.MGT3_GSSWDJZ.MGT3_GSSWDJZDBO;
import com.aek56.atm.credentials.MGT3_GSSWDJZ.MGT3_GSSWDJZService;
import com.aek56.atm.credentials.MGT4_JXSQS.MGT4_JXSQSDBO;
import com.aek56.atm.credentials.MGT4_JXSQS.MGT4_JXSQSService;
import com.aek56.atm.credentials.MGT5_XSRYWTS.MGT5_XSRYWTSDBO;
import com.aek56.atm.credentials.MGT5_XSRYWTS.MGT5_XSRYWTSService;
import com.aek56.atm.credentials.MGT6_SHFWCNS.MGT6_SHFWCNSDBO;
import com.aek56.atm.credentials.MGT6_SHFWCNS.MGT6_SHFWCNSService;
import com.aek56.atm.credentials.MGT7_JKSJBG.MGT7_JKSJBGDBO;
import com.aek56.atm.credentials.MGT7_JKSJBG.MGT7_JKSJBGService;
import com.aek56.atm.credentials.MGT8_CJYYZZ.MGT8_CJYYZZDBO;
import com.aek56.atm.credentials.MGT8_CJYYZZ.MGT8_CJYYZZService;
import com.aek56.atm.credentials.MGT9_CJSCXKZ.MGT9_CJSCXKZDBO;
import com.aek56.atm.credentials.MGT9_CJSCXKZ.MGT9_CJSCXKZService;
import com.aek56.atm.credentials.MGTA_CJYLQXZCZ.MGTA_CJYLQXZCZDBO;
import com.aek56.atm.credentials.MGTA_CJYLQXZCZ.MGTA_CJYLQXZCZService;
import com.aek56.atm.credentials.MGTB_CJHCSPRZ.MGTB_CJHCSPRZDBO;
import com.aek56.atm.credentials.MGTB_CJHCSPRZ.MGTB_CJHCSPRZService;
import com.aek56.atm.credentials.MGTC_CJXDCPWSXKZ.MGTC_CJXDCPWSXKZDBO;
import com.aek56.atm.credentials.MGTC_CJXDCPWSXKZ.MGTC_CJXDCPWSXKZService;

/**
 * 企业信息
 * 
 * @author Administrator
 */
@Service
public class CompanyService extends MyServiceSupport {
    protected static final Logger logger = LoggerFactory.getLogger(CompanyService.class);

    public CompanyDao getDao(){
        return getMySqlSession().getMapper(CompanyDao.class);
    }
    
	@Resource
	MQC_PZZXService MQC_PZZXService_;// 配置信息
	@Resource
	MGYS0_JBXXService MGYS0_JBXXService_;// 供应商基本信息
	@Resource
	MYY0_JBXXService MYY0_JBXXService_;// 医院基本信息
	@Resource
	CGTZ_TSJLService CGTZ_TSJLService_;// 推送记录
	
	public void loadMyMessage(LoginerBean loginer) {
		FrameworkDataBean fdbo = getDao().loadMyMessage(loginer.getCompanyId());
		loginer.setEb1(fdbo.getEb1());
	}	
	
	/**
	 * 获得我的个人配置信息
	 * 
	 * @param cmpId
	 * @return
	 */
	public MQC_PZZXDBO myConfig(String cmpId) {
		MQC_PZZXDBO dbo = new MQC_PZZXDBO();
		dbo.setPuk(cmpId);
		dbo = (MQC_PZZXDBO) MQC_PZZXService_.doRead(dbo);
		if (dbo == null)
			dbo = new MQC_PZZXDBO();
		return dbo;
	}

	/**
	 * 获得企业资料
	 * 
	 * @param cmpId
	 * @param type
	 */
	public FrameworkDataBean myInfo(String cmpId, String type) {
		if (ONE.equals(type)) {
			MGYS0_JBXXDBO cmp = new MGYS0_JBXXDBO();
			cmp.setPuk(cmpId);
			return MGYS0_JBXXService_.doRead(cmp);
		} else if (TWO.equals(type)) {
			MYY0_JBXXDBO cmp = new MYY0_JBXXDBO();
			cmp.setPuk(cmpId);
			return MYY0_JBXXService_.doRead(cmp);
		}
		return null;
	}

	/**
	 * 推送自动审核
	 */
	@SuppressWarnings("unchecked")
	public void auditingCredentialsWithAuto(CGTZ_TSJLDBO aud) {
		if(!TWO.equals(aud.getF13_shzt())){
			return;
		}
		
		// 数据缓存
		HashMap<String, Object> res = (HashMap<String, Object>) super.getSessionAttribute(AUDITING);
		if (res == null) {
			res = (HashMap<String, Object>) auditingCredentialsForView(aud, EMPTY).getResult();
		}

		// 1换证2推送
		if (ONE.equals(aud.getGgg())) {
			// 证件标识（换证原因）P02_ZJBS，1新增2变更3替换4修正
			ArrayList<FrameworkDataBean> list = (ArrayList<FrameworkDataBean>) res.get("f10_cjylqxzcz");
			for (FrameworkDataBean cs : list) {
				if (FOUR.equals(aud.getFb5())) {
					MGAAC_YLQXZCZDBO gys = (MGAAC_YLQXZCZDBO) cs;
					MGTA_CJYLQXZCZDBO yy = new MGTA_CJYLQXZCZDBO();
					BeanUtils.copyProperties(gys, yy);
					yy.setP01_gysid(aud.getK01_gysid());// 供应商ID
					yy.setP02_zjbs(aud.getFb5());// （换证原因）
					yy.setP03_yyid(aud.getK02_yyid());// 医院ID
					yy.setF11_shzt(ZERO);
					MGTA_CJYLQXZCZService_.doUpdate(yy);
				} else //if (THREE.equals(aud.getFb5()))
				{
					MGAAC_YLQXZCZDBO gys = (MGAAC_YLQXZCZDBO) cs;
					MGTA_CJYLQXZCZDBO yy = new MGTA_CJYLQXZCZDBO();
					BeanUtils.copyProperties(gys, yy);
					yy.setP01_gysid(aud.getK01_gysid());// 供应商ID
					yy.setP02_zjbs(aud.getFb5());// （换证原因）
					yy.setP03_yyid(aud.getK02_yyid());// 医院ID
					yy.setF11_shzt(ZERO);
					MGTA_CJYLQXZCZService_.doInsert(yy);
				}
			}
		} else if (TWO.equals(aud.getGgg())) {
			ArrayList<FrameworkDataBean> list;
			// 接受所有
			// 供应商提供医院营业执照
			if (EmptyHelper.isEmpty(aud.getF01_yyzz()) == false) {
				list = (ArrayList<FrameworkDataBean>) res.get("f01_yyzz");
				for (FrameworkDataBean cs : list) {
					MGA1_YYZZDBO gys = (MGA1_YYZZDBO) cs;
					MGT1_YYZZDBO yy = new MGT1_YYZZDBO();
					BeanUtils.copyProperties(gys, yy);
					yy.setK01_gysid(aud.getK01_gysid());// 供应商ID
					yy.setP01_yyid(aud.getK02_yyid());// 医院ID
					yy.setF06_shzt(ZERO);
					MGT1_YYZZService_.doInsert(yy);
				}
			}
			// 供应商提供医院经营许可证
			if (EmptyHelper.isEmpty(aud.getF02_jyxkz()) == false) {
				list = (ArrayList<FrameworkDataBean>) res.get("f02_jyxkz");
				for (FrameworkDataBean cs : list) {
					MGA2_JYXKZDBO gys = (MGA2_JYXKZDBO) cs;
					MGT2_JYXKZDBO yy = new MGT2_JYXKZDBO();
					BeanUtils.copyProperties(gys, yy);
					yy.setK01_gysid(aud.getK01_gysid());// 供应商ID
					yy.setP01_yyid(aud.getK02_yyid());// 医院ID
					yy.setF06_shzt(ZERO);
					MGT2_JYXKZService_.doInsert(yy);
				}
			}
			// 供应商提供医院工商税务登记证
			if (EmptyHelper.isEmpty(aud.getF03_gsswdjz()) == false) {
				list = (ArrayList<FrameworkDataBean>) res.get("f03_gsswdjz");
				for (FrameworkDataBean cs : list) {
					MGA3_GSSWDJZDBO gys = (MGA3_GSSWDJZDBO) cs;
					MGT3_GSSWDJZDBO yy = new MGT3_GSSWDJZDBO();
					BeanUtils.copyProperties(gys, yy);
					yy.setK01_gysid(aud.getK01_gysid());// 供应商ID
					yy.setP01_yyid(aud.getK02_yyid());// 医院ID
					yy.setF06_shzt(ZERO);
					MGT3_GSSWDJZService_.doInsert(yy);
				}
			}
			// 供应商提供医院经销授权书
			if (EmptyHelper.isEmpty(aud.getF04_jxsqs()) == false) {
				list = (ArrayList<FrameworkDataBean>) res.get("f04_jxsqs");
				for (FrameworkDataBean cs : list) {
					MGA4_JXSQSDBO gys = (MGA4_JXSQSDBO) cs;
					MGT4_JXSQSDBO yy = new MGT4_JXSQSDBO();
					BeanUtils.copyProperties(gys, yy);
					yy.setK01_gysid(aud.getK01_gysid());// 供应商ID
					yy.setP01_yyid(aud.getK02_yyid());// 医院ID
					yy.setF06_shzt(ZERO);
					MGT4_JXSQSService_.doInsert(yy);
				}
			}
			// 供应商提供医院销售人员委托书
			if (EmptyHelper.isEmpty(aud.getF05_xsrywts()) == false) {
				list = (ArrayList<FrameworkDataBean>) res.get("f05_xsrywts");
				for (FrameworkDataBean cs : list) {
					MGA5_XSRYWTSDBO gys = (MGA5_XSRYWTSDBO) cs;
					MGT5_XSRYWTSDBO yy = new MGT5_XSRYWTSDBO();
					BeanUtils.copyProperties(gys, yy);
					yy.setK01_gysid(aud.getK01_gysid());// 供应商ID
					yy.setP01_yyid(aud.getK02_yyid());// 医院ID
					yy.setF06_shzt(ZERO);
					MGT5_XSRYWTSService_.doInsert(yy);
				}
			}
			// 供应商提供医院售后服务承诺书
			if (EmptyHelper.isEmpty(aud.getF06_shfwcns()) == false) {
				list = (ArrayList<FrameworkDataBean>) res.get("f06_shfwcns");
				for (FrameworkDataBean cs : list) {
					MGA6_SHFWCNSDBO gys = (MGA6_SHFWCNSDBO) cs;
					MGT6_SHFWCNSDBO yy = new MGT6_SHFWCNSDBO();
					BeanUtils.copyProperties(gys, yy);
					yy.setK01_gysid(aud.getK01_gysid());// 供应商ID
					yy.setP01_yyid(aud.getK02_yyid());// 医院ID
					yy.setF06_shzt(ZERO);
					MGT6_SHFWCNSService_.doInsert(yy);
				}
			}
			// 供应商提供医院进口商检报告
			if (EmptyHelper.isEmpty(aud.getF07_jksjbg()) == false) {
				list = (ArrayList<FrameworkDataBean>) res.get("f07_jksjbg");
				for (FrameworkDataBean cs : list) {
					MGA7_JKSJBGDBO gys = (MGA7_JKSJBGDBO) cs;
					MGT7_JKSJBGDBO yy = new MGT7_JKSJBGDBO();
					BeanUtils.copyProperties(gys, yy);
					yy.setK01_gysid(aud.getK01_gysid());// 供应商ID
					yy.setP01_yyid(aud.getK02_yyid());// 医院ID
					yy.setF06_shzt(ZERO);
					MGT7_JKSJBGService_.doInsert(yy);
				}
			}
			// 供应商提供医院厂家营业执照
			if (EmptyHelper.isEmpty(aud.getF08_cjyyzz()) == false) {
				list = (ArrayList<FrameworkDataBean>) res.get("f08_cjyyzz");
				for (FrameworkDataBean cs : list) {
					MGA8C_YYZZDBO gys = (MGA8C_YYZZDBO) cs;
					MGT8_CJYYZZDBO yy = new MGT8_CJYYZZDBO();
					BeanUtils.copyProperties(gys, yy);
					yy.setK01_gysid(aud.getK01_gysid());// 供应商ID
					yy.setP01_yyid(aud.getK02_yyid());// 医院ID
					yy.setF06_shzt(ZERO);
					MGT8_CJYYZZService_.doInsert(yy);
				}
			}
			// 供应商提供医院厂家生产许可证
			if (EmptyHelper.isEmpty(aud.getF09_cjscxkz()) == false) {
				list = (ArrayList<FrameworkDataBean>) res.get("f09_cjscxkz");
				for (FrameworkDataBean cs : list) {
					MGA9C_SCXKZDBO gys = (MGA9C_SCXKZDBO) cs;
					MGT9_CJSCXKZDBO yy = new MGT9_CJSCXKZDBO();
					BeanUtils.copyProperties(gys, yy);
					yy.setK01_gysid(aud.getK01_gysid());// 供应商ID
					yy.setP01_yyid(aud.getK02_yyid());// 医院ID
					yy.setF06_shzt(ZERO);
					MGT9_CJSCXKZService_.doInsert(yy);
				}
			}
			// 供应商提供医院厂家医疗器械注册证
			if (EmptyHelper.isEmpty(aud.getN10_cjylqxzcz()) == false) {
				list = (ArrayList<FrameworkDataBean>) res.get("f10_cjylqxzcz");
				for (FrameworkDataBean cs : list) {
					if(ONE.equals(cs.getDdd())){
						break;
					}
					MGAAC_YLQXZCZDBO gys = (MGAAC_YLQXZCZDBO) cs;
					MGTA_CJYLQXZCZDBO yy = new MGTA_CJYLQXZCZDBO();
					BeanUtils.copyProperties(gys, yy);
					yy.setP01_gysid(aud.getK01_gysid());// 供应商ID
					yy.setP02_zjbs(ONE);// （换证原因）
					yy.setP03_yyid(aud.getK02_yyid());// 医院ID
					yy.setF11_shzt(ZERO);
					MGTA_CJYLQXZCZService_.doInsert(yy);
				}
			}
			// 供应商提供医院厂家耗材商品3c认证
			if (EmptyHelper.isEmpty(aud.getF11_cjhcsprz()) == false) {
				list = (ArrayList<FrameworkDataBean>) res.get("f11_cjhcsprz");
				for (FrameworkDataBean cs : list) {
					MGABC_HCSPRZDBO gys = (MGABC_HCSPRZDBO) cs;
					MGTB_CJHCSPRZDBO yy = new MGTB_CJHCSPRZDBO();
					BeanUtils.copyProperties(gys, yy);
					yy.setK01_gysid(aud.getK01_gysid());// 供应商ID
					yy.setP01_yyid(aud.getK02_yyid());// 医院ID
					yy.setF06_shzt(ZERO);
					MGTB_CJHCSPRZService_.doInsert(yy);
				}
			}
			// 供应商提供医院厂家消毒产品卫生许可证
			if (EmptyHelper.isEmpty(aud.getF12_cjxdcpwsxkz()) == false) {
				list = (ArrayList<FrameworkDataBean>) res.get("f12_cjxdcpwsxkz");
				for (FrameworkDataBean cs : list) {
					MGACC_XDCPWSXKZDBO gys = (MGACC_XDCPWSXKZDBO) cs;
					MGTC_CJXDCPWSXKZDBO yy = new MGTC_CJXDCPWSXKZDBO();
					BeanUtils.copyProperties(gys, yy);
					yy.setK01_gysid(aud.getK01_gysid());// 供应商ID
					yy.setP01_yyid(aud.getK02_yyid());// 医院ID
					yy.setF06_shzt(ZERO);
					MGTC_CJXDCPWSXKZService_.doInsert(yy);
				}
			}
		}
	}

	/**
	 * 推送手动审核
	 */
	public void auditingCredentialsWithManual(String aid) {
		CGTZ_TSJLDBO cgtz = new CGTZ_TSJLDBO();
		cgtz.setPuk(aid);
		// 医院审核
		auditingCredentialsWithAuto((CGTZ_TSJLDBO) CGTZ_TSJLService_.doRead(cgtz));
	}

	public static final String AUDITING = "auditing";

	/**
	 * 推送信息查看
	 */
	public RESTResultBean auditingCredentialsForView(String aid, String type) {
		CGTZ_TSJLDBO cgtz = new CGTZ_TSJLDBO();
		cgtz.setPuk(aid);
		// 推送记录
		cgtz = (CGTZ_TSJLDBO) CGTZ_TSJLService_.doRead(cgtz);

		return auditingCredentialsForView(cgtz, type);
	}

	/**
	 * 推送信息查看
	 */
	public RESTResultBean auditingCredentialsForView(CGTZ_TSJLDBO cgtz, String type) {
		// 页面显示类型
		if (EmptyHelper.isEmpty(type))
			type = "0123456789ABC";
		else
			type = type.toUpperCase();

		RESTResultBean rb = new RESTResultBean();

		HashMap<String, Object> res = new HashMap<String, Object>();
		// ///////////////////////////////////////////////////////////////
		// 供应商资料
		MGYS0_JBXXDBO cmpGys = (MGYS0_JBXXDBO) myInfo(cgtz.getK01_gysid(), ONE);
		// 医院资料
		// MYY0_JBXXDBO cmpYy = (MYY0_JBXXDBO) myInfo(cgtz.getK02_yyid(), TWO);
		res.put("cmp", cmpGys);
		// /////////////////////////////////////////////////////////////////
		res.put("puk", cgtz.getPuk());

		// /////////////////////基本资料//////////////////////////////
		res.put("bbb", cgtz.getBbb());

		res.put("fb1", cgtz.getFb1());
		res.put("fb2", cgtz.getFb2());
		res.put("fb3", cgtz.getFb3());
		res.put("fb4", cgtz.getFb4());
		res.put("fb5", cgtz.getFb5());
		res.put("eb1", cgtz.getEb1());
		res.put("eb2", cgtz.getEb2());
		res.put("eb3", cgtz.getEb3());
		res.put("eb4", cgtz.getEb4());
		res.put("eb5", cgtz.getEb5());

		res.put("ggg", cgtz.getGgg());
		res.put("ppp", cgtz.getPpp());

		// //////////////////////证件资料//////////////////////////////
		ArrayList<FrameworkDataBean> list;
		// 供应商提供医院营业执照
		if (type.indexOf("1") >= 0) {
			list = new ArrayList<FrameworkDataBean>();
			MGA1_YYZZDBO dbo = new MGA1_YYZZDBO();
			if (EmptyHelper.isEmpty(cgtz.getF01_yyzz()) == false) {
				for (String cid : cgtz.getF01_yyzz().split(COMMA)) {
					dbo.setPuk(cid);
					dbo.setK01_gysid(cgtz.getK01_gysid());
					list.add(MGA1_YYZZService_.doRead(dbo));
				}
			}
			res.put("f01_yyzz", list);
		}
		// 供应商提供医院经营许可证
		if (type.indexOf("2") >= 0) {
			list = new ArrayList<FrameworkDataBean>();
			MGA2_JYXKZDBO dbo = new MGA2_JYXKZDBO();
			if (EmptyHelper.isEmpty(cgtz.getF02_jyxkz()) == false) {
				for (String cid : cgtz.getF02_jyxkz().split(COMMA)) {
					dbo.setPuk(cid);
					dbo.setK01_gysid(cgtz.getK01_gysid());
					list.add(MGA2_JYXKZService_.doRead(dbo));
				}
			}
			res.put("f02_jyxkz", list);
		}
		// 供应商提供医院工商税务登记证
		if (type.indexOf("3") >= 0) {
			list = new ArrayList<FrameworkDataBean>();
			MGA3_GSSWDJZDBO dbo = new MGA3_GSSWDJZDBO();
			if (EmptyHelper.isEmpty(cgtz.getF03_gsswdjz()) == false) {
				for (String cid : cgtz.getF03_gsswdjz().split(COMMA)) {
					dbo.setPuk(cid);
					dbo.setK01_gysid(cgtz.getK01_gysid());
					list.add(MGA3_GSSWDJZService_.doRead(dbo));
				}
			}
			res.put("f03_gsswdjz", list);
		}
		// 供应商提供医院经销授权书
		if (type.indexOf("4") >= 0) {
			list = new ArrayList<FrameworkDataBean>();
			MGA4_JXSQSDBO dbo = new MGA4_JXSQSDBO();
			if (EmptyHelper.isEmpty(cgtz.getF04_jxsqs()) == false) {
				for (String cid : cgtz.getF04_jxsqs().split(COMMA)) {
					dbo.setPuk(cid);
					dbo.setK01_gysid(cgtz.getK01_gysid());
					list.add(MGA4_JXSQSService_.doRead(dbo));
				}
			}
			res.put("f04_jxsqs", list);
		}
		// 供应商提供医院销售人员委托书
		if (type.indexOf("5") >= 0) {
			list = new ArrayList<FrameworkDataBean>();
			MGA5_XSRYWTSDBO dbo = new MGA5_XSRYWTSDBO();
			if (EmptyHelper.isEmpty(cgtz.getF05_xsrywts()) == false) {
				for (String cid : cgtz.getF05_xsrywts().split(COMMA)) {
					dbo.setPuk(cid);
					dbo.setK01_gysid(cgtz.getK01_gysid());
					list.add(MGA5_XSRYWTSService_.doRead(dbo));
				}
			}
			res.put("f05_xsrywts", list);
		}
		// 供应商提供医院售后服务承诺书
		if (type.indexOf("6") >= 0) {
			list = new ArrayList<FrameworkDataBean>();
			MGA6_SHFWCNSDBO dbo = new MGA6_SHFWCNSDBO();
			if (EmptyHelper.isEmpty(cgtz.getF06_shfwcns()) == false) {
				for (String cid : cgtz.getF06_shfwcns().split(COMMA)) {
					dbo.setPuk(cid);
					dbo.setK01_gysid(cgtz.getK01_gysid());
					list.add(MGA6_SHFWCNSService_.doRead(dbo));
				}
			}
			res.put("f06_shfwcns", list);
		}
		// 供应商提供医院进口商检报告
		if (type.indexOf("7") >= 0) {
			list = new ArrayList<FrameworkDataBean>();
			MGA7_JKSJBGDBO dbo = new MGA7_JKSJBGDBO();
			if (EmptyHelper.isEmpty(cgtz.getF07_jksjbg()) == false) {
				for (String cid : cgtz.getF07_jksjbg().split(COMMA)) {
					dbo.setPuk(cid);
					dbo.setK01_gysid(cgtz.getK01_gysid());
					list.add(MGA7_JKSJBGService_.doRead(dbo));
				}
			}
			res.put("f07_jksjbg", list);
		}
		// 供应商提供医院厂家营业执照
		if (type.indexOf("8") >= 0) {
			list = new ArrayList<FrameworkDataBean>();
			MGA8C_YYZZDBO dbo = new MGA8C_YYZZDBO();
			if (EmptyHelper.isEmpty(cgtz.getF08_cjyyzz()) == false) {
				for (String cid : cgtz.getF08_cjyyzz().split(COMMA)) {
					dbo.setPuk(cid);
					dbo.setK01_gysid(cgtz.getK01_gysid());
					list.add(MGA8C_YYZZService_.doRead(dbo));
				}
			}
			res.put("f08_cjyyzz", list);
		}
		// 供应商提供医院厂家生产许可证
		if (type.indexOf("9") >= 0) {
			list = new ArrayList<FrameworkDataBean>();
			MGA9C_SCXKZDBO dbo = new MGA9C_SCXKZDBO();
			if (EmptyHelper.isEmpty(cgtz.getF09_cjscxkz()) == false) {
				for (String cid : cgtz.getF09_cjscxkz().split(COMMA)) {
					dbo.setPuk(cid);
					dbo.setK01_gysid(cgtz.getK01_gysid());
					list.add(MGA9C_SCXKZService_.doRead(dbo));
				}
			}
			res.put("f09_cjscxkz", list);
		}
		// 供应商提供医院厂家医疗器械注册证
		if (type.indexOf("A") >= 0) {
			list = new ArrayList<FrameworkDataBean>();
			MGAAC_YLQXZCZDBO dbo = new MGAAC_YLQXZCZDBO();
			if (EmptyHelper.isEmpty(cgtz.getF10_cjylqxzcz()) == false) {
				//换证处理
				//证件标识（换证原因）P02_ZJBS，1新增2变更3替换4修正	
				if(!ONE.equals(cgtz.getFb5())){					
					dbo.setPuk(EMPTY);
					dbo.setP01_gysid(cgtz.getK01_gysid());
					dbo.setK01_zczid(dbo.getK01_zczid());
					dbo = (MGAAC_YLQXZCZDBO) MGAAC_YLQXZCZService_.loadNewCredentials(dbo);
					dbo.setDdd(ONE);
					list.add(dbo);
				}else{				
					for (String cid : cgtz.getF10_cjylqxzcz().split(COMMA)) {
						dbo = new MGAAC_YLQXZCZDBO();
						dbo.setPuk(cid);
						dbo.setP01_gysid(cgtz.getK01_gysid());
						
						list.add((MGAAC_YLQXZCZDBO) MGAAC_YLQXZCZService_.doRead(dbo));
					}
				}
			}
			res.put("f10_cjylqxzcz", list);
		}
		// 供应商提供医院厂家耗材商品3c认证
		if (type.indexOf("B") >= 0) {
			list = new ArrayList<FrameworkDataBean>();
			MGABC_HCSPRZDBO dbo = new MGABC_HCSPRZDBO();
			if (EmptyHelper.isEmpty(cgtz.getF11_cjhcsprz()) == false) {
				for (String cid : cgtz.getF11_cjhcsprz().split(COMMA)) {
					dbo.setPuk(cid);
					dbo.setK01_gysid(cgtz.getK01_gysid());
					list.add(MGABC_HCSPRZService_.doRead(dbo));
				}
			}
			res.put("f11_cjhcsprz", list);
		}
		// 供应商提供医院厂家消毒产品卫生许可证
		if (type.indexOf("C") >= 0) {
			list = new ArrayList<FrameworkDataBean>();
			MGACC_XDCPWSXKZDBO dbo = new MGACC_XDCPWSXKZDBO();
			if (EmptyHelper.isEmpty(cgtz.getF12_cjxdcpwsxkz()) == false) {
				for (String cid : cgtz.getF12_cjxdcpwsxkz().split(COMMA)) {
					dbo.setPuk(cid);
					dbo.setK01_gysid(cgtz.getK01_gysid());
					list.add(MGACC_XDCPWSXKZService_.doRead(dbo));
				}
			}
			res.put("f12_cjxdcpwsxkz", list);
		}

		rb.setResult(res);

		// 数据缓存
		super.setSessionAttribute(AUDITING, res);

		return rb;
	}

	@Resource	MGA1_YYZZService MGA1_YYZZService_;// 供应商营业执照
	@Resource	MGA2_JYXKZService MGA2_JYXKZService_;// 供应商经营许可证
	@Resource	MGA3_GSSWDJZService MGA3_GSSWDJZService_;// 供应商工商税务登记证
	@Resource	MGA4_JXSQSService MGA4_JXSQSService_;// 供应商经销授权书
	@Resource	MGA5_XSRYWTSService MGA5_XSRYWTSService_;// 供应商销售人员委托书
	@Resource	MGA6_SHFWCNSService MGA6_SHFWCNSService_;// 供应商售后服务承诺书
	@Resource	MGA7_JKSJBGService MGA7_JKSJBGService_;// 供应商进口商检报告
	@Resource	MGA8C_YYZZService MGA8C_YYZZService_;// 厂家营业执照
	@Resource	MGA9C_SCXKZService MGA9C_SCXKZService_;// 厂家生产许可证
	@Resource 	MGAAC_YLQXZCZService MGAAC_YLQXZCZService_;// 厂家医疗器械注册证
	@Resource	MGABC_HCSPRZService MGABC_HCSPRZService_;// 厂家耗材商品3C认证
	@Resource	MGACC_XDCPWSXKZService MGACC_XDCPWSXKZService_;// 厂家消毒产品卫生许可证

	@Resource	MGT1_YYZZService MGT1_YYZZService_;// 供应商提供医院营业执照
	@Resource	MGT2_JYXKZService MGT2_JYXKZService_;// 供应商提供医院经营许可证
	@Resource	MGT3_GSSWDJZService MGT3_GSSWDJZService_;// 供应商提供医院工商税务登记证
	@Resource	MGT4_JXSQSService MGT4_JXSQSService_;// 供应商提供医院经销授权书
	@Resource	MGT5_XSRYWTSService MGT5_XSRYWTSService_;// 供应商提供医院销售人员委托书
	@Resource	MGT6_SHFWCNSService MGT6_SHFWCNSService_;// 供应商提供医院售后服务承诺书
	@Resource	MGT7_JKSJBGService MGT7_JKSJBGService_;// 供应商提供医院进口商检报告
	@Resource	MGT8_CJYYZZService MGT8_CJYYZZService_;// 供应商提供医院厂家营业执照
	@Resource	MGT9_CJSCXKZService MGT9_CJSCXKZService_;// 供应商提供医院厂家生产许可证
	@Resource	MGTA_CJYLQXZCZService MGTA_CJYLQXZCZService_;// 供应商提供医院厂家医疗器械注册证
	@Resource	MGTB_CJHCSPRZService MGTB_CJHCSPRZService_;// 供应商提供医院厂家耗材商品3C认证
	@Resource	MGTC_CJXDCPWSXKZService MGTC_CJXDCPWSXKZService_;// 供应商提供医院厂家消毒产品卫生许可证

}
