package com.aek56.qt.yy.CGTZ2;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.helper.FilePathHelper;
import org.jfpc.framework.support.MyDataBaseObjectSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.jfpc.framework.support.MyServiceSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.aek56.atm.company.MGYS0_JBXX.MGYS0_JBXXDBO;
import com.aek56.atm.company.MGYS0_JBXX.MGYS0_JBXXService;
import com.aek56.atm.company.cmp.CompanyService;
import com.aek56.atm.credentials.CGTZ_TSJL.CGTZ_TSJLDBO;
import com.aek56.atm.credentials.CGTZ_TSJL.CGTZ_TSJLService;
import com.aek56.atm.credentials.MGA1_YYZZ.MGA1_YYZZDBO;
import com.aek56.atm.credentials.MGA1_YYZZ.MGA1_YYZZService;
import com.aek56.atm.credentials.MGA2_JYXKZ.MGA2_JYXKZDBO;
import com.aek56.atm.credentials.MGA2_JYXKZ.MGA2_JYXKZService;
import com.aek56.atm.credentials.MGA3_GSSWDJZ.MGA3_GSSWDJZService;
import com.aek56.atm.credentials.MGA4_JXSQS.MGA4_JXSQSDBO;
import com.aek56.atm.credentials.MGA4_JXSQS.MGA4_JXSQSService;
import com.aek56.atm.credentials.MGA5_XSRYWTS.MGA5_XSRYWTSDBO;
import com.aek56.atm.credentials.MGA5_XSRYWTS.MGA5_XSRYWTSService;
import com.aek56.atm.credentials.MGA6_SHFWCNS.MGA6_SHFWCNSService;
import com.aek56.atm.credentials.MGA8C_YYZZ.MGA8C_YYZZDBO;
import com.aek56.atm.credentials.MGA9C_SCXKZ.MGA9C_SCXKZDBO;
import com.aek56.atm.credentials.MGAAC_YLQXZCZ.MGAAC_YLQXZCZDBO;
import com.aek56.atm.credentials.MGAAC_YLQXZCZ.MGAAC_YLQXZCZService;
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
import com.aek56.atm.credentials.MGT8_CJYYZZ.MGT8_CJYYZZDBO;
import com.aek56.atm.credentials.MGT8_CJYYZZ.MGT8_CJYYZZService;
import com.aek56.atm.credentials.MGT9_CJSCXKZ.MGT9_CJSCXKZDBO;
import com.aek56.atm.credentials.MGT9_CJSCXKZ.MGT9_CJSCXKZService;
import com.aek56.atm.credentials.MGTA_CJYLQXZCZ.MGTA_CJYLQXZCZDBO;
import com.aek56.atm.credentials.MGTA_CJYLQXZCZ.MGTA_CJYLQXZCZService;
import com.aek56.atm.master.MD2_GYSXX.MD2_GYSXXDBO;
import com.aek56.atm.master.MD2_GYSXX.MD2_GYSXXService;
import com.aek56.qt.credentials.CredentialsBusiness;
@Service
public class CGTZ2Business extends MyServiceSupport{

