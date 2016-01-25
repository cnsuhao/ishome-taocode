package com.aek56.qt.gys.MGAAC;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.helper.FilePathHelper;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
import com.aek56.atm.credentials.MGA8C_YYZZ.MGA8C_YYZZDBO;
import com.aek56.atm.credentials.MGA8C_YYZZ.MGA8C_YYZZService;
import com.aek56.atm.credentials.MGA9C_SCXKZ.MGA9C_SCXKZDBO;
import com.aek56.atm.credentials.MGA9C_SCXKZ.MGA9C_SCXKZService;
import com.aek56.atm.credentials.MGAAC_YLQXZCZ.MGAAC_YLQXZCZDBO;
import com.aek56.atm.credentials.MGAAC_YLQXZCZ.MGAAC_YLQXZCZService;
import com.aek56.atm.master.MD8_CPZCZXX.MD8_CPZCZXXDBO;
import com.aek56.atm.master.MD8_CPZCZXX.MD8_CPZCZXXService;

/**
 * 供应商注册证管理
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Controller
public class MGAACController extends MyControllerSupport {
    private static final Logger logger = LoggerFactory
            .getLogger(MGAACController.class);
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
    
    /**
     * 注册证主数据
     */
    @Resource
    private MD8_CPZCZXXService MD8_CPZCZXXService_;
    
    /**
     * 厂家营业执照
     */
    @Resource
    protected MGA8C_YYZZService MGA8C_YYZZService_;
    
    /**
     * 厂家生产许可证
     */
    @Resource
    protected MGA9C_SCXKZService MGA9C_SCXKZService_;
    
    /**
     * 事务类
     */
    @Resource
    private MGAACBusiness MGAACBusiness_;
    
    /**
     * 跳转到证件管理（main.jsp）页面
     *
     * @return
     * @Author:zhengxw
     * @Date:2014-12-3
     */
    @RequestMapping(value = "/3111000", method = RequestMethod.POST)
    public ModelAndView m3111000post() {
        // 查询供应商营业执照
        MGA1_YYZZDBO mga1Param = new MGA1_YYZZDBO();
        mga1Param.setK01_gysid(this.getCompanyId());
        List<FrameworkDataBean> mga1List = this.MGA1_YYZZService_.doSelectPage(mga1Param, false);
        MGA1_YYZZDBO mga1Rs = (MGA1_YYZZDBO) (mga1List == null || mga1List.size() < 1 ? new MGA1_YYZZDBO() : mga1List.get(0));

        // 查询供应商经营许可证
        MGA2_JYXKZDBO mga2Param = new MGA2_JYXKZDBO();
        mga2Param.setK01_gysid(this.getCompanyId());
        List<FrameworkDataBean> mga2List = this.MGA2_JYXKZService_.doSelectPage(mga2Param, false);
        MGA2_JYXKZDBO mga2Rs = (MGA2_JYXKZDBO) (mga2List == null || mga2List.size() < 1 ? new MGA2_JYXKZDBO() : mga2List.get(0));
        
        // 查询供应商税务登记证
        MGA3_GSSWDJZDBO mga3Param = new MGA3_GSSWDJZDBO();
        mga3Param.setK01_gysid(this.getCompanyId());
        List<FrameworkDataBean> mga3List = this.MGA3_GSSWDJZService_.doSelectPage(mga3Param, false);
        MGA3_GSSWDJZDBO mga3Rs = (MGA3_GSSWDJZDBO) (mga3List == null || mga3List.size() < 1 ? new MGA3_GSSWDJZDBO() : mga3List.get(0));
        
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("qt/gys/MGAAC/main");
        view.addObject("yyzz", mga1Rs);
        view.addObject("jyxkz", mga2Rs);
        view.addObject("swdjz", mga3Rs);
        return view;
    }
    
    @RequestMapping(value = "/3111000", method = RequestMethod.GET)
    public ModelAndView m3111000get() {
        // 查询供应商营业执照
        MGA1_YYZZDBO mga1Param = new MGA1_YYZZDBO();
        mga1Param.setK01_gysid(this.getCompanyId());
        List<FrameworkDataBean> mga1List = this.MGA1_YYZZService_.doSelectPage(mga1Param, false);
        MGA1_YYZZDBO mga1Rs = (MGA1_YYZZDBO) (mga1List == null || mga1List.size() < 1 ? new MGA1_YYZZDBO() : mga1List.get(0));

        // 查询供应商经营许可证
        MGA2_JYXKZDBO mga2Param = new MGA2_JYXKZDBO();
        mga2Param.setK01_gysid(this.getCompanyId());
        List<FrameworkDataBean> mga2List = this.MGA2_JYXKZService_.doSelectPage(mga2Param, false);
        MGA2_JYXKZDBO mga2Rs = (MGA2_JYXKZDBO) (mga2List == null || mga2List.size() < 1 ? new MGA2_JYXKZDBO() : mga2List.get(0));
        
        // 查询供应商税务登记证
        MGA3_GSSWDJZDBO mga3Param = new MGA3_GSSWDJZDBO();
        mga3Param.setK01_gysid(this.getCompanyId());
        List<FrameworkDataBean> mga3List = this.MGA3_GSSWDJZService_.doSelectPage(mga3Param, false);
        MGA3_GSSWDJZDBO mga3Rs = (MGA3_GSSWDJZDBO) (mga3List == null || mga3List.size() < 1 ? new MGA3_GSSWDJZDBO() : mga3List.get(0));
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("qt/gys/MGAAC/main");
        view.addObject("yyzz", mga1Rs);
        view.addObject("jyxkz", mga2Rs);
        view.addObject("swdjz", mga3Rs);
        return view;
    }

    /**
     * 获取我的注册证列表
     * 
     * @param pageCurrent
     * @param pageLimit
     * @param param
     * @return
     * @Author:zhengxw
     * @Date:2014-10-20
     */
    @RequestMapping(value = "/311100000", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m311100000post(MGAAC_YLQXZCZDBO param, String pageCurrent,
            String pageLimit) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MGAACController#m311100000post()!");
            logger.debug("param:{}, pageCurrent:{}, pageLimit:{}", param,
                    pageCurrent, pageLimit);
        }

        // 设置分页参数
        int pageNo = StringUtils.isEmpty(pageCurrent) ? 1 : Integer
                .parseInt(pageCurrent);
        int pageSize = StringUtils.isEmpty(pageLimit) ? DEFAULT_PAGELIMIT
                : Integer.parseInt(pageLimit);
        super.pageModel.setPageCurrent(pageNo);
        super.pageModel.setPageLimit(pageSize);
        param.setP01_gysid(this.getCompanyId());
        super.pageModel.setFormParamBean(param);
        
        this.MGAACBusiness_.doSelectPage(super.pageModel, false);
        
        JSONObject jsonRs = new JSONObject();
        jsonRs.put("list", super.pageModel.getPageListData());
        jsonRs.put("pageCount", super.pageModel.getResultCount());

        if (logger.isDebugEnabled()) {
            logger.debug("pageModel:{}", super.pageModel);
            logger.debug("Exit MGAACController#m311100000post()!");
        }
        RESTResultBean rs =  new RESTResultBean();
        rs.setResult(jsonRs);
        return rs;
    }
    
    /**
     * 跳转注册证添加页面
     *
     * @param type 请求类型 0：编辑，1：详情，2：换证
     * @param pukid
     * @return
     * @Author:zhengxw
     * @Date:2014-12-5
     */
    @RequestMapping(value = "/311100001/{type}", method = RequestMethod.GET)
    public ModelAndView m311100001post(@PathVariable String type, String pukid) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MGAACController#m311100001post()!");
            logger.debug("type:{}, pukid:{}", type, pukid);
        }
    	MGAAC_YLQXZCZDBO rs = new MGAAC_YLQXZCZDBO();
    	if (!StringUtils.isEmpty(pukid)) {
    		MGAAC_YLQXZCZDBO param = new MGAAC_YLQXZCZDBO();
    		param.setPuk(pukid);
    		param.setP01_gysid(this.getCompanyId());
    		rs = (MGAAC_YLQXZCZDBO) this.MGAAC_YLQXZCZService_.doRead(param);
    	}
    	
    	if (logger.isDebugEnabled()) {
            logger.debug("Exit MGAACController#m311100001post()!");
            logger.debug("rs:{}", rs);
        }
        MyModelAndViewSupport view = this.getModelAndView();
        String viewName = "qt/gys/MGAAC/registerCert_add"; 
        if (ONE.equals(type)) {
            viewName = "qt/gys/MGAAC/registerCert_view";
        } else if (TWO.equals(type)) {
            viewName = "qt/gys/MGAAC/changeCert";
        }
        view.setViewName(viewName);
        view.addObject(USER_DATA, rs);
        return view;
    }
    
    /**
     * 保存注册证
     *
     * @param param
     * @return
     * @Author:zhengxw
     * @Date:2014-12-5
     */
    @RequestMapping(value = "/311100002", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m311100002post(MGAAC_YLQXZCZDBO param, String isErrorCov, String oldPuk) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MGAACController#m311100002post()!");
            logger.debug("param:{}, isErrorCov:{}", param, isErrorCov);
        }
        String bbb = param.getBbb();
        if (!StringUtils.isEmpty(bbb)) {
            param.setBbb(bbb.substring(0, bbb.length() - 1));
        }
        if (StringUtils.isEmpty(param.getF12_yqbz())) {
            param.setF12_yqbz(ONE);
        }
        // 纠错
        if (ONE.equals(isErrorCov) && !StringUtils.isEmpty(param.getPuk())) {
        	this.MGAACBusiness_.errorRecoveryOrUpdate(param);
        } else { // 添加或者编辑注册证或者换证
            this.MGAACBusiness_.optZcz(param, oldPuk);
        }
        
        if (logger.isDebugEnabled()) {
            logger.debug("Exit MGAACController#m311100002post()!");
        }
        RESTResultBean rs =  new RESTResultBean();
        rs.setMessage("3111000021");
        JSONObject url = new JSONObject();
        url.put("url", "/311100000");
        rs.setResult(url);
        return rs;
    }
    
    /**
     * 删除
     *
     * @param pukid
     * @return
     * @Author:zhengxw
     * @Date:2014-12-7
     */
    @RequestMapping(value = "/311100003/{pukid}", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m311100003post(@PathVariable String pukid) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MGAACController#m311100002post()!");
            logger.debug("pukid:{}", pukid);
        }
        
        MGAAC_YLQXZCZDBO param = new MGAAC_YLQXZCZDBO();
        param.setPuk(pukid);
        param.setP01_gysid(this.getCompanyId());
        int rsNum = this.MGAAC_YLQXZCZService_.doDelete(param);
        
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MGAACController#m311100002post()!");
            logger.debug("pukid:{}", pukid);
        }
        RESTResultBean rs =  new RESTResultBean();
        rs.setMessage(rsNum > 0 ? "3111000031" : "3111000032");
        return rs;
    }
    
    /**
     * 获取注册证详情
     *
     * @param pukid
     * @return
     * @Author:zhengxw
     * @Date:2014-12-10
     */
    @RequestMapping(value = "/311100004", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m311100004post(String pukid) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MGAACController#m311100002post()!");
            logger.debug("pukid:{}", pukid);
        }
        
        MD8_CPZCZXXDBO param = new MD8_CPZCZXXDBO();
        param.setPuk(pukid);
        MD8_CPZCZXXDBO dm8Rs = (MD8_CPZCZXXDBO) this.MD8_CPZCZXXService_.doRead(param);
        
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MGAACController#m311100002post()!");
            logger.debug("pukid:{}", pukid);
        }
        RESTResultBean rs =  new RESTResultBean();
        rs.setResult(dm8Rs);
        return rs;
    }
    
    /**
     * 照片墙通用方法
     *
     * @param type 0:营业执照，1：经营许可证，2：税务登记证，3：授权书，4：委托书，5：承诺书，
     *              6：注册证，7：厂家营业执照，8：厂家生产许可证
     * @param pukid
     * @return
     * @Author:zhengxw
     * @Date:2014-12-11
     */
    @RequestMapping(value = "/311100005/{type}", method = RequestMethod.GET)
    public ModelAndView m311100005post(@PathVariable String type, String pukid) {
        int typeInt = Integer.parseInt(type);
        List<FrameworkDataBean> rsList = new ArrayList<FrameworkDataBean>();
        FrameworkDataBean rs = new FrameworkDataBean();
        String typeName = "";
        switch (typeInt) {
            case 0 :
                MGA1_YYZZDBO yyzzParam = new MGA1_YYZZDBO();
                yyzzParam.setPuk(pukid);
                rs = this.MGA1_YYZZService_.doRead(yyzzParam);
            //    rs.setEb1(".jpg");
                typeName = "营业执照";
                break;
            case 2 :
                MGA3_GSSWDJZDBO swdjParam = new MGA3_GSSWDJZDBO();
                swdjParam.setPuk(pukid);
                rs = this.MGA3_GSSWDJZService_.doRead(swdjParam);
            //    rs.setEb1(".jpg");
                typeName = "税务登记证";
                break;
            case 3 : 
                MGA4_JXSQSDBO sqsParam = new MGA4_JXSQSDBO();
                sqsParam.setPuk(pukid);
                rs = this.MGA4_JXSQSService_.doRead(sqsParam);
             //   rs.setEb1(".jpg");
                typeName = "授权书";
                break;
            case 4 :
                MGA5_XSRYWTSDBO wtsParam = new MGA5_XSRYWTSDBO();
                wtsParam.setPuk(pukid);
                MGA5_XSRYWTSDBO mga5Rs = (MGA5_XSRYWTSDBO) this.MGA5_XSRYWTSService_.doRead(wtsParam);
                mga5Rs.setEb1(".jpg");
                typeName = "委托书";
                rsList.add(mga5Rs);
                
                MGA5_XSRYWTSDBO wtsSfz = new MGA5_XSRYWTSDBO();
                wtsSfz.setBbb(mga5Rs.getF13());
                wtsSfz.setEb1(".jpg");
                rsList.add(wtsSfz);
                break;
            case 5 :
                MGA6_SHFWCNSDBO cnsParam = new MGA6_SHFWCNSDBO();
                cnsParam.setPuk(pukid);
                rs = this.MGA6_SHFWCNSService_.doRead(cnsParam);
                typeName = "承诺书";
           //     rs.setEb1(".jpg");
                break;
            case 6 :
                MGAAC_YLQXZCZDBO zczParam = new MGAAC_YLQXZCZDBO();
                zczParam.setPuk(pukid);
                MGAAC_YLQXZCZDBO zczRs = (MGAAC_YLQXZCZDBO) this.MGAAC_YLQXZCZService_.doRead(zczParam);
                typeName = zczRs.getF01_zczzwmc();
                // 解析注册证图片编码
                if (!StringUtils.isEmpty(zczRs.getBbb())) {
                    String[] imgPaths = zczRs.getBbb().split(",");
                    for (int i = 0; i < imgPaths.length; i++) {
                        MGA1_YYZZDBO tmp = new MGA1_YYZZDBO();
                        String imgPath = imgPaths[i];
                        if (!StringUtils.isEmpty(imgPath)) {
                            String [] imgInfo = FilePathHelper.getFilePath(imgPath, true);
                            tmp.setBbb(imgPath);
                            tmp.setF04_yxksrq(zczRs.getF09_yxksrq());
                            tmp.setF05_yxzzrq(zczRs.getF10_yxzzrq());
                            tmp.setEb1(imgInfo[3]);
                            rsList.add(tmp);
                        }
                    }
                }
                break;
            case 7 :
                MGA8C_YYZZDBO cjyyzzParam = new MGA8C_YYZZDBO();
                cjyyzzParam.setK02_sccj_qyid(pukid);
                List<FrameworkDataBean> list = this.MGA8C_YYZZService_.doSelectPage(cjyyzzParam, false);
                typeName = "厂家证书";
                if (null != list && list.size() > 0) {
                    MGA8C_YYZZDBO mga8cRs = (MGA8C_YYZZDBO) list.get(0);
                    mga8cRs.setEb1(".jpg");
                    rsList.add(mga8cRs);
                }
                
                MGA9C_SCXKZDBO mga9cParam = new MGA9C_SCXKZDBO();
                mga9cParam.setK02_sccj_qyid(pukid);
                List<FrameworkDataBean> list2 = this.MGA9C_SCXKZService_.doSelectPage(mga9cParam, false);
                if (null != list2 && list2.size() > 0) {
                    MGA9C_SCXKZDBO mga9cRs = (MGA9C_SCXKZDBO) list2.get(0);
                    mga9cRs.setEb1(".jpg");
                    rsList.add(mga9cRs);
                }
                break;
            case 1 :
                MGA2_JYXKZDBO jyxkzParam = new MGA2_JYXKZDBO();
                jyxkzParam.setPuk(pukid);
                rs = this.MGA2_JYXKZService_.doRead(jyxkzParam);
                typeName = "经营许可证";
            //    rs.setEb1(".jpg");
                break;
        }
        if (typeInt != 6 && typeInt != 7 && typeInt != 4) {
            if (null != rs && !StringUtils.isEmpty(rs.getPuk()) && !StringUtils.isEmpty(rs.getBbb())) {
                rs.setEb1(".jpg");
                rsList.add(rs);
            }
        }
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("qt/pic");
        view.addObject("typeName", typeName);
        view.addObject("gysxx", this.getLoginerBean().getCompanyNameCN());
        view.addObject("imgpaths", rsList);
        return view;
    }
}
