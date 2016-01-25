package com.aek56.qt.gys.MGYS2;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.bean.RESTResultBean;
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

import com.aek56.atm.company.MGYS2_GYSTJCSXX.MGYS2_GYSTJCSXXDBO;
import com.aek56.atm.company.MGYS2_GYSTJCSXX.MGYS2_GYSTJCSXXService;
import com.aek56.atm.credentials.MGA8C_YYZZ.MGA8C_YYZZDBO;
import com.aek56.atm.credentials.MGA8C_YYZZ.MGA8C_YYZZService;
import com.aek56.atm.credentials.MGA9C_SCXKZ.MGA9C_SCXKZDBO;
import com.aek56.atm.credentials.MGA9C_SCXKZ.MGA9C_SCXKZService;

/**
 * 供应商厂家管理
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Controller
public class MGYS2Controller extends MyControllerSupport {
    private static final Logger logger = LoggerFactory
            .getLogger(MGYS2Controller.class);

    /**
     * 供应商添加厂商表
     */
    @Resource
    protected MGYS2_GYSTJCSXXService MGYS2_GYSTJCSXXService_;
    
    /**
     * 营业执照
     */
    @Resource
    protected MGA8C_YYZZService MGA8C_YYZZService_;
    
    /**
     * 生产许可证
     */
    @Resource
    protected MGA9C_SCXKZService MGA9C_SCXKZService_;
    
    /**
     * 事务类
     */
    @Resource
    protected MGYS2Business MGYS2Business_;

    /**
     * 获取我的厂家列表
     * 
     * @param pageCurrent
     * @param pageLimit
     * @param param
     * @return
     * @Author:zhengxw
     * @Date:2014-10-20
     */
    @RequestMapping(value = "/3301000", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m3301000post(MGYS2_GYSTJCSXXDBO param, String pageCurrent,
            String pageLimit) {

        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MCS0Controller#m3111000post()!");
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
        
        this.MGYS2_GYSTJCSXXService_.doSelectPage(super.pageModel, false);
        
        JSONObject jsonRs = new JSONObject();
        jsonRs.put("list", super.pageModel.getPageListData());
        jsonRs.put("pageCount", super.pageModel.getResultCount());

        if (logger.isDebugEnabled()) {
            logger.debug("pageModel:{}", super.pageModel);
            logger.debug("Exit MCS0Controller#m3111000post()!");
        }
        RESTResultBean rs =  new RESTResultBean();
        rs.setResult(jsonRs);
        return rs;
    }
    
    /**
     * 跳转到添加或者修改页面
     *
     * @return
     * @Author:zhengxw
     * @Date:2014-12-5
     */
    @RequestMapping(value = "/33010001", method = RequestMethod.GET)
    public ModelAndView m33010001post(String pukid) {
        MGYS2_GYSTJCSXXDBO cjxx = new MGYS2_GYSTJCSXXDBO();
        MGA8C_YYZZDBO yyzz = new MGA8C_YYZZDBO();
        MGA9C_SCXKZDBO scxkz = new MGA9C_SCXKZDBO();
        if (!StringUtils.isEmpty(pukid)) {
        	String gysid = this.getCompanyId();
            MGYS2_GYSTJCSXXDBO param = new MGYS2_GYSTJCSXXDBO();
            param.setPuk(pukid);
            cjxx = (MGYS2_GYSTJCSXXDBO) this.MGYS2_GYSTJCSXXService_.doRead(param);
            
            MGA8C_YYZZDBO mga8cParam = new MGA8C_YYZZDBO();
            mga8cParam.setK01_gysid(gysid);
            mga8cParam.setK02_sccj_qyid(pukid);
            List<FrameworkDataBean> yyzzList = this.MGA8C_YYZZService_.doSelectPage(mga8cParam, false);
            yyzz = (MGA8C_YYZZDBO) (yyzzList == null || yyzzList.size() < 1 ? new MGA8C_YYZZDBO() : yyzzList.get(0));
            
            MGA9C_SCXKZDBO mga9cParam = new MGA9C_SCXKZDBO();
            mga9cParam.setK01_gysid(gysid);
            mga9cParam.setK02_sccj_qyid(pukid);
            List<FrameworkDataBean> jyxkzList = this.MGA9C_SCXKZService_.doSelectPage(mga9cParam, false);
            scxkz = (MGA9C_SCXKZDBO) (jyxkzList == null || jyxkzList.size() < 1 ? new MGA9C_SCXKZDBO() : jyxkzList.get(0));
        }
        
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("qt/gys/MGAAC/factory_add");
        view.addObject("sccj", cjxx);
        view.addObject("yyzz", yyzz);
        view.addObject("scxkz", scxkz);
        return view;
    }
    
    /**
     * 保存添加或者修改信息
     *
     * @param param
     * @return
     * @Author:zhengxw
     * @Date:2014-12-5
     */
    @RequestMapping(value = "/33010002", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m31105002post(MGYS2_GYSTJCSXXDBO param) {
        param.setN03_logogjz(param.getF01_qyqc().substring(0, 1));
        this.MGYS2Business_.saveFactory(param);
        RESTResultBean rs =  new RESTResultBean();
        rs.setMessage(StringUtils.isEmpty(param.getFb1()) || StringUtils.isEmpty(param.getFb2()) ? "330100021" : "330100023");
        JSONObject url = new JSONObject();
        url.put("url", "/3301000");
        rs.setResult(url);
        return rs;
    }
    
    /**
     * 删除
     *
     * @param param
     * @return
     * @Author:zhengxw
     * @Date:2014-12-5
     */
    @RequestMapping(value = "/33010003/{pukid}", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m31105003post(@PathVariable String pukid) {
    	MGYS2_GYSTJCSXXDBO param = new MGYS2_GYSTJCSXXDBO();
    	param.setPuk(pukid);
        int rsNum = this.MGYS2_GYSTJCSXXService_.doDelete(param);
        RESTResultBean rs =  new RESTResultBean();
        rs.setMessage(rsNum > 0 ? "330100031" : "330100032");
        rs.setResult(super.pageModel.getPageListData());
        return rs;
    }
}