	@Resource
	private CGTZ_TSJLService cgtz_TSJLService;
	@Resource
	private CompanyService companyService;
	@Resource
	private MGAAC_YLQXZCZService mgaac_YLQXZCZService;
	//推送表注入BEGIN
	/**
	 * 供应商提供医院营业执照
	 */
	@Resource
	private MGT1_YYZZService mgt1_YYZZService;
	/**
	 * 供应商提供医院经营许可证
	 */
	@Resource
	private MGT2_JYXKZService mgt2_JYXKZService;
	/**
	 * 供应商提供医院工商税务登记证
	 */
	@Resource
	private MGT3_GSSWDJZService mgt3_GSSWDJZService;
	/**
	 * 供应商提供医院经销授权书
	 */
	@Resource
	private MGT4_JXSQSService mgt4_JXSQSService;
	/**
	 * 供应商提供医院销售人员委托书
	 */
	@Resource
	private MGT5_XSRYWTSService mgt5_XSRYWTSService;
	/**
	 * 供应商提供医院售后服务承诺书
	 */
	@Resource
	private MGT6_SHFWCNSService mgt6_SHFWCNSService;
	/**
	 * 供应商提供医院厂家营业执照
	 */
	@Resource
	private MGT8_CJYYZZService mgt8_CJYYZZService;
	/**
	 * 供应商提供医院厂家生产许可证
	 */
	@Resource
	private MGT9_CJSCXKZService mgt9_CJSCXKZService;
	/**
	 * 供应商提供医院厂家医疗器械注册证
	 */
	@Resource
	private MGTA_CJYLQXZCZService mgta_CJYLQXZCZService;
	
	//推送表注入END
	
	
	
	/**
	 * 推送審核
	 * @param param
	 */
	@Transactional
	public void tsshenhe(CGTZ_TSJLDBO param)
	{

		//审核通过
		if(ZERO.equals(param.getF13_shzt()))
		{	//调用接收方法
			companyService.auditingCredentialsWithManual(param.getPuk());
		}
		//更新推送记录表
		cgtz_TSJLService.doUpdate(param);
	}
	
	/**
	 * 审核时，查看注册证详情
	 */
	public MGAAC_YLQXZCZDBO  m321132105post(MGAAC_YLQXZCZDBO param){
		MGAAC_YLQXZCZDBO dbo =(MGAAC_YLQXZCZDBO)mgaac_YLQXZCZService.doRead(param);
		return dbo;
	}
	

