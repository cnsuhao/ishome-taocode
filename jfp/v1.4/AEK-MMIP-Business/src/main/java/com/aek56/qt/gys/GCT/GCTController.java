package com.aek56.qt.gys.GCT;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.jfpc.framework.bean.FrameworkDataBean;
import org.jfpc.framework.bean.RESTResultBean;
import org.jfpc.framework.helper.DateHelper;
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

import com.aek56.atm.credentials.MGAAC_YLQXZCZ.MGAAC_YLQXZCZDBO;
import com.aek56.atm.credentials.MGAAC_YLQXZCZ.MGAAC_YLQXZCZService;
import com.aek56.atm.credentials.MGTA_CJYLQXZCZ.MGTA_CJYLQXZCZDBO;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXDBO;
import com.aek56.atm.master.MD3_YYXX.MD3_YYXXService;
import com.aek56.qt.credentials.CredentialsBusiness;

/**
 * 供应商推送证件
 *
 * @Author:zhengxw
 * @Date:2014-12-8
 */
@Controller
public class GCTController extends MyControllerSupport{
    private static final Logger logger = LoggerFactory
            .getLogger(GCTController.class);
    /**
     * 供应商推送证件事务类
     */
    @Resource
    protected GCTBusiness GCTBusiness_;
    
    /**
     * 跳转到推送页面
     */
    @Resource
    protected CredentialsBusiness CredentialsBusiness_;
    
    /**
     * 医院主数据信息
     */
    @Resource
    protected MD3_YYXXService MD3_YYXXService_;
    
    /**
     * 注册证表
     */
    @Resource
    protected MGAAC_YLQXZCZService MGAAC_YLQXZCZService_;
    
    /**
     * 跳转推送页面
     *
     * @param yyid
     * @return
     * @Author:zhengxw
     * @Date:2014-12-9
     */
    @RequestMapping(value = "/3120090/{yyid}", method = RequestMethod.GET)
    public ModelAndView m3220020get(@PathVariable String yyid) {
        // 获取我的全部注册证数量
        MGAAC_YLQXZCZDBO param1 = new MGAAC_YLQXZCZDBO();
        param1.setP01_gysid(this.getCompanyId());
        List<FrameworkDataBean> allList = this.MGAAC_YLQXZCZService_.doSelectPage(param1, false);
        int allNum = null == allList || allList.size() < 1 ? 0 : allList.size();
        
        // 获取已经推送给医院的注册证数量
        MGTA_CJYLQXZCZDBO param2 = new MGTA_CJYLQXZCZDBO();
        param2.setP01_gysid(this.getCompanyId());
        param2.setP03_yyid(yyid);
        param2.setEb1(DateHelper.currentTimeMillisCN3());
        List<FrameworkDataBean> unSentList = this.GCTBusiness_.doSelectPageUnsentZcz(param2);
        int unSentNum = null == unSentList || unSentList.size() < 1 ? 0 : unSentList.size();
        
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("qt/gys/MGAAC/push");
        view.addObject("yyid", yyid);
        view.addObject("allZczNum", allNum);
        view.addObject("alreadyPushNum", allNum - unSentNum);
        return view;
    }
    
    /**
     * 跳转到推送页面
     *
     * @return
     * @Author:zhengxw
     * @Date:2014-12-8
     */
    @RequestMapping(value = "/3120090", method = RequestMethod.POST)
    public ModelAndView m3220020post() {
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("qt/gys/MGAAC/push");
        return view;
    }
    
    /**
     * 查询待选择注册证
     *
     * @return
     * @Author:zhengxw
     * @Date:2014-12-9
     */
    @RequestMapping(value = "/31200901/{yyid}", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m32200201post(@PathVariable String yyid, String pageCurrent,
            String pageLimit) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MGA1Controller#m3110100post()!");
        }
     // 设置分页参数
        int pageNo = StringUtils.isEmpty(pageCurrent) ? 1 : Integer
                .parseInt(pageCurrent);
        int pageSize = StringUtils.isEmpty(pageLimit) ? DEFAULT_PAGELIMIT
                : Integer.parseInt(pageLimit);
        super.pageModel.setPageCurrent(pageNo);
        super.pageModel.setPageLimit(pageSize);
        MGTA_CJYLQXZCZDBO param = new MGTA_CJYLQXZCZDBO();
        param.setP01_gysid(this.getCompanyId());
        param.setP03_yyid(yyid);
        param.setEb1(DateHelper.currentTimeMillisCN3());
        super.pageModel.setFormParamBean(param);
        this.GCTBusiness_.doSelectPageUnsentZcz(super.pageModel);
        RESTResultBean rs =  new RESTResultBean();
        JSONObject rsJson = new JSONObject();
        rsJson.put("list", super.pageModel.getPageListData());
        rsJson.put("pageCount", super.pageModel.getPageCount());
        rs.setResult(rsJson);
        return rs;
    }
    
    /**
     * 推送操作
     *
     * @return
     * @Author:zhengxw
     * @Date:2014-12-9
     */
    @RequestMapping(value = "/31200902", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m32200202post(String yyid, String zczids) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MGA1Controller#m3110100post()!");
        }
        
        int rsNum = this.GCTBusiness_.sentAllZs(yyid, zczids);
        
        RESTResultBean rs =  new RESTResultBean();
        if (rsNum < 1) {
            rs.setCode("9");
            rs.setMessage("3120090202");
        } else {
            rs.setMessage("3120090201");
        }
        return rs;
    }
    
    /**
     * 获取医院信息
     * @param yyid
     * @return
     */
    @RequestMapping(value = "/31200903/{yyid}", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m32200203post(@PathVariable String yyid) {
        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MGA1Controller#m3110100post()!");
        }
        
        MD3_YYXXDBO param = new MD3_YYXXDBO();
        param.setPuk(yyid);
        MD3_YYXXDBO dbo = (MD3_YYXXDBO) this.MD3_YYXXService_.doRead(param);

        RESTResultBean rs =  new RESTResultBean();
        rs.setResult(dbo);
        return rs;
    }
}
