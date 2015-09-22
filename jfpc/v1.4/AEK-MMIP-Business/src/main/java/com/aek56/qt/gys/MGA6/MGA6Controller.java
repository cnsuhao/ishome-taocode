package com.aek56.qt.gys.MGA6;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

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

import com.aek56.atm.credentials.MGA6_SHFWCNS.MGA6_SHFWCNSDBO;
import com.aek56.atm.credentials.MGA6_SHFWCNS.MGA6_SHFWCNSService;

/**
 * 供应商承诺书管理
 * 
 * @Author:zhengxw
 * @Date:2014-11-29
 */
@Controller
public class MGA6Controller extends MyControllerSupport {
    private static final Logger logger = LoggerFactory
            .getLogger(MGA6Controller.class);

    /**
     * 供应商管理承诺书
     */
    @Resource
    protected MGA6_SHFWCNSService MGA6_SHFWCNSService_;
    
    /**
     * 事务类
     */
    @Resource
    protected MGA6Business MDGBusiness_;

    /**
     * 获取我的承诺书列表
     * 
     * @return
     * @Author:zhengxw
     * @Date:2014-10-20
     */
    @RequestMapping(value = "/3110600", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m3110600post(MGA6_SHFWCNSDBO param, String pageCurrent,
            String pageLimit) {

        if (logger.isDebugEnabled()) {
            logger.debug("Enter into MGA6Controller#m3110600post()!");
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
        param.setK01_gysid(this.getCompanyId());
        super.pageModel.setFormParamBean(param);
        
        this.MGA6_SHFWCNSService_.doSelectPage(super.pageModel, false);
        
        JSONObject jsonRs = new JSONObject();
        jsonRs.put("list", super.pageModel.getPageListData());
        jsonRs.put("pageCount", super.pageModel.getResultCount());

        if (logger.isDebugEnabled()) {
            logger.debug("pageModel:{}", super.pageModel);
            logger.debug("Exit MGA6Controller#m3110600post()!");
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
    @RequestMapping(value = "/31106001", method = RequestMethod.GET)
    public ModelAndView m31106001post(String pukid) {
        MGA6_SHFWCNSDBO rs = new MGA6_SHFWCNSDBO();
        if (!StringUtils.isEmpty(pukid)) {
            MGA6_SHFWCNSDBO param = new MGA6_SHFWCNSDBO();
            param.setPuk(pukid);
            rs = (MGA6_SHFWCNSDBO) this.MGA6_SHFWCNSService_.doRead(param);
        }
        
        MyModelAndViewSupport view = this.getModelAndView();
        view.setViewName("qt/gys/MGAAC/promise_add");
        view.addObject(USER_DATA, rs);
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
    @RequestMapping(value = "/31106002", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m31106002post(MGA6_SHFWCNSDBO param) {
        int rsNum = 0;
        String mesCode = "";
        if (StringUtils.isEmpty(param.getPuk())) {
            param.setK01_gysid(this.getCompanyId());
            rsNum = this.MGA6_SHFWCNSService_.doInsert(param);
            mesCode = rsNum > 0 ? "311060011" : "311060012";
        } else {
            rsNum = this.MGA6_SHFWCNSService_.doUpdate(param);
            mesCode = rsNum > 0 ? "311060013" : "311060014";
        }
        
        RESTResultBean rs =  new RESTResultBean();
        rs.setMessage(mesCode);
        JSONObject url = new JSONObject();
        url.put("url", "/3110600");
        rs.setResult(url);
        return rs;
    }
    
    /**
     * 删除
     *
     * @param pukid
     * @return
     * @Author:zhengxw
     * @Date:2014-12-5
     */
    @RequestMapping(value = "/31106003/{pukid}", method = RequestMethod.POST)
    @ResponseBody
    public RESTResultBean m31106003post(@PathVariable String pukid) {
        
    	MGA6_SHFWCNSDBO param = new MGA6_SHFWCNSDBO();
    	param.setPuk(pukid);
        this.MGA6_SHFWCNSService_.doDelete(param);
        
        RESTResultBean rs =  new RESTResultBean();
        return rs;
    }
}
