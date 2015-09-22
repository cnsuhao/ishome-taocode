package com.aek56.qt.yy.MGTA;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.page.PageVOSupport;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aek56.atm.company.MGYS2_GYSTJCSXX.MGYS2_GYSTJCSXXDBO;
import com.aek56.atm.company.MGYS2_GYSTJCSXX.MGYS2_GYSTJCSXXService;
import com.aek56.atm.company.MYY1_YYTJGYSXX.MYY1_YYTJGYSXXDBO;
import com.aek56.atm.company.MYY1_YYTJGYSXX.MYY1_YYTJGYSXXService;
import com.aek56.atm.credentials.MGA1_YYZZ.MGA1_YYZZDBO;
import com.aek56.atm.credentials.MGA1_YYZZ.MGA1_YYZZService;
import com.aek56.atm.credentials.MGA2_JYXKZ.MGA2_JYXKZDBO;
import com.aek56.atm.credentials.MGA2_JYXKZ.MGA2_JYXKZService;
import com.aek56.atm.credentials.MGA3_GSSWDJZ.MGA3_GSSWDJZDBO;
import com.aek56.atm.credentials.MGA3_GSSWDJZ.MGA3_GSSWDJZService;
import com.aek56.atm.credentials.MGA5_XSRYWTS.MGA5_XSRYWTSDBO;
import com.aek56.atm.credentials.MGA5_XSRYWTS.MGA5_XSRYWTSService;
import com.aek56.atm.credentials.MGA6_SHFWCNS.MGA6_SHFWCNSDBO;
import com.aek56.atm.credentials.MGA6_SHFWCNS.MGA6_SHFWCNSService;
import com.aek56.atm.credentials.MGA8C_YYZZ.MGA8C_YYZZDBO;
import com.aek56.atm.credentials.MGA8C_YYZZ.MGA8C_YYZZService;
import com.aek56.atm.credentials.MGA9C_SCXKZ.MGA9C_SCXKZDBO;
import com.aek56.atm.credentials.MGA9C_SCXKZ.MGA9C_SCXKZService;
import com.aek56.atm.credentials.MGAAC_YLQXZCZ.MGAAC_YLQXZCZDBO;
import com.aek56.atm.credentials.MGAAC_YLQXZCZ.MGAAC_YLQXZCZService;
import com.aek56.atm.credentials.MGAD_SQSGLZCZXX.MGAD_SQSGLZCZXXDBO;
import com.aek56.atm.credentials.MGTA_CJYLQXZCZ.MGTA_CJYLQXZCZDBO;
import com.aek56.atm.credentials.MGTA_CJYLQXZCZ.MGTA_CJYLQXZCZService;
import com.aek56.qt.credentials.CredentialsBusiness;
import com.aek56.qt.yy.MGT1.MGT1PVO;

/**
 * 供应商提供医院厂家医疗器械注册证
 * 
 * @author tangmuming 2014-12-5 3211000
 */
@Controller
public class MGTAController extends MyControllerSupport {
	private static final Logger logger = LoggerFactory.getLogger(MGTAController.class);

	/**
	 * 默认加载页
	 */
	public MyModelAndViewSupport getModelAndView(String strPath) {
		return new MyModelAndViewSupport(strPath);
	}