	/**
	 * 查看我的证件图片，   已经通过医院审核的图片
	 * @param cid  供应商ID    
	 * @param zid  证件ID      营业执照、经营许可证、税务登记证、 注册证 传 推送表里的PUK也就是zid
	 * @param type 类型
	 * @return
	 */
	public void showImage3(String cid,String zid,int type,MyModelAndViewSupport mv){
		List<FrameworkDataBean> relist = new ArrayList<FrameworkDataBean>();
		FrameworkDataBean result = new MyDataBaseObjectSupport();
		String typeName ="";
		String gysxx = "";
		switch (type) {
			case 1:
					typeName = "营业执照";
					MGT1_YYZZDBO param1 = new MGT1_YYZZDBO();
					param1.setPuk(zid);
					result = mgt1_YYZZService.doRead(param1);
					gysxx = result.getFb1();
					relist.add(result);
				break;
			case 2:
					typeName = "经营许可证";
					MGT2_JYXKZDBO param2 = new MGT2_JYXKZDBO();
					param2.setPuk(zid);
					result = mgt2_JYXKZService.doRead(param2);
					gysxx = result.getFb1();
					relist.add(result);
				break;
			case 3:
					typeName = "税务登记证";
					MGT3_GSSWDJZDBO param3 = new MGT3_GSSWDJZDBO();
					param3.setPuk(zid);
					result = mgt3_GSSWDJZService.doRead(param3);
					gysxx = result.getFb1();
					relist.add(result);
				break;
			case 4:
					typeName = "经销授权书";
					MGT4_JXSQSDBO param4 = new MGT4_JXSQSDBO();
					param4.setP01_yyid(super.getCompanyId());
					param4.setK01_gysid(cid);
					relist.addAll(mgt4_JXSQSService.doSelectPage(param4, false));
					gysxx=relist.get(0).getFb1();
				break;
			case 5:
					typeName = "人员委托书";
					MGT5_XSRYWTSDBO param5 = new MGT5_XSRYWTSDBO();
					param5.setP01_yyid(super.getCompanyId());
					param5.setK01_gysid(cid);
					List<FrameworkDataBean> temp5 =mgt5_XSRYWTSService.doSelectPage(param5, false);
					gysxx=temp5.get(0).getFb1();
					for (FrameworkDataBean fb : temp5) {
						//员工照片
						MGT1_YYZZDBO tmp = new MGT1_YYZZDBO();
						MGT5_XSRYWTSDBO mgt5 = (MGT5_XSRYWTSDBO) fb;
						tmp.setBbb(mgt5.getF13());
						tmp.setF04_yxksrq(mgt5.getF04_yxksrq());
						tmp.setF05_yxzzrq(mgt5.getF05_yxzzrq());
						relist.add(fb);
						relist.add(tmp);
					}
				break;
			case 6:
					typeName = "售后服务承诺书";
					MGT6_SHFWCNSDBO param6 = new MGT6_SHFWCNSDBO();
					param6.setP01_yyid(super.getCompanyId());
					param6.setK01_gysid(cid);
					relist.addAll(mgt6_SHFWCNSService.doSelectPage(param6, false));
					gysxx = relist.get(0).getFb1();
				break;
			case 7:
					typeName = "厂家营业执照";
					MGT8_CJYYZZDBO param7 = new MGT8_CJYYZZDBO();
					param7.setP01_yyid(super.getCompanyId());
					param7.setK01_gysid(cid);
					relist.addAll(mgt8_CJYYZZService.doSelectPage(param7, false));
					gysxx = relist.get(0).getFb1();
				break;
			case 8:
					typeName = "厂家生产许可证";
					MGT9_CJSCXKZDBO param8 = new MGT9_CJSCXKZDBO();
					param8.setP01_yyid(super.getCompanyId());
					param8.setK01_gysid(cid);
					relist.addAll(mgt9_CJSCXKZService.doSelectPage(param8, false));
					gysxx = relist.get(0).getFb1();
				break;
			case 9:
					MGTA_CJYLQXZCZDBO param9 = new MGTA_CJYLQXZCZDBO();
					param9.setPuk(zid);
					MGTA_CJYLQXZCZDBO tmp9=(MGTA_CJYLQXZCZDBO)mgta_CJYLQXZCZService.doRead(param9);
					gysxx = tmp9.getFb1();
					 if (!StringUtils.isEmpty(tmp9.getBbb())) {
	                    String[] imgPaths = tmp9.getBbb().split(",");
	                    for (int i = 0; i < imgPaths.length; i++) {
	                    	MGT1_YYZZDBO tmp = new MGT1_YYZZDBO();
	                        String imgPath = imgPaths[i];
	                        if (!StringUtils.isEmpty(imgPath)) {
	                            String [] imgInfo = FilePathHelper.getFilePath(imgPath, true);
	                            tmp.setBbb(imgPath);
	                            tmp.setEb1(imgInfo[3]);
	                            tmp.setF04_yxksrq(tmp9.getF09_yxksrq());
	                            tmp.setF05_yxzzrq(tmp9.getF10_yxzzrq());
	                            relist.add(tmp);
	                        }
	                    }
					}
					typeName = tmp9.getF01_zczzwmc();
				break;
		}
        mv.addObject("gysxx", gysxx);
        mv.addObject("typeName", typeName);
        mv.addObject("imgpaths", relist);
	}
	/**
     * 供应商营业执照
     */
    @Resource
    protected MGA1_YYZZService MGA1_YYZZService_;
    
    /**
     * 经营许可证
     */
    @Resource
    protected MGA2_JYXKZService MGA2_JYXKZService_;
    
    /**
     * 税务登记证
     */
    @Resource
    protected MGA3_GSSWDJZService MGA3_GSSWDJZService_;

    /**
     * 注册证表
     */
    @Resource
    private MGAAC_YLQXZCZService MGAAC_YLQXZCZService_;
    
    /**
     * 授权书
     */
    @Resource
    protected MGA4_JXSQSService MGA4_JXSQSService_;
    
