package com.aek56.qt.gys.WYD;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.helper.DateHelper;
import org.jfpc.framework.support.MyControllerSupport;
import org.jfpc.framework.support.MyModelAndViewSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aek56.atm.credentials.MGT1_YYZZ.MGT1_YYZZDBO;
import com.aek56.qt.credentials.CredentialsBusiness;

/**
 * 证件一览
 *
 * @Author:zhengxw
 * @Date:2014-12-8
 */
@Controller
public class WYDController extends MyControllerSupport{
    private static final Logger logger = LoggerFactory
            .getLogger(WYDController.class);
    
    /**
     * 统计操作
     */
    @Resource
    protected CredentialsBusiness CredentialsBusiness_;
    
    @RequestMapping(value = "/3220020", method = RequestMethod.GET)
    public ModelAndView m3220020get() {
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("qt/gys/MGAAC/overDue");
        return view;
    }
    
    /**
     * 跳转到过期一览界面
     *
     * @return
     * @Author:zhengxw
     * @Date:2014-12-8
     */
    @RequestMapping(value = "/3220020", method = RequestMethod.POST)
    public ModelAndView m3220020post() {
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("qt/gys/MGAAC/overDue");
        return view;
    }
    
    /**
     * 过期证书一览
     *
     * @param param
     * @param pageCurrent
     * @param pageLimit
     * @return
     * @Author:zhengxw
     * @Date:2014-12-8
     */
    @RequestMapping(value = "/32200201", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m32200201post(String pageCurrent, String pageLimit) {

        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MCS0Controller#m3111000post()!");
            logger.debug("pageCurrent:{}, pageLimit:{}", pageCurrent, pageLimit);
        }

        // 设置分页参数
        int pageNo = StringUtils.isEmpty(pageCurrent) ? 1 : Integer
                .parseInt(pageCurrent);
        int pageSize = StringUtils.isEmpty(pageLimit) ? DEFAULT_PAGELIMIT
                : Integer.parseInt(pageLimit);
        super.pageModel.setPageCurrent(pageNo);
        super.pageModel.setPageLimit(pageSize);
        MGT1_YYZZDBO param = new MGT1_YYZZDBO();
        param.setK01_gysid(this.getCompanyId());
        param.setDdd(ZERO);
        super.pageModel.setFormParamBean(param);
        
        this.CredentialsBusiness_.doSelectPageCompanyCredentialsList(super.pageModel);

        if (logger.isDebugEnabled()) {
            logger.debug("pageModel:{}", super.pageModel);
            logger.debug("Exit MCS0Controller#m3111000post()!");
        }
        RESTResultBean rs =  new RESTResultBean();
        JSONObject jsonRs = new JSONObject();
        jsonRs.put("list", super.pageModel.getPageListData());
        jsonRs.put("pageCount", super.pageModel.getResultCount());
        rs.setResult(jsonRs);
        return rs;
    }
    
    /**
     * 查看三个月内过期列表
     *
     * @param pageCurrent
     * @param pageLimit
     * @return
     * @Author:zhengxw
     * @Date:2014-12-8
     */
    @RequestMapping(value = "/32200202", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m32200202post(String pageCurrent, String pageLimit) {

        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MCS0Controller#m3111000post()!");
            logger.debug("pageCurrent:{}, pageLimit:{}", pageCurrent, pageLimit);
        }

        // 设置分页参数
        int pageNo = StringUtils.isEmpty(pageCurrent) ? 1 : Integer
                .parseInt(pageCurrent);
        int pageSize = StringUtils.isEmpty(pageLimit) ? DEFAULT_PAGELIMIT
                : Integer.parseInt(pageLimit);
        super.pageModel.setPageCurrent(pageNo);
        super.pageModel.setPageLimit(pageSize);
        MGT1_YYZZDBO param = new MGT1_YYZZDBO();
        param.setK01_gysid(this.getCompanyId());
        param.setDdd("90");
        param.setEb5(DateHelper.currentTimeMillisCN3());
        super.pageModel.setFormParamBean(param);
        
        this.CredentialsBusiness_.doSelectPageCompanyCredentialsList(super.pageModel);

        if (logger.isDebugEnabled()) {
            logger.debug("pageModel:{}", super.pageModel);
            logger.debug("Exit MCS0Controller#m3111000post()!");
        }
        RESTResultBean rs =  new RESTResultBean();
        JSONObject jsonRs = new JSONObject();
        jsonRs.put("list", super.pageModel.getPageListData());
        jsonRs.put("pageCount", super.pageModel.getResultCount());
        rs.setResult(jsonRs);
        return rs;
    }
}