	/**
	 * 跳转到我的证件页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/32110001", method = RequestMethod.POST)
	public MyModelAndViewSupport gopage() {
		MyModelAndViewSupport mv = getModelAndView("qt/yy/MGT1/MGT101");
		return mv;
	}

	// 供应商提供医院厂家医疗器械注册证
	@Resource
	protected MGTA_CJYLQXZCZService MGTA_CJYLQXZCZService_;

	/**
	 * 一览
	 * 
	 * @return
	 * @param page当前页
	 * @param pageSize条数
	 * @param orderBy过期
	 *            ，未过期
	 * @param keywords供应商名称
	 *            ，关键字
	 */
	@RequestMapping(value = "/32110002", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean listPost(String page, String pageSize, String orderBy, String keywords,String gysid) {
		RESTResultBean rb = new RESTResultBean();
		pageModel.setPageCurrent(Integer.parseInt(page));
		pageModel.setPageLimit(Integer.parseInt(pageSize));
		MGTA_CJYLQXZCZDBO param = new MGTA_CJYLQXZCZDBO();
		param.setFb1(keywords);
		param.setP03_yyid(super.getCompanyId());
		param.setP01_gysid(gysid);
		pageModel.setFormParamBean(param);
		PageVOSupport pvo = MGTA_CJYLQXZCZService_.doSelectPage(pageModel, false);
		MGT1PVO mg = new MGT1PVO();
		if (pvo != null) {
			mg.setList(pvo.getPageListData());
			mg.setCount(pvo.getResultCount());
		}
		rb.setResult(mg);
		return rb;
	}

	/**
	 * 删除证件
	 * 
	 * @param puk
	 * @return
	 */
	@RequestMapping(value = "/32110003", method = RequestMethod.POST)
	@ResponseBody
	public RESTResultBean delete(String puk) {
		RESTResultBean rb = new RESTResultBean();
		MGTA_CJYLQXZCZDBO param = new MGTA_CJYLQXZCZDBO();
		param.setPuk(puk);
		int flag = MGTA_CJYLQXZCZService_.toDelete(param);
		if (flag == 1) {
			rb.setMessage(messageModel.getLocalMessage("321100031"));
		} else {
			rb.setCode(ONE);
			rb.setMessage(messageModel.getLocalMessage("321100032"));
		}
		return rb;
	}

	// 添加by高勤
	// 厂商信息service
	@Resource
	private MGYS2_GYSTJCSXXService mgys2_GYSTJCSXXService;
	// 供应商信息service
	@Resource
	private MYY1_YYTJGYSXXService myy1_YYTJGYSXXService;

	// 供应商注册证service
	@Resource
	private MGAAC_YLQXZCZService mgaac_YLQXZCZService;
	// 委托书
	@Resource
	private MGA5_XSRYWTSService mga5_XSRYWTSService;

	@Resource
	private CredentialsBusiness credentialsBusiness;
	//厂家营业执照
	@Resource
	private MGA8C_YYZZService mga8c_YYZZService;
	//厂家生产许可证
	@Resource
	private MGA9C_SCXKZService mga9c_SCXKZService;
	@Resource
	private MGA1_YYZZService mga1_YYZZService;
	@Resource
	private MGA2_JYXKZService mga2_JYXKZService;
	@Resource
	private MGA3_GSSWDJZService mga3_GSSWDJZService;
	@Resource
	private MGA6_SHFWCNSService mga6_SHFWCNSService;
	
	/**
	 * 跳转到回溯页面
	 * 
	 * @param cid
	 *            证件ID
	 * @return
	 */
	@RequestMapping(value = "/32110004", method = RequestMethod.GET)
	public MyModelAndViewSupport m32101004idGET(String cid) {
		if(logger.isDebugEnabled())
			logger.debug(cid);
		MyModelAndViewSupport mv = getModelAndView("qt/yy/MGT1/MGT102");
		// 医疗器械注册证实例
		MGTA_CJYLQXZCZDBO mgta = new MGTA_CJYLQXZCZDBO();
		mgta.setPuk(cid);
		mgta.setP03_yyid(super.getCompanyId());
		mgta = (MGTA_CJYLQXZCZDBO) MGTA_CJYLQXZCZService_.doRead(mgta);

		
		load(mv,mgta);
		return mv;
	}
	/**
	 * 跳转到回溯页面(审核场合)
	 * 
	 * @param cid
	 *            证件ID
	 * @return
	 */
	@RequestMapping(value = "/32110004/1", method = RequestMethod.GET)
	public MyModelAndViewSupport m321010041idGET(String cid) {
		if(logger.isDebugEnabled())
			logger.debug(cid);
		MyModelAndViewSupport mv = getModelAndView("qt/yy/MGT1/MGT102");
		// 医疗器械注册证实例
		MGAAC_YLQXZCZDBO mgaa = new MGAAC_YLQXZCZDBO();
		mgaa.setPuk(cid);
		//mgta.setP01_gysid(gid);
		mgaa = (MGAAC_YLQXZCZDBO) mgaac_YLQXZCZService.doRead(mgaa);

		MGTA_CJYLQXZCZDBO mgta = new MGTA_CJYLQXZCZDBO();
		BeanUtils.copyProperties(mgaa, mgta);
		mgta.setP03_yyid(super.getCompanyId());
		load(mv,mgta);
		return mv;
	}
	
	private void load(MyModelAndViewSupport mv,MGTA_CJYLQXZCZDBO mgta){
		// 产品名称
				mv.addObject("CPMC", mgta.getF03_cpzwmc());
				// 注册号mgta
				mv.addObject("ZCH", mgta.getF01_zczzwmc());
				// 厂家/总代理
				{
					MGYS2_GYSTJCSXXDBO mgys2 = new MGYS2_GYSTJCSXXDBO();
					mgys2.setPuk(mgta.getK04_scqyid());
					mgys2.setP01_gysid(mgta.getP01_gysid());
					mv.addObject("CJDBO", mgys2_GYSTJCSXXService.doRead(mgys2));
					//查询厂家/总代  营业执照
					MGA8C_YYZZDBO cyyzz = new MGA8C_YYZZDBO();
					cyyzz.setK02_sccj_qyid(mgta.getK04_scqyid());
					cyyzz.setK01_gysid(mgta.getP01_gysid());
					List<FrameworkDataBean> cyyzzlist = mga8c_YYZZService.doSelectPage(cyyzz, false);
					if(cyyzzlist!=null){
						cyyzz = (MGA8C_YYZZDBO)cyyzzlist.get(0);
					}
					mv.addObject("CJ_YYZZ", cyyzz);
					//生产许可证
					MGA9C_SCXKZDBO cscxkz = new MGA9C_SCXKZDBO();
					cscxkz.setK02_sccj_qyid(mgta.getK04_scqyid());
					cscxkz.setK01_gysid(mgta.getP01_gysid());
					List<FrameworkDataBean> scxkzlist = mga9c_SCXKZService.doSelectPage(cscxkz, false);
					if(scxkzlist!=null){
						cscxkz = (MGA9C_SCXKZDBO)scxkzlist.get(0);
					}
					mv.addObject("CJ_SCXKZ", cscxkz);
				}
				// 委托书
				{
					MGA5_XSRYWTSDBO mga5 = new MGA5_XSRYWTSDBO();
					mga5.setK01_gysid(mgta.getP01_gysid());
					mga5.setF11(mgta.getP03_yyid());
					mv.addObject("WTSLIST", mga5_XSRYWTSService.doRead(mga5));
				}
				// 授权书
				{
					MGAD_SQSGLZCZXXDBO mgad = new MGAD_SQSGLZCZXXDBO();
					mgad.setK01_gysid(mgta.getP01_gysid());
					mgad.setK02_zczid(mgta.getK01_zczid());
					List<MGAD_SQSGLZCZXXPVO> sqslist = new ArrayList<MGAD_SQSGLZCZXXPVO>();
					List<FrameworkDataBean> temp = credentialsBusiness.loadCredentialsHistory(mgad);
		/*			if(temp!=null){
						for (FrameworkDataBean frameworkDataBean : temp) {
							MGAD_SQSGLZCZXXDBO fb = (MGAD_SQSGLZCZXXDBO)frameworkDataBean;
							MGAD_SQSGLZCZXXPVO pvo = new MGAD_SQSGLZCZXXPVO();
							BeanUtils.copyProperties(fb, pvo);
						    String bbb = fb.getBbb();
						    if(!StringUtils.isEmpty(bbb)){
						    	String[] images = bbb.split(COMMA);
						    	pvo.setImages(images);
						    }
						    sqslist.add(pvo);
						}
					}*/
					mv.addObject("SQSLIST", temp);// 注册证DBO
				}
				// 注册证
				{
					// TODO是否查看所有的历史数据
					MGAAC_YLQXZCZDBO mgaac = new MGAAC_YLQXZCZDBO();
					mgaac.setK01_zczid(mgta.getK01_zczid());
					mgaac.setP01_gysid(mgta.getP01_gysid());
					List<MGAAC_YLQXZCZPVO> sqslist = new ArrayList<MGAAC_YLQXZCZPVO>();
					List<FrameworkDataBean> temp2 = mgaac_YLQXZCZService.doSelectPage(mgaac, false);
					if(temp2!=null){
						for (FrameworkDataBean frameworkDataBean : temp2) {
							MGAAC_YLQXZCZDBO fb = (MGAAC_YLQXZCZDBO)frameworkDataBean;
							MGAAC_YLQXZCZPVO pvo2 = new MGAAC_YLQXZCZPVO();
							BeanUtils.copyProperties(fb, pvo2);
							String bbb = fb.getBbb();
						    if(!StringUtils.isEmpty(bbb)){
						    	String[] images = bbb.split(COMMA);
						    	pvo2.setImages(images);
						    }
						    sqslist.add(pvo2);
						}
					}
					mv.addObject("ZCZLIST", sqslist);// 注册证DBO
					
				}
				// 供应商
				{
					MYY1_YYTJGYSXXDBO mgys0 = new MYY1_YYTJGYSXXDBO();
					mgys0.setPuk(mgta.getP01_gysid());
					mgys0.setP01_yyid(mgta.getP03_yyid());
					mv.addObject("GYS", myy1_YYTJGYSXXService.doRead(mgys0));
					//供应商营业执照
					
					MGA1_YYZZDBO gysyyzz = new MGA1_YYZZDBO();
					gysyyzz.setK01_gysid(mgta.getP01_gysid());
					List<FrameworkDataBean> gysyyzzlist = mga1_YYZZService.doSelectPage(gysyyzz, false);
					if(gysyyzzlist!=null){
						gysyyzz = (MGA1_YYZZDBO)gysyyzzlist.get(0);
					}
					mv.addObject("GYS_YYZZ",gysyyzz);
					//经营许可证
					MGA2_JYXKZDBO gysjyxkz = new MGA2_JYXKZDBO();
					gysjyxkz.setK01_gysid(mgta.getP01_gysid());
					List<FrameworkDataBean> gysjyxkzlist = mga2_JYXKZService.doSelectPage(gysjyxkz, false);
					if(gysjyxkzlist!=null){
						gysjyxkz = (MGA2_JYXKZDBO)gysjyxkzlist.get(0);
					}
					mv.addObject("GYS_JYXKZ",gysjyxkz);
					//税务登记证
					MGA3_GSSWDJZDBO gysswdjz = new MGA3_GSSWDJZDBO();
					gysswdjz.setK01_gysid(mgta.getP01_gysid());
					List<FrameworkDataBean> gysswdjzlist = mga3_GSSWDJZService.doSelectPage(gysswdjz, false);
					if(gysswdjzlist!=null){
						gysswdjz = (MGA3_GSSWDJZDBO)gysswdjzlist.get(0);
					}
					mv.addObject("GYS_SWDJZ", gysswdjz);
					//承诺书
					MGA6_SHFWCNSDBO gysshfwcns = new MGA6_SHFWCNSDBO();
					gysshfwcns.setK01_gysid(mgta.getP01_gysid());
					gysshfwcns.setF10(mgta.getP03_yyid());
					List<FrameworkDataBean> gyscnslist = mga6_SHFWCNSService.doSelectPage(gysshfwcns, false);
					mv.addObject("GYS_CNS",gyscnslist);
				}
				// 医院
				mv.addObject("YYMC", super.getLoginerBean().getCompanyNameCN());
	}
}