    /**
     * 委托书
     */
    @Resource
    protected MGA5_XSRYWTSService MGA5_XSRYWTSService_;
    
    /**
     * 承诺书
     */
    @Resource
    protected MGA6_SHFWCNSService MGA6_SHFWCNSService_;
    @Resource
    protected MD2_GYSXXService MD2_GYSXXService_;
    @Resource
    protected MGYS0_JBXXService MGYS0_JBXXService_;
    @Resource
    protected CredentialsBusiness CredentialsBusiness_;
    
    
	/**
     * 查看供应商图片
     * @param cid  供应商ID    
     * @param zid  证件ID      营业执照、经营许可证、税务登记证、 注册证 传 推送表里的PUK也就是zid
     * @param type 类型
     * @return
     */
    public void showImage2(String cid ,int type, String tsbId, String zczId, MyModelAndViewSupport mv){
        CGTZ_TSJLDBO cgtzParam = new CGTZ_TSJLDBO();
        cgtzParam.setPuk(tsbId);
        CGTZ_TSJLDBO cgtzRs = (CGTZ_TSJLDBO) this.cgtz_TSJLService.doRead(cgtzParam);
        
        List<FrameworkDataBean> relist = new ArrayList<FrameworkDataBean>();
        FrameworkDataBean result = new MyDataBaseObjectSupport();
        String typeName ="";
        
        // 查询供应商信息
        String gysxx = "";
        MD2_GYSXXDBO md2Param = new MD2_GYSXXDBO();
        md2Param.setPuk(cid);
        MD2_GYSXXDBO md2Rs =  (MD2_GYSXXDBO) this.MD2_GYSXXService_.doRead(md2Param);
        if (null == md2Rs || StringUtils.isEmpty(md2Rs)) {
            MGYS0_JBXXDBO mgys0Param = new MGYS0_JBXXDBO();
            mgys0Param.setPuk(cid);
            gysxx = ((MGYS0_JBXXDBO) MGYS0_JBXXService_.doRead(mgys0Param)).getF01_qyqc();
        } else {
            gysxx = md2Rs.getF01_qyqc();
        }
        
        switch (type) {
            case 1:
                    typeName = "营业执照";
                    MGA1_YYZZDBO param1 = new MGA1_YYZZDBO();
                    param1.setPuk(cgtzRs.getF01_yyzz());
                    result = this.MGA1_YYZZService_.doRead(param1);
                    relist.add(result);
                break;
            case 2:
                    typeName = "经营许可证";
                    MGA2_JYXKZDBO param2 = new MGA2_JYXKZDBO();
                    param2.setPuk(cgtzRs.getF02_jyxkz());
                    result = this.MGA2_JYXKZService_.doRead(param2);
                    relist.add(result);
                break;
            case 3:
                    typeName = "税务登记证";
                    MGT3_GSSWDJZDBO param3 = new MGT3_GSSWDJZDBO();
                    param3.setPuk(cgtzRs.getF03_gsswdjz());
                    result = this.MGA3_GSSWDJZService_.doRead(param3);
                    relist.add(result);
                break;
            case 4:
                    typeName = "经销授权书";
                    MGA4_JXSQSDBO param4 = new MGA4_JXSQSDBO();
                    param4.setK01_gysid(super.getCompanyId());
                    param4.setPuk(cgtzRs.getF04_jxsqs());
                    relist.addAll(this.CredentialsBusiness_.getSqsByIds(param4));
                break;
            case 5:
                    typeName = "人员委托书";
                    MGA5_XSRYWTSDBO param5 = new MGA5_XSRYWTSDBO();
                    param5.setK01_gysid(cid);
                    param5.setF11(this.getCompanyId());
                    List<FrameworkDataBean> list5 = this.MGA5_XSRYWTSService_.doSelectPage(param5, false);
                    if (null != list5 && list5.size() > 0) {
                        MGA5_XSRYWTSDBO zs = (MGA5_XSRYWTSDBO) list5.get(0);
                        relist.add(zs);
                        
                        MGA5_XSRYWTSDBO sfz = new MGA5_XSRYWTSDBO();
                        BeanUtils.copyProperties(zs, sfz);
                        relist.add(list5.get(0));
                        relist.add(sfz);
                    }
                break;
            case 6:
                    typeName = "售后服务承诺书";
                    MGA5_XSRYWTSDBO param6 = new MGA5_XSRYWTSDBO();
                    param6.setF10(this.getCompanyId());
                    param6.setK01_gysid(cid);
                    relist.addAll(this.MGA5_XSRYWTSService_.doSelectPage(param6, false));
                    gysxx = relist.get(0).getFb1();
                break;
            case 7:
                    typeName = "厂家营业执照";
                    MGA8C_YYZZDBO param7 = new MGA8C_YYZZDBO();
                    param7.setPuk(cgtzRs.getF08_cjyyzz());
                    param7.setK01_gysid(cid);
                    relist.addAll(this.CredentialsBusiness_.getCjyyzzByIds(param7));
                break;
            case 8:
                    typeName = "厂家生产许可证";
                    MGA9C_SCXKZDBO param8 = new MGA9C_SCXKZDBO();
                    param8.setPuk(cgtzRs.getF09_cjscxkz());
                    param8.setK01_gysid(cid);
                    relist.addAll(this.CredentialsBusiness_.getCjscxkzByIds(param8));
                break;
            case 9:
                    MGAAC_YLQXZCZDBO param9 = new MGAAC_YLQXZCZDBO();
                    param9.setPuk(zczId);
                    MGAAC_YLQXZCZDBO tmp9= (MGAAC_YLQXZCZDBO)this.mgaac_YLQXZCZService.doRead(param9);
                    typeName = tmp9.getF01_zczzwmc();
                     if (!StringUtils.isEmpty(tmp9.getBbb())) {
                        String[] imgPaths = tmp9.getBbb().split(",");
                        for (int i = 0; i < imgPaths.length; i++) {
                            MGT1_YYZZDBO tmp = new MGT1_YYZZDBO();
                            String imgPath = imgPaths[i];
                            if (!StringUtils.isEmpty(imgPath)) {
                                String [] imgInfo = FilePathHelper.getFilePath(imgPath, true);
                                tmp.setBbb(imgPath);
                                tmp.setEb1(imgInfo[3]);
                                tmp.setF04_yxksrq(tmp9.getF09_yxksrq());
                                tmp.setF05_yxzzrq(tmp9.getF10_yxzzrq());
                                relist.add(tmp);
                            }
                        }
                    }
                break;
        }
        if (type != 9) {
            for (int i = 0; i < relist.size(); i++) {
                relist.get(i).setEb1(".jpg");
            }
        }
        mv.addObject("gysxx", gysxx);
        mv.addObject("typeName", typeName);
        mv.addObject("imgpaths", relist);
    }
    
    
    
    /**
	 * 查看我的证件图片，   已经通过医院审核的图片     单本证书查看
	 * @param zid  证件ID     推送表里的PUK
	 * @param type 类型
	 * @return
	 */
	public void showImage1(String zid,int type,MyModelAndViewSupport mv){
		List<FrameworkDataBean> relist = new ArrayList<FrameworkDataBean>();
		FrameworkDataBean result = new MyDataBaseObjectSupport();
		String typeName ="";
		String gysxx = "";
		switch (type) {
			case 1:
					typeName = "营业执照";
					MGT1_YYZZDBO param1 = new MGT1_YYZZDBO();
					param1.setPuk(zid);
					result = mgt1_YYZZService.doRead(param1);
					gysxx = result.getFb1();
					relist.add(result);
				break;
			case 2:
					typeName = "经营许可证";
					MGT2_JYXKZDBO param2 = new MGT2_JYXKZDBO();
					param2.setPuk(zid);
					result = mgt2_JYXKZService.doRead(param2);
					gysxx = result.getFb1();
					relist.add(result);
				break;
			case 3:
					typeName = "税务登记证";
					MGT3_GSSWDJZDBO param3 = new MGT3_GSSWDJZDBO();
					param3.setPuk(zid);
					result = mgt3_GSSWDJZService.doRead(param3);
					gysxx = result.getFb1();
					relist.add(result);
				break;
			case 4:
					typeName = "经销授权书";
					MGT4_JXSQSDBO param4 = new MGT4_JXSQSDBO();
					param4.setPuk(zid);
					result = mgt4_JXSQSService.doRead(param4);
					gysxx=result.getFb1();
					relist.add(result);
				break;
			case 5:
					typeName = "人员委托书";
					MGT5_XSRYWTSDBO param5 = new MGT5_XSRYWTSDBO();
					param5.setPuk(zid);
					result = mgt5_XSRYWTSService.doRead(param5);
					gysxx=result.getFb1();
					MGT1_YYZZDBO tmp = new MGT1_YYZZDBO();
					MGT5_XSRYWTSDBO mgt5 = (MGT5_XSRYWTSDBO) result;
					tmp.setBbb(mgt5.getF13());
					tmp.setF04_yxksrq(mgt5.getF04_yxksrq());
					tmp.setF05_yxzzrq(mgt5.getF05_yxzzrq());
					relist.add(result);
					relist.add(tmp);
				break;
			case 6:
					typeName = "售后服务承诺书";
					MGT6_SHFWCNSDBO param6 = new MGT6_SHFWCNSDBO();
					param6.setPuk(zid);
					result = mgt6_SHFWCNSService.doRead(param6);
					gysxx = result.getFb1();
					relist.add(result);
				break;
			case 7:
					typeName = "厂家营业执照";
					MGT8_CJYYZZDBO param7 = new MGT8_CJYYZZDBO();
					param7.setPuk(zid);
					result = mgt8_CJYYZZService.doRead(param7);
					gysxx = result.getFb1();
					relist.add(result);
				break;
			case 8:
					typeName = "厂家生产许可证";
					MGT9_CJSCXKZDBO param8 = new MGT9_CJSCXKZDBO();
					param8.setPuk(zid);
					result = mgt9_CJSCXKZService.doRead(param8);
					gysxx = result.getFb1();
					relist.add(result);
				break;
			case 9:
					MGTA_CJYLQXZCZDBO param9 = new MGTA_CJYLQXZCZDBO();
					param9.setPuk(zid);
					MGTA_CJYLQXZCZDBO tmp9=(MGTA_CJYLQXZCZDBO)mgta_CJYLQXZCZService.doRead(param9);
					gysxx = tmp9.getFb1();
					 if (!StringUtils.isEmpty(tmp9.getBbb())) {
	                    String[] imgPaths = tmp9.getBbb().split(",");
	                    for (int i = 0; i < imgPaths.length; i++) {
	                    	MGT1_YYZZDBO tmp2 = new MGT1_YYZZDBO();
	                        String imgPath = imgPaths[i];
	                        if (!StringUtils.isEmpty(imgPath)) {
	                            String [] imgInfo = FilePathHelper.getFilePath(imgPath, true);
	                            tmp2.setBbb(imgPath);
	                            tmp2.setEb1(imgInfo[3]);
	                            tmp2.setF04_yxksrq(tmp9.getF09_yxksrq());
	                            tmp2.setF05_yxzzrq(tmp9.getF10_yxzzrq());
	                            relist.add(tmp2);
	                        }
	                    }
					}
					typeName = tmp9.getF01_zczzwmc();
				break;
		}
        mv.addObject("gysxx", gysxx);
        mv.addObject("typeName", typeName);
        mv.addObject("imgpaths", relist);
	}
}
